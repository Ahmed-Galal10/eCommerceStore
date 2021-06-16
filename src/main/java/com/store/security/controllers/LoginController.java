package com.store.security.controllers;

import com.store.dtos.GenericResponse;
import com.store.security.UserDetailsServiceImpl;
import com.store.security.model.AuthenticationRequest;
import com.store.security.model.AuthenticationResponse;
import com.store.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<GenericResponse<?>> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(new GenericResponse<>(null,HttpStatus.BAD_REQUEST,e.getMessage()));
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        System.out.println(userDetails.getAuthorities().stream().collect(Collectors.toList()));
        GenericResponse<?> response =
                new GenericResponse<>(new AuthenticationResponse(jwt, userDetails.getAuthorities()),
                        HttpStatus.OK,"REQUEST_SUCCEEDED");
        return ResponseEntity.ok(response);
    }
}
