//Are www.ibiblio.org and helios.ibiblio.org the same?

import java.net.*;
import java.util.Scanner;

public class IBiblioAliases {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String Domain1 = input.nextLine();
        String Domain2 = input.nextLine();
        try {
            InetAddress ibiblio = InetAddress.getByName(Domain1);
            InetAddress helios = InetAddress.getByName(Domain2);
            if (ibiblio.equals(helios)) {
                System.out.println(Domain1 + " is the same as " + Domain2);
            } else {
                System.out.println(Domain1 + " isnot  same as " + Domain2);
            }
        } catch (UnknownHostException ex) {
            System.out.println("Host lookup failed.");
        }
    }
}