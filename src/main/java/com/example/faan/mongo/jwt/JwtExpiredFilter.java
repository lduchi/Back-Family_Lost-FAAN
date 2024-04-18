package com.example.faan.mongo.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

@Component
public class JwtExpiredFilter extends WebRequestHandlerInterceptorAdapter {

    public JwtExpiredFilter(@Qualifier("MiWebRequestInterceptor") WebRequestInterceptor webRequestInterceptor){
        super(webRequestInterceptor);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            return super.preHandle(request, response, handler);
        } catch (ExpiredJwtException ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "El token JWT ha expirado");
            return false;
        }
    }
}