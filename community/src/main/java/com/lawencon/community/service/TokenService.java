package com.lawencon.community.service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dto.user.LoginData;
import com.lawencon.community.dto.user.LoginRes;
import com.lawencon.community.model.User;
import com.lawencon.util.JwtUtil;
import com.lawencon.util.JwtUtil.ClaimKey;

@Service
public class TokenService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;

	public LoginRes generateNewToken(String token) throws Exception {
		LoginRes response = new LoginRes();
		LoginData data = new LoginData();

		User user = userService.findByRefreshToken(token);

		Map<String, Object> claims = new HashMap<>();
		claims.put(ClaimKey.ID.name(), user.getId());
		claims.put(ClaimKey.ROLE.name(), user.getRole().getRoleCode());
		claims.put("exp", Timestamp.valueOf(LocalDateTime.now().plusSeconds(10)));

		String newToken = jwtUtil.generateToken(claims, Duration.ofMinutes(45));

		data.setId(user.getId());
		data.setEmail(user.getEmail());
		data.setRoleCode(user.getRole().getRoleCode());
		data.setToken(newToken);
		data.setRefreshToken(token);

		response.setData(data);

		return response;
	}

}
