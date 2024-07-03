import java.net.*;
import java.util.Scanner;

public class AddressTests {
    public static int getVersion(InetAddress ia) {
        byte[] address = ia.getAddress();
        if (address.length == 4) {
            return 4;
        } else if (address.length == 16) {
            return 6;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter the website:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        try {
            InetAddress address = InetAddress.getByName(name);
            System.out.println("Version is ipv" + getVersion(address));
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
    }
}