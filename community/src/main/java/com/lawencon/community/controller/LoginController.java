package com.lawencon.community.controller;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.user.LoginData;
import com.lawencon.community.dto.user.LoginReq;
import com.lawencon.community.dto.user.LoginRes;
import com.lawencon.community.exception.InvalidLoginException;
import com.lawencon.community.model.User;
import com.lawencon.community.service.UserService;
import com.lawencon.security.ApiSecurity;
import com.lawencon.util.JwtUtil;
import com.lawencon.util.JwtUtil.ClaimKey;


@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private ApiSecurity apiSecurity;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier("premiumChecking")
	private Boolean result;

	@PostMapping()
	public ResponseEntity<LoginRes> login(@RequestBody LoginReq loginReq) throws Exception {
		LoginRes response = new LoginRes();
		LoginData data = new LoginData();
		
		try {
			apiSecurity
			.authenticationManagerBean()
			.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()))
			.isAuthenticated();
		} catch (Exception e) {
			throw new InvalidLoginException("Email/Password is wrong");
		}
		
		User user = userService.findByUsername(loginReq.getEmail());
		
		Map<String, Object> claims = new HashMap<>();
		claims.put(ClaimKey.ID.name(), user.getId());
		claims.put(ClaimKey.ROLE.name(), user.getRole().getRoleCode());
		claims.put("exp", Timestamp.valueOf(LocalDateTime.now().plusHours(6)));
		
		String token = jwtUtil.generateToken(claims, Duration.ofMinutes(45));
		
		data.setId(user.getId());
		data.setEmail(user.getEmail());
		data.setRoleCode(user.getRole().getRoleCode());
		data.setToken(token);
		data.setRefreshToken(userService.updateToken(user.getId()));
		data.setPremiumStatus(result);
		
		response.setData(data);
		
		return new ResponseEntity<LoginRes>(response, HttpStatus.OK);
			
	}
}
