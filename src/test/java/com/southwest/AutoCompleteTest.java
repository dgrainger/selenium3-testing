package test.java.com.southwest;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AutoCompleteTest {
    WebDriver driver;
    WebDriverWait wdw;
    String browser = "firefox";

    protected void screenshot(String prefix) {
        TakesScreenshot tss = (TakesScreenshot)driver;
        File file = tss.getScreenshotAs(OutputType.FILE);
        try {
            String filename = String.format("%s-%s.png", prefix, browser);
            FileUtils.copyFile(file, new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {
        if(browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "./geckodriver");
            DesiredCapabilities dc = DesiredCapabilities.firefox();
            driver = new FirefoxDriver(dc);
        } else if(browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "./chromedriver");
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            driver = new ChromeDriver(dc);
        }
        wdw = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();

    }

    @Test
    public void autoCompleteFromInput() throws InterruptedException {
        driver.get("https://www.southwest.com/flight/?clk=GSUBNAV-AIR-BOOK");
        WebElement fromInput = driver.findElement(By.cssSelector("#originAirport_displayed"));
        wdw.until(ExpectedConditions.elementToBeClickable(fromInput));
        fromInput.click();
        wdw.until(ExpectedConditions.elementToBeClickable(fromInput));
        fromInput.sendKeys("new york");
        By resultsLocator = By.cssSelector("div.ac_results li");
        wdw.until(ExpectedConditions.numberOfElementsToBeMoreThan(resultsLocator, 3));
        List<WebElement> suggestions = driver.findElements(resultsLocator);
        for (WebElement suggestion : suggestions) {
            String s = suggestion.getText();
            System.out.println(s);
            if(s.equals("New York/Newark, NJ - EWR")) {
                suggestion.click(); // does not work with Firefox???
                Thread.sleep(2000);
                break;
            }
        }
        screenshot("autoCompleteFromInput");
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
