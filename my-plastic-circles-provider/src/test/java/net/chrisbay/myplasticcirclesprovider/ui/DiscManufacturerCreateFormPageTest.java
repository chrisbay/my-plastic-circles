package net.chrisbay.myplasticcirclesprovider.ui;

import net.chrisbay.myplasticcirclesprovider.ui.pages.DiscManufacturerFormPage;
import net.chrisbay.myplasticcirclesprovider.ui.pages.DiscManufacturersPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DiscManufacturerCreateFormPageTest extends AbstractUITest {

    DiscManufacturerFormPage page;

    @BeforeMethod
    public void loadPage() {
        driver.get("http://localhost:8080/manufacturers/new");
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

    // verify submission errors
    @Test
    public void verifyValidationErrorsAreDisplayed() {
        try {
            this.page.fillAndSubmitForm("");
            throw new IllegalStateException("Form submission should throw an exception");
        } catch (Exception e) {
            // do nothing
        }

        assertEquals("http://localhost:8080/manufacturers/new", driver.getCurrentUrl());
        assertNotNull(page.getFieldErrorMessage("name"));
    }

}
