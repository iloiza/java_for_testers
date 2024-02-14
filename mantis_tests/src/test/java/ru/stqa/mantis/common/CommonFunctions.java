package ru.stqa.mantis.common;

import ru.stqa.mantis.model.MailMessage;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(i -> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());
        return result;
    }

    public static String randomNumber() {
        var rnd = new Random();
        var result = "";
            for (int i = 0; i < 11; i++) {
                result = result + (char) ('0' + rnd.nextInt(10));
        }
        return result;
    }
    public static String extractLink(List<MailMessage> messages) {
        String url = null;
        if (!messages.isEmpty()) {
            String text = messages.get(0).content();
            Pattern pattern = Pattern.compile("http://\\S*");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                url = text.substring(matcher.start(), matcher.end());
            }
        }
        return url;
    }

    public static String extractLinkLikeText(String message) {
        if (message != null && !message.isEmpty()) {
            message = message.replaceAll("3D", "");
            message = message.replaceAll("=\\r", "");
            message = message.replaceAll("\\n", "");
            Pattern pattern = Pattern.compile("http://\\S+");
            Matcher matcher = pattern.matcher(message);
            if (matcher.find()) {
                return message.substring(matcher.start(), matcher.end());
            }
        }
        return null;
    }
}
