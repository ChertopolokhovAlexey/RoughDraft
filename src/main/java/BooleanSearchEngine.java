import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BooleanSearchEngine implements SearchEngine {
    protected Map<String, Integer> wordsList;
    Map<String, List<PageEntry>> asf;
    List<PageEntry> pageEntries = new ArrayList<>();
PageEntry pageEntry = new PageEntry(null,0,0);

    protected List<SearchEntry> searchEntries = new ArrayList<>();

    public BooleanSearchEngine(List<String> stopWordsList, File folder) {

        for (File pdfFile : Objects.requireNonNull(folder.listFiles())) {
            wordsList = new HashMap<>();

            String fileName = pdfFile.getName();

            try (PdfDocument doc = new PdfDocument(new PdfReader(pdfFile))) {

                for (int page = 1; page <= 1; page++) { //doc.getNumberOfPages() заменить единицу после прогонов

                    String text = PdfTextExtractor.getTextFromPage(doc.getPage(page));
                    String[] words = (text.toLowerCase()).split("\\P{IsAlphabetic}+"); // получаю массив текстовый

                    wordsList = pageScanner(words, stopWordsList);

                    for (Map.Entry<String, Integer> entry : wordsList.entrySet()) {
                        searchEntries.add(new SearchEntry(entry.getKey(), fileName, page, entry.getValue()));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sortedEntriesMap(searchEntries);
    }

    public Map<String, Integer> pageScanner(String[] words, List<String> stopWordsList) {
        for (String word : words) {

            if (stopWordsList.contains(word)) {
                continue;
            }
            wordsList.put(word, wordsList.getOrDefault(word, 0) + 1);
        }
        return wordsList;
    }
    // TODO: 13.02.2023
    public void sortedEntriesMap(List<SearchEntry> searchEntries) {
        asf = new HashMap<>();
        Collections.sort(searchEntries);
        for (SearchEntry searchEntry : searchEntries) {
            List<PageEntry> asfList = new ArrayList<>();

            if (asf.containsKey(searchEntry.getWord())) {
                asfList = asf.get(searchEntry.getWord());
                asfList.add(new PageEntry(
                        searchEntry.getFileName(),
                        searchEntry.getPage(),
                        searchEntry.getFreq()));
                asf.put(searchEntry.getWord(), asfList);
                continue;
            }
            if (!asf.containsKey(searchEntry.getWord())) {
                asfList.add( new PageEntry(
                        searchEntry.getFileName(),
                        searchEntry.getPage(),
                        searchEntry.getFreq()));
                asf.put(searchEntry.getWord(), asfList);
            }
        }
        System.out.println(asf.size());
    }

    @Override
    public List<PageEntry> search(String word) {

        for (Map.Entry<String, List<PageEntry>> entry : asf.entrySet()) {
            if (entry.getKey().equals(word)) {
                System.out.println(entry.getKey());
                return entry.getValue();
            }
        }
return null;
    }
}
