// 16. Write a program to set ifModifiedSince to 24 Hours prior to Now.

import java.io.*;
import java.net.*;
import java.util.*;

public class ifModifiedSince {
    public static void main(String[] args) {
        // Initialize a Date object with the current date and time
        Date today = new Date();
        long millisecondsPerDay = 24 * 60 * 60 * 1000;

        // Define the URL you want to test
        String[] urls = {"https://www.facebook.com"};

        for (String urlString : urls) {
            try {
                URL u = new URL(urlString);
                URLConnection uc = u.openConnection();
                System.out.println("Original if modified since: " + new Date(uc.getIfModifiedSince()));

                // Set If-Modified-Since to 24 hours ago
                uc.setIfModifiedSince(today.getTime() - millisecondsPerDay);
                System.out.println("Will retrieve file if it's modified since " + new Date(uc.getIfModifiedSince()));

                try (InputStream in = new BufferedInputStream(uc.getInputStream())) {
                    Reader r = new InputStreamReader(in);
                    int c;
                    while ((c = r.read()) != -1) {
                        System.out.print((char) c);
                    }
                    System.out.println();
                }

            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}
