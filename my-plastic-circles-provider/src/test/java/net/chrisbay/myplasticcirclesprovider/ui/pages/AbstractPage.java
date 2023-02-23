package net.chrisbay.myplasticcirclesprovider.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractPage {

    protected WebDriver driver;
    public static final String BASE_URL = "http://localhost:8080";
    private final String pageUrl;

    @FindBy(css = "h1")
    private WebElement pageHeadingElement;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.pageUrl = BASE_URL + getPagePath();
        if (!driver.getTitle().equals("My Plastic Circles - " + getPageName())
            || !driver.getCurrentUrl().equals(this.pageUrl)) {
            throw new IllegalStateException("This is not the " + getPageName() + " Page. Current URL is: " + driver.getCurrentUrl());
        }
    }

    public abstract String getPageName();

    public abstract String getPagePath();

    public String getPageHeading() {
        return this.pageHeadingElement.getText();
    }

    public String getPageUrl() {
        return this.pageUrl;
    }
}
