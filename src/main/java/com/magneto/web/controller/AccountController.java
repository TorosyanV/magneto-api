package com.magneto.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.magneto.dto.RegistrationResult;
import com.magneto.service.user.UserService;
import com.magneto.web.viewmodel.RegistrationRequest;

@Controller
public class AccountController {

	@Autowired
	private UserService userService;
	@Autowired
	protected AuthenticationManager authenticationManager;

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@RequestMapping(value = "/{[path:[^\\.]*}")
	public String redirect() {
		return "home";
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showForm(Map<String, Object> model) {
		RegistrationRequest registrationForm = new RegistrationRequest();
		model.put("registrationForm", registrationForm);
		return "registration";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String checkPersonInfo(@Valid @ModelAttribute("registrationForm") RegistrationRequest registrationForm,
			BindingResult result, Map<String, Object> model) {
		String returnResult = "registration";

		if (result.hasErrors()) {
			return "registration";
		} else {
			RegistrationResult registrationResult = userService.createUserIfNotExist(registrationForm);

			switch (registrationResult) {
			case SUCCESS: {
				// After successfully Creating user
				UserDetails userDetails = userDetailsService.loadUserByUsername(registrationForm.getEmail());
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,
						registrationForm.getPassword(), userDetails.getAuthorities());
				authenticationManager.authenticate(auth);
				if (auth.isAuthenticated()) {
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
				returnResult = "redirect:/";
				// TODO
				break;
			}

			case USER_EXIST: {
				// TODO
				returnResult = "registration";
				break;
			}
			case ERROR: {
				returnResult = "registration";
				// TODO
				break;
			}
			default:
				break;
			}
		}
		return returnResult;
	}

}
