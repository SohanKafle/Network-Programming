//Testing the characteristics of an IP address.
import java.net.*;
import java.util.*;

public class IPCharacteristics {
    public static void main(String[] args) {
        System.out.println("Enter Domain Name:");
        Scanner input = new Scanner(System.in);
        String Domain = input.nextLine();
        try {
            InetAddress address = InetAddress.getByName(Domain);
            if (address.isAnyLocalAddress()) {
                System.out.println(address + " is a wildcard address.");
            }
            if (address.isLoopbackAddress()) {
                System.out.println(address + " is loopback address.");
            }
            if (address.isLinkLocalAddress()) {
                System.out.println(address + " is a link-local address.");
            } else if (address.isSiteLocalAddress()) {
                System.out.println(address + " is a site-local address.");
            } else {
                System.out.println(address + " is a global address.");
            }
            if (address.isMulticastAddress()) {
                if (address.isMCGlobal()) {
                    System.out.println(address + " is a global multicast address.");
                } else if (address.isMCOrgLocal()) {
                    System.out.println(address + " is an organization wide multicast address.");
                } else if (address.isMCSiteLocal()) {
                    System.out.println(address + " is a site wide multicastaddress.");
                } else if (address.isMCLinkLocal()) {
                    System.out.println(address + " is a subnet wide multicastaddress.");
                } else if (address.isMCNodeLocal()) {
                    System.out.println(address + " is an interface-local multicast address.");
                } else {
                    System.out.println(address + " is an unknown multicastaddress type.");
                }
            } else {
                System.out.println(address + " is a unicast address.");
            }
        } catch (UnknownHostException ex) {
            System.err.println("Could not resolve " + Domain);
        }
    }
}