package com.javarush.test.level26.lesson15.big01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Family on 03.01.2017.
 */
public class Language {
    private static ResourceBundle langRes = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "language");
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void chooseLanguage() {
        writeMessage(langRes.getString("before"));
        writeMessage(langRes.getString("choose.lang"));
        outer:
        while (true) {
            String lang = readString();
            switch (lang.toLowerCase()) {
                case "ua":
                    Locale.setDefault(new Locale("ua", "UA"));
                    break outer;
                case "ru":
                    Locale.setDefault(new Locale("ru", "RU"));
                    break outer;
                case "en":
                    Locale.setDefault(Locale.ENGLISH);
                    break outer;
                default:
                    writeMessage(langRes.getString("invalid.data"));
                    continue;
            }
        }
        writeMessage(String.format(langRes.getString("choose.lang.format"), Locale.getDefault().getDisplayLanguage()));

    }

    private static String readString() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private static void writeMessage(String string) {
        System.out.println(string);
    }
}
