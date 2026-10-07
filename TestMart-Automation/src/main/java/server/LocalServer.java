package server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Serves the TestMart single-file app over HTTP so Selenium gets a real
 * http:// URL instead of file://.
 *
 * Usage in a @BeforeSuite / @BeforeClass:
 *   LocalServer.start(8080, "src/test/resources/app");
 *   String baseUrl = "http://localhost:8080/testmart.html";
 *
 * Usage in @AfterSuite:
 *   LocalServer.stop();
 */
public final class LocalServer {

    private static HttpServer server;

    private LocalServer() {}

    public static void start(int port, String appDirectory) {
        try {
            Path root = Paths.get(appDirectory).toAbsolutePath();
            server = HttpServer.create(new InetSocketAddress(port), 8080);
            server.createContext("/", staticFileHandler(root));
            server.setExecutor(null);
            server.start();
            System.out.println("TestMart served at http://localhost:" + port + "/testmart.html");
        } catch (IOException e) {
            throw new RuntimeException("Could not start local server on port " + port, e);
        }
    }

    public static void stop() {
        if (server != null) {
            server.stop(0);
            System.out.println("TestMart local server stopped");
        }
    }

    private static HttpHandler staticFileHandler(Path root) {
        return exchange -> {
            String requested = exchange.getRequestURI().getPath();
            if (requested.equals("/")) requested = "/testmart.html";
            Path file = root.resolve(requested.substring(1)).normalize();

            if (!file.startsWith(root) || !Files.exists(file) || Files.isDirectory(file)) {
                byte[] body = "404 Not Found".getBytes();
                exchange.sendResponseHeaders(404, body.length);
                try (OutputStream os = exchange.getResponseBody()) { os.write(body); }
                return;
            }

            String contentType = file.toString().endsWith(".html") ? "text/html" : "application/octet-stream";
            byte[] bytes = Files.readAllBytes(file);
            exchange.getResponseHeaders().add("Content-Type", contentType);
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
        };
    }
}