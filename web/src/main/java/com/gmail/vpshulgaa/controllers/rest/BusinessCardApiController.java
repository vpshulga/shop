package com.gmail.vpshulgaa.controllers.rest;

import com.gmail.vpshulgaa.service.BusinessService;
import com.gmail.vpshulgaa.service.dto.BusinessCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class BusinessCardApiController {
    private final BusinessService businessService;

    @Autowired
    public BusinessCardApiController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('API_USER_PERMISSION')")
    public List<BusinessCardDto> getCards(@PathVariable("id") Long id) {
        List<BusinessCardDto> businessCards = businessService.findCardsForUser(id);
        return businessCards;
    }

    @DeleteMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('API_USER_PERMISSION')")
    public BusinessCardDto deleteCard(@PathVariable("id") Long id) {
        return businessService.delete(businessService.findOne(id));
    }
}
