import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.net.URI;
import java.net.URISyntaxException;

public class URLShortener {
    private Map<String, String> shortToLongMap;
    private HttpServer server;

    public URLShortener() throws IOException {
        this.shortToLongMap = new HashMap<>();
        this.server = HttpServer.create(new InetSocketAddress(8000), 0);
    }

    // Method to validate a URL
    private boolean isValidURL(String url) {
        return url != null && !url.trim().isEmpty();
    }

    // Method to shorten a long URL
    public String shortenURL(String longURL) {
        if (!isValidURL(longURL)) {
            return "Invalid long URL!";
        }

        String shortCode = String.valueOf(longURL.hashCode());
        String shortURL = "http://localhost:8000/" + shortCode;
        shortToLongMap.put(shortCode, longURL);
        return shortURL;
    }

    // Method to expand a short URL
    public String expandURL(String shortURL) {
        try {
            URI uri = new URI(shortURL);
            String path = uri.getPath();
            String shortCode = path.substring(1); // Remove leading slash
            String longURL = shortToLongMap.get(shortCode);
            return longURL != null ? longURL : "Invalid short URL!";
        } catch (URISyntaxException e) {
            return "Invalid URL format!";
        }
    }

    // Method to start the HTTP server
    public void startServer() {
        server.createContext("/", new RequestHandler());
        server.setExecutor(null); // Create a default executor
        server.start();
    }

    // Method to stop the HTTP server
    public void stopServer() {
        server.stop(0);
    }

    // Handler for handling HTTP requests
    private class RequestHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestPath = exchange.getRequestURI().getPath();
            try {
                String expandedURL = expandURL(requestPath);
                if (expandedURL.startsWith("http://") || expandedURL.startsWith("https://")) {
                    exchange.getResponseHeaders().set("Location", expandedURL);
                    exchange.sendResponseHeaders(302, -1); // HTTP 302: Found
                } else {
                    // Serve error message for invalid URLs
                    String response = "<html><body><h1>" + expandedURL + "</h1></body></html>";
                    exchange.sendResponseHeaders(400, response.getBytes().length);
                    exchange.getResponseBody().write(response.getBytes());
                }
            } catch (Exception e) {
                // Handle unexpected exceptions
                String response = "<html><body><h1>Internal Server Error</h1></body></html>";
                exchange.sendResponseHeaders(500, response.getBytes().length);
                exchange.getResponseBody().write(response.getBytes());
            } finally {
                exchange.close();
            }
        }
    }

    // Main method for CLI
    public static void main(String[] args) throws IOException {
        URLShortener shortener = new URLShortener();
        shortener.startServer();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the long URL to shorten: ");
                    String longURL = scanner.nextLine();
                    String shortURL = shortener.shortenURL(longURL);
                    System.out.println("Shortened URL: " + shortURL);
                    break;
                case 2:
                    System.out.print("Enter the short URL to expand: ");
                    String inputURL = scanner.nextLine();
                    String expandedURL = shortener.expandURL(inputURL);
                    System.out.println("Expanded URL: " + expandedURL);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    shortener.stopServer();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
