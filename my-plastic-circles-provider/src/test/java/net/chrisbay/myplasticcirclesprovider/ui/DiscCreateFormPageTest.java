package net.chrisbay.myplasticcirclesprovider.ui;

import net.chrisbay.myplasticcirclesprovider.ui.pages.DiscFormPage;
import net.chrisbay.myplasticcirclesprovider.ui.pages.DiscsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

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
        driver.get("http://localhost:8080/discs/edit/0");
        this.page = new DiscFormPage(driver, 0);
        PageFactory.initElements(driver, this.page);
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
        WebElement lastRow = tableRows.get(tableRows.size() - 1);

        List<WebElement> rowData = lastRow.findElements(By.tagName("td"));

        assertEquals(testManufacturer, rowData.get(1).getText());
        assertEquals(testModel, rowData.get(2).getText());
        assertEquals(testSpeed + " / " + testGlide + " / " + testTurn + " / " + testFade, rowData.get(3).getText());
        assertEquals(testNotes, rowData.get(4).getText());
    }

    @Test
    public void verifyNotesFieldIsOptional() {

        DiscsPage discsPage = this.page.fillAndSubmitForm(testModel, testManufacturer, testSpeed, testGlide, testTurn, testFade, "");

        List<WebElement> tableRows = discsPage.getRows();
        WebElement lastRow = tableRows.get(tableRows.size() - 1);

        List<WebElement> rowData = lastRow.findElements(By.tagName("td"));

        assertEquals(testManufacturer, rowData.get(1).getText());
        assertEquals(testModel, rowData.get(2).getText());
        assertEquals(testSpeed + " / " + testGlide + " / " + testTurn + " / " + testFade, rowData.get(3).getText());
        assertEquals("", rowData.get(4).getText());
    }


    @Test
    public void verifyValidationErrorsAreDisplayed() {
        try {
            DiscsPage discsPage = this.page.fillAndSubmitForm("", "Discraft", "", "", "", "", "");
            throw new IllegalStateException("Form submission should throw an exception");
        } catch (Exception e) {
            // do nothing
        }

        assertEquals("http://localhost:8080/discs/edit/0", driver.getCurrentUrl());
        assertNotNull(page.getFieldErrorMessage("model"));
        assertNotNull(page.getFieldErrorMessage("speed"));
        assertNotNull(page.getFieldErrorMessage("glide"));
        assertNotNull(page.getFieldErrorMessage("turn"));
        assertNotNull(page.getFieldErrorMessage("fade"));
    }

}
