package com.wiki.search.steps;


import com.wiki.search.pages.WikiPage;
import com.wiki.search.pages.SearchPage;
import com.wiki.search.service.ConfigFileReader;
import com.wiki.search.service.WebDriverManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.Is;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertThat;

public class WikiSearchStep {
    private WikiPage wikiPage = null;
    private WebDriverManager webDriverManager = null;
    private SearchPage searchPage = null;
    private ConfigFileReader configFileReader;
    private WebDriver driver;

    public WikiSearchStep() {
        configFileReader = new ConfigFileReader();
        webDriverManager = new WebDriverManager();
    }

    @Given("^I navigate to wiki site$")
    public void i_navigate_to_wiki_site() {
        driver = webDriverManager.getDriver();
        wikiPage = new WikiPage(driver);
    }

    @When("^I enter \"([^\"]*)\" search word in search text box$")
    public void i_enter_search_word_in_search_text_box(String searchText) {
        wikiPage.setSearchTest(searchText);
    }

    @When("^I select \"([^\"]*)\" as my search language\\.$")
    public void i_select_as_my_search_language(String lang) {
        if(lang.equalsIgnoreCase("English"))
            lang = "en";
        wikiPage.setSearchLanguage(lang);
    }

    @When("^I click search button$")
    public void i_click_search_button() {
        searchPage = wikiPage.clickSearchButton();
    }

    @Then("^I redirect to search results page successfully$")
    public void i_redirect_to_search_results_page_successfully() {
        String searchPageTitle = searchPage.getPageTitle();
        Assert.assertThat("Invalid Page Title",searchPageTitle, IsEqualIgnoringCase.equalToIgnoringCase("Selenium - Wikipedia"));
    }

    @Then("^validate the search results page title with \"([^\"]*)\" search text$")
    public void validate_the_search_results_page_title_with_search_text(String search) {
        String searchHeader = searchPage.getSearchHeadder();
        Assert.assertEquals("Invalid english search title", searchHeader, search);
    }

    @Then("^I can navigate to \"([^\"]*)\" language search page$")
    public void i_can_navigate_to_German_language_search_page(String lang) {
        if(lang.equalsIgnoreCase("German"))
            searchPage.clickLanguageLink(lang);

        String searchHeader = searchPage.getSearchHeadder();
        Assert.assertEquals("Invalid german search title", searchHeader, "Selen");

    }

    @Then("^I can see english search link$")
    public void i_can_see_english_search_link() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue("English language String not found", searchPage.existsEnlishLinkElement());
        driver.close();
    }


}
