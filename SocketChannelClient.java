import java.io.IOException;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;
import java.util.*;

public class SocketChannelClient {
    public static void main(String[] args) throws IOException{
        ServerSocketChannel ServerSocket = null;
        SocketChannel client = null;
        ServerSocket.socket().bind(new InetSocketAddress(9000));
        Client = ServerSocket.accept();
        
    }
}
