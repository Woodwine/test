package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class DataProvider {
    public static Stream<Arguments> providerYandexMarket() {
        return Stream.of(
                Arguments.of("Электроника", "Смартфоны", List.of("Apple"), List.of("iPhone"))
        );
    }
}
