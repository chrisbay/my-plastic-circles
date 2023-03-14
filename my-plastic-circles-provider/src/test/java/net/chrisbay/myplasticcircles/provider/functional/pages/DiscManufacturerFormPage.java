package net.chrisbay.myplasticcircles.provider.functional.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DiscManufacturerFormPage extends AbstractFormPage {

    private static final String PAGE_NAME;
    private static final String PAGE_PATH;

    static {
        PAGE_NAME = "New Manufacturer";
        PAGE_PATH = "/manufacturers/new";
    }

    @FindBy(id = "name-input")
    private WebElement nameField;

    @FindBy(id = "manufacturer-form")
    private WebElement form;

    public DiscManufacturerFormPage(WebDriver driver) {
        super(driver);
    }

    public DiscManufacturersPage fillAndSubmitForm(String name) {
        this.fillNameField(name);
        this.form.submit();
        this.driver.findElement(By.id("manufacturers-table"));
        return PageFactory.initElements(driver, DiscManufacturersPage.class);
    }

    public void fillNameField(String name) {
        this.nameField.sendKeys(name);
    }

    public void waitForDelayedNameError() {
        this.driver.findElement(By.id("name.errors"));
    }

    @Override
    public String getPageName() {
        return PAGE_NAME;
    }

    @Override
    public String getPagePath() {
        return PAGE_PATH;
    }
}
