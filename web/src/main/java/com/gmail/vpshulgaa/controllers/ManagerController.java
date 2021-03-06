package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.BusinessService;
import com.gmail.vpshulgaa.service.dto.BusinessCardDto;
import com.gmail.vpshulgaa.util.URLPrefix;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(URLPrefix.WEB_PREFIX + "/cards")
public class ManagerController {

    private final PageProperties pageProperties;
    private final BusinessService businessService;
    private final Validator businessCardValidator;

    @Autowired
    public ManagerController(PageProperties pageProperties,
                             BusinessService businessService,
                             @Qualifier("businessCardValidator") Validator businessCardValidator) {
        this.pageProperties = pageProperties;
        this.businessService = businessService;
        this.businessCardValidator = businessCardValidator;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public String getCards(ModelMap modelMap) {
        List<BusinessCardDto> businessCards = businessService.findCardsForUser();
        modelMap.addAttribute("cards", businessCards);
        return pageProperties.getCardsPagePath();
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public String addCardPage(ModelMap modelMap) {
        modelMap.addAttribute("businessCard", new BusinessCardDto());
        return pageProperties.getCreateCardPagePath();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public String createCard(@ModelAttribute BusinessCardDto businessCard,
                             BindingResult result,
                             ModelMap modelMap) {
        businessCardValidator.validate(businessCard, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("businessCard", businessCard);
            return pageProperties.getCreateCardPagePath();
        } else {
            businessService.create(businessCard);
            modelMap.addAttribute("businessCard", businessCard);
            return "redirect:" + URLPrefix.WEB_PREFIX + "/cards";
        }
    }

}
