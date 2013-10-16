/****************************************************************************************************************
 *
 *  Copyright (c) 2013 OCLC, Inc. All Rights Reserved.
 *
 *  OCLC proprietary information: the enclosed materials contain
 *  proprietary information of OCLC, Inc. and shall not be disclosed in whole or in 
 *  any part to any third party or used by any person for any purpose, without written
 *  consent of OCLC, Inc.  Duplication of any portion of these materials shall include this notice.
 *
 ******************************************************************************************************************/

package nl.molnet.app.selenium;

import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Selenium setup structure and helpers
 * 
 */
public class AbstractSeleniumHelper extends TestCase {
    protected static WebDriver driver = null;
    protected boolean acceptNextAlert = true;
    protected StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
//        driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        // reusing the driver = faster
        if (driver == null) {
        	System.out.println("open driver");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        } else {
            driver.manage().deleteAllCookies();
        }
        System.out.println(driver.toString());
    }

    /**
     * 
     * @param url E.g. http://localhost:8080/CustomWebapp/index.html?name=madvoc
     */
    public void getUrl(String url) {
        driver.get(url);
    }

    @After
    public void tearDown() throws Exception {
        //driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
        verificationErrorString = ""; // reset string (used for reusing the driver)
    }

	@AfterClass
	public static void close() {
		if (driver != null) {
			System.out.println("close driver");
			driver.quit();
		}
	}

    /**
     * Check is element exists
     * @param by search element
     * @return true if element exists, otherwise return false
     */
    public boolean isElementPresent(By by) {
        return driver.findElements(by).size() > 0;
    }

    public String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alert.getText();
        } finally {
            acceptNextAlert = true;
        }
    }
}
