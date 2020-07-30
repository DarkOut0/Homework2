package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageObjectGoogleWithSearch {

    private String selectorSearchItem = "//div[@class='g']";
    private String selectorURL = ".//div[@class='r']/a[@href]";
    private String selectorNamePage = ".//div[@class='r']/a[@href]/h3";
    private String selectorDiscription = ".//div[@class='s']";

    private List<WebElement> webSearchItem = new ArrayList<>();
    private List<Map<String, Object>> collectResult = new ArrayList<>();
    private WebDriver chromeDriver;

    public PageObjectGoogleWithSearch(WebDriver chromeDriver, String search){
        this.chromeDriver = chromeDriver;
        this.chromeDriver.get("https://www.google.com/search?q=" + search);
        webSearchItem = chromeDriver.findElements(By.xpath(selectorSearchItem));
    }

    public PageObjectGoogleWithSearch(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        webSearchItem = chromeDriver.findElements(By.xpath(selectorSearchItem));
    }

    public List<Map<String, Object>> getCollectResult() {
        for(WebElement result : webSearchItem){
            collectResult.add(Map.of(
                    "WEB_ELEMENT", result,
                    "URL", result.findElement(By.xpath(selectorURL)).getAttribute("href"),
                    "NAME_PAGE", result.findElement(By.xpath(selectorNamePage)).getText(),
                    "DISCRIPTION", result.findElement(By.xpath(selectorDiscription)).getText()
            ));
        }
        return collectResult;
    }

    public boolean goFindPage(String namePage){
        WebElement pageLink =(WebElement)collectResult.stream()
                .filter(x->x.get("NAME_PAGE").toString().contains(namePage))
                .findFirst()
                .get()
                .get("WEB_ELEMENT");
        pageLink.findElement(By.xpath(selectorNamePage)).click();
        List<String> tabs = new ArrayList<>(chromeDriver.getWindowHandles());
        for(String tab : tabs){
            chromeDriver.switchTo().window(tab);
            if(chromeDriver.getTitle().contains(namePage))
                return true;
        }
        return false;
    }

}
