package ru.yandex.market;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class MainPage extends BasePage{
    @Step("Переходим в католог и наводим курсор на раздел {categoryName}")
    public MainPage goToCatalog(String categoryName) {
        $x("//button[.//span[contains(.,'Каталог')]]").shouldBe(enabled).click();
        $x("//li[@data-zone-name='category-link']/a[.//span[contains(.,'" + categoryName + "')]]")
                .shouldBe(visible)
                .scrollTo();
        return this;
    }

    @Step("Переходим в подраздел {subCategoryName}")
    public <T extends BasePage> T goToSubCategoryPage(String subCategoryName,  Class<T> typeNextPage) {
        $x("//ul[@data-autotest-id='subItems']//a[contains(.,'" + subCategoryName + "')]")
                .shouldBe(visible)
                .click();
        return typeNextPage.cast(page(typeNextPage));
    }
}
