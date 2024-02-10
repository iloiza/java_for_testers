package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUser(String username){
        var email = String.format("%s@localhost", username);
        //создать пользователя(адрес) на почтовом сервере (JamesHelper)
        //заполняем форму создания пользователя через браузер и отправляем (браузер) -- создать класс помощник, в котором будут совершаться эти действияя
        //ждем почту (MailHelper)
        //извлекаем ссылку из письма
        //проходим по ссылке и завершаем регистрацию пользователя (браузер)
        //проверяем, что пользователь может залогиниться (HttpSessionHelper)

    }
}
