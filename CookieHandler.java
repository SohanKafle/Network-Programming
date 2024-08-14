// 11. Write a simple program for implementation of cookiepolicy

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;

public class CookieHandler {
    public static void main(String[] args) {
        try {
            // Set the default CookieManager with a custom CookiePolicy
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            java.net.CookieHandler.setDefault(cookieManager);

            // Create a URL object
            URL url = new URL("http://www.google.com");

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response
            //System.out.println("Response: " + response.toString());
			System.out.println("URL: ww.google.com");

            // Print the cookies stored in the CookieManager
            System.out.println("Cookies:");
            cookieManager.getCookieStore().getCookies().forEach(cookie -> {
                System.out.println(cookie);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setDefault(CookieManager cookieManager) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDefault'");
    }
}
