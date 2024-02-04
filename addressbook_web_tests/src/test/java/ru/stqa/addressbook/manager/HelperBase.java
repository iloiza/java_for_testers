package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;


public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }


    protected void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    protected void attach(By locator, String file) {
        manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }


    protected void submitItemModification() {
        click(By.name("update"));
    }

    public void submitItemCreation() { click(By.name("submit")); }

    public void waiting() {
        manager.driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
    }

    public void openHomePage() {
       manager.driver.get("http://localhost/addressbook/");
        manager.driver.navigate().refresh();
    }
}
