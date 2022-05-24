package com.yeqifu.interceptor;

import com.yeqifu.bus.entity.RequestMethod;
import com.yeqifu.bus.service.IRequestMethodService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import java.net.InetAddress;


public class ControllerInterceptor implements HandlerInterceptor {
    @Resource
    private IRequestMethodService requestMethodService;
    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        System.out.println("ControllerInterceptor preHandle");
        RequestMethod requestMethod = new RequestMethod();
        requestMethod.setMethod(request.getMethod());
        requestMethod.setUri(request.getRequestURI());
        requestMethod.setIpAddress(request.getRemoteAddr());
        InetAddress ia = InetAddress.getLocalHost();
        requestMethod.setMac(ia.toString());
        requestMethodService.save(requestMethod);
        return true;
    }

}