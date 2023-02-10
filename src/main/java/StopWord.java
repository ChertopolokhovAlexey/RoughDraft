import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StopWord {
    protected final static String STOP_WORDS = "stop-ru.txt";
    List<String> stopWords = new ArrayList<>();

    public List stopWordList() {
        File textFile = new File(STOP_WORDS);
        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            String line = br.readLine();
            while (line != null) {
                stopWords.add(line.toLowerCase());
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return stopWords;
    }
}
