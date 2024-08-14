// 2. Create InetAddress objects for “www.ibiblio.org ”and “helios.ibiblio.org ”and then tell whether they’re same machine or not.

import java.net.*;
import java.util.Arrays;

    public class IBiblioAliases {
        public static void main(String[] args) {
            try {
                // Create InetAddress objects for both hostnames
                InetAddress address1 = InetAddress.getByName("www.fb.com");
                InetAddress address2 = InetAddress.getByName("www.facebook.com");
    
                // Print the IP addresses
                System.out.println("www.fb.com: " + address1.getHostAddress());
                System.out.println("www.facebook.com: " + address2.getHostAddress());
    
                // Check if they are the same
                if (address1.equals(address2)) {
                    System.out.println("Both hostnames are the same.");
                } else {
                    System.out.println("The hostnames are not same.");
                }
            } catch (UnknownHostException e) {
                System.out.println("Unknown host exception: " + e.getMessage());
            }
        }
    }
    