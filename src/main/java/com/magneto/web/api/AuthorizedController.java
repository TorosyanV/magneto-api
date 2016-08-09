package com.magneto.web.api;

import org.springframework.security.core.context.SecurityContextHolder;

import com.magneto.dto.User;

public abstract class AuthorizedController {

	protected User getUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	}

}
