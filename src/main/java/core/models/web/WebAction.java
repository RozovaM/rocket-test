package core.models.web;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class WebAction {

    public WebAssertion useCssElementForAssertion(String css) {
        SelenideElement element = $(By.cssSelector(css));
        return new WebAssertion(element);
    }

    public WebAction useCssElementForAssertionAnd(String css) {
        SelenideElement element = $(By.cssSelector(css));
        return this;
    }

    public WebAssertion useXpathElementForAssertion(String xpath) {
        SelenideElement element = $(By.xpath(xpath));
        return new WebAssertion(element);
    }

    public WebAction useXpathElementForAssertionAnd(String xpath) {
        SelenideElement element = $(By.xpath(xpath));
        return this;
    }

    public WebAssertion useCssElementForSelectDropdown(String css) {
        SelenideElement element = $(By.cssSelector(css));
        return new WebAssertion(element);
    }

    public WebAction useCssElementForSelectDropdownAnd(String css) {
        SelenideElement element = $(By.cssSelector(css));
        return this;
    }


    public WebAssertion useXpathElementForSelectDropdown(String xpath) {
        SelenideElement element = $(By.xpath(xpath));
        return new WebAssertion(element);
    }

    public WebAction useXpathElementForSelectDropdownAnd(String xpath) {
        SelenideElement element = $(By.xpath(xpath));
        return this;
    }

    public WebAssertion useElementByCss(String css) {
        SelenideElement element = $(By.cssSelector(css));
        return new WebAssertion(element);
    }


    public WebAction useElementByCssAnd(String css) {
        SelenideElement element = $(By.cssSelector(css));
        return this;
    }

    public WebAssertion useElementByXpath(String xpath) {
        SelenideElement element = $(By.xpath(xpath));
        return new WebAssertion(element);
    }


    public WebAction useElementByXpathAnd(String xpath) {
        SelenideElement element = $(By.xpath(xpath));
        return this;
    }
}
