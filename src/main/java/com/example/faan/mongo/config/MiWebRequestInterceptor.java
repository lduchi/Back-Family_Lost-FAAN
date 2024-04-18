package com.example.faan.mongo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

@Component
public class MiWebRequestInterceptor implements WebRequestInterceptor {
    @Override
    public void preHandle(WebRequest request) throws Exception {
        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        HttpServletResponse servletResponse = ((ServletWebRequest) request).getResponse();
        // Lógica antes de manejar la solicitud
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        // Lógica después de manejar la solicitud, antes de renderizar la vista
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {

    }
}
