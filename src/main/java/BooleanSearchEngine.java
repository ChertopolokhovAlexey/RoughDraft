import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
public class BooleanSearchEngine implements SearchEngine {
    protected final String PDFsFolder = "./pdfs";
    protected Map<String, Integer> wordsList = new HashMap<>();
    SearchMap searchMap = new SearchMap();
    public BooleanSearchEngine(List<String> stopWordsList) {

        for (File pdfFile : Objects.requireNonNull(new File(PDFsFolder).listFiles())) {

            String fileName = pdfFile.getName();
            System.out.println(fileName);

            try (PdfDocument doc = new PdfDocument(new PdfReader(pdfFile))) {

                for (int page = 1; page <= 1; page++) { //doc.getNumberOfPages() заменить единицу после прогонов

                    String text = PdfTextExtractor.getTextFromPage(doc.getPage(page));
                    String[] words = (text.toLowerCase()).split("\\P{IsAlphabetic}+"); // получаю массив текстовый

                    wordsList = pageScanner(words, stopWordsList);
                    System.out.println(wordsList.toString());


                    for (Map.Entry<String, Integer> entry : wordsList.entrySet()) {
                    searchMap.searchMapAdd(entry.getKey(),
                            new SearchEntry(fileName, page, entry.getValue()));
                }
                }
            } catch (IOException e) {e.printStackTrace();}
        }

    }

    public Map<String, Integer> pageScanner (String[] words,List<String> stopWordsList) {
        for (String word : words) {

            if (stopWordsList.contains(word)) {
                continue;
            }
            wordsList.put(word, wordsList.getOrDefault(word, 0) + 1);

        }
        return wordsList;
    }

    // TODO: 31.01.2023
    public static String search(String word) {
        return null;
    }

//    @Override
//    public List<PageEntry> search(String word) {
//        // тут реализуйте поиск по слову
//        return Collections.emptyList();
//    }
}
