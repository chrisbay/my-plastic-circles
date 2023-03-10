package net.chrisbay.myplasticcirclesprovider.functional;

import net.chrisbay.myplasticcirclesprovider.functional.pages.AbstractPage;
import net.chrisbay.myplasticcirclesprovider.functional.pages.DiscsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiscsPageTest extends AbstractUITest {

    private DiscsPage page;

    @BeforeMethod
    public void loadPage() {
        driver.get(AbstractPage.BASE_URL + "/discs");
        this.page = PageFactory.initElements(driver, DiscsPage.class);
    }

    @Test
    public void verifyDiscsPageContents() {
        assertEquals("My Collection", this.page.getPageHeading());
        assertTrue(this.page.getRows().size() > 0);
    }

    @Test
    public void verifyAddDiscButton() {
        this.page.clickAddDiscBtn();
        assertEquals(AbstractPage.BASE_URL + "/discs/0/edit", driver.getCurrentUrl());
    }

    @Test
    public void verifyToggleFavoriteStatus() {
        Integer idx = 0;
        boolean initialStatus = this.page.getFavoriteStatus(idx);
        this.page.clickFavoriteStar(idx);
        boolean finalStatus = this.page.getFavoriteStatus(idx);
        assertEquals(!initialStatus, finalStatus);

        driver.get(this.page.getPageUrl());
        boolean reloadedStatus = this.page.getFavoriteStatus(idx);
        assertEquals(finalStatus, reloadedStatus);
    }

    @Test
    public void verifyToggleFavoriteStatusSuccessMessage() {
        Integer idx = 0;
        boolean initialStatus = this.page.getFavoriteStatus(idx);
        this.page.clickFavoriteStar(idx);
        String messageTail = initialStatus ? "was removed from your favorites" : "was added to your favorites";

        List<Map<String, String>> messages = this.page.getMessages();
        assertTrue(messages.size() > 0);
        Map<String, String> firstMessage = messages.get(0);
        assertEquals(firstMessage.get("type"), "Info");

        Pattern pattern = Pattern.compile(".*(" + messageTail + ")$");
        Matcher matcher = pattern.matcher(firstMessage.get("message"));
        if (!matcher.lookingAt()) {
            fail("Did not find expected message");
        }

        // Check the other info message
        this.page.clickFavoriteStar(idx);
        messageTail = initialStatus ? "was added to your favorites" : "was removed from your favorites";

        messages = this.page.getMessages();
        assertTrue(messages.size() > 0);
        firstMessage = messages.get(0);
        assertEquals("Info", firstMessage.get("type"));

        pattern = Pattern.compile(".*(" + messageTail + ")$");
        matcher = pattern.matcher(firstMessage.get("message"));
        if (!matcher.lookingAt()) {
            fail("Did not find expected message");
        }
    }

}
