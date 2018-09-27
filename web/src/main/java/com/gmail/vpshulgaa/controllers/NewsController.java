package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.NewsService;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/news")
public class NewsController {
    private static final int COUNT_OF_NEWS_ON_PAGE = 10;
    private final NewsService newsService;
    private final PageProperties pageProperties;
    private final UserService userService;

    @Autowired
    public NewsController(NewsService newsService, PageProperties pageProperties, UserService userService) {
        this.newsService = newsService;
        this.pageProperties = pageProperties;
        this.userService = userService;
    }

    @GetMapping
    public String getNews(@RequestParam(value = "page", defaultValue = "1") Long page,
                          ModelMap modelMap) {
        Long pagesCount = ServiceUtils.countOfPages(newsService.countOfNews(), COUNT_OF_NEWS_ON_PAGE);
        List<Long> pages = new ArrayList<>();
        for (int i = 1; i <= pagesCount; i++) {
            pages.add((long) i);
        }
        List<NewsDto> news = newsService.findNewsByPage(page, COUNT_OF_NEWS_ON_PAGE);
        modelMap.addAttribute("pages", pages);
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
        modelMap.addAttribute("news", news);
        return pageProperties.getOneNewsPagePath();
    }

    @PostMapping
    public String createNews(@ModelAttribute NewsDto news,
                             BindingResult result,
                             ModelMap modelMap) {
        news.setUser(userService.findOne(1L));
        news.setCreated(LocalDateTime.now());
        newsService.create(news);
        modelMap.addAttribute("news", news);
        return "redirect:/news";
    }

}
