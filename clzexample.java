//Write a java program to accept the url from user and encode the url and display encoded url string and then decode the encoded url and display the decoded url.

import java.net.URLEncoder;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
public class clzexample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a URL: ");
        String inputURL = scanner.nextLine();

        try {
            // Encode the URL
            String encodedURL = URLEncoder.encode(inputURL, StandardCharsets.UTF_8.toString());
            System.out.println("Encoded URL: " + encodedURL);

            // Decode the URL
            String decodedURL = URLDecoder.decode(encodedURL, StandardCharsets.UTF_8.toString());
            System.out.println("Decoded URL: " + decodedURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    
}

}
