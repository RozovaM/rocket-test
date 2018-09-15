package core.modules.web.models;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebAssertion {

    private WebElement elementForAssert;
    private List<WebElement> elementListForAssert;

    public WebAssertion(WebElement elementForAssert) {
        this.elementForAssert = elementForAssert;
    }

    public WebAssertion(List<WebElement> elementListForAssert) {
        this.elementListForAssert = elementListForAssert;
    }

    public void shouldBeEnabled(boolean condition) throws Exception {
        throw new Exception("Method isn't support");
    }

    public void shouldBeDisplayed(boolean condition) {
        if (condition) {
            Assert.assertTrue(elementForAssert.isDisplayed(), "Element " + elementForAssert.toString() + " is not displayed");
        } else {
            Assert.assertFalse(elementForAssert.isDisplayed(), "Element " + elementForAssert.toString() + " is displayed");
        }
    }

    public void elementListShouldBeDisplayed(boolean condition) {
        SoftAssert softAssert = new SoftAssert();
        for (WebElement element : elementListForAssert) {
            if (condition) {
                softAssert.assertTrue(element.isDisplayed(), "Element " + element.toString() + " is not displayed");
            } else {
                softAssert.assertFalse(element.isDisplayed(), "Element " + element.toString() + " is displayed");
            }
        }
        softAssert.assertAll();
    }

    public void shouldBeImage(boolean condition) throws Exception {
        throw new Exception("Method isn't support");
    }

    public void shouldBeSelected(boolean condition) {
        if (condition) {
            Assert.assertTrue(elementForAssert.isSelected(), "Element " + elementForAssert.toString() + " is not selected");
        } else {
            Assert.assertFalse(elementForAssert.isSelected(), "Element " + elementForAssert.toString() + " is selected");
        }
    }

    public void elementListShouldContainText(String... expectedText) {
        checkTextListForEqual(expectedText);
    }

    public void fieldShouldContainText (String attributeName, Object value) {
        checkAttributeValueTextForEqual (attributeName, value);
    }

    private void checkTextListForEqual (Object value) {
        if (value instanceof String) {
            String actualValue = elementForAssert.getText().toLowerCase().trim();
            String expectedValue = ((String) value).toLowerCase().trim();
            Assert.assertEquals(actualValue, expectedValue, "Text " + actualValue + " is not present");
        } else if (value instanceof String[]) {
            ArrayList<String> actualValueList = new ArrayList<>();
            ArrayList<String> expectedValues = convertToLowerCase(new ArrayList<>(Arrays.asList((String[]) value)));

            for (WebElement element : elementListForAssert) {
                String label = element.getText().toLowerCase();
                actualValueList.add(label.toLowerCase());
            }

            List<String> sourceList = new ArrayList<String>(expectedValues);
            List<String> destinationList = new ArrayList<String>(actualValueList);
            sourceList.removeAll(actualValueList);
            destinationList.removeAll(expectedValues);

            System.out.println(actualValueList.toString());
            System.out.println(expectedValues.toString());

            Assert.assertEquals(actualValueList.toString(), expectedValues.toString(), "Text" + sourceList.toString() + " are not present");
        }
    }

    private void checkAttributeValueTextForEqual (String attributeName, Object value) {
        if (value instanceof String){
            String actualValue = elementForAssert.getAttribute(attributeName).toLowerCase().trim();
            String expectedValue = ((String) value).toLowerCase();
            Assert.assertEquals(actualValue, expectedValue, "Text " + actualValue + " is not present");
        } else if (value instanceof String []){
            ArrayList<String> actualValueList = new ArrayList<>();
            ArrayList<String> expectedValues = convertToLowerCase(new ArrayList<>(Arrays.asList((String [])value)));

            for (WebElement elemnent : elementListForAssert) {
                String label = elemnent.getAttribute(attributeName).toLowerCase();
                actualValueList.add(label.toLowerCase());
            }

            List<String> sourceList = new ArrayList<String>(expectedValues);
            List<String> destinationList = new ArrayList<String>(actualValueList);
            sourceList.removeAll(actualValueList);
            destinationList.removeAll(expectedValues);
            Assert.assertEquals(actualValueList.toString(), expectedValues.toString(), "Text" + sourceList.toString() + " are not present");
        }
    }

    private ArrayList<String> convertToLowerCase (ArrayList<String> list) {
        ArrayList<String> lowerCaseList  = new ArrayList<>();
        for (String value : list) {
            lowerCaseList.add(value.toLowerCase());
        }
        return lowerCaseList;
    }
}
