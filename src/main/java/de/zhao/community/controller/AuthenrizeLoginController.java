package de.zhao.community.controller;

import de.zhao.community.dto.AccessTokenDTO;
import de.zhao.community.dto.GithubUser;
import de.zhao.community.mapper.UserMapper;
import de.zhao.community.model.User;
import de.zhao.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthenrizeLoginController {

    @Autowired //自动将spring容器中的实例加载到上下文
    private GithubProvider githubProvider;

    @Value("${github.client.id}") //此注解加载properties文件中相关的配置参数
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if (githubUser != null && githubUser.getId() != 0) {
            //登录成功，把用户写入数据库
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);//自动生成随机uuid作为token
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());//系统毫秒数
            user.setGmtModified(user.getGmtCreate());//此处创建和修改时间相同。
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userMapper.insert(user);//用户数据写入数据库

            //把token写入cookie中
            response.addCookie(new Cookie("token", token));
//            登录成功，请求session
//            request.getSession().setAttribute("user", githubUser); //相当于创建了一个用户
            return "redirect:/"; //重新定向，跳转到index页面。（原本成功后返回的地址为localhost:8080/callback?code=....)
        } else {
            //登录失败，重新登录
            System.out.println("login failed.");
            return "redirect:/";
        }
    }
}
