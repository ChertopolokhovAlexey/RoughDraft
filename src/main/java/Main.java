import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.List;


public class Main {

    public final static int PORT = 8989;
    public static void main(String[] args) throws Exception {
        StopWord stopWord = new StopWord();
        List<String> stopWordsList = stopWord.stopWordList();
        BooleanSearchEngine engine = new BooleanSearchEngine(stopWordsList);
//        System.out.println(engine.search("бизнес"));
//
//        try (ServerSocket serverSocket = new ServerSocket(PORT);) { // стартуем сервер один(!) раз
//            while (true) { // в цикле(!) принимаем подключения
//                try (
//                        Socket socket = serverSocket.accept();
//                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                        PrintWriter out = new PrintWriter(socket.getOutputStream());
//                ) {
//                    // обработка одного подключения
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Не могу стартовать сервер");
//            e.printStackTrace();
//        }

        // здесь создайте сервер, который отвечал бы на нужные запросы
        // слушать он должен порт 8989
        // отвечать на запросы /{word} -> возвращённое значение метода search(word) в JSON-формате
    }
}