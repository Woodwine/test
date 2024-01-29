package helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:src/test/resources/tests.properties"
})
public interface TestsProperties extends Config {

    @Config.Key("yandex.market.url")
    String yandexMarketUrl();
}
