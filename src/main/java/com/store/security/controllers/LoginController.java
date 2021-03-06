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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<GenericResponse<?>> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponse<>(null, HttpStatus.FORBIDDEN, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN ).body(new GenericResponse<>(null, HttpStatus.FORBIDDEN, "Bad credentials"));
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        System.out.println(userDetails.getAuthorities().stream().collect(Collectors.toList()));
        GenericResponse<?> response =
                new GenericResponse<>(new AuthenticationResponse(jwt, userDetails.getAuthorities()),
                        HttpStatus.OK, "REQUEST_SUCCEEDED");
        return ResponseEntity.ok(response);
    }
}
