package com.store.security.excptionHandlers;

import com.google.gson.Gson;
import com.store.dtos.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Autowired
    private Gson gson;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json");
        GenericResponse genericResponse = new GenericResponse(null,HttpStatus.UNAUTHORIZED,"Unauthorized request");
        response.getWriter().println(gson.toJson(genericResponse));
    }
}
