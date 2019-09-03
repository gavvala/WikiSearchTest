package com.wiki.search.pages;

import com.wiki.search.service.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SearchPage extends WebDriverManager {

    private WebDriver driver;

    @FindBy(id = "firstHeading")
    WebElement searchHeadder;

    @FindBy(xpath = "//a[@hreflang=\"de\" and @class=\"interlanguage-link-target\"]")
    WebElement germanLangLink;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getSearchHeadder() {
        return searchHeadder.getAttribute("innerHTML");
    }

    public void clickLanguageLink(String lang) {
        if (lang.equalsIgnoreCase("German"))
            germanLangLink.click();
        else {
            throw new NotImplementedException();
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean existsEnlishLinkElement() {
        try {
            driver.findElement(By.xpath("//a[@hreflang=\"en-simple\" and @class=\"interlanguage-link-target\"]"));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
