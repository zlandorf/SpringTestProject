package gaming.news.api.controllers;

import gaming.news.api.exceptions.InvalidParameterException;
import gaming.news.api.exceptions.ResourceNotFoundException;
import gaming.news.api.models.entities.Article;
import gaming.news.api.models.entities.Comment;
import gaming.news.api.models.forms.CommentForm;
import gaming.news.api.models.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class CommentController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/articles/{articleId}/comment", method = RequestMethod.GET)
    public String comment(@PathVariable long articleId, Model model, HttpServletRequest request) throws ResourceNotFoundException {
        Article article = articleService.get(articleId);
        if (article == null) {
            throw new ResourceNotFoundException(String.format("Article with id[%d]", articleId));
        }
        model.addAttribute("commentForm", new CommentForm());
        return "comment";
    }

    @RequestMapping(value = "/articles/{articleId}/comment", method = RequestMethod.POST)
    public @ResponseBody Comment addCommentDefault(@PathVariable long articleId, @Valid CommentForm form, BindingResult result, HttpServletResponse response) throws ResourceNotFoundException, InvalidParameterException {
        Article article = articleService.get(articleId);
        if (article == null) {
            throw new ResourceNotFoundException(String.format("Article with id[%d]", articleId));
        }
        if (result.hasErrors()) {
            throw new InvalidParameterException("Invalid POST parameters");
        }
        Comment comment = new Comment();
        comment.setText(form.getText());
        article.addComment(comment);
        articleService.saveArticle(article);
        return comment;
    }

    @RequestMapping(value = "/articles/{articleId}/comment", method = RequestMethod.POST, produces = {"text/html"})
    public String addComment(@PathVariable long articleId, @Valid CommentForm form, BindingResult result, HttpServletResponse response) throws ResourceNotFoundException {
        try {
            addCommentDefault(articleId, form, result, response);
            return "redirect:/articles/" + articleId;
        } catch (InvalidParameterException e) {
            return "comment";
        }
    }
}
