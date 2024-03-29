package com.lawencon.community.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
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
import com.lawencon.community.dao.BalanceDao;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.PremiumPaymentHistoryDao;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.dao.TokenDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.user.UpdatePhotoProfileReq;
import com.lawencon.community.dto.user.UserChangePasswordReq;
import com.lawencon.community.dto.user.UserData;
import com.lawencon.community.dto.user.UserFindByIdRes;
import com.lawencon.community.dto.user.UserInsertReq;
import com.lawencon.community.dto.user.UserUpdateReq;
import com.lawencon.community.exception.InvalidLoginException;
import com.lawencon.community.model.Balance;
import com.lawencon.community.model.File;
import com.lawencon.community.model.PremiumPaymentHistory;
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
	private BalanceDao balanceDao;

	@Autowired
	private FileDao fileDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private TokenDao tokenDao;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private PremiumPaymentHistoryDao premiumPaymentHistoryDao;

	@Autowired
	private RefreshTokenService tokenService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public InsertRes insert(UserInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String userCode = RandomStringUtils.randomAlphanumeric(5);
		String profileCode = RandomStringUtils.randomAlphanumeric(5);
		String balanceCode = RandomStringUtils.randomAlphanumeric(5);
		try {
			begin();
			User userSystem = userDao.findByRoleCode("SYSTEM");

			Profile profile = new Profile();
			profile.setProfileCode(profileCode);
			profile.setFullName(data.getFullName());
			profile.setCompany(data.getCompany());
			profile.setIndustry(data.getIndustry());
			profile.setPosition(data.getPosition());
			profile.setIsActive(true);

			profile.setCreatedBy(userSystem.getId());

			User user = new User();
			user.setUserCode(userCode);
			user.setEmail(data.getEmail());
			user.setPassword(passwordEncoder.encode(data.getPassword()));
			user.setIsActive(true);

			Role role = roleDao.findByRoleMember();
			user.setRole(role);
			
			

			Profile profResult = profileDao.save(profile);
			user.setProfile(profResult);
			User userRes = saveNonLogin(user, () -> {
				return userSystem.getId();
			});
			
			Balance balance = new Balance();
			balance.setBalanceCode(balanceCode);
			balance.setUser(userRes);
			balance.setCurrentBalance(new BigDecimal(0));
			balance.setCreatedBy(userSystem.getId());
			balance.setIsActive(true);
			
			balanceDao.save(balance);
			
			commit();

			InsertDataRes dataRes = new InsertDataRes();
			dataRes.setId(userRes.getId());

			result.setData(dataRes);
			result.setMessage(MessageResponse.SAVED.name());

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

	public UpdateRes update(UserUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();
		try {
			User userDb = userDao.getById(data.getId());
			Profile profile = profileDao.getById(userDb.getProfile().getId());
			profile.setFullName(data.getFullName());
			profile.setCompany(data.getCompany());
			profile.setIndustry(data.getIndustry());
			profile.setPosition(data.getPosition());

			profile.setUpdatedBy(getAuthPrincipal());

			begin();
			if (data.getFileName() != null) {
				File newFile = new File();
				newFile.setFileName(data.getFileName());
				newFile.setFileExtension(data.getFileExtension());
				newFile.setCreatedBy(getAuthPrincipal());
				newFile.setIsActive(true);
				File inserted = fileDao.save(newFile);
				profile.setFile(inserted);
			} else {
				File oldFile = fileDao.getById(profile.getFile().getId());
				profile.setFile(oldFile);
			}

			Profile updated = profileDao.save(profile);
			commit();

			UpdateDataRes dataRes = new UpdateDataRes();
			dataRes.setVersion(updated.getVersion());

			result.setData(dataRes);
			result.setMessage(MessageResponse.SAVED.name());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public UpdateRes updateProfilePic(UpdatePhotoProfileReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			User user = userDao.getById(getAuthPrincipal());
			Profile profile = profileDao.getById(user.getProfile().getId());

			File newFile = new File();
			newFile.setFileName(data.getFileName());
			newFile.setFileExtension(data.getFileExtension());
			newFile.setCreatedBy(getAuthPrincipal());
			newFile.setIsActive(true);
			begin();
			File inserted = fileDao.save(newFile);
			profile.setFile(inserted);

			Profile updated = profileDao.save(profile);
			commit();

			UpdateDataRes dataRes = new UpdateDataRes();
			dataRes.setVersion(updated.getVersion());

			result.setData(dataRes);
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
			data.setUserCode(user.getUserCode());
			data.setRoleId(user.getRole().getId());
			data.setEmail(user.getEmail());
			data.setIsActive(user.getIsActive());
			data.setVersion(user.getVersion());
			
			Role roleDb = roleDao.getById(user.getRole().getId());
			data.setRoleName(roleDb.getRoleName());

			if (user.getProfile() != null) {
				Profile profile = profileDao.getById(user.getProfile().getId());
				
				if(profile.getFile() != null) {
					data.setFileId(profile.getFile().getId());
				}
				
				data.setProfileId(profile.getId());
				data.setProfileCode(profile.getProfileCode());
				data.setFullName(profile.getFullName());
				data.setCompany(profile.getCompany());
				data.setIndustry(profile.getIndustry());
				data.setPosition(profile.getPosition());
				if(user.getProfile().getPremiumPaymentHistory() != null) {
					PremiumPaymentHistory premium = premiumPaymentHistoryDao.getById(user.getProfile().getPremiumPaymentHistory().getId());
					data.setPremiumHistory(premium.getId());	
				}	
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
		data.setUserCode(user.getUserCode());
		data.setRoleId(user.getRole().getId());
		data.setEmail(user.getEmail());
		data.setIsActive(user.getIsActive());
		data.setVersion(user.getVersion());
		
		Role roleDb = roleDao.getById(user.getRole().getId());
		data.setRoleName(roleDb.getRoleName());
		
		Profile profile = null;
		profile = profileDao.getById(user.getProfile().getId());
		if (profile != null) {
			data.setProfileId(profile.getId());
			data.setProfileCode(profile.getProfileCode());
			data.setFullName(profile.getFullName());
			data.setCompany(profile.getCompany());
			data.setIndustry(profile.getIndustry());
			data.setPosition(profile.getPosition());
			if (profile.getFile() != null) {
				data.setFileId(profile.getFile().getId());
			}
		}

		UserFindByIdRes result = new UserFindByIdRes();
		result.setData(data);
		return result;
	}

	public User findByUsername(String email) throws Exception {
		User userResult = userDao.findByEmail(email);
		return userResult;
	}
	
	public User findByRefreshToken(String email) throws Exception {
		User userResult = userDao.findByToken(email);
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

		Authentication auth = new UsernamePasswordAuthenticationToken(id, null);
		SecurityContextHolder.getContext().setAuthentication(auth);
		RefreshTokenEntity refreshToken = jwtUtil.generateRefreshToken();
		if (user.getToken() != null) {
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

	public UpdateRes changePassword(UserChangePasswordReq data) throws Exception {
		UpdateRes response = new UpdateRes();

		try {
			User user = userDao.getById(getAuthPrincipal().toString());

			if (passwordEncoder.matches(data.getOldPassword(), user.getPassword())) {
				user.setPassword(passwordEncoder.encode(data.getNewPassword()));

				begin();
				User userResult = save(user);
				commit();

				UpdateDataRes dataRes = new UpdateDataRes();
				dataRes.setVersion(userResult.getVersion());

				response.setMessage("Password updated successfully!");
				response.setData(dataRes);
			} else {
				response.setMessage("Old password didn't match!");
				response.setData(null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return response;
	}

	public UpdateRes logout() throws Exception {
		UpdateRes response = new UpdateRes();
		
		try {
			begin();
			User user = userDao.getById(getAuthPrincipal());
			String currentTokenId = user.getToken().getId();
			user.setToken(null);
			
			tokenDao.deleteById(currentTokenId);
			
			
			User userResult = userDao.save(user);
			commit();
			
			UpdateDataRes dataRes = new UpdateDataRes();
			dataRes.setVersion(userResult.getVersion());
			
			response.setMessage("Logout Success!");
			response.setData(dataRes);
			
		}catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
		return response;
	}
	
	
}
