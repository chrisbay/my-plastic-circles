package net.chrisbay.myplasticcirclesprovider.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DiscFormPage extends AbstractPage {

    private final String pageName;
    private String pagePath;

    @FindBy(name = "model")
    private WebElement modelField;

    @FindBy(id = "manufacturer-input")
    private WebElement manufacturerField;

    @FindBy(name = "speed")
    private WebElement speedField;

    @FindBy(name = "glide")
    private WebElement glideField;

    @FindBy(name = "turn")
    private WebElement turnField;

    @FindBy(name = "fade")
    private WebElement fadeField;

    @FindBy(name = "notes")
    private WebElement notesField;

    @FindBy(css = "form button[type=submit]")
    private WebElement submitBtn;

    public DiscFormPage(WebDriver driver, Integer id) {
        super();
        this.driver = driver;
        this.pagePath = "/discs/edit/" + id;

        pageName = id == 0 ? "New Disc" : "Edit Disc";

        this.pageUrl = BASE_URL + this.pagePath;
        if (!driver.getTitle().equals("My Plastic Circles - " + getPageName())
                || !driver.getCurrentUrl().equals(this.pageUrl)) {
            throw new IllegalStateException("This is not the " + getPageName() + " Page. Current URL is: " + driver.getCurrentUrl());
        }
    }

    public boolean isFormEmpty() {
        if (!this.modelField.getAttribute("value").equals("")) return false;
        if (!this.speedField.getAttribute("value").equals("")) return false;
        if (!this.glideField.getAttribute("value").equals("")) return false;
        if (!this.turnField.getAttribute("value").equals("")) return false;
        if (!this.fadeField.getAttribute("value").equals("")) return false;
        if (!this.notesField.getAttribute("value").equals("")) return false;

        return true;
    }

    public DiscsPage fillAndSubmitForm(String model, String manufacturer,
                                  String speed, String glide, String turn, String fade, String notes) {
        this.modelField.sendKeys(model);
        this.getManufacturerSelectElement().selectByVisibleText(manufacturer);
        this.speedField.sendKeys(speed);
        this.glideField.sendKeys(glide);
        this.turnField.sendKeys(turn);
        this.fadeField.sendKeys(fade);
        this.notesField.sendKeys(notes);
        this.submitBtn.click();

        return PageFactory.initElements(this.driver, DiscsPage.class);
    }

    @Override
    public String getPageName() {
        return pageName;
    }

    @Override
    public String getPagePath() {
        return this.pagePath;
    }

    public Select getManufacturerSelectElement() {
        return new Select(manufacturerField);
    }

    public String getFieldErrorMessage(String fieldName) {
        WebElement errorElement;
        try {
            errorElement = driver.findElement(By.id(fieldName + ".errors"));
        } catch (NoSuchElementException e) {
            return null;
        }

        return errorElement.getText();
    }
}
