package net.chrisbay.myplasticcirclesprovider.functional.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage{

    private static final String PAGE_NAME;
    private static final String PAGE_PATH;

    static {
        PAGE_NAME = "Home";
        PAGE_PATH = "/";
    }

    @FindBy(id = "get-started")
    private WebElement getStartedBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageName() {
        return PAGE_NAME;
    }

    @Override
    public String getPagePath() {
        return PAGE_PATH;
    }

    public String getGetStartedBtnText() {
        return this.getStartedBtn.getText();
    }

    public void clickGetStartedBtn() {
        this.getStartedBtn.click();
    }

}
