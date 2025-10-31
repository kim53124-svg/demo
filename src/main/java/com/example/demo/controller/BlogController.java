package com.example.demo.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.domain.Article;
import com.example.demo.model.service.BlogService;
import com.example.demo.model.service.AddArticleRequest;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;




@Controller 
public class BlogController {
@Autowired
BlogService blogService;


// @GetMapping("/article_list")
// public String article_list() {
//     return "article_list";
// }
    
@GetMapping("/article_list")
public String article_list(Model model) {
    List<Article> list = blogService.findAll();
    model.addAttribute("articles", list);
    return "article_list";
}

@PostMapping("/api/articles")
public String addArticle(AddArticleRequest request) {
    blogService.save(request);
    return "redirect:/article_list";
}

@GetMapping("/articl_edit/{id}")
public String aritcle_edit(Model model, @PathVariable Long id) {
    Optional<Article> list = (Optional<Article>) blogService.findById(id);

    if (list.isPresent()) {
        model.addAttribute("article", list.get());
    } else {
        return "error";
    }
    return "artticle_edit";
}

@PutMapping("/api/article_edit/{id}")
public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
    blogService.update(id, request);
    return "redirect:/article_list";
}
    
}