package net.chrisbay.myplasticcirclesprovider.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DiscsPage extends AbstractPage {

    private static final String PAGE_NAME;
    private static final String PAGE_PATH;

    static {
        PAGE_NAME = "My Discs";
        PAGE_PATH = "/discs";
    }

    @FindBy(id = "add-disc-btn")
    private WebElement addDiscsBtn;

    @FindBy(css = "#discs-table tbody tr")
    private List<WebElement> rows;

    public DiscsPage(WebDriver driver) {
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

    private WebElement getFavoriteStar(Integer idx) {
        WebElement row = this.getRow(idx);
        return row.findElement(By.cssSelector("i.fa-star"));
    }

    public boolean getFavoriteStatus(Integer idx) {
        WebElement star = getFavoriteStar(idx);

        if (star.getDomAttribute("class").contains("fa-regular")) return false;

        if (star.getDomAttribute("class").contains("fa-solid")) return true;

        throw new IllegalStateException("Star element at index ["
                + idx + "]" + "must have either 'fa-regular' or 'fa-solid' class attribute");
    }

    public void clickFavoriteStar(Integer idx) {
        WebElement star = getFavoriteStar(idx);
        star.click();
    }

    public void clickAddDiscBtn() {
        this.addDiscsBtn.click();
    }

    public String getDiscEditURL(Integer idx) {
        WebElement row = this.getRow(idx);
        List<WebElement> rowData = row.findElements(By.tagName("td"));
        return rowData.get(5).findElement(By.tagName("a")).getDomAttribute("href");
    }

    public String getFieldText(Integer rowIdx, String column) {
        WebElement row = this.getRow(rowIdx);
        List<WebElement> rowData = row.findElements(By.tagName("td"));

        if (column.equals("manufacturer")) {
            return rowData.get(1).getText();
        } else if (column.equals("model")) {
            return rowData.get(2).getText();
        } else if (column.equals("flightNumbers")) {
            return rowData.get(3).getText();
        } else if (column.equals("notes")) {
            return rowData.get(4).getText();
        }

        throw new IllegalArgumentException("Invalid column");
    }

    private WebElement getRow(Integer idx) {
        if (idx < 0 || idx > this.rows.size()) {
            throw new IllegalArgumentException("Attempting to access item with index ["
                    + idx + "] in the table. Table has " + this.rows.size() + "rows");
        }

        return this.rows.get(idx);
    }
}
