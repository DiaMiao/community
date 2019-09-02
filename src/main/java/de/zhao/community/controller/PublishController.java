package de.zhao.community.controller;

import de.zhao.community.mapper.QuestionMapper;
import de.zhao.community.mapper.UserMapper;
import de.zhao.community.model.Question;
import de.zhao.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model   //model可以获取页面上的内容
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if ( title == null || title == ""){
            model.addAttribute("error","Please fill out title");
            return "public";
        }


        if ( description == null || description == ""){
            model.addAttribute("error","Please fill out description");
            return "public";
        }


        if ( tag == null || tag == ""){
            model.addAttribute("error","Please fill out tag");
            return "public";
        }
        User user = null;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ( cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        if (user == null) {
            model.addAttribute("error", "Pleas login first.");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
        /*此处为非前后端分离项目，提交后返回成功，失败后则返回原页面，提示问题。
        //前后端分离可以做到局部刷新等..
         */
    }
}
