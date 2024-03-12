package ru.yandex.market;

import com.codeborne.selenide.ElementsCollection;
import helpers.Assertions;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CategoryPage extends MainPage {
    private final String categoryHeader = "//h1[@data-auto='title']";

    private final String preloader = "//div[@data-grabber='SearchSerp']//div[@data-auto='preloader']";

    private final String nextPaginationButton = "//div[@data-baobab-name='next']";

    private final String resulArticles = "//article[contains(@data-autotest-id, 'product-snippet')]//h3/a/span";

    @Step("Проверяем соответствие категории {subCategoryName} на странице сайта")
    public CategoryPage checkPageTitle(String subCategoryName) {
        String pageCategory = $x(categoryHeader).shouldBe(visible).getText();
        Assertions.assertTrue(
                pageCategory.equalsIgnoreCase(subCategoryName),
                "Ожида емое название категории на странице: " + subCategoryName + ". Получили: " + pageCategory
        );
        return this;
    }

    @Step("Выбираем производителей: {brands}")
    public CategoryPage setBrands(List<String> brands) {
        $x("//div[@data-auto='filter' and .//h4[contains(.,'Производитель')]]//button")
                .shouldBe(enabled)
                .click();
        for (String brand : brands) {
            $x("//div[@data-auto='filter' and .//h4[contains(.,'Производитель')]]//label[.//span[contains(.,'" + brand + "')]]")
                    .shouldBe(visible)
                    .click();
            $x(preloader)
                    .shouldBe(visible)
                    .shouldBe(disappear);
        }
        return this;
    }

    @Step("Ждем результаты поиска")
    public CategoryPage waitForResults() {
        $$x(resulArticles).shouldHave(sizeNotEqual(0));
        return this;
    }

    private List<Boolean> checkElementsOnPage(List<String> productNames, ElementsCollection pageResult) {
        List<Boolean> checkResults = new ArrayList<>();

        for (String text : pageResult.texts()) {
            Boolean res = productNames.stream().anyMatch(x -> text.toLowerCase().contains(x.toLowerCase()));
            if (!res) {
                System.out.println(text);
                checkResults.add(res);
            }
        }
        return checkResults;
    }

    @Step("Проверяем, что на всех страницах в выборку попали только товары с названием:  {productNames}")
    public CategoryPage checkResults(List<String> productNames) {
        $x("//div[contains(@data-apiary-widget-name,'SearchPager')]")
                .shouldBe(exist)
                .scrollTo();
        List<Boolean> results = new ArrayList<>();

        ElementsCollection pageResult = $$x(resulArticles)
                .shouldHave(sizeNotEqual(0));
        results.addAll(checkElementsOnPage(productNames, pageResult));

        int counter = 0;
        while (!$$x(nextPaginationButton).isEmpty()) {
            $x(nextPaginationButton).shouldBe(enabled).click();

            counter++;
            if (counter > 100)
                Assertions.fail("Бесконечный цикл из-за кнопки Вперед");

            $x(preloader)
                    .shouldBe(visible)
                    .shouldBe(disappear);

            $x("//div[contains(@data-apiary-widget-name,'SearchPager')]")
                    .shouldBe(exist)
                    .scrollTo();

            pageResult = $$x(resulArticles).shouldHave(sizeNotEqual(0));

            results.addAll(checkElementsOnPage(productNames, pageResult));

        }

        Assertions.assertTrue(
                results.isEmpty(),
                "В результатах поиска присутствуют товары, в названии которых нет названий: " + productNames
        );
        return this;
    }






}
