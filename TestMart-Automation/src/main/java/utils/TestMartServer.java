package utils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class TestMartServer {

    private static final int PORT = 7070;

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(
                new java.net.InetSocketAddress(PORT), 0);

        server.createContext("/", TestMartServer::handleRequest);

        server.setExecutor(null);
        server.start();

        System.out.println("TestMart Server Started");
        System.out.println("URL: http://localhost:7070/testmart.html");
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {

        String path = exchange.getRequestURI().getPath();

        if (path.equals("/")) {
            path = "/testmart.html";
        }

        Path file = Paths.get(
                "src/test/resources/app" + path
        );

        System.out.println("Requested: " + path);
        System.out.println("Looking for: " + file.toAbsolutePath());

        if (!Files.exists(file)) {

            String response = "404 Not Found: " + path;

            exchange.sendResponseHeaders(404, response.length());

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }

            return;
        }

        byte[] data = Files.readAllBytes(file);

        exchange.getResponseHeaders().set(
                "Content-Type",
                "text/html"
        );

        exchange.sendResponseHeaders(200, data.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(data);
        }
    }
}