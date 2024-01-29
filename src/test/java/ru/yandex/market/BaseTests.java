package ru.yandex.market;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.BeforeAll;

public class BaseTests {

    @BeforeAll
    public static void setup() {
        Configuration.baseUrl = "http://localhost:8000";
    }
}
