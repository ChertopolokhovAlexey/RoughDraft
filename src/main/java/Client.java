import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket(Main.HOST, Main.PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println(reader.readLine());

            System.out.println("Введите слово для поиска.");
            writer.println(scanner.nextLine());

            int character;
            StringBuilder jsonAnswer = new StringBuilder();
            while ((character = reader.read()) != -1) {
                jsonAnswer.append((char) character);
            }

            System.out.println(jsonAnswer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}