package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.domain.Article;
import com.example.demo.model.service.BlogService;
import com.example.demo.model.service.AddArticleRequest;



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


    
}