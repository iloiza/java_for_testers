package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase{

    public SessionHelper (ApplicationManager manager){
        super(manager);
    }


    public void login(String user, String pass) {
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), pass);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLogetIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }
}
