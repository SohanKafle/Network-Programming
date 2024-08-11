import java.nio.*;
public class BufferDemo {
    public static void main(String[] args)
    {
        CharBuffer buffer = CharBuffer.allocate(10);
        String text = "bufferDemo";
        System.out.println("Input Text: " + text);
        for (int i = 0; i < text.length(); i++)
        {
           Char C =  text.charAt(i);
           buffer.put(C);
        }
        int bufferpos = buffer.position();
        System.out.println("Position after data written into buffer: " + bufferpos);
        buffer.flip();
        System.out.println("Reading buffer contents: ");
        while (buffer.hasRemaining())
        {
            System.out.print(buffer.get());
        }
        buffer.position(5);
        buffer.mark();
        buffer.;position(8);
        buffer.reset();
        System.out.println("\nRestored buffer position: " +buffer.position());

    }
}
