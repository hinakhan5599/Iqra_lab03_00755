import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = null;

    try {
        serverSocket = new ServerSocket(55555);
    } catch (IOException ex) {
        System.out.println("Can't setup server on this port number. ");
    }

    Socket socket = null;
    InputStream is = null;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    int bufferSize = 0;

    try {
        socket = serverSocket.accept();
    } catch (IOException ex) {
        System.out.println("Can't accept client connection. ");
    }

    try {
        is = socket.getInputStream();

        bufferSize = socket.getReceiveBufferSize();
        System.out.println("Buffer size: " + bufferSize);
    } catch (IOException ex) {
        System.out.println("Can't get socket input stream. ");
    }

    try {
        fos = new FileOutputStream("E:\\lab1.txt");
        bos = new BufferedOutputStream(fos);

    } catch (FileNotFoundException ex) {
        System.out.println("File not found. ");
    }

    byte[] bytes = new byte[bufferSize];

    int count;

    while ((count = is.read(bytes)) > 0) {
        bos.write(bytes, 0, count);
    }

    bos.flush();
    bos.close();
    is.close();
    socket.close();
    serverSocket.close();
}

}