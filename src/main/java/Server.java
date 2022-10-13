
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        while (true) {
            try (ServerSocket servSocket = new ServerSocket(23446);
                 Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new
                         InputStreamReader(socket.getInputStream()))) {
                String line;
                long number;
                while ((line = in.readLine()) != null) {
                    if (line.equals("end")) {
                        break;
                    } else
                        number = Long.parseLong(line);
                    if (number == 0) {
                        out.println(number + "-ой член ряда Фибоначчи равен 0");
                    } else if (number == 1) {
                        out.println(number + "-ый член ряда Фибоначчи равен 1");
                    } else {
                        long a = 0;
                        long b = 1;
                        for (long i = 2; i <= number; ++i) {
                            long next = a + b;
                            a = b;
                            b = next;
                        }
                        out.println(number + "-ый член ряда Фибоначчи равен " + b);
                    }
                }
            } catch(IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
}