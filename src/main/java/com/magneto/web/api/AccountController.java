package com.magneto.web.api;

import com.magneto.service.user.UserAlreadyExistsException;
import com.magneto.service.user.UserService;
import com.magneto.web.model.RegistrationRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
public class AccountController {

    private static final Logger logger = Logger.getLogger(AccountController.class.getName());

    @Autowired
    private UserService userService;
    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showForm(Map<String, Object> model) {
        RegistrationRequest registrationForm = new RegistrationRequest();
        model.put("registrationForm", registrationForm);
        return "registration";
    }

    @PostMapping(value = "/register")
    public ResponseEntity registerNewUser(@Validated @RequestBody RegistrationRequest registrationForm,
                                          Map<String, Object> model) throws UserAlreadyExistsException {


        int userId = userService.createUserIfNotExist(registrationForm);

        return new ResponseEntity(HttpStatus.CREATED);
//        // After successfully Creating user
//        UserDetails userDetails = userDetailsService.loadUserByUsername(registrationForm.getEmail());
//        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,
//                registrationForm.getPassword(), userDetails.getAuthorities());
//        authenticationManager.authenticate(auth);
//        if (auth.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }

    }

}
