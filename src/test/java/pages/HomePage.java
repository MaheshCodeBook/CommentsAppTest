package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;


public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "image")
    WebElement appImageEle;

    @FindBy(className = "app-heading")
    WebElement appHeadingEle;

    @FindBy(className = "form-description")
    WebElement appDescriptionEle;

    @FindBy(className = "heading")
    WebElement commentsCountTextEle;

    @FindBy(className = "name-input")
    WebElement userNameInputEle;

    @FindBy(className = "comment-input")
    WebElement commentInputEle;

    @FindBy(className = "add-button")
    WebElement commentButtonEle;

    @FindBy(className = "comments-count")
    WebElement commentsContEle;

    @FindBy(className = "initial")
    WebElement initialTextElementEle;


    public WebElement appImage(){
        return appImageEle;
    }

    public String appHeading(){
        return appHeadingEle.getText();
    }

    public String appDescription(){
        return appDescriptionEle.getText();
    }

    public String commentsCountText(){
        return commentsCountTextEle.getText();
    }

    public void userNameInput(String username){
        userNameInputEle.sendKeys(username);
    }

    public void commentInput(String comment){
        commentInputEle.sendKeys(comment);
    }

    public void clickOnAddComment(){
        commentButtonEle.click();
    }

    public void addComment(String username,String comment){
        userNameInput(username);
        commentInput(comment);
        clickOnAddComment();
    }

    public int commentCount(){
        return Integer.parseInt(commentsContEle.getText());
    }

    public String initialText() {
        return initialTextElementEle.getText();
    }





}
