package core.modules.web.models;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WebAssertion {

    private WebElement elementForAssert;
    private List<WebElement> elementListForAssert;
    private String url;
    private String[] params;

    public WebAssertion(WebElement elementForAssert) {
        this.elementForAssert = elementForAssert;
    }

    public WebAssertion(List<WebElement> elementListForAssert) {
        this.elementListForAssert = elementListForAssert;
    }

    public WebAssertion(String url, String[] params) {
        try {
            this.url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.params = params;
    }

    public void shouldBeEnabled(boolean condition) throws Exception {
        throw new Exception("Method isn't support");
    }

    public void shouldBeDisplayed(boolean condition) {
        if (condition) {
            Assert.assertTrue(elementForAssert.isDisplayed(), "Element " + elementForAssert.toString() + " is not displayed");
            return;
        }
        Assert.assertFalse(elementForAssert.isDisplayed(), "Element " + elementForAssert.toString() + " is displayed");
    }

    public void elementListShouldBeDisplayed(boolean condition) {
        SoftAssert softAssert = new SoftAssert();
        for (WebElement element : elementListForAssert) {
            if (condition) {
                softAssert.assertTrue(element.isDisplayed(), "Element " + element.toString() + " is not displayed");
                return;
            }
            softAssert.assertFalse(element.isDisplayed(), "Element " + element.toString() + " is displayed");
        }
        softAssert.assertAll();
    }

    public void shouldBeImage(boolean condition) throws Exception {
        throw new Exception("Method isn't support");
    }

    public void shouldBeSelected(boolean condition) {
        if (condition) {
            Assert.assertTrue(elementForAssert.isSelected(), "Element " + elementForAssert.toString() + " is not selected");
            return;
        }
        Assert.assertFalse(elementForAssert.isSelected(), "Element " + elementForAssert.toString() + " is selected");
    }

    public void elementListShouldContainText(String... expectedText) {
        if(!checkListForEqual(Arrays.asList(expectedText)))
            Assert.fail("Expected data does not match with actual data");
    }

    public void fieldShouldContainText(String attributeName, String value) {
        if(!checkAttributeValueTextForEqual(attributeName, value))
            Assert.fail("Expected data does not match with actual data");
    }

    public void urlShouldContainValues(String... expectedValues) {
        System.out.println(url);
        List<String> expectedValueList = convertToLowerCase(new ArrayList<>(Arrays.asList((String[]) expectedValues)));
        List<String> paramList = new ArrayList<>(Arrays.asList((String[]) params));
        List<String> actualValueList = getUrlValues(paramList);

        List<String> sourceList = new ArrayList<String>(expectedValueList);
        List<String> destinationList = new ArrayList<String>(actualValueList);
        sourceList.removeAll(actualValueList);
        destinationList.removeAll(expectedValueList);

        Assert.assertEquals(actualValueList.toString(), expectedValueList.toString(), "Values" + sourceList.toString() + " are not present in url");
    }

    private boolean checkListForEqual(List <String> expectedValues){

        List<String> actualValuesToLowerCase = getTextFromWebElements()
                .stream().map(s -> s.toLowerCase().trim()).sorted().collect(Collectors.toList());
        List<String> expectedValuesToLowerCase = expectedValues
                .stream().map(s -> s.toLowerCase().trim()).sorted().collect(Collectors.toList());

        return actualValuesToLowerCase.equals(expectedValuesToLowerCase);
    }

    private boolean checkAttributeValueTextForEqual (String attributeName, String expectedValue){
        return getAttributeValue(attributeName).toLowerCase().trim()
                .equals(expectedValue.toLowerCase().trim());
    }

    private List<String> getTextFromWebElements(){
        List<String> values = new ArrayList<>();

        for (WebElement element : elementListForAssert) {
            values.add(element.getText());
        }
        return values;
    }

    private String getAttributeValue(String attributeName){
        return elementForAssert.getAttribute(attributeName);
    }

    private ArrayList<String> convertToLowerCase(ArrayList<String> list) {
        ArrayList<String> lowerCaseList = new ArrayList<>();
        for (String value : list) {
            lowerCaseList.add(value.toLowerCase());
        }
        return lowerCaseList;
    }

    private List<String> getUrlValues(List<String> paramList) {
        List<String> actualValuesList = new ArrayList<>();
        for (String parameter : paramList) {
            Pattern pattern = Pattern.compile("(?<=" + parameter + "=)(.*?)(?=&)");
            Matcher matcher = pattern.matcher(url);
            while (matcher.find()) {
                actualValuesList.add(matcher.group().toLowerCase());
            }
        }
        return actualValuesList;
    }
}
