import java.io.*;
import java.net.*;
import java.util.Scanner;

public class BinarySaver {
    public static void main(String[] args) {
        System.out.println("Enter URL:");
        Scanner input = new Scanner(System.in);
        String myurl = input.nextLine();
        input.close();
        try {
            URL root = new URL(myurl);
            saveBinaryFile(root);
        } catch (MalformedURLException ex) {
            System.err.println(myurl + " is not a URL I understand.");
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public static void saveBinaryFile(URL u) throws IOException {
        URLConnection uc = u.openConnection();
        uc.setRequestProperty("User-Agent", "Mozilla/5.0");
        String contentType = uc.getContentType();
        int contentLength = uc.getContentLength();
        if (contentType == null || contentType.startsWith("text/") || contentLength == -1) {
            throw new IOException("This is not a binary file or the content length is invalid.");
        }
        try (InputStream raw = uc.getInputStream()) {
            InputStream in = new BufferedInputStream(raw);
            byte[] data = new byte[contentLength];
            int offset = 0;
            while (offset < contentLength) {
                int bytesRead = in.read(data, offset, data.length - offset);
                if (bytesRead == -1)
                    break;
                offset += bytesRead;
            }
            if (offset != contentLength) {
                throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
            }
            String filename = getFileName(u);
            try (FileOutputStream fout = new FileOutputStream(filename)) {
                fout.write(data);
                fout.flush();
                System.out.println("File saved as " + filename);
            }
        }
    }

    private static String getFileName(URL u) {
        String filename = u.getFile();
        if (filename == null || filename.isEmpty() || filename.equals("/")) {
            filename = "downloaded_file";
        } else {
            filename = filename.substring(filename.lastIndexOf('/') + 1);
            if (filename.contains("?")) {
                filename = filename.substring(0, filename.indexOf('?'));
            }
        }
        return filename;
    }
}
