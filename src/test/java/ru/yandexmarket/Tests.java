package ru.yandexmarket;

import PageObject.PageObjectGoogleWithSearch;
import PageObject.PageObjectYandexMarket;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class Tests extends WebDriverSettings {
    @Test
    @Description(value = "Тест ЯндексМаркета")
    public void testYandexMarket() {
        PageObjectGoogleWithSearch googlePage = new PageObjectGoogleWithSearch(chromeDriver, "яндекс маркет");
        List<Map<String, Object>> resultSearch = googlePage.getCollectResult();
        Steps.checkContainsName(chromeDriver, resultSearch, "Яндекс.Маркет");
        googlePage.goFindPage("Яндекс.Маркет");
        PageObjectYandexMarket yandexMarketPage = new PageObjectYandexMarket(chromeDriver);
        yandexMarketPage.choiceMobilePhone();
        yandexMarketPage.choiceCatalog();
        yandexMarketPage.choicePhone();
        yandexMarketPage.choiceFilterPhone();;
        yandexMarketPage.collectResult("GB");
    }

}
