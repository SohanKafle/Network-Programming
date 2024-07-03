//Download a web page with the correct character set

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GetContentType {
    public static void main(String[] args) {
        System.out.println("Enter URL:");
        Scanner input = new Scanner(System.in);
        String myurl = input.nextLine();
        try {
            // set default encoding
            String encoding = "ISO-8859-1";
            URL u = new URL(myurl);
            URLConnection uc = u.openConnection();
            String contentType = uc.getContentType();
            int encodingStart = contentType.indexOf("charset=");
            if (encodingStart != -1) {
                encoding = contentType.substring(encodingStart + 8);
            }
            InputStream in = new BufferedInputStream(uc.getInputStream());
            Reader r = new InputStreamReader(in, encoding);
            int c;
            while ((c = r.read()) != -1) {
                System.out.print((char) c);
            }
            r.close();
        } catch (MalformedURLException ex) {
            System.err.println(myurl + " is not a parseable URL");
        } catch (UnsupportedEncodingException ex) {
            System.err.println(
                    "Server sent an encoding Java does not support: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}