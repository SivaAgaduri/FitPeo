package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseDriverClass {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public BaseDriverClass(WebDriver driver, WebDriverWait wait) {
        BaseDriverClass.driver = driver;
        BaseDriverClass.wait = wait;
    }

    protected void waitUntilElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void scrollToTheElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) BaseDriverClass.driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void waitForTimeOut(Duration duration){
        try{
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}