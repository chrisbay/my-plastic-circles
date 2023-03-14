package net.chrisbay.myplasticcircles.provider.functional.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiscFormPage extends AbstractFormPage {

    private final String pageName;
    private String pagePath;

    @FindBy(id = "model-input")
    private WebElement modelField;

    @FindBy(id = "manufacturer-input")
    private WebElement manufacturerField;

    @FindBy(id = "speed-input")
    private WebElement speedField;

    @FindBy(id = "glide-input")
    private WebElement glideField;

    @FindBy(id = "turn-input")
    private WebElement turnField;

    @FindBy(id = "fade-input")
    private WebElement fadeField;

    @FindBy(id = "notes-input")
    private WebElement notesField;

    @FindBy(id = "disc-form")
    private WebElement form;

    public DiscFormPage(WebDriver driver, String path) {
        super();
        this.driver = driver;
        this.pagePath = path;

        Integer id = getIdFromEditUrl(path);

        if (id != null) {
            pageName = id == 0 ? "New Disc" : "Edit Disc";
        } else {
            throw new IllegalStateException("Invalid edit path");
        }

        this.pageUrl = BASE_URL + this.pagePath;
        if (!driver.getCurrentUrl().equals(this.pageUrl)) {
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
        this.fillForm(model, manufacturer, speed, glide, turn, fade, notes);
        form.submit();

        driver.findElement(By.id("discs-table"));

        return PageFactory.initElements(this.driver, DiscsPage.class);
    }

    public void fillForm(String model, String manufacturer,
                         String speed, String glide, String turn, String fade, String notes) {
        this.clearForm();
        this.modelField.sendKeys(model);
        this.getManufacturerSelectElement().selectByVisibleText(manufacturer);
        this.speedField.sendKeys(speed);
        this.glideField.sendKeys(glide);
        this.turnField.sendKeys(turn);
        this.fadeField.sendKeys(fade);
        this.notesField.sendKeys(notes);
    }

    private void clearForm() {
        this.modelField.clear();
        this.speedField.clear();
        this.glideField.clear();
        this.turnField.clear();
        this.fadeField.clear();
        this.notesField.clear();
    }

    public DiscsPage fillNotesFieldAndSubmitForm(String notes) {
        this.notesField.clear();
        this.notesField.sendKeys(notes);
        this.form.submit();

        driver.findElement(By.id("discs-table"));

        return PageFactory.initElements(this.driver, DiscsPage.class);
    }

    public DiscsPage clickDeleteLink() {
        WebElement deleteLink = driver.findElement(By.id("delete-link"));
        deleteLink.click();

        WebElement confirmDeleteBtn = driver.findElement(By.id("confirmDeleteButton"));
        confirmDeleteBtn.click();

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

    public Map<String, String> getFormValues() {
        Map<String, String> values = new HashMap<>();
        values.put("model", modelField.getAttribute("value"));
        values.put("manufacturer", getManufacturerSelectElement().getFirstSelectedOption().getText());
        values.put("speed", speedField.getAttribute("value"));
        values.put("glide", glideField.getAttribute("value"));
        values.put("turn", turnField.getAttribute("value"));
        values.put("fade", fadeField.getAttribute("value"));
        values.put("notes", notesField.getAttribute("value"));
        return values;
    }

    private static Integer getIdFromEditUrl(String url) {
        Pattern pattern = Pattern.compile(".*/(\\d+)/edit");
        Matcher matcher = pattern.matcher(url);

        if (!matcher.lookingAt()) return null;

        return Integer.parseInt(matcher.group(1));
    }

    public void waitForDelayedModelError() {
        this.driver.findElement(By.id("model.errors"));
    }

    public static DiscFormPage loadCreateDiscPage(WebDriver driver) {
        driver.get("http://localhost:4200/discs/0/edit");
        DiscFormPage createDiscPage = new DiscFormPage(driver, "/discs/0/edit");
        PageFactory.initElements(driver, createDiscPage);
        return createDiscPage;
    }
}
