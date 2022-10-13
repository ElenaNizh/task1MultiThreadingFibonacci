import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 23446);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            String number;
            while (true) {
                System.out.println("\"Введите число для расчета n-го члена Фибоначи\"...(\"end\" для завершения программы)");
                number = scanner.nextLine();
                out.println(number);
                if ("end".equals(number)) break;
                System.out.println("SERVER: " + in.readLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
//Выбрала способ взаимодействия Blocking IO, так как нам не нужны промежуточные данные, а нам нужен конечный результат.
//входные данные клиента сначала читаются через InputStream, затем обрабатываются, а затем выводятся через OutputStream.
// В этом типичном процессе каждый шаг синхронизирован. Например, наш поток-обработчик не может ничего сделать,
// пока метод accept сервера не вернет результат. Обработчик считывает ввод, обрабатывает и затем выводит шаг за шагом.