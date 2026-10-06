package com.testmart.utils;

public class StartTestMart {
    public static void main(String[] args) {
    	LocalServer.start(9090, "resources/app");  // ✅ matches your actual folder
        System.out.println("Open this URL: http://localhost:9090/testmart.html");
        System.out.println("Press Ctrl+C in the console to stop.");
    }
}