package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }


    protected void type(By locator, String text) {
        click(locator);
        manager.driver().findElement(locator).clear();
        manager.driver().findElement(locator).sendKeys(text);
    }
    protected void click(By locator) {
        manager.driver().findElement(locator).click();
    }

    public void waiting() {
        manager.driver().manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
    }

    public boolean isElementPresent(By locator) {
         return manager.driver().findElements(locator).size() > 0;
    }

    public void clickRegistrationLink(String url){
        manager.driver().get(url);
        //manager.driver().close();
    }

}
