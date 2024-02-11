package ru.stqa.mantis.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import ru.stqa.mantis.manager.developermail.AddUserResponse;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.io.IOException;
import java.net.CookieManager;

import static ru.stqa.mantis.manager.JamesApiHelper.JSON;

public class DeveloperMailHelper extends HelperBase {
    OkHttpClient client;

    public DeveloperMailHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public DeveloperMailUser addUser() {
        RequestBody body = RequestBody.create("", JSON);
        Request request = new Request.Builder()
                .url("https://www.developermail.com/api/v1/mailbox/")
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            var text = response.body().string();
            var addUserResponse = new ObjectMapper().readValue(text, AddUserResponse.class);
            if (!addUserResponse.success()) {
                throw new RuntimeException(addUserResponse.errors().toString());
            }
            return addUserResponse.result();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteUser(DeveloperMailUser user) {
        RequestBody body = RequestBody.create("", JSON);
        Request request = new Request.Builder()
                .url(String.format("https://www.developermail.com/api/v1/mailbox/%s", user.name()))
                .header("X-MailBoxToken", user.token())
                .delete()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
