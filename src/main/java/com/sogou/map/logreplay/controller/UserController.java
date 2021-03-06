package com.sogou.map.logreplay.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sogou.map.logreplay.bean.User;
import com.sogou.map.logreplay.controller.base.BaseController;
import com.sogou.map.logreplay.exception.LogReplayException;
import com.sogou.map.logreplay.service.UserService;
import com.sogou.map.logreplay.util.AuthUtil;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/detail")
	public ModelMap detail() {
		User user = userService.getUserByUsername(AuthUtil.getUsername());
		return successResult(user);
	}
	
	@ResponseBody
	@RequestMapping(value = "/profile/update", method = RequestMethod.POST)
	public ModelMap updateProfile(@RequestParam String screenName) { 
		if(StringUtils.isBlank(screenName)) {
			throw LogReplayException.invalidParameterException("ScreenName should not be null!");
		}
		String username = AuthUtil.getUsername();
		try {
			User user = userService.getUserByUsername(username);
			user.setScreenName(screenName);
			userService.updateUser(user);
			return successResult("User[%s] is successfully updated", username);
		} catch (Exception e) {
			e.printStackTrace();
			throw LogReplayException.operationFailedException("Failed to update User[%s]", username);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/password/update", method = RequestMethod.POST)
	public ModelMap updatePassword(
			@RequestParam String oldPassword,
			@RequestParam String newPassword) {
		if(StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
			throw LogReplayException.invalidParameterException("Neither oldPassword nor newPassword should be null!");
		}
		if((newPassword = newPassword.trim()).length() < User.PASSWORD_MIN_LENGTH) {
			throw LogReplayException.invalidParameterException("New password is too short!");
		}
		String username = AuthUtil.getUsername();
		User user = userService.getUserByUsername(username);
		if(!AuthUtil.hashPassword(username, oldPassword).equals(user.getPassword())) {
			throw LogReplayException.invalidParameterException("Parameter oldPassword is wrong!");
		}
		try {
			user.setPassword(AuthUtil.hashPassword(username, newPassword));
			userService.updateUser(user);
			return successResult("The password of User[%s] is successfully updated", username);
		} catch (Exception e) {
			e.printStackTrace();
			throw LogReplayException.operationFailedException("Failed to update the password of User[%s]", username);
		}
	}
	
	@ResponseBody
	@RequestMapping("/checkPassword")
	public boolean checkPassword(String password) {
		if(StringUtils.isBlank(password)) {
			return false;
		}
		User user = userService.getUserByUsername(AuthUtil.getUsername());
		if(!AuthUtil.hashPassword(user.getUsername(), password).equals(user.getPassword())) { 
			return false;
		}
		return true;
	}
}
