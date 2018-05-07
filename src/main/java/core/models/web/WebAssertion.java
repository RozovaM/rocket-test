package core.models.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class WebAssertion {

    private SelenideElement elementForAssert;


    public WebAssertion(SelenideElement elementForAssert) {
        this.elementForAssert = elementForAssert;
    }

    public void shouldHaveText(String condition) {
        this.elementForAssert.shouldHave(Condition.text(condition));
    }

    public void shouldBeEnabled(boolean condition) {
        this.elementForAssert.isEnabled();
    }

    public void shouldBeDisplayed(boolean condition) {
        this.elementForAssert.isDisplayed();
    }

    public void shouldBeImage(boolean condition) {
        this.elementForAssert.isImage();
    }

    public void shouldBeSelected(boolean condition) {
        this.elementForAssert.isSelected();
    }

}
