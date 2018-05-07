package core.models.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class WebElement {

    private SelenideElement element;
    private Web web;

    public WebElement(SelenideElement element, Web web) {
        this.element = element;
        this.web = web;
    }

    public WebAction click() {
        element.click();
        return new WebAction();
    }

    public Web clickAnd() {
        element.click();
        return web;
    }

    public WebAction useElementByCss(String css) {
        element = $(By.cssSelector(css));
        return new WebAction();
    }

    public WebElement useElementByCssAnd(String css) {
        SelenideElement element = $(By.cssSelector(css));
        return new WebElement(element, web);
    }

    public WebAction useElementByXpath(String xpath) {
        element = $(By.xpath(xpath));
        return new WebAction();
    }

    public WebElement useElementByXpathAnd(String xpath) {
        SelenideElement element = $(By.xpath(xpath));
        return new WebElement(element, web);
    }

    public WebAction selectOptionContainingText(String text) {
        element.selectOptionContainingText(text);
        return new WebAction();
    }

    public WebAction selectOptionByValue(String text) {
        element.selectOptionByValue(text);
        return new WebAction();
    }

    public WebAction selectRadio(String text) {
        element.selectRadio(text);
        return new WebAction();
    }

    public WebElement typeTextToInputFieldCss(String css, String value) {
        SelenideElement element = $(By.cssSelector(css)).setValue(value);
        return new WebElement(element, web);
    }

    public WebElement typeTextToInputFieldXpath(String xpath, String value) {
        SelenideElement element = $(By.xpath(xpath)).setValue(value);
        return new WebElement(element, web);
    }

    public WebElement waitUntilTextAppearsCss(String css, String text, int milisecondsToWait) {
        SelenideElement element = $(By.cssSelector(css)).waitUntil(Condition.hasText(text), milisecondsToWait);
        return new WebElement(element, web);
    }

    public WebElement waitUntilTextAppearsXpath(String xpath, String text, int milisecondsToWait) {
        SelenideElement element = $(By.xpath(xpath)).waitUntil(Condition.hasText(text), milisecondsToWait);
        return new WebElement(element, web);
    }

    public WebElement waitUntilAttributeAppearsCss(String css, String attributeName, String attributeValue, int milisecondsToWait) {
        SelenideElement element = $(By.cssSelector(css)).waitUntil(Condition.hasAttribute(attributeName, attributeValue), milisecondsToWait);
        return new WebElement(element, web);
    }

    public WebElement waitUntilAttributeAppearsXpath(String xpath, String attributeName, String attributeValue, int milisecondsToWait) {
        SelenideElement element = $(By.xpath(xpath)).waitUntil(Condition.hasAttribute(attributeName, attributeValue), milisecondsToWait);
        return new WebElement(element, web);
    }

    public WebElement waitUntilElementIsPresentCss(String css, String attributeName, String attributeValue, int milisecondsToWait) {
        SelenideElement element = $(By.cssSelector(css)).waitUntil(Condition.present, milisecondsToWait);
        return new WebElement(element, web);
    }

    public WebElement waitUntilElementIsPresentXpath(String xpath, int milisecondsToWait) {
        SelenideElement element = $(By.xpath(xpath)).waitUntil(Condition.present, milisecondsToWait);
        return new WebElement(element, web);
    }

}