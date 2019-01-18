package com.wrike;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WrikeTest {

    private static SeleniumExample seleniumExample;
    private static String expectedTitle;
    private static String expectedLink;

    @BeforeClass
    public static void setUp() {
        seleniumExample = new SeleniumExample();
        expectedLink = "https://twitter.com/wrike";
        expectedTitle = "Thank you for choosing Wrike!";
    }

    @AfterClass
    public static void tearDown() {
        seleniumExample.closeWindow();
    }

    @Test
    public void checkRegistration() {
        try{
            assertEquals("Registration is not complete", seleniumExample.goToAccountCreation(), expectedTitle);
        } catch (TimeoutException e){
            fail("Modal window not handled -- TimeoutException caught");
        } catch (NoSuchElementException e){
            fail("Failed to find some element");
        }
        assertTrue("The answers are not submitted.", seleniumExample.submitAnswers());
        assertTrue("The mail is not resend", seleniumExample.resendEmail());
        try {
            assertEquals("The twitter link is wrong", seleniumExample.checkTwitter(), expectedLink);
        } catch (NoSuchElementException e){
            fail("Twitter link not found");
        }
    }
}
