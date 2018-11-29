package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.sql.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.*;

public class Stepdefs {
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Given("^front page is selected$")
    public void front_page_selected() throws Throwable {
        driver.get(baseUrl);
    }

    @When("^list all tips clicked$")
    public void list_all_tips_clicked() throws Throwable {
        WebElement element = driver.findElement(By.name("toVinkit"));
        element.click();
    }

    @Then("^list all view is open$")
    public void list_all_view_is_open() throws Throwable {
        pageHasContent("Lukuvinkit listana");
    }

    @When("^new tip is clicked$")
    public void new_tip_is_clicked() throws Throwable {
        WebElement element = driver.findElement(By.name("toNewVinkki"));
        element.click();
    }

    @When("^submitted read of the tip$")
    public void submitted_read_of_the_tip() throws Throwable {
        WebElement element = driver.findElement(By.name("luettuButton"));
        element.click();
    }
    
    @Then("^new tip view is open$")
    public void new_tip_view_is_open() throws Throwable {
        pageHasContent("uusi vinkki");
    }

    @Given("^new tip view is selected$")
    public void new_tip_view_is_selected() throws Throwable {
        driver.get(baseUrl + "/newVinkki");
    }

    @When("^otsikko \"([^\"]*)\", tekija \"([^\"]*)\", kuvaus \"([^\"]*)\", linkki \"([^\"]*)\" are given$")
    public void otsikko_tekija_kuvaus_linkki_are_given(String otsikko, String tekija, String kuvaus, String linkki) throws Throwable {
        createTip(otsikko, tekija, kuvaus, linkki, "");
    }
    
    @When("^video \"([^\"]*)\" and tags \"([^\"]*)\" are given$")
    public void video_and_tags_are_given(String video, String tags) throws Throwable {
        createTip("tagsTest", "automatic", "test", video, tags);
    }
    
    @When("^wanted tags \"([^\"]*)\" typed and search button clicked$")
    public void tags_give_and_search_clicked(String tags) throws Throwable {
        search(tags);
    }
    
    @Given("^the page displaying all tips is selected$")
    public void the_page_displaying_all_tips_is_selected() throws Throwable {
        driver.get(baseUrl+"/vinkit");
    }

    @When("^heading of the chosen tip is clicked$")
    public void heading_of_the_chosen_tip_is_clicked() throws Throwable {
        WebElement element = driver.findElement(By.name("otsikko"));
        element.click();
    }

    @When("^heading of the tip with otsikko \"([^\"]*)\" is clicked$")
    public void heading_of_the_tip_with_otsikko_is_clicked(String otsikko) throws Throwable {
        WebElement element = driver.findElement(By.xpath("//*[text() = 'tagsTest']")).findElement(By.xpath("./.."));
        element.click();
    }
    
    @Then("^single tip details are displayed on a separate page$")
    public void single_tip_details_are_displayed_on_a_separate_page() throws Throwable {
        pageHasContent("testi");
    }

    @Then("^automatic tag can be found$")
    public void automatic_tag_can_be_found() throws Throwable {
        pageHasContent("test,amazing,video");
    }

    @Then("^result gives found tip \"([^\"]*)\" with tags asked$")
    public void result_gives_found_tips_acording_to_tags(String tipFound) throws Throwable {
        pageHasContent(tipFound);
    }
    
    @Then("^tip is signed read and have returned list view$")
    public void tip_is_signed_read_and_have_returned_list_view() throws Throwable {
        pageHasContent("Lukuvinkit listana");
        pageHasContent(new Date(System.currentTimeMillis()).toString());
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void createTip(String otsikko, String tekija, String kuvaus, String linkki, String tags) {
        assertTrue(driver.getPageSource().contains("uusi vinkki"));
        WebElement element = driver.findElement(By.name("otsikko"));
        element.sendKeys(otsikko);
        element = driver.findElement(By.name("tekija"));
        element.sendKeys(tekija);
        element = driver.findElement(By.name("kuvaus"));
        element.sendKeys(kuvaus);
        element = driver.findElement(By.name("linkki"));
        element.sendKeys(linkki);
        element = driver.findElement(By.name("tagit"));
        element.sendKeys(tags);
        element = driver.findElement(By.name("addVinkki"));
        element.submit();
    }
    
    private void search(String tags) {
        WebElement element = driver.findElement(By.name("etsi"));
        element.sendKeys(tags);
        element = driver.findElement(By.name("action"));
        element.submit();
    }
    
    
    
    /*
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void createUserWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    private void createUserUnValidConfirm(String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("");
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    */
}
