package com.aaxis.interceptor;

import com.aaxis.schedule.ScheduledTasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DemoInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(DemoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime",System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long time = System.currentTimeMillis() - Long.valueOf(request.getAttribute("startTime").toString());
        System.out.println(time);
        request.removeAttribute("startTime");
        request.setAttribute("totalTime",time);
        log.error("totalTime :"+ time);
    }
}
