package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;

public class UserRegistrationTests extends TestBase{

    DeveloperMailUser user;

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
            //var username = CommonFunctions.randomString(8);
            var password = "password";

            user = app.developerMail().addUser();
            var email = String.format("%s@developermail.com", user.name());
//            app.user().userRegistration(user, email);
//            var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
//            var url = app.mail().extractLink(messages);
//            app.mail().clickRegistrationLink(url);
//            app.user().confirmAccount(user, password);
//            app.http().login(user, password);
//            Assertions.assertTrue(app.http().isLoggedIn());
        }

        @AfterEach
    void deleteMailUser(){
            app.developerMail().deleteUser(user);
        }

}


