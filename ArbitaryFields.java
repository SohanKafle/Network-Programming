// 15. Write a program for retrieving Arbitrary Fields.

import java.io.*;
import java.net.*;

public class ArbitaryFields {
    public static void main(String[] args) {
        // Define the URL you want to test
        String[] urls = {"https://www.lict.edu.np"};

        for (String urlString : urls) {
            try {
                // Create a URL object from the string
                URL u = new URL(urlString);
                // Open a connection to the URL
                URLConnection uc = u.openConnection();
                // Print all the headers
                for (int j = 1;; j++) {
                    String header = uc.getHeaderField(j);
                    if (header == null) break;
                    System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
                }
            } catch (MalformedURLException ex) {
                System.err.println(urlString + " is not a URL I understand.");
            } catch (IOException ex) {
                System.err.println(ex);
            }
            System.out.println();
        }
    }
}
