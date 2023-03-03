package net.chrisbay.myplasticcirclesprovider.ui;

import net.chrisbay.myplasticcirclesprovider.ui.pages.AbstractPage;
import net.chrisbay.myplasticcirclesprovider.ui.pages.DiscFormPage;
import net.chrisbay.myplasticcirclesprovider.ui.pages.DiscsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

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
        DiscFormPage createDiscPage = DiscFormPage.loadCreateDiscPage(driver);
        DiscsPage discsPage = createDiscPage.fillAndSubmitForm(testModel, testManufacturer, testSpeed, testGlide, testTurn, testFade, testNotes);
        List<WebElement> tableRows = discsPage.getRows();
        String editUrl = discsPage.getDiscEditURL(tableRows.size() - 1);

        driver.get(AbstractPage.BASE_URL + editUrl);
        this.page = new DiscFormPage(driver, editUrl);
        PageFactory.initElements(driver, this.page);
    }

    @Test
    public void verifyFormLoadsWithCorrectData() {
        Map<String, String> values = page.getFormValues();
        assertEquals(testModel, values.get("model"));
        assertEquals(testManufacturer, values.get("manufacturer"));
        assertEquals(testSpeed, values.get("speed"));
        assertEquals(testGlide, values.get("glide"));
        assertEquals(testTurn, values.get("turn"));
        assertEquals(testFade, values.get("fade"));
        assertEquals(testNotes, values.get("notes"));
    }

    @Test
    public void verifyModifiedFieldsAreSaved() {
        String newNotes = "something different";
        DiscsPage discsPage = this.page.fillNotesFieldAndSubmitForm(newNotes);

        List<WebElement> tableRows = discsPage.getRows();
        WebElement firstRow = tableRows.get(tableRows.size() - 1);

        List<WebElement> rowData = firstRow.findElements(By.tagName("td"));

        assertEquals(testManufacturer, rowData.get(1).getText());
        assertEquals(testModel, rowData.get(2).getText());
        assertEquals(testSpeed + " / " + testGlide + " / " + testTurn + " / " + testFade, rowData.get(3).getText());
        assertEquals(newNotes, rowData.get(4).getText());
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
    public void verifyCanDeleteDisc() {
        String thisEditUrl = this.page.getPagePath();
        DiscsPage discsPage = this.page.clickDeleteLink();
        List<WebElement> tableRows = discsPage.getRows();
        String lastEditUrl = discsPage.getDiscEditURL(tableRows.size() - 1);
        assertNotEquals(lastEditUrl, thisEditUrl);
    }

}
