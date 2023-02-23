package net.chrisbay.myplasticcirclesprovider.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractFormPage extends AbstractPage {

    public AbstractFormPage() {}

    public AbstractFormPage(WebDriver driver) {
        super(driver);
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
