//Write a simple program to list all network interface on the local host
import java.net.*;
public class MyAddress
{
public static void main(String []args)
{
try
{
InetAddress address = InetAddress.getLocalHost();
System.out.println(address);
}
catch(UnknownHostException ex)
{
System.out.println("Couldnot find in this computer");
}
}
}