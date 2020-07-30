package ru.yandexmarket;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class Steps {
    @Step("Проверка наличия имени: (name)")
    public static void checkContainsName(WebDriver driver, List<Map<String, Object>> resultSearch, String name){
        if(resultSearch.stream().anyMatch(x->x.get("NAME_PAGE").toString().contains(name))){
            Assertions.assertTrue(true);
        }else{
            CustomUtils.getScreen(driver);
            Assertions.fail("Сылка не содержит нужное имя");
        }
    }

    @Step("Проверка на наличие только айфонов на странице")
    public static void checkCollectResult(WebElement element, String text, WebDriver driver){
        if(element.getText().contains(text)){
            Assertions.assertTrue(true);
        }else{
            CustomUtils.getScreen(driver);
            Assertions.fail("На всех страницах не только айфоны");
        }
    }
}
