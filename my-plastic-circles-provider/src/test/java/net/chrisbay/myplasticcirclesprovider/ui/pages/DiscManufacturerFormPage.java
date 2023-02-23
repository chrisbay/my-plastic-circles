package net.chrisbay.myplasticcirclesprovider.ui.pages;

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

    @FindBy(name = "name")
    private WebElement nameField;

    @FindBy(css = "form button[type=submit]")
    private WebElement submitBtn;

    public DiscManufacturerFormPage(WebDriver driver) {
        super(driver);
    }

    public DiscManufacturersPage fillAndSubmitForm(String name) {
        this.nameField.sendKeys(name);
        this.submitBtn.click();
        return PageFactory.initElements(driver, DiscManufacturersPage.class);
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
