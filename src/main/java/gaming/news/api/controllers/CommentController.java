package gaming.news.api.controllers;

import gaming.news.api.exceptions.InvalidParameterException;
import gaming.news.api.exceptions.ResourceNotFoundException;
import gaming.news.api.models.entities.Article;
import gaming.news.api.models.entities.Comment;
import gaming.news.api.models.services.ArticleService;
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

@Controller
public class CommentController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/articles/{articleId}/comments", method = RequestMethod.POST)
    @ResponseBody
    public Comment addComment(@PathVariable long articleId, @Valid Comment comment, BindingResult result, HttpServletResponse response) throws ResourceNotFoundException, InvalidParameterException {
        Article article = articleService.get(articleId);
        if (article == null) {
            throw new ResourceNotFoundException(String.format("Article with id[%d]", articleId));
        }
        if (result.hasErrors()) {
            throw new InvalidParameterException("Invalid POST parameters");
        }
        article.addComment(comment);
        articleService.saveArticle(article);
        response.setStatus(HttpStatus.CREATED.value());
        return comment;
    }

    @RequestMapping(value = "/articles/{articleId}/comments", method = RequestMethod.GET, produces = {"text/html"})
    public String commentHtml(@PathVariable long articleId, Model model, HttpServletRequest request) throws ResourceNotFoundException {
        Article article = articleService.get(articleId);
        if (article == null) {
            throw new ResourceNotFoundException(String.format("Article with id[%d]", articleId));
        }
        model.addAttribute("comment", new Comment());
        return "comment";
    }

    @RequestMapping(value = "/articles/{articleId}/comments", method = RequestMethod.POST, produces = {"text/html"})
    public String addCommentHtml(@PathVariable long articleId, @Valid Comment comment, BindingResult result, HttpServletResponse response) throws ResourceNotFoundException {
        try {
            addComment(articleId, comment, result, response);
            return "redirect:/articles/" + articleId;
        } catch (InvalidParameterException e) {
            return "comment";
        }
    }
}
