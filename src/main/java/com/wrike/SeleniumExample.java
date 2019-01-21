package com.wrike;

import com.wrike.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class SeleniumExample {

    private SeleniumConfig config;
    private String url = "http://www.wrike.com/";

    public SeleniumExample() {
        config = new SeleniumConfig();
        config.getDriver().get(url);
    }

    public void closeWindow() {
        this.config.getDriver().close();
    }

    private String generateString(){
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder(10);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        } return sb.toString();
    }

    public String goToAccountCreation() throws TimeoutException, NoSuchElementException{
        config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Enter your business email'])[2]/following::button[1]")).click();
        config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Enter your business email'])[4]/following::input[1]")).sendKeys(generateString() + "wpt@wriketask.qaa");
        config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Enter your business email'])[4]/following::button[1]")).click();
        WebDriverWait wait = new WebDriverWait(config.getDriver(),10);
        //there only was one accident when I got a List of 1 <iframe> element instead of 2 (even though there were 2 elements in the browser),
        //but the following if/else construction is made to protect from such accidents
        if( config.getDriver().findElements(By.tagName("iframe")).size() > 1){
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(config.getDriver().findElements(By.tagName("iframe")).get(1)));
        } else {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(config.getDriver().findElement(By.tagName("iframe"))));
        }
        WebElement button = config.getDriver().findElement(By.className("RveJvd"));
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
        config.getDriver().switchTo().defaultContent();
        return this.config.getDriver().getTitle();
    }

    public boolean submitAnswers(){
        try {
            WebDriverWait wait = new WebDriverWait(config.getDriver(),10);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Help us provide you the best possible experience:'])[1]/following::button[1]")));
            int random = (int)(Math.random());
            switch(random) {
                case 0:
                    config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Help us provide you the best possible experience:'])[1]/following::button[1]")).click();
                    break;
                case 1:
                    config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Very interested'])[1]/following::button[1]")).click();
                    break;
            }
            random = (int)(Math.random() * 4);
            switch(random) {
                case 0:
                    config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Just looking'])[1]/following::button[1]")).click();
                    break;
                case 1:
                    config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Just looking'])[1]/following::button[2]")).click();
                    break;
                case 2:
                    config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Just looking'])[1]/following::button[3]")).click();
                    break;
                case 3:
                    config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Just looking'])[1]/following::button[4]")).click();
                    break;
                case 4:
                    config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Just looking'])[1]/following::button[5]")).click();
                    break;
            }
            random = (int)(Math.random() * 2);
            switch(random) {
                case 0:
                    config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Just looking'])[1]/following::button[6]")).click();
                    break;
                case 1:
                    config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Yes'])[1]/following::button[1]")).click();
                    break;
                case 2:
                    config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='No'])[1]/following::button[1]")).click();
                    config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='No'])[1]/following::input[2]")).clear();
                    config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='No'])[1]/following::input[2]")).sendKeys(generateString());
                    break;
            }
            config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Other'])[1]/following::button[1]")).click();
            //we can be sure that the answers are submitted when the 'submit' button is invisible
            wait = new WebDriverWait(config.getDriver(), 10);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Just looking'])[1]/following::button[6]")));
        } catch (TimeoutException e) {
            return false;
        } catch (NoSuchElementException e) {
            return false;
        } return true;
    }

    public boolean resendEmail(){
        try {
            config.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='contact us'])[2]/following::button[1]")).click();
            //we can be sure that the email is send when the button is invisible
            WebDriverWait wait = new WebDriverWait(config.getDriver(),10);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='contact us'])[2]/following::button[1]")));
        } catch (TimeoutException e) {
            return false;
        } catch (NoSuchElementException e) {
            return false;
        } return true;
    }

    public String checkTwitter()throws NoSuchElementException{
        return config.getDriver().findElement(By.cssSelector(".wg-footer__social-link")).getAttribute("href");
    }

}