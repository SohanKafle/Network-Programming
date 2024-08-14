// 4. Write a simple program to illustrate spam check. 
import java.net.*;

public class Spamcheck {
    public static final String BLACKHOLE = "sbl.spamhaus.org";

    public static void main(String[] args) throws UnknownHostException {
        for (String arg : args) {
            if (isSpammer(arg)) {
                System.out.println(arg + " is a known spammer.");
            } else {
                System.out.println(arg + " appears legitimate.");
            }
        }
    }

    private static boolean isSpammer(String arg) {
        try {
            InetAddress address = InetAddress.getByName(arg);
            byte[] quad = address.getAddress();

            // Reverse the octet order to form the correct query
            String query = BLACKHOLE;
            for (int i = quad.length - 1; i >= 0; i--) {
                int unsignedByte = quad[i] < 0 ? quad[i] + 256 : quad[i];
                query = unsignedByte + "." + query;
            }

            InetAddress.getByName(query);
            return true;  // If no exception, the IP is listed
        } catch (UnknownHostException e) {
            return false;  // Not listed in the blacklist
        }
    }
}
