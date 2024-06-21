//Create InetAddress objects for “www.ibiblio.org ”and “helios.ibiblio.org ”and then tell whether they’re same machine or not.

import java.net.*;

public class IBiblioAliases {
    public static void main(String args[]) {
        try {
            InetAddress ibiblio = InetAddress.getByName("www.facebook.com");
            InetAddress helios = InetAddress.getByName("www.fb.com");
            if (ibiblio.equals(helios)) {
                System.out.println("www.facebook.com is the same as www.fb.com");
            } else {
                System.out.println("www.facebook.com is not the same as www.fb.com");
            }
        } catch (UnknownHostException ex) {
            System.out.println("Host lookup failed.");
        }
    }
}