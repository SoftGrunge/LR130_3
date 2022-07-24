import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class FileSender {
    public static final int BUFFER_SIZE = 4096;
    public static final int SERVER_PORT = 63344;


    public static void main(String[] args) {
        FileSender fileSender = new FileSender();
        fileSender.sendFile(new File("D:\\123\\321.txt"));

    }

    public void sendFile(File file){
        try(Socket socket = new Socket("localhost", SERVER_PORT);
            OutputStream os = socket.getOutputStream();){
            FileInputStream fis = new FileInputStream(file);
            byte [] buf = new byte[BUFFER_SIZE];
            int n;

            while (true) {
                n = fis.read(buf);
                if (n < 0) {
                    break;
                }
                os.write(buf, 0, n);
                os.flush();
            }

        }
        catch(IOException io){
            System.out.println("something goes wrong(2) "+io.getMessage());}
    }

}