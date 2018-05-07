package core.models.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class Web {

    private String baseAdminUrl;

    public Web(String baseAdminUrl) {
        this.baseAdminUrl = baseAdminUrl;
    }

    public Web open(String url) {
        Selenide.open(baseAdminUrl + url);
        return this;
    }

    public Web open() {
        Selenide.open(baseAdminUrl);
        return this;
    }

    public Web openAnyUrl(String url) {
        Selenide.open(url);
        return this;
    }

    public WebAssertion useCssElementForAssertion(String css) {
        SelenideElement element = $(By.cssSelector(css));
        return new WebAssertion(element);
    }

    public Web useCssElementForAssertionAnd(String css) {
        SelenideElement element = $(By.cssSelector(css));
        return this;
    }

    public WebAssertion useXpathElementForAssertion(String xpath) {
        SelenideElement element = $(By.xpath(xpath));
        return new WebAssertion(element);
    }

    public Web useXpathElementForAssertionAnd(String xpath) {
        SelenideElement element = $(By.xpath(xpath));
        return this;
    }

    public WebElement useElementByCss(String css) {
        SelenideElement element = $(By.cssSelector(css));
        return new WebElement(element, this);
    }

    public Web useElementByCssAnd(String css) {
        SelenideElement element = $(By.cssSelector(css));
        return this;
    }

    public WebElement useElementByXpath(String xpath) {
        SelenideElement element = $(By.xpath(xpath));
        return new WebElement(element, this);
    }

    public Web useElementByXpathAnd(String xpath) {
        SelenideElement element = $(By.xpath(xpath));
        return this;
    }

    public WebElement typeTextToInputFieldCss(String css, String value) {
        SelenideElement element = $(By.cssSelector(css)).setValue(value);
        return new WebElement(element, this);
    }

    public Web typeTextToInputFieldCssAnd(String css, String value) {
        SelenideElement element = $(By.cssSelector(css)).setValue(value);
        return this;
    }

    public WebElement typeTextToInputFieldXpath(String xpath, String value) {
        SelenideElement element = $(By.xpath(xpath)).setValue(value);
        return new WebElement(element, this);
    }

    public Web typeTextToInputFieldXpathAnd(String xpath, String value) {
        SelenideElement element = $(By.xpath(xpath)).setValue(value);
        return this;
    }

    public WebElement pressEnterUsingCss(String css) {
        SelenideElement element = $(By.cssSelector(css)).pressEnter();
        return new WebElement(element, this);
    }

    public Web pressEnterUsingCssAnd(String css) {
        SelenideElement element = $(By.cssSelector(css)).pressEnter();
        return this;
    }


    public WebElement pressEnterUsingXpath(String xpath) {
        SelenideElement element = $(By.xpath(xpath)).pressEnter();
        return new WebElement(element, this);
    }

    public Web pressEnterUsingXpathAnd(String xpath) {
        SelenideElement element = $(By.xpath(xpath)).pressEnter();
        return this;
    }

    public WebElement useCssElementAndTextForDropdownList(String css) {
        SelenideElement element = $(By.cssSelector(css));
        return new WebElement(element, this);
    }

    public Web useCssElementAndTextForDropdownListAnd(String css) {
        SelenideElement element = $(By.cssSelector(css));
        return this;
    }

    public WebAssertion useXpathElementAndTextForDropdownList(String xpath) {
        SelenideElement element = $(By.xpath(xpath));
        return new WebAssertion(element);
    }

    public Web useXpathElementAndTextForDropdownListAnd(String xpath) {
        SelenideElement element = $(By.xpath(xpath));
        return this;
    }

    public WebAssertion waitUntilTextAppearsCss(String css, String text, int milisecondsToWait) {
        SelenideElement element = $(By.cssSelector(css)).waitUntil(Condition.hasText(text), milisecondsToWait);
        return new WebAssertion(element);
    }

    public Web waitUntilTextAppearsCssAnd(String css, String text, int milisecondsToWait) {
        SelenideElement element = $(By.cssSelector(css)).waitUntil(Condition.hasText(text), milisecondsToWait);
        return this;
    }

    public WebAssertion waitUntilTextAppearsXpath(String xpath, String text, int milisecondsToWait) {
        SelenideElement element = $(By.xpath(xpath)).waitUntil(Condition.hasText(text), milisecondsToWait);
        return new WebAssertion(element);
    }

    public Web waitUntilTextAppearsXpathAnd(String xpath, String text, int milisecondsToWait) {
        SelenideElement element = $(By.xpath(xpath)).waitUntil(Condition.hasText(text), milisecondsToWait);
        return this;
    }

    public WebAssertion waitUntilAttributeAppearsCss(String css, String attributeName, String attributeValue, int milisecondsToWait) {
        SelenideElement element = $(By.cssSelector(css)).waitUntil(Condition.hasAttribute(attributeName, attributeValue), milisecondsToWait);
        return new WebAssertion(element);
    }

    public Web waitUntilAttributeAppearsCssAnd(String css, String attributeName, String attributeValue, int milisecondsToWait) {
        SelenideElement element = $(By.cssSelector(css)).waitUntil(Condition.hasAttribute(attributeName, attributeValue), milisecondsToWait);
        return this;
    }

    public WebAssertion waitUntilAttributeAppearsXpath(String xpath, String attributeName, String attributeValue, int milisecondsToWait) {
        SelenideElement element = $(By.xpath(xpath)).waitUntil(Condition.hasAttribute(attributeName, attributeValue), milisecondsToWait);
        return new WebAssertion(element);
    }

    public Web waitUntilAttributeAppearsXpathAnd(String xpath, String attributeName, String attributeValue, int milisecondsToWait) {
        SelenideElement element = $(By.xpath(xpath)).waitUntil(Condition.hasAttribute(attributeName, attributeValue), milisecondsToWait);
        return this;
    }

    public WebAssertion waitUntilElementIsPresentCss(String css, int milisecondsToWait) {
        SelenideElement element = $(By.cssSelector(css)).waitUntil(Condition.present, milisecondsToWait);
        return new WebAssertion(element);
    }

    public Web waitUntilElementIsPresentCssAnd(String css, int milisecondsToWait) {
        SelenideElement element = $(By.cssSelector(css)).waitUntil(Condition.present, milisecondsToWait);
        return this;
    }

    public WebAssertion waitUntilElementIsPresentXpath(String xpath, int milisecondsToWait) {
        SelenideElement element = $(By.xpath(xpath)).waitUntil(Condition.present, milisecondsToWait);
        return new WebAssertion(element);
    }

    public Web waitUntilElementIsPresentXpathAnd(String xpath, int milisecondsToWait) {
        SelenideElement element = $(By.xpath(xpath)).waitUntil(Condition.present, milisecondsToWait);
        return this;
    }


    public WebAssertion useElementByNameAndAttributeForAssertion(String name, String attribute, String expectedText) {
        SelenideElement element = $(By.name(name));
        String value = element.getAttribute(attribute).toString();
        Assert.assertEquals(value, expectedText);
        return new WebAssertion(element);
    }

    public WebAssertion useCssSelectorAndOpacityAttrForAssertion(String css, String expectedOpacity) {
        SelenideElement element = $(By.cssSelector(css));
        $(css).isEnabled();
        if (element.getCssValue("opacity").toString().equals(expectedOpacity)) {
            Assert.assertEquals(element.getCssValue("opacity").toString(), expectedOpacity, "Input Field should be enabled");
        } else {
            Assert.assertEquals(element.getCssValue("opacity").toString(), expectedOpacity, "Input Field should be disabled");
        }
        return new WebAssertion(element);
    }
}

