package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase{
    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void userRegistration(String username, String email) throws InterruptedException {
        click(By.linkText("Signup for a new account"));
        type(By.id("username"), username);
        type(By.id("email-field"), email);
        Thread.sleep(5000);
        click(By.cssSelector("input[type='submit']"));
    }

    public void confirmAccount(String username, String password) {
        type(By.id("realname"), username);
        type(By.id("password"), password);
        type(By.id("password-confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
