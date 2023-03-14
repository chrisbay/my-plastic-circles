package net.chrisbay.myplasticcirclesprovider.functional;

import net.chrisbay.myplasticcirclesprovider.functional.pages.DiscFormPage;
import net.chrisbay.myplasticcirclesprovider.functional.pages.DiscsPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DiscCreateFormPageTest extends AbstractUITest {

    private DiscFormPage page;

    private final String testModel = "Freetail";
    private final String testManufacturer = "Mint";
    private final String testSpeed = "10";
    private final String testGlide = "5";
    private final String testTurn = "-4";
    private final String testFade = "1";
    private final String testNotes = "Apex plastic";

    @BeforeMethod
    public void loadPage() {
        this.page = DiscFormPage.loadCreateDiscPage(driver);
    }

    @Test
    public void verifyEmptyForm() {
        assertTrue(this.page.isFormEmpty());
        assertNotNull(this.page.getManufacturerSelectElement());
    }

    @Test
    public void verifyValidFormSubmission() {

        DiscsPage discsPage = this.page.fillAndSubmitForm(testModel, testManufacturer, testSpeed, testGlide, testTurn, testFade, testNotes);

        List<WebElement> tableRows = discsPage.getRows();
        Integer rowIdx = tableRows.size() - 1;

        assertEquals(testManufacturer, discsPage.getFieldText(rowIdx, "manufacturer"));
        assertEquals(testModel, discsPage.getFieldText(rowIdx, "model"));
        assertEquals(testSpeed + " / " + testGlide + " / " + testTurn + " / " + testFade, discsPage.getFieldText(rowIdx, "flightNumbers"));
        assertEquals(testNotes, discsPage.getFieldText(rowIdx, "notes"));
    }

    @Test
    public void verifyNotesFieldIsOptional() {

        DiscsPage discsPage = this.page.fillAndSubmitForm(testModel, testManufacturer, testSpeed, testGlide, testTurn, testFade, "");

        List<WebElement> tableRows = discsPage.getRows();
        Integer rowIdx = tableRows.size() - 1;

        assertEquals(testManufacturer, discsPage.getFieldText(rowIdx, "manufacturer"));
        assertEquals(testModel, discsPage.getFieldText(rowIdx, "model"));
        assertEquals(testSpeed + " / " + testGlide + " / " + testTurn + " / " + testFade, discsPage.getFieldText(rowIdx, "flightNumbers"));
        assertEquals("", discsPage.getFieldText(rowIdx, "notes"));
    }


    @Test
    public void verifyValidationErrorsAreDisplayed() {
        this.page.fillForm("a", "Discraft", "", "", "", "", "");

        this.page.waitForDelayedModelError();

        assertNotNull(page.getFieldErrorMessage("model"));
        assertNotNull(page.getFieldErrorMessage("speed"));
        assertNotNull(page.getFieldErrorMessage("glide"));
        assertNotNull(page.getFieldErrorMessage("turn"));
        assertNotNull(page.getFieldErrorMessage("fade"));
    }

    @Test
    public void verifySuccessMessageOnNewDisc() {
        String expectedMessage = testModel + " was added to your collection";
        DiscsPage discsPage = this.page.fillAndSubmitForm(testModel, testManufacturer, testSpeed, testGlide, testTurn, testFade, testNotes);

        List<Map<String, String>> messages = discsPage.getMessages();
        assertTrue(messages.size() > 0);
        Map<String, String> firstMessage = messages.get(0);
        assertEquals("Success", firstMessage.get("type"));
        assertEquals(expectedMessage, firstMessage.get("message"));
    }

}
