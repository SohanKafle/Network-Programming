import java.net.URL;
import java.net.MalformedURLException;

public class URLPartsDemo {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a URL as a command-line argument.");
            return;
        }

        String urlString = args[0];
        try {
            URL url = new URL(urlString);

            // Display each part of the URL
            System.out.println("URL: " + url.toString());
            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Host: " + url.getHost());
            System.out.println("Port: " + (url.getPort() == -1 ? "Default" : url.getPort()));
            System.out.println("Path: " + (url.getPath().isEmpty() ? "/" : url.getPath()));
            System.out.println("Query: " + (url.getQuery() == null ? "None" : url.getQuery()));
            System.out.println("Fragment: " + (url.getRef() == null ? "None" : url.getRef()));

        } catch (MalformedURLException e) {
            System.err.println("Error parsing URL: " + e.getMessage());
        }
    }
}
