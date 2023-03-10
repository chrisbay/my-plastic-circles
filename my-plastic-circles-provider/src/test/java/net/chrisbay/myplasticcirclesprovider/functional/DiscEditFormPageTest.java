package net.chrisbay.myplasticcirclesprovider.functional;

import net.chrisbay.myplasticcirclesprovider.functional.pages.AbstractPage;
import net.chrisbay.myplasticcirclesprovider.functional.pages.DiscFormPage;
import net.chrisbay.myplasticcirclesprovider.functional.pages.DiscsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;

public class DiscEditFormPageTest extends AbstractUITest {

    private DiscFormPage page;

    // test data depends on data imported by src/main/resources/import.sql; should be first record in disc table
    private final String testModel = "Avenger SS";
    private final String testManufacturer = "Discraft";
    private final String testSpeed = "10";
    private final String testGlide = "5";
    private final String testTurn = "-3";
    private final String testFade = "1";
    private final String testNotes = "Z-line swirl plastic";

    @BeforeMethod
    public void loadPage() {
        DiscFormPage createDiscPage = DiscFormPage.loadCreateDiscPage(this.driver);
        DiscsPage discsPage = createDiscPage.fillAndSubmitForm(
                this.testModel,
                this.testManufacturer,
                this.testSpeed,
                this.testGlide,
                this.testTurn,
                this.testFade,
                this.testNotes);
        List<WebElement> tableRows = discsPage.getRows();
        String editUrl = discsPage.getDiscEditURL(tableRows.size() - 1);

        this.driver.get(AbstractPage.BASE_URL + editUrl);
        this.page = new DiscFormPage(this.driver, editUrl);
        PageFactory.initElements(this.driver, this.page);
    }

    @Test
    public void verifyFormLoadsWithCorrectData() {
        Map<String, String> values = this.page.getFormValues();
        assertEquals(this.testModel, values.get("model"));
        assertEquals(this.testManufacturer, values.get("manufacturer"));
        assertEquals(this.testSpeed, values.get("speed"));
        assertEquals(this.testGlide, values.get("glide"));
        assertEquals(this.testTurn, values.get("turn"));
        assertEquals(this.testFade, values.get("fade"));
        assertEquals(this.testNotes, values.get("notes"));
    }

    @Test
    public void verifyModifiedFieldsAreSaved() {
        String newNotes = "something different";
        DiscsPage discsPage = this.page.fillNotesFieldAndSubmitForm(newNotes);

        List<WebElement> tableRows = discsPage.getRows();
        WebElement firstRow = tableRows.get(tableRows.size() - 1);

        List<WebElement> rowData = firstRow.findElements(By.tagName("td"));

        assertEquals(this.testManufacturer, rowData.get(1).getText());
        assertEquals(this.testModel, rowData.get(2).getText());
        assertEquals(this.testSpeed + " / " + this.testGlide + " / " + this.testTurn + " / " + this.testFade, rowData.get(3).getText());
        assertEquals(newNotes, rowData.get(4).getText());
    }

    @Test
    public void verifyValidationErrorsAreDisplayed() {
        this.page.fillForm("a", "Discraft", "", "", "", "", "");
        this.page.waitForDelayedModelError();

        assertNotNull(this.page.getFieldErrorMessage("model"));
        assertNotNull(this.page.getFieldErrorMessage("speed"));
        assertNotNull(this.page.getFieldErrorMessage("glide"));
        assertNotNull(this.page.getFieldErrorMessage("turn"));
        assertNotNull(this.page.getFieldErrorMessage("fade"));
    }

    @Test
    public void verifyCanDeleteDisc() {
        String thisEditUrl = this.page.getPagePath();
        DiscsPage discsPage = this.page.clickDeleteLink();
        List<WebElement> tableRows = discsPage.getRows();
        String lastEditUrl = discsPage.getDiscEditURL(tableRows.size() - 1);
        assertNotEquals(lastEditUrl, thisEditUrl);
    }

    @Test
    public void verifyDeleteDiscSuccessMessage() {
        String expectedMessage = this.testModel + " was deleted";
        String thisEditUrl = this.page.getPagePath();
        DiscsPage discsPage = this.page.clickDeleteLink();
        List<Map<String, String>> messages = discsPage.getMessages();
        assertTrue(messages.size() > 0);
        Map<String, String> firstMessage = messages.get(0);
        assertEquals("Success", firstMessage.get("type"));
        assertEquals(expectedMessage, firstMessage.get("message"));
    }

    @Test
    public void verifyErrorMessageOnNonNumericId() {
        String invalidId = "asdf";
        this.driver.get(AbstractPage.BASE_URL + "/discs/" + invalidId + "/edit");
        String expectedMessage = "Invalid disc id: " + invalidId + "";
        DiscsPage discsPage = PageFactory.initElements(driver, DiscsPage.class);

        List<Map<String, String>> messages = discsPage.getMessages();
        assertTrue(messages.size() > 0);
        Map<String, String> firstMessage = messages.get(0);
        assertEquals("Error", firstMessage.get("type"));
        assertEquals(expectedMessage, firstMessage.get("message"));
    }

    @Test
    public void verifyErrorMessageOnNegativeId() {
        String invalidId = "-1";
        this.driver.get(AbstractPage.BASE_URL + "/discs/" + invalidId + "/edit");
        String expectedMessage = "Invalid disc id: " + invalidId + "";
        DiscsPage discsPage = PageFactory.initElements(driver, DiscsPage.class);

        List<Map<String, String>> messages = discsPage.getMessages();
        assertTrue(messages.size() > 0);
        Map<String, String> firstMessage = messages.get(0);
        assertEquals("Error", firstMessage.get("type"));
        assertEquals(expectedMessage, firstMessage.get("message"));
    }

    @Test
    public void verifyErrorMessageOnNonExistentId() {
        String invalidId = "5000";
        this.driver.get(AbstractPage.BASE_URL + "/discs/" + invalidId + "/edit");
        String expectedMessage = "An error occured while retrieving the selected disc";

        List<Map<String, String>> messages = this.page.getMessages();
        assertTrue(messages.size() > 0);
        Map<String, String> firstMessage = messages.get(0);
        assertEquals("Error", firstMessage.get("type"));
        assertEquals(expectedMessage, firstMessage.get("message"));
    }

    @Test
    public void verifySuccessMessageOnSaveDisc() {
        String expectedMessage = testModel + " was saved";
        DiscsPage discsPage = this.page.fillNotesFieldAndSubmitForm("edited disc");

        List<Map<String, String>> messages = discsPage.getMessages();
        assertTrue(messages.size() > 0);
        Map<String, String> firstMessage = messages.get(0);
        assertEquals("Success", firstMessage.get("type"));
        assertEquals(expectedMessage, firstMessage.get("message"));
    }

}
