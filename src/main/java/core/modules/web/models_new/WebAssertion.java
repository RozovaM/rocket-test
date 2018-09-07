package core.modules.web.models_new;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class WebAssertion {

    private WebElement elementForAssert;

    public WebAssertion(WebElement elementForAssert) {
        this.elementForAssert = elementForAssert;
    }

//    public void shouldHaveText(String condition) {
//        this.elementForAssert.shouldHave(Condition.text(condition));
//    }

    public void shouldBeEnabled(boolean condition) {
        Assert.assertTrue(elementForAssert.isEnabled(), "Element is not enabled");
    }

    public void shouldBeDisplayed(boolean condition) {
        Assert.assertTrue(elementForAssert.isDisplayed(), "Element is not displayed");
    }

//     public void shouldBeImage(boolean condition) {
//        this.elementForAssert.isImage();
//    }

    public void shouldBeSelected(boolean condition) {
        Assert.assertTrue(elementForAssert.isSelected(), "Element is not selected");
    }

}
