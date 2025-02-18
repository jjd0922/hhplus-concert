package com.hanghe.infrastructure.intercepter;

import com.hanghe.domain.queue.service.QueueTokenService;
import com.hanghe.common.annotation.NotTokenCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class QueueIntercepter implements HandlerInterceptor {

    @Autowired
    QueueTokenService queueTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(((HandlerMethod) handler).hasMethodAnnotation(NotTokenCheck.class)) {
            return true;
        }else{
            String userId = request.getParameter("userId");
            String token = request.getParameter("token");
            if (userId == null || userId.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            if (token == null || token.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            queueTokenService.validateToken(Long.parseLong(userId),token);
            return true;
        }
    }
}
