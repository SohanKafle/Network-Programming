import java.net.*;
import java.util.Scanner;
import java.io.IOException;

public class IBiblioAliasess {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first domain: ");
        String Domain = input.nextLine();

        
        int timeout = 5000;
        
        try {
            InetAddress abc = InetAddress.getByName(Domain);
     
            boolean isabcReachable = abc.isReachable(timeout);
            
            System.out.println(Domain + " is reachable: " + isabcReachable);
                       
        } catch (IOException ex) {
            System.out.println("An error occurred while checking reachability.");
        }
    }
}
