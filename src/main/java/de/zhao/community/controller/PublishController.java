package de.zhao.community.controller;

import de.zhao.community.dto.QuestionDTO;
import de.zhao.community.model.Question;
import de.zhao.community.model.User;
import de.zhao.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable (name = "id") Long id,
                       Model model){

        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());

        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "tag") String tag,
            @RequestParam(value = "id",required = false) Long id,
            HttpServletRequest request,
            Model model   //model可以获取页面上的内容
    ) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || title == "") {
            model.addAttribute("error", "Please fill out title");
            return "public";
        }


        if (description == null || description == "") {
            model.addAttribute("error", "Please fill out description");
            return "public";
        }


        if (tag == null || tag == "") {
            model.addAttribute("error", "Please fill out tag");
            return "public";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "Pleas login first.");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);

        questionService.createOrUpdate(question);
        return "redirect:/";
        /*此处为非前后端分离项目，提交后返回成功，失败后则返回原页面，提示问题。
        //前后端分离可以做到局部刷新等..
         */
    }
}
