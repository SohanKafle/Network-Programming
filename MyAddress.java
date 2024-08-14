// 3. Write a simple program to list all network interface on the local host.
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class MyAddress {
    public static void main(String[] args) {
        try {
            // Get all network interfaces on the local host
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            // Iterate through the network interfaces
            for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
                System.out.println("Interface Name: " + networkInterface.getName());
                System.out.println("Display Name: " + networkInterface.getDisplayName());
                System.out.println("Is Up: " + networkInterface.isUp());
                System.out.println("Is Loopback: " + networkInterface.isLoopback());
                System.out.println("Supports Multicast: " + networkInterface.supportsMulticast());
                System.out.println("Is Virtual: " + networkInterface.isVirtual());
                System.out.println("--------------------------------");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
