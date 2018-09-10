package core.modules.web.models;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class WebAssertion {

    private WebElement elementForAssert;

    public WebAssertion(WebElement elementForAssert) {
        this.elementForAssert = elementForAssert;
    }

    public void shouldBeEnabled(boolean condition) {
        Assert.assertTrue(elementForAssert.isEnabled(), "Element is not enabled");
    }

    public void shouldBeDisplayed(boolean condition) {
        Assert.assertTrue(elementForAssert.isDisplayed(), "Element is not displayed");
    }

     public void shouldBeImage(boolean condition) throws Exception{
        throw new Exception("Method isn't support");
    }

    public void shouldBeSelected(boolean condition) {
        Assert.assertTrue(elementForAssert.isSelected(), "Element is not selected");
    }

}
