package net.chrisbay.myplasticcirclesprovider.ui;

import net.chrisbay.myplasticcirclesprovider.ui.pages.DiscsPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscsPageTest extends AbstractUITest {

    private DiscsPage page;

    @BeforeMethod
    public void loadPage() {
        driver.get("http://localhost:8080/discs");
        this.page = PageFactory.initElements(driver, DiscsPage.class);
    }

    @Test
    public void verifyDiscsPageContents() {
        assertEquals("My Collection", this.page.getPageHeading());
        assertEquals(6, this.page.getRows().size());
    }

    @Test
    public void verifyAddDiscButton() {
        this.page.clickAddDiscBtn();
        assertEquals("http://localhost:8080/discs/edit/0", driver.getCurrentUrl());
    }

    @Test
    public void toggleFavoriteStatus() {
        Integer idx = 0;
        boolean initialStatus = this.page.getFavoriteStatus(idx);
        this.page.clickFavoriteStar(idx);
        boolean finalStatus = this.page.getFavoriteStatus(idx);
        assertEquals(!initialStatus, finalStatus);

        driver.get(this.page.getPageUrl());
        boolean reloadedStatus = this.page.getFavoriteStatus(idx);
        assertEquals(finalStatus, reloadedStatus);
    }

}
