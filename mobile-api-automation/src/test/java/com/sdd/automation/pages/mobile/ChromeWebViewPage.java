
package com.sdd.automation.pages.mobile;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChromeWebViewPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    private final By introduction = By.xpath(
        "//*[@text='Hello, can you please tell me your name?']"
    );

    private final By nameField = By.id("name_input");

    private final By carOption = By.xpath(
        "//*[@text='Volvo' or @text='Mercedes']"
    );

    private final By submitButton = By.xpath(
        "//*[@text='Send me your name!']"
    );

    public ChromeWebViewPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(15)
        );
    }

    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[@text='Hello, can you please tell me your name?']")
        ));

        By nameField = By.xpath(
            "//*[@class='android.widget.EditText' and @resource-id='name_input']"
        );

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(nameField)
        );
    }

    public void enterName(String name) {
        By nameField = By.xpath(
            "//*[@class='android.widget.EditText' and @resource-id='name_input']"
        );

        WebElement field = wait.until(
            ExpectedConditions.elementToBeClickable(nameField)
        );

        field.click();
        field.clear();
        field.sendKeys(name);
    }

    public String getIntroductionText() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(introduction)
        ).getText();
    }



    public void selectCar(String car) {
        By carControl = By.xpath(
            "//*[@text='Volvo' or @content-desc='Volvo']"
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(carControl)
        ).click();

        By carOption = By.xpath(
            "//*[@text='" + car + "' or @content-desc='" + car + "']"
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(carOption)
        ).click();
    }

    public void submitName() {
        wait.until(
            ExpectedConditions.elementToBeClickable(submitButton)
        ).click();
    }

    public String getPageText() {
        return driver.getPageSource();
    }

    public String getConfirmationText() {
        return getPageText();
    }

    public String getSelectedCar() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@text='Volvo']")
            )
        ).getText();
    }

    public void clickHereLink() {
        By hereLink = By.xpath("//*[@content-desc='here']");

        wait.until(
            ExpectedConditions.elementToBeClickable(hereLink)
        ).click();
    }
}