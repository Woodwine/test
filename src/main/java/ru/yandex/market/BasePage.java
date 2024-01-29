package ru.yandex.market;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

public class BasePage {

    // предусловие
    @Step("Переходим на сайт {url}")
    public static <T extends BasePage> T openPage(String url, Class<T> typeNextPage) {
        return open(url, typeNextPage);
    }
}
