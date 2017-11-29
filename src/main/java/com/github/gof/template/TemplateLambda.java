package com.github.gof.template;

import java.util.function.Consumer;

//Template design pattern is refactored using a Consumer
public class TemplateLambda {

    public static void execute(Consumer<Resource> consumer) {
        Resource resource = new Resource();
        try {
            consumer.accept(resource);
        } finally {
            resource.disposeResource();
        }
    }

    public static void main(String[] args) {
        execute(resource -> {
            resource.employResource();
            resource.useResource();
        });
    }
}
