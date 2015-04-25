package gaming.news.api.controllers;

import gaming.news.api.exceptions.InvalidParameterException;
import gaming.news.api.exceptions.ResourceNotFoundException;
import gaming.news.api.models.daos.ArticlesDao;
import gaming.news.api.models.daos.CommentsDao;
import gaming.news.api.models.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class CommentsController {
    @Autowired
    CommentsDao commentsDao;
    @Autowired
    ArticlesDao articlesDao;

    @RequestMapping(value = "/articles/{articleId}/comment", method = RequestMethod.GET)
    public String comment(@PathVariable long articleId, Model model, HttpServletRequest request) throws ResourceNotFoundException {
        if (articlesDao.getArticle(articleId) == null) {
            throw new ResourceNotFoundException(String.format("Article [id=%d]", articleId));
        }
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        model.addAttribute("comment", comment);
        return "comment";
    }

    @RequestMapping(value = "/articles/{articleId}/comment", method = RequestMethod.POST)
    public @ResponseBody Comment addCommentDefault(@Valid Comment comment, BindingResult result, HttpServletResponse response) throws ResourceNotFoundException, InvalidParameterException {
        if (result.hasErrors()) {
            throw new InvalidParameterException("Invalid POST parameters");
        }
        int affectsRows = commentsDao.save(comment);
        if (affectsRows <= 0) {
            throw new ResourceNotFoundException(String.format("Article [id=%d]", comment.getArticleId()));
        }
        return comment;
    }

    @RequestMapping(value = "/articles/{articleId}/comment", method = RequestMethod.POST, produces = {"text/html"})
    public String addComment(@Valid Comment comment, BindingResult result, HttpServletResponse response) throws ResourceNotFoundException {
        try {
            comment = addCommentDefault(comment, result, response);
            return "redirect:/articles/" + comment.getArticleId();
        } catch (InvalidParameterException e) {
            return "comment";
        }
    }
}
