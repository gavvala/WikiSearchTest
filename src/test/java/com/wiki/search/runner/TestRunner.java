package com.wiki.search.runner;

        import cucumber.api.CucumberOptions;
        import cucumber.api.junit.Cucumber;
        import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(  monochrome = true,
        tags = "@SmokeTest",
        features = "src/test/java/com/wiki/search/feature/",
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        glue = "com.wiki.search.steps" )
public class TestRunner {
}
