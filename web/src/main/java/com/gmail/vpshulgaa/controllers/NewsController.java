package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.CommentService;
import com.gmail.vpshulgaa.service.NewsService;
import com.gmail.vpshulgaa.service.dto.CommentDto;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import com.gmail.vpshulgaa.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/news")
public class NewsController {
    private static final int COUNT_OF_NEWS_ON_PAGE = 10;
    private final NewsService newsService;
    private final PageProperties pageProperties;
    private final CommentService commentService;

    @Autowired
    public NewsController(NewsService newsService,
                          PageProperties pageProperties,
                          CommentService commentService) {
        this.newsService = newsService;
        this.pageProperties = pageProperties;
        this.commentService = commentService;
    }

    @GetMapping
    public String getNews(@RequestParam(value = "page", defaultValue = "1") Long page,
                          ModelMap modelMap) {
        Long pagesCount = ServiceUtils.countOfPages(newsService.countOfNews(), COUNT_OF_NEWS_ON_PAGE);
        List<NewsDto> news = newsService.findNewsByPage(page, COUNT_OF_NEWS_ON_PAGE);
        modelMap.addAttribute("pages", pagesCount);
        modelMap.addAttribute("news", news);
        return pageProperties.getNewsPagePath();
    }

    @GetMapping(value = "/create")
    public String addNewsPage(ModelMap modelMap) {
        modelMap.addAttribute("news", new NewsDto());
        return pageProperties.getCreateNewsPagePath();
    }

    @GetMapping(value = "/{id}")
    public String getOneNews(@PathVariable Long id,
                             ModelMap modelMap) {
        NewsDto news = newsService.findOne(id);
        CommentDto comment = new CommentDto();
        List<CommentDto> comments = commentService.findCommentsByNewsId(id);
        modelMap.addAttribute("comments", comments);
        modelMap.addAttribute("news", news);
        modelMap.addAttribute("comment", comment);
        return pageProperties.getOneNewsPagePath();
    }

    @PostMapping
    public String createNews(@ModelAttribute NewsDto news,
                             BindingResult result,
                             ModelMap modelMap) {
        newsService.create(news, WebUtils.getPrincipal().getId());
        modelMap.addAttribute("news", news);
        return "redirect:/web/news";
    }

    @PostMapping(value = "/{news_id}")
    public String createComment(@ModelAttribute CommentDto comment,
                                ModelMap modelMap, @PathVariable("news_id") Long news_id) {
        NewsDto news = newsService.findOne(news_id);
        commentService.create(comment, news_id, WebUtils.getPrincipal().getId());
        List<CommentDto> comments = commentService.findCommentsByNewsId(news_id);
        modelMap.addAttribute("comment", comment);
        modelMap.addAttribute("comments", comments);
        modelMap.addAttribute("news", news);
        return pageProperties.getOneNewsPagePath();
    }

    @GetMapping(value = "/{id}/update")
    public String updateNewsPage(@PathVariable Long id,
                                 ModelMap modelMap) {
        NewsDto news = newsService.findOne(id);
        modelMap.addAttribute("news", news);
        return pageProperties.getUpdateNewsPagePath();
    }

    @PostMapping(value = "/{id}/update")
    public String updateNews(@ModelAttribute NewsDto news,
                             BindingResult result,
                             ModelMap modelMap,
                             @PathVariable("id") Long id) {
        news.setId(id);
        Long userId = news.getUserId();
        newsService.update(news, userId);
        modelMap.addAttribute("news", news);
        return "redirect:/web/news";
    }

    @PostMapping("/delete")
    public String deleteNews(@RequestParam("ids") Long[] ids) {
        for (Long id : ids) {
            newsService.deleteById(id);
        }
        return "redirect:/news";
    }

}
