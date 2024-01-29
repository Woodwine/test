package helpers;

import io.qameta.allure.Step;

/**
 * Класс с переопределенными методами класса Assertions
 *
 * @author Семенова Дина
 */
public class Assertions {

    /**
     * Проверяет, что условие возвращает true
     *
     * @param condition проверяемое условие
     * @param message сообщение, которое возвращается если условие некорректно
     */
    @Step("Проверяем, что нет ошибки: {message}")
    public static void assertTrue(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }

    /**
     * Проверяет, что условие возвращает false
     *
     * @param condition проверяемое условие
     * @param message сообщение, которое возвращается если условие некорректно
     */
    @Step("Проверяем, что нет ошибки: {message}")
    public static void assertFalse(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertFalse(condition, message);
    }

    /**
     * Тест не пройден с указанием основной причины
     *
     * @param message сообщение, которое возвращается если условие некорректно
     */
    @Step("Тест не пройден из-за ошибки: {message}")
    public static void fail(String message) {
        org.junit.jupiter.api.Assertions.fail(message);
    }
}
