import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Objects;

import static java.lang.System.out;

public class Server {

    public final static int PORT = 8989;
    public final static String HOST = "127.0.0.1";
    final static String PDFsFolder = "./pdfs";


    public static void main(String[] args) {
        BooleanSearchEngine engine = null;

        try (ServerSocket server = new ServerSocket(PORT)) { // запускаем сервер
            out.println("Сервер запущен.");
            StopWord stopWord = new StopWord();
            List<String> stopWordsList = stopWord.stopWordList();
            SearchMap searchMap = new SearchMap();
            File folder = new File(PDFsFolder);
            engine = new BooleanSearchEngine(stopWordsList, searchMap, folder);
            while (true) {
                //принимаем подключение
                try (Socket client = server.accept();
                     // что бы общаться с клиентом
                     PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
                    writer.println("Есть подключение");
                    writer.flush();
                    //поиск
                    String request = reader.readLine().toLowerCase();
                    if (request.isEmpty()) {
                        writer.println("Не задан запрос на поиск");
                        continue;
                    }
                    String[] requestArray = request.split(" ");
                    if (stopListCheck(requestArray, stopWordsList)) {
                        writer.println("Данный запрос не доступен для поиска");
                        continue;
                    }
                    writer.print(new Util().gson(Objects.requireNonNull(engine).search(requestArray)));

                }
            }
        } catch (IOException e) {
            out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
        out.println("Сервер остановлен.");
    }

    public static boolean stopListCheck(String[] requestArray, List<String> stopWordsList) {
        boolean answer = false;
        for (String s : requestArray) {
            if (stopWordsList.contains(s)) {
                answer = true;
                break;
            }
        }
        return answer;
    }

}