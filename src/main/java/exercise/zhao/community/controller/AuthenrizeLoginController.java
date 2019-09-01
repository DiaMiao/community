package exercise.zhao.community.controller;

import exercise.zhao.community.dto.AccessTokenDTO;
import exercise.zhao.community.dto.GithubUser;
import exercise.zhao.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);

        if (user != null){
            //登录成功，写cookie和session
            request.getSession().setAttribute("user", user); //相当于创建了一个用户
            return "redirect:/"; //重新定向，跳转到index页面。（原本成功后返回的地址为localhost:8080/callback?code=....)
        }else{
            //登录失败，重新登录
            System.out.println("login failed.");
            return "redirect:/";
        }
    }
}
