package gaming.news.api.controllers;

import gaming.news.api.exceptions.ResourceNotFoundException;
import gaming.news.api.models.daos.ArticlesDao;
import gaming.news.api.models.daos.CommentsDao;
import gaming.news.api.models.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class CommentsController {
    @Autowired
    CommentsDao commentsDao;
    @Autowired
    ArticlesDao articlesDao;

    @RequestMapping(value = "/articles/{articleId}/comment", method = RequestMethod.GET)
    public String comment(@PathVariable long articleId, Model model, HttpServletRequest request) {
        if (articlesDao.getArticle(articleId) == null) {
            throw new ResourceNotFoundException(String.format("Article [id=%d]", articleId));
        }
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        model.addAttribute("comment", comment);
        return "comment";
    }

    @RequestMapping(value = "/articles/{articleId}/comment", method = RequestMethod.POST)
    public String addComment(@Valid Comment comment, BindingResult result) {
        if (result.hasErrors()) {
            result.getModel().put("myErrors", result.getAllErrors());
            return "comment";
        }
        int affectsRows = commentsDao.save(comment);
        if (affectsRows <= 0) {
            //TODO : no comment was saved, log error
        }
        return "redirect:/articles/" + comment.getArticleId();
    }
}
