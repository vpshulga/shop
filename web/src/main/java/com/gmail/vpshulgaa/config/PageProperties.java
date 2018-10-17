package com.gmail.vpshulgaa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PageProperties {

    @Value("${login.page.path}")
    private String loginPagePath;
    @Value("${users.page.path}")
    private String usersPagePath;
    @Value("${errors.page.path}")
    private String errorsPagePath;
    @Value("${items.page.path}")
    private String itemsPagePath;
    @Value("${create.user.page.path}")
    private String createUserPagePath;
    @Value("${update.user.page.path}")
    private String updateUserPagePath;
    @Value("${news.page.path}")
    private String newsPagePath;
    @Value("${create.news.page.path}")
    private String createNewsPagePath;
    @Value("${update.news.page.path}")
    private String updateNewsPagePath;
    @Value("${one.news.page.path}")
    private String oneNewsPagePath;
    @Value("${one.item.page.path}")
    private String oneItemPagePath;
    @Value("${create.order.page.path}")
    private String createOrderPagePath;
    @Value("${user.profile.page.path}")
    private String userProfilePagePath;
    @Value("${ready.order.page.path}")
    private String readyOrderPagePath;
    @Value("${orders.page.path}")
    private String ordersPagePath;
    @Value("${update.order.page.path}")
    private String updateOrderPagePath;
    @Value("${logout.page.path}")
    private String logoutPagePath;
    @Value("${create.item.page.path}")
    private String createItemPagePath;
    @Value("${change.password.page.path}")
    private String changePasswordPagePath;
    @Value("${create.card.page.path}")
    private String createCardPagePath;
    @Value("${cards.page.path}")
    private String cardsPagePath;
    @Value("${registration.page.path}")
    private String registrationPagePath;
    @Value("${access.denied.page.path}")
    private String accessDeniedPagePath;
    @Value("${entity.not.found.page.path}")
    private String entityNotFoundPagePath;
    @Value("${unmarshal.error.page.path}")
    private String unmarshalErrorPagePath;
    @Value("${unsupported.operation.page.path}")
    private String unsupportedErrorPagePath;
    @Value("${api.errors.page.path}")
    private String apiErrorPagePath;

    @Value("${count.of.entities.on.page}")
    private int countOfEntitiesOnPage;


    public String getLoginPagePath() {
        return loginPagePath;
    }

    public String getUsersPagePath() {
        return usersPagePath;
    }

    public String getErrorsPagePath() {
        return errorsPagePath;
    }

    public String getItemsPagePath() {
        return itemsPagePath;
    }

    public String getCreateUserPagePath() {
        return createUserPagePath;
    }

    public String getUpdateUserPagePath() {
        return updateUserPagePath;
    }

    public String getNewsPagePath() {
        return newsPagePath;
    }

    public String getCreateNewsPagePath() {
        return createNewsPagePath;
    }

    public String getUpdateNewsPagePath() {
        return updateNewsPagePath;
    }

    public String getOneNewsPagePath() {
        return oneNewsPagePath;
    }

    public String getOneItemPagePath() {
        return oneItemPagePath;
    }

    public String getCreateOrderPagePath() {
        return createOrderPagePath;
    }

    public String getUserProfilePagePath() {
        return userProfilePagePath;
    }

    public String getReadyOrderPagePath() {
        return readyOrderPagePath;
    }

    public String getOrdersPagePath() {
        return ordersPagePath;
    }

    public String getUpdateOrderPagePath() {
        return updateOrderPagePath;
    }

    public String getLogoutPagePath() {
        return logoutPagePath;
    }

    public String getCreateItemPagePath() {
        return createItemPagePath;
    }

    public String getChangePasswordPagePath() {
        return changePasswordPagePath;
    }

    public int getCountOfEntitiesOnPage() {
        return countOfEntitiesOnPage;
    }

    public String getCreateCardPagePath() {
        return createCardPagePath;
    }

    public String getCardsPagePath() {
        return cardsPagePath;
    }

    public String getAccessDeniedPagePath() {
        return accessDeniedPagePath;
    }

    public String getRegistrationPagePath() {
        return registrationPagePath;
    }

    public String getEntityNotFoundPagePath() {
        return entityNotFoundPagePath;
    }

    public String getUnmarshalErrorPagePath() {
        return unmarshalErrorPagePath;
    }

    public String getUnsupportedErrorPagePath() {
        return unsupportedErrorPagePath;
    }

    public String getApiErrorPagePath() {
        return apiErrorPagePath;
    }
}
