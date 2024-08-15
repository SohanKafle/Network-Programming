import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RetrieveData {
    public static void main(String[] args) {
        try {
            // Specify the URL you want to retrieve data from
            String urlString = "https://sohankafle.com.np";
            URL url = new URL(urlString);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method (GET, POST, etc.)
            connection.setRequestMethod("GET");

            // Check the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                // Create an InputStreamReader and BufferedReader to read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                // Read the input line by line
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                // Close the BufferedReader
                in.close();

                // Print the retrieved content
                System.out.println("Response Content: ");
                System.out.println(content.toString());
            } else {
                System.out.println("GET request failed");
            }

            // Disconnect the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
