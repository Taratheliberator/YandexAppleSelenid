package org.example.pageobjects;



import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import java.util.List;

public class YandexPage {

    private SelenideElement marketButton = $x("//span[contains(text(),'Каталог')]");
    private SelenideElement electronicsMenu = $x("(//span[contains(text(),'Электроника')])[2]");
    private SelenideElement smartphonesButton = $(By.linkText("Смартфоны"));
    private SelenideElement allFilters = $x("//span[contains(.,'Все фильтры')]");
    private SelenideElement resultsButton = $x("//a[contains(text(),'Показать')]");
    private SelenideElement showAs = $x("//button[contains(.,'Показывать по')]");
    private SelenideElement searchField = $(By.name("text"));

    private ElementsCollection noteList = $$x("//*[@data-autotest-id='offer-snippet' or @data-autotest-id='product-snippet']");

    public YandexPage goToMarket() {
        marketButton.click();
        return this;
    }

    public YandexPage goToElectronics() {
        electronicsMenu.hover();
        return this;
    }

    public YandexPage goSmartphones() {
        smartphonesButton.shouldBe(visible).click();
        return this;
    }

    public YandexPage openFilter() {
        allFilters.click();
        return this;
    }

    public YandexPage showResults() {
        resultsButton.click();
        return this;
    }

    public YandexPage setVendorName(String name) {
        $x(String.format("//label[contains(.,'%s')]", name)).click();
        return this;
    }

    public boolean isTargetPresent(String name) {
        return $x(String.format("//span[contains(text(),'%s')]", name)).shouldBe(visible).isDisplayed();
    }

    public ElementsCollection getList() {
        return noteList;
    }


    public void loadAllSmartphones() {
        while (true) {
            ElementsCollection buttons = $$("button[data-auto='pager-more']").filter(Condition.visible);
            if (buttons.isEmpty()) {
                break;
            }
            buttons.first().click();
            // Add appropriate wait or sleep here if necessary to allow for loading
        }
    }






    public static boolean isSmartphoneValid(String element) {
        String lowerCaseElement = element.toLowerCase();
        return lowerCaseElement.contains("iphone");
    }
}

