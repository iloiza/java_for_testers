package manager;

import org.openqa.selenium.By;

public class LoginHelper {
    private final ApplicationManager manager;

    public LoginHelper(ApplicationManager manager){

        this.manager = manager;
    }


    void login(String user, String pass) {
        manager.driver.findElement(By.name("user")).sendKeys(user);
        manager.driver.findElement(By.name("pass")).sendKeys(pass);
        manager.driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
    }
}
