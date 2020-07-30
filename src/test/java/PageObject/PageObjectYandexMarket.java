package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandexmarket.Steps;

import java.util.ArrayList;
import java.util.List;

public class PageObjectYandexMarket {
    private WebDriver chromeDriver;
    public List<String> listOfWebElement = new ArrayList<>();

    private String selectorCheckIphone = ".//span[text()='Apple']";
    private String selectorNameIphone = "//span[@data-tid = 'ce80a508']";
    private String selectorCatalog = "//span[text()=\"Каталог товаров\"]//ancestor::button";
    private String selectorCheckFirstCatalog = "//*[@class = '_35SYuInI1T _3G-TuDjlyZ _2njYUPL9xd']";
    private String selectorCheckSecondCatalog = "//*[@class = '_35SYuInI1T _2njYUPL9xd']";
    private String selectorButtonElectronics = "//*[@id=\"39889743-tab\"]";
    private String selectorHelpButton = "//div[@class=\"_13-JCDb2SZ _1No7Ewj2pS\"]";
    private String selectorChoicePhone = "//a[@href='/catalog--mobilnye-telefony/54726/list?hid=91491']";
    private String selectorNextPage = "//a[@data-tid = 'b9b3cc25']";

    private WebElement checkIphone;
    private WebElement catalog;
    private WebElement choiceCatalog;
    private WebElement choicePhone;

    public PageObjectYandexMarket(WebDriver chromedriver){
        this.chromeDriver = chromedriver;
        catalog = chromedriver.findElement(By.xpath(selectorCatalog));
    }

    public boolean choiceMobilePhone(){
        while(true){
            if(selectorCheckSecondCatalog != selectorCheckFirstCatalog){
                catalog.click();
            }
            return true;
        }
    }
    public void choiceCatalog(){
        choiceCatalog = chromeDriver.findElement(By.xpath(selectorButtonElectronics));
        choiceCatalog.click();
    }

    public void choicePhone(){
        choicePhone = chromeDriver.findElement(By.xpath(selectorChoicePhone));
        choicePhone.click();
    }

    public void choiceFilterPhone(){
        checkIphone = chromeDriver.findElement(By.xpath(selectorCheckIphone));
        checkIphone.click();
        new WebDriverWait(chromeDriver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(selectorHelpButton)));
    }


    public void collectResult(String text){
        List<WebElement> listOfWebElement = chromeDriver.findElements(By.xpath(selectorNextPage));
        for(int i = 0; i < listOfWebElement.size(); i++){
            listOfWebElement.stream().forEach(x->x.isDisplayed());
            new WebDriverWait(chromeDriver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(selectorHelpButton)));
            chromeDriver.findElements(By.xpath(selectorNameIphone)).forEach(x -> Steps.checkCollectResult(x, text, chromeDriver));
        }
    }
}
