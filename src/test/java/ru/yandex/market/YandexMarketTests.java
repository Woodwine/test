package ru.yandex.market;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static helpers.Properties.testsProperties;

public class YandexMarketTests extends BaseTests {
    @Feature("Проверка сайта Yandex Market")
    @DisplayName("Проверка сайта Yandex Market")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerYandexMarket")
    public void testsYandexMarket(
            String categoryName, String subCategoryName, List<String> brands, List<String> productNames
    ) {
        MainPage.openPage(testsProperties.yandexMarketUrl(), MainPage.class)
                .goToCatalog(categoryName)
                .goToSubCategoryPage(subCategoryName, CategoryPage.class)
                .checkPageTitle(subCategoryName)
                .setBrands(brands)
                .waitForResults()
                .checkResults(productNames);
    }
}
