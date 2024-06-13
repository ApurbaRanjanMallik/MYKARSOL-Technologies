package com.arm.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arm.app.entity.Article;
import com.arm.app.service.ArticleService;
import com.arm.app.service.AuthorService;
import com.arm.app.service.EmailService;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public String listArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "articles/listpage";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "articles/addpage";
    }

    @PostMapping
    public String createArticle(@ModelAttribute Article article) {
        articleService.saveArticle(article);
        emailService.sendEmail("admin@example.com", "New Article Added", "A new article titled '" + article.getTitle() + "' has been added.");
        return "redirect:/articles";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Article> article = articleService.getArticleById(id);
        if (article.isPresent()) {
            model.addAttribute("article", article.get());
            model.addAttribute("authors", authorService.getAllAuthors());
            return "articles/updatepage";
        } else {
            return "error";
        }
    }

    @PostMapping("/update/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute Article article) {
        article.setId(id);
        articleService.saveArticle(article);
        return "redirect:/articles";
    }

    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "redirect:/articles";
    }
}
