package net.chrisbay.myplasticcirclesprovider.ui;

import net.chrisbay.myplasticcirclesprovider.ui.pages.AbstractPage;
import net.chrisbay.myplasticcirclesprovider.ui.pages.DiscManufacturerFormPage;
import net.chrisbay.myplasticcirclesprovider.ui.pages.DiscManufacturersPage;
import org.openqa.selenium.By;
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

}
