package server;

import org.testng.annotations.Test;


public class StartTestMart {
	
	@Test()
	public void startServer() {
    	LocalServer.start(9090, "resources/app");  // ✅ matches your actual folder
        System.out.println("Open this URL: http://localhost:9090/testmart.html");
        System.out.println("Press Ctrl+C in the console to stop.");
    }

}