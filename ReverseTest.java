import java.net.*;
public class ReverseTest
{
public static void main(String []args) throws UnknownHostException
{
InetAddress ia = InetAddress.getByName("142.250.72.206");
System.out.println(ia.getCanonicalHostName());
}
}