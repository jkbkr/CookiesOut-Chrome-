package com.my.projects.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

//Solves >>> https://www.kdomivolal.eu/
public class CookieBanner_1 extends Constructor {

    public CookieBanner_1(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@class='fc-button fc-cta-manage-options fc-secondary-button']")
    WebElement manageOptions;

    @FindBy(xpath = "//p[@class='fc-navigation-button-label']")
    WebElement vendorPreferences;

    //Click on Vendor preferences
    public void moreCookiesOptions(){

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='fc-navigation-button-label']")));
        vendorPreferences.click();

    }

    //Click on Manage options
    public void cookiesOptions(){

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='fc-button fc-cta-manage-options fc-secondary-button']")));
    manageOptions.click();

}

    // Buttons loop >>> unselecting all selected buttons
    public static void buttonsLoop(){
// List of all buttons on the page
        List<WebElement> checkedButtons = driver.findElements(By.xpath("//input[@type='checkbox' and @checked]/following-sibling::span[@class='fc-slider-el']"));
        // Loop through each checked button
        for (WebElement button : checkedButtons) {
            // Check if the button is visible and enabled
            if (button.isDisplayed() && button.isEnabled()) {

                Actions actions = new Actions(driver);
                actions.moveToElement(button).perform();

                button.click();
            }
        }
    }

    //TODO >>> assertion here currently does not work >>> will be solved later
    public static void noButtonsCheckedAssertion(){

        // List of all buttons on the page
        List<WebElement> checkedButtons = driver.findElements(By.xpath("//input[@type='checkbox' and @checked]/following-sibling::span[@class='fc-slider-el']"));

        // Assert none are checked
        boolean anyChecked = false;
        for (WebElement button : checkedButtons) {
            if (button.isEnabled()) {
                anyChecked = true;
                break;
            }
        }

        if (anyChecked) {
            System.out.println("There is a selected button on the page! - Will be solved soon");
            buttonsLoop();
            noButtonsCheckedAssertion();

        } else {
            System.out.println("Assertion passed: No buttons are selected.");
        }
    }
}






