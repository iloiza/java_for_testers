package ru.stqa.addressbook.generator;

public class Generator {
    public static void main(String[] args) {
        new Generator().run();
    }

    private void run() {
        var data = generate();
        save(data);
    }

    private void save(Object data) {
    }

    private Object generate() {
        return null;
    }
}
