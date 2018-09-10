package core.modules.web.models;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebAssertion {

    private WebElement elementForAssert;
    private List <WebElement> elementListForAssert;

    public WebAssertion(WebElement elementForAssert) {
        this.elementForAssert = elementForAssert;
    }

    public WebAssertion(List<WebElement> elementListForAssert) {
        this.elementListForAssert = elementListForAssert;
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
        if(condition){
            Assert.assertTrue(elementForAssert.isSelected(), "Element is not selected");
        } else {
            Assert.assertFalse(elementForAssert.isSelected(), "Element is selected");
        }
    }

    public void labelListShouldContainText (String ... expectedText) {
        checkTextListForEqual(expectedText);
    }

    public void fieldShouldContainText (String attributeName, String ... expectedText) {
        checkAttributeValueTextForEqual (attributeName, expectedText);
    }

    public void attributeValueListShouldContainText (String attributeName, String ... expectedText) {
        checkAttributeValueTextForEqual (attributeName, expectedText);
    }

    private void checkTextListForEqual (String ... expectedText) {
        ArrayList<String> actualValues = new ArrayList<>();
        ArrayList<String> expectedValues = convertToLowerCase(new ArrayList<>(Arrays.asList(expectedText)));

        for (WebElement elemnent : elementListForAssert) {
            String label = elemnent.getText().toLowerCase();
            actualValues.add(label.toLowerCase());
        }

        List<String> sourceList = new ArrayList<String>(expectedValues);
        List<String> destinationList = new ArrayList<String>(actualValues);

        sourceList.removeAll(actualValues);
        destinationList.removeAll(expectedValues);

        Assert.assertEquals(actualValues.toString(), expectedValues.toString(), "Labels " + sourceList.toString() + " are not present");
    }

    private void checkAttributeValueTextForEqual (String attributeName, String ... expectedText) {
        ArrayList<String> actualLabels = new ArrayList<>();
        ArrayList<String> expectedValues = convertToLowerCase(new ArrayList<>(Arrays.asList(expectedText)));

        for (WebElement elemnent : elementListForAssert) {
            String label = elemnent.getAttribute(attributeName).toLowerCase();
            actualLabels.add(label.toLowerCase());
        }

        List<String> sourceList = new ArrayList<String>(expectedValues);
        List<String> destinationList = new ArrayList<String>(actualLabels);

        sourceList.removeAll(actualLabels);
        destinationList.removeAll(expectedValues);

        Assert.assertEquals(actualLabels.toString(), expectedValues.toString(), "Labels " + sourceList.toString() + " are not present");
    }


    private ArrayList<String> convertToLowerCase (ArrayList<String> list) {
        ArrayList<String> lowerCaseList  = new ArrayList<>();
        for (String value : list) {
            lowerCaseList.add(value.toLowerCase());
        }
        return lowerCaseList;
    }

}
