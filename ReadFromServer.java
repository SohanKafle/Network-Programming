// 14. Write a program for reading data from server.

import java.io.*;
import java.net.*;

public class ReadFromServer {
    public static void main(String[] args) {
        // Define the URL you want to connect to
        String urlString = "https://sohankafle.com.np/";

        try {
            // Create a URL object
            URL url = new URL(urlString);
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            try (InputStream in = new BufferedInputStream(connection.getInputStream());
                 Reader reader = new InputStreamReader(in)) {
                int c;
                while ((c = reader.read()) != -1) {
                    System.out.print((char) c);
                }
            }

        } catch (MalformedURLException e) {
            System.err.println("The URL is not valid: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }
}
