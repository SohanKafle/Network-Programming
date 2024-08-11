import java.nio.*;
public class BufferDemo {
    public static void main(String[] args){
        CharBuffer buffer = CharBuffer.allocate(10);
        String text = "bufferDemo";
        System.out.println("Input text:" +text);
        for(int i=0; i<text.length();i++)
        {
            char c = text.charAt(i);
            buffer.put(c);
        }
        int buffpos = buffer.position();
        System.out.println("Position after Data written into buffer:" + buffpos);
        buffer.flip();
        System.out.println("Reading buffer content:");
        while (buffer.hasRemaining()) 
        {
            System.out.println(buffer.get());  
        }

        buffer.position(5);
        buffer.mark();
        buffer.position(6);
        buffer.reset();
        System.out.println("Restored bufferposition: " + buffer.position());
    }
}
