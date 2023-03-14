package net.chrisbay.myplasticcircles.provider.functional.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DiscManufacturersPage extends AbstractPage {

    private static final String PAGE_NAME;
    private static final String PAGE_PATH;

    static {
        PAGE_NAME = "All Manufacturers";
        PAGE_PATH = "/manufacturers";
    }

    @FindBy(id = "add-manufacturer-btn")
    private WebElement addManufacturerBtn;

    @FindBy(css = "#manufacturers-table tbody tr")
    private List<WebElement> rows;

    public DiscManufacturersPage(WebDriver driver) {
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

    public List<WebElement> getRows() {
        return rows;
    }

    public void clickAddManufacturerBtn() {
        this.addManufacturerBtn.click();
    }

    public String getManufacturerName(Integer idx) {
        if (idx < 0 || idx > this.rows.size()) {
            throw new IllegalArgumentException("Attempting to access item with index ["
                    + idx + "] in the table. Table has " + this.rows.size() + "rows");
        }

        return this.rows.get(idx).findElements(By.tagName("td")).get(0).getText();
    }
}
