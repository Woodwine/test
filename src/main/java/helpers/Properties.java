package helpers;

import org.aeonbits.owner.ConfigFactory;

/**
 * Класс для получения сохраненных переменных окружения для тестов
 *
 * @author Семенова Дина
 */
public class Properties {
    public static TestsProperties testsProperties = ConfigFactory.create(TestsProperties.class);
}
