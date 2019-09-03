package com.wiki.search.pages;

import com.wiki.search.service.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class WikiPage extends WebDriverManager {

    private WebDriver driver;

    @FindBy(xpath = "//button[@class=\"pure-button pure-button-primary-progressive\" and @type=\"submit\"]")
    WebElement buttonSearch;

    @FindBy(id = "searchInput")
    WebElement inputSearch;

    @FindBy(id = "searchLanguage")
    WebElement searchLanguage;

    public WikiPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setSearchTest(String searchText) {
        inputSearch.sendKeys(searchText);
    }

    public SearchPage clickSearchButton() {
        buttonSearch.click();
        return new SearchPage(driver);
    }

    public void setSearchLanguage(String language) {
        Select lang = new Select(searchLanguage);
        lang.selectByValue(language);
    }

    /*public Boolean validateLanguageExixts (String language){
        Select langDropDown = new Select(searchLanguage);
        Boolean isExists = false;
        List<WebElement> options = langDropDown.getOptions();
        for (WebElement item : options) {
            if (item.getText().equalsIgnoreCase(language)) {
                isExists = true;
                break;
            }
        }
        return isExists;
    }*/

    public String getPageTitle() {
        return driver.getTitle();
    }

}
