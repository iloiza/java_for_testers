package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUser() {
        var username = CommonFunctions.randomString(8);
        var password = "password";
        var email = String.format("%s@localhost", username);
        app.jamesCli().addUser(email, password);
        app.user().userRegistration(username, email);
        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        var url = app.mail().extractLink(messages);
        app.mail().clickRegistrationLink(url);
        app.user().confirmAccount(username, password);
            app.http().login(username, password);
            Assertions.assertTrue(app.http().isLoggedIn());
        }

        @Test
        void canRegisterUserApi() {
            var username = CommonFunctions.randomString(8);
            var password = "password";
            var email = String.format("%s@localhost", username);
            app.jamesApi().addUser(email, password);
            app.user().userRegistration(username, email);
            var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
            var url = app.mail().extractLink(messages);
            app.mail().clickRegistrationLink(url);
            app.user().confirmAccount(username, password);
            app.http().login(username, password);
            Assertions.assertTrue(app.http().isLoggedIn());
        }

}


