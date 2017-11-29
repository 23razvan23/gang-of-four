package com.github.gof.template;

import java.util.Random;

public class Resource {
    public Resource() {
        System.out.println("Resource created");
    }

    public void useResource() {
        riskyOperation();
        System.out.println("Resource used");
    }

    public void employResource() {
        riskyOperation();
        System.out.println("Resource employed");
    }

    //I want to make sure the resource is disposed; this is the most important step
    public void disposeResource() {
        System.out.println("Resource dispose");
    }

    private void riskyOperation() {
        if (new Random().nextInt(3) == 0) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        Resource resource = new Resource();
        resource.useResource();
        resource.employResource();
        resource.disposeResource();
    }
}
