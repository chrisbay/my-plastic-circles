package net.chrisbay.myplasticcirclesprovider.functional;

import net.chrisbay.myplasticcirclesprovider.functional.pages.AbstractPage;
import net.chrisbay.myplasticcirclesprovider.functional.pages.DiscManufacturerFormPage;
import net.chrisbay.myplasticcirclesprovider.functional.pages.DiscManufacturersPage;
import net.chrisbay.myplasticcirclesprovider.functional.pages.DiscsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DiscManufacturerCreateFormPageTest extends AbstractUITest {

    DiscManufacturerFormPage page;

    @BeforeMethod
    public void loadPage() {
        driver.get(AbstractPage.BASE_URL + "/manufacturers/new");
        this.page = PageFactory.initElements(driver, DiscManufacturerFormPage.class);
    }

    @Test
    public void verifyValidFormSubmission() {
        String testName = "New Manufacturer";
        DiscManufacturersPage manufacturersPage = this.page.fillAndSubmitForm(testName);

        List<WebElement> tableRows = manufacturersPage.getRows();
        Integer rowIdx = tableRows.size() - 1;

        assertEquals(testName, manufacturersPage.getManufacturerName(rowIdx));
    }

    @Test
    public void verifyValidationErrorsAreDisplayed() {
        this.page.fillNameField("a");
        this.page.waitForDelayedNameError();
        assertNotNull(page.getFieldErrorMessage("name"));
    }

    @Test
    public void verifySuccessMessageOnNewManufacturer() {

        String testName = "New Manufacturer";
        String expectedMessage = testName + " was added to the system";
        DiscManufacturersPage manufacturersPage = this.page.fillAndSubmitForm(testName);

        List<Map<String, String>> messages = manufacturersPage.getMessages();
        assertTrue(messages.size() > 0);
        Map<String, String> firstMessage = messages.get(0);
        assertEquals("Success", firstMessage.get("type"));
        assertEquals(expectedMessage, firstMessage.get("message"));
    }

}
