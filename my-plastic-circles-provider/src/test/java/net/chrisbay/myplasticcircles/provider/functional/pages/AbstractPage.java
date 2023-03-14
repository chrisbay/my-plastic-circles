package net.chrisbay.myplasticcircles.provider.functional.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Map<String, String>> getMessages() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#message-center .toast")));
        List<WebElement> messageElements = this.driver
                .findElements(By.cssSelector("#message-center .toast"));
        List<Map<String, String>> messages = new ArrayList<>();
        for (WebElement messageElement : messageElements) {
            String type = messageElement.findElement(By.className("message-type")).getText();
            String messageBody = messageElement.findElement(By.className("message-body")).getText();
            Map<String, String> message = new HashMap<>();
            message.put("type", type);
            message.put("message", messageBody);
            messages.add(message);
        }
        return messages;
    }
}
