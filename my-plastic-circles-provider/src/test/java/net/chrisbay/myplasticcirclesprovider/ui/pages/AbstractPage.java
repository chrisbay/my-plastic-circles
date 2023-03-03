package net.chrisbay.myplasticcirclesprovider.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractPage {

    protected WebDriver driver;
    public static final String BASE_URL = "http://localhost:4200";
    protected String pageUrl;

    @FindBy(css = "h1")
    private WebElement pageHeadingElement;

    public AbstractPage() {}

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.pageUrl = BASE_URL + getPagePath();
        if (!driver.getCurrentUrl().equals(this.pageUrl)) {
            throw new IllegalStateException("Invalid URL. Expected: " + this.getPageUrl() + " . Current URL is: " + driver.getCurrentUrl());
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
