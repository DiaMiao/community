package de.zhao.community.interceptor;

import de.zhao.community.mapper.UserMapper;
import de.zhao.community.model.User;
import de.zhao.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service  //因为之前Autowired不工作，所以此处让spring接管这个类
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
                if (cookies != null && cookies.length != 0) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("token")) {
                            String token = cookie.getValue();
                            UserExample userExample = new UserExample();
                            userExample.createCriteria()
                                    .andTokenEqualTo(token);
                            List<User> users = userMapper.selectByExample(userExample);
                            if (users.size() != 0) {
                                request.getSession().setAttribute("user", users.get(0));
                            }
                            break;
                        }
                    }
                }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

