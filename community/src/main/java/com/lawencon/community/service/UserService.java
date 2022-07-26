package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.base.ConnHandler;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.user.UserData;
import com.lawencon.community.dto.user.UserFindByIdRes;
import com.lawencon.community.dto.user.UserInsertReq;
import com.lawencon.community.exception.InvalidLoginException;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;
import com.lawencon.security.RefreshTokenEntity;
import com.lawencon.security.RefreshTokenService;
import com.lawencon.util.JwtUtil;

@Service
public class UserService extends BaseCoreService<User> implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProfileDao profileDao;

	@Autowired
	private FileDao fileDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private RefreshTokenService tokenService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public InsertRes insert(UserInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			begin();
			User user = new User();
			user.setEmail(data.getEmail());
			user.setPassword(passwordEncoder.encode(data.getPassword()));
			
			Role role = roleDao.getById(data.getRoleId());
			user.setRole(role);
			user.setIsActive(true);
			
			User insertUser = saveNonLogin(user, () -> {
				return user.getId();
			});

			Profile profile = new Profile();
			profile.setUser(insertUser);
			profile.setFullName(data.getFullName());
			profile.setCompany(data.getCompany());
			profile.setIndustry(data.getIndustry());
			profile.setPosition(data.getPosition());
			profile.setCreatedBy(insertUser.getCreatedBy());
			profile.setIsActive(true);

			File file = new File();
			file.setFileName(data.getFileName());
			file.setFileExtension(data.getFileExtension());
			file.setCreatedBy(insertUser.getCreatedBy());
			file.setIsActive(true);
			File insertedFile = fileDao.save(file);

			profile.setFile(insertedFile);
			profileDao.save(profile);
			userDao.save(user);
			commit();

			InsertDataRes insertRes = new InsertDataRes();
			insertRes.setId(insertUser.getId());

			result.setData(insertRes);
			result.setMessage(MessageResponse.SAVED.name());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}
	


	public SearchQuery<UserData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<User> dataDb = userDao.findAll(query, startPage, maxPage);

		List<UserData> users = new ArrayList<>();
		dataDb.getData().forEach(user -> {
			
			UserData data = new UserData();
			data.setId(user.getId());
			data.setRoleId(user.getRole().getId());
			data.setEmail(user.getEmail());
			data.setIsActive(user.getIsActive());
			data.setVersion(user.getVersion());
			
			Profile profile = null;
			
			try {
				profile = profileDao.findByUserId(user.getId());
				if(profile != null) {
					data.setProfileId(profile.getId());
					data.setFullName(profile.getFullName());
					data.setCompany(profile.getCompany());
					data.setIndustry(profile.getIndustry());
					data.setPosition(profile.getPosition());
					data.setFileId(profile.getFile().getId());
//					data.setStatus(profile.getStatus());
//					data.setStatusDuration(profile.getStatusDuration());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			users.add(data);

		});

		SearchQuery<UserData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(users);

		return result;
	}

	public UserFindByIdRes getById(String id) throws Exception {
		User user = userDao.getById(id);
		UserData data = new UserData();
		data.setId(user.getId());
		data.setEmail(user.getEmail());
		Profile profile = null;
		try {
			profile = profileDao.findByUserId(user.getId());
			data.setProfileId(profile.getId());
			data.setFullName(profile.getFullName());
			data.setCompany(profile.getCompany());
			data.setIndustry(profile.getIndustry());
			data.setPosition(profile.getPosition());
			data.setFileId(profile.getFile().getId());
//			data.setStatus(profile.getStatus());
//			data.setStatusDuration(profile.getStatusDuration());
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserFindByIdRes result = new UserFindByIdRes();
		return result;
	}

	public User findByUsername(String email) throws Exception {
		User userResult = userDao.findByEmail(email);
		return userResult;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userDb = null;
		try {
			userDb = userDao.findByEmail(email);
			if (userDb == null) {
				throw new InvalidLoginException("User Invalid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Authentication auth = new UsernamePasswordAuthenticationToken(userDb.getId(), null);
        SecurityContextHolder.getContext().setAuthentication(auth);

		return new org.springframework.security.core.userdetails.User(email, userDb.getPassword(), new ArrayList<>());
	}
	
	public String updateToken(String id) throws Exception {
        User user = userDao.getById(id);

        RefreshTokenEntity refreshToken = jwtUtil.generateRefreshToken();
        if(user.getToken() != null) {                        
            RefreshTokenEntity token = ConnHandler.getManager().find(RefreshTokenEntity.class, user.getToken().getId());
            token.setToken(refreshToken.getToken());
            token.setExpiredDate(refreshToken.getExpiredDate());
            begin();
            tokenService.saveToken(token);
        } else {
            begin();
            RefreshTokenEntity tokenNew = tokenService.saveToken(refreshToken);
            user.setToken(tokenNew);
        }
        
        User res = save(user);
        String token = res.getToken().getToken();
        commit();
        return token;
    }

}
