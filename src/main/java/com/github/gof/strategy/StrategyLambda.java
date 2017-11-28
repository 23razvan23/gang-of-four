package com.github.gof.strategy;

import java.util.function.Function;
import java.util.function.Predicate;

public class StrategyLambda {

    //this is the behavior
    public static void publishText(String text, Predicate<String> filter, Function<String, String> format) {
        if (filter.test(text)) {
            System.out.println(format.apply(text));
        }
    }

    public static void main(String[] args) {
        Predicate<String> filter = text -> text.startsWith("ERROR");
        publishText("DEBUG - I'm here", filter, String::toUpperCase);
        publishText("ERROR - something bad happened", filter, String::toUpperCase);
    }
}
