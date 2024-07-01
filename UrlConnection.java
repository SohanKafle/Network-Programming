import java.io.*;
import java.net.*;
import java.util.*;

public class UrlConnection {
    public static void main(String[] args) {
        System.out.println("Enter URL:");
        Scanner input = new Scanner(System.in);
        String myurl = input.nextLine();

        try {
            // Open the URLConnection for reading
            URL u = new URL(myurl);
            URLConnection uc = u.openConnection();
            try (InputStream raw = uc.getInputStream()) { // autoclose
                InputStream buffer = new BufferedInputStream(raw);
                // chain the InputStream to a Reader
                Reader reader = new InputStreamReader(buffer);
                int c;
                while ((c = reader.read()) != -1) {
                    System.out.print((char) c);
                }
            }
        } catch (MalformedURLException ex) {
            System.err.println(myurl + " is not a parseable URL");
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }
}
