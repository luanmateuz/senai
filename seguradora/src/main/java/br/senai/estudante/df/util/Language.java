package br.senai.estudante.df.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {

    public static final Locale DEFAULT = Locale.getDefault();
    private static ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("language", Language.DEFAULT);
    }

    private Language() {
        throw new IllegalStateException("Utility class");
    }

    public static String text(String text) {
        return bundle.getString(text);
    }

}
