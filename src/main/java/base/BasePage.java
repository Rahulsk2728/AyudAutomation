package base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    //Click method
    public void click(Locator locator) {
        locator.click();
    }

    //Fill method
    public void fill(Locator locator, String text) {
        locator.fill(text);
    }

    //Get text method
    public String getText(Locator locator) {
        return locator.textContent();
    }

    //Is visble method
    public boolean isVisible(Locator locator) {
        return locator.isVisible();
    }

    //Hover method
    public void hover(Locator locator) {
        locator.hover();
    }

    //Type text
    public void type(Locator locator, String text) {
        locator.pressSequentially(text);
    }

    //Clear method
    public void clear(Locator locator) {
        locator.clear();
    }

    //double click
    public void doubleClick(Locator locator) {
        locator.dblclick();
    }

    //right click
    public void rightClick(Locator locator) {
        locator.click(new Locator.ClickOptions()
                .setButton(com.microsoft.playwright.options.MouseButton.RIGHT));
    }

    //Scrooll into view
    public void scrollIntoView(Locator locator) {
        locator.scrollIntoViewIfNeeded();
    }

    //get attribute
    public String getAttribute(Locator locator, String attribute) {
        return locator.getAttribute(attribute);
    }

    //Check
    public void check(Locator locator) {
        locator.check();
    }

    //Uncheck
    public void uncheck(Locator locator) {
        locator.uncheck();
    }

    //Select dropdown
    public void selectDropdown(Locator locator, String value) {
        locator.selectOption(value);
    }

    //Press enter
    public void pressEnter(Locator locator) {
        locator.press("Enter");
    }



}