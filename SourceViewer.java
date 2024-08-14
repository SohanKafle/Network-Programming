// 17. Write a program to demonstrate a sourceviewer that includes the response code and message.

import java.io.*;
import java.net.*;

public class SourceViewer {
    public static void main(String[] args) {
        String[] urls = {"https://google.com"};

        for (String urlString : urls) {
            try {
                // Open the URLConnection for reading
                URL u = new URL(urlString);
                HttpURLConnection uc = (HttpURLConnection) u.openConnection();
                int code = uc.getResponseCode();
                String response = uc.getResponseMessage();
                System.out.println("HTTP/1.x " + code + " " + response);

                for (int j = 1;; j++) {
                    String header = uc.getHeaderField(j);
                    String key = uc.getHeaderFieldKey(j);
                    if (header == null || key == null) break;
                    System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
                }

                System.out.println();

                try (InputStream in = new BufferedInputStream(uc.getInputStream())) {
                    // chain the InputStream to a Reader
                    Reader r = new InputStreamReader(in);
                    int c;
                    while ((c = r.read()) != -1) {
                        System.out.print((char) c);
                    }
                }

            } catch (MalformedURLException ex) {
                System.err.println(urlString + " is not a parseable URL");
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}
