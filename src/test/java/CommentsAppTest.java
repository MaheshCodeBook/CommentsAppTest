import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.Duration;


public class CommentsAppTest {
    public WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    int commentsCount;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Downloads\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        driver.get("https://qacommentsapp.ccbp.tech/");
        commentsCount = 0;
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void testAppUI(){
        Assert.assertTrue(homePage.appImage().isDisplayed(),"App image is not displayed");
        Assert.assertEquals(homePage.appHeading(),"Comments","Heading text does not match");
        Assert.assertEquals(homePage.appDescription(),"Say something about 4.0 Technologies","Description text does not match");
        Assert.assertEquals(homePage.commentsCountText(),"0Comments","Comments count text does not match");
    }

    public Object[][] dataset = {
            { "John", "Innovative and interconnected systems." },
            { "Alice", "Smart factories, improved efficiency." },
            { "Robert", "AI-powered automation, limitless potential." },
            { "Harry", "Data-driven decision-making, game-changer." },
            { "Bob", "IoT revolutionizing daily life." }
    };

    @DataProvider
    public Object[][] commentsData() {
        return dataset;
    }

    public void addCommentsData() {
        for (Object[] data : dataset) {
            String username = String.valueOf(data[0]);
            String comment = String.valueOf(data[1]);
            homePage.addComment(username, comment);
            commentsCount++;
        }
    }

    @Test(priority = 2)
    public void testCommentsCount(){
        for (Object[] data : dataset){
            String username = String.valueOf(data[0]);
            String comment = String.valueOf(data[1]);
            homePage.addComment(username,comment);
            commentsCount++;

            int actualCount = homePage.commentCount();
            Assert.assertEquals(actualCount,commentsCount,"Comments does not match");
        }
    }

    @Test(priority = 3,dataProvider = "commentsData")
    public void testCommentsInitial(String username,String comment){
        homePage.addComment(username,comment);

        String initial = homePage.initialText();
        Assert.assertEquals(initial, username.substring(0, 1).toUpperCase(), "Initial do not match");
    }




}
