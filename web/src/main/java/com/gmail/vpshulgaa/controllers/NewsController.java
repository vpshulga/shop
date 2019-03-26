package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.CommentService;
import com.gmail.vpshulgaa.service.NewsService;
import com.gmail.vpshulgaa.service.dto.CommentDto;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import com.gmail.vpshulgaa.service.util.PaginationUtils;
import com.gmail.vpshulgaa.util.URLPrefix;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(URLPrefix.WEB_PREFIX + "/news")
public class NewsController {

    private final NewsService newsService;
    private final PageProperties pageProperties;
    private final CommentService commentService;
    private final Validator newsValidator;
    private final Validator commentValidator;

    @Autowired
    public NewsController(
            NewsService newsService,
            PageProperties pageProperties,
            CommentService commentService,
            @Qualifier("commentValidator") Validator commentValidator,
            @Qualifier("newsValidator") Validator newsValidator) {
        this.newsService = newsService;
        this.pageProperties = pageProperties;
        this.commentService = commentService;
        this.commentValidator = commentValidator;
        this.newsValidator = newsValidator;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SHOW_NEWS')")
    public String getNews(
            @RequestParam(value = "page", defaultValue = "1") Long page,
            ModelMap modelMap) {
        Long pagesCount = PaginationUtils.countOfPages(newsService.countOfNews(), pageProperties.getCountOfEntitiesOnPage());
        modelMap.addAttribute("pages", pagesCount);
        List<NewsDto> news = newsService.findNewsByPage(page, pageProperties.getCountOfEntitiesOnPage());
        modelMap.addAttribute("news", news);
        return pageProperties.getNewsPagePath();
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('CREATE_NEWS')")
    public String addNewsPage(ModelMap modelMap) {
        modelMap.addAttribute("news", new NewsDto());
        return pageProperties.getCreateNewsPagePath();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('SHOW_NEWS')")
    public String getOneNews(
            @PathVariable Long id,
            ModelMap modelMap,
            @RequestParam(value = "page", defaultValue = "1") Long page) {
        NewsDto news = newsService.findOne(id);
        modelMap.addAttribute("news", news);
        CommentDto comment = new CommentDto();
        modelMap.addAttribute("comment", comment);
        Long pagesCount = PaginationUtils.countOfPages(commentService.countOfCommentsByNewsId(id),
                pageProperties.getCountOfEntitiesOnPage());
        modelMap.addAttribute("pages", pagesCount);
        List<CommentDto> comments = commentService.findCommentsByPageForNews(id, page,
                pageProperties.getCountOfEntitiesOnPage());
        modelMap.addAttribute("comments", comments);

        return pageProperties.getOneNewsPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_NEWS')")
    public String createNews(@ModelAttribute NewsDto news,
                             BindingResult result,
                             ModelMap modelMap) {
        newsValidator.validate(news, result);
        {
            if (result.hasErrors()) {
                modelMap.addAttribute("news", news);
                return pageProperties.getCreateNewsPagePath();
            } else {
                newsService.create(news);
                return "redirect:" + URLPrefix.WEB_PREFIX + "/news";
            }
        }
    }

    @PostMapping(value = "/{news_id}")
    @PreAuthorize("hasAuthority('CREATE_COMMENT')")
    public String createComment(
            @ModelAttribute CommentDto comment,
            ModelMap modelMap,
            BindingResult result,
            @PathVariable("news_id") Long news_id) {
        commentValidator.validate(comment, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("comment", comment);
            return "redirect:" + URLPrefix.WEB_PREFIX + "/news/" + news_id;
        } else {
            commentService.create(comment, news_id);
            return "redirect:" + URLPrefix.WEB_PREFIX + "/news/" + news_id;
        }
    }

    @GetMapping(value = "/{id}/update")
    @PreAuthorize("hasAuthority('UPDATE_NEWS')")
    public String updateNewsPage(@PathVariable Long id,
                                 ModelMap modelMap) {
        NewsDto news = newsService.findOne(id);
        modelMap.addAttribute("news", news);
        return pageProperties.getUpdateNewsPagePath();
    }

    @PostMapping(value = "/{id}/update")
    @PreAuthorize("hasAuthority('UPDATE_NEWS')")
    public String updateNews(
            @ModelAttribute NewsDto news,
            BindingResult result,
            ModelMap modelMap,
            @PathVariable("id") Long id) {
        news.setId(id);
        newsValidator.validate(news, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("news", news);
            return pageProperties.getUpdateNewsPagePath();
        } else {
            Long userId = news.getUserId();
            newsService.update(news, userId);
            modelMap.addAttribute("news", news);
            return "redirect:" + URLPrefix.WEB_PREFIX + "/news";
        }
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE_NEWS')")
    public String deleteNews(@RequestParam(value = "ids", required = false) Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                newsService.deleteById(id);
            }
        }
        return "redirect:" + URLPrefix.WEB_PREFIX + "/news";
    }

    @PostMapping("/comment/delete")
    @PreAuthorize("hasAuthority('DELETE_COMMENTS')")
    public String deleteComment(@RequestParam(value = "commentId") Long commentId,
                                @RequestParam(value = "newsId") Long newsId) {
        commentService.deleteById(commentId);
        return "redirect:" + URLPrefix.WEB_PREFIX + "/news/" + newsId;
    }

}
