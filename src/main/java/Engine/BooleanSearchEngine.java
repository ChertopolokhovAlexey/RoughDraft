package Engine;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BooleanSearchEngine implements SearchEngine {
    protected Map<String, Integer> wordsList;
    protected Map<String, List<PageEntry>> searchMap;
    protected List<SearchEntry> searchEntries = new ArrayList<>();

    public BooleanSearchEngine(List<String> stopWordsList, File folder) {

        for (File pdfFile : Objects.requireNonNull(folder.listFiles())) {

            String fileName = pdfFile.getName();

            try (PdfDocument doc = new PdfDocument(new PdfReader(pdfFile))) {

                for (int page = 1; page <= doc.getNumberOfPages(); page++) { //doc.getNumberOfPages() заменить единицу после прогонов
                    wordsList = new HashMap<>();
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

    public void sortedEntriesMap(List<SearchEntry> searchEntries) {
        searchMap = new HashMap<>();
        Collections.sort(searchEntries);
        for (SearchEntry searchEntry : searchEntries) {
            List<PageEntry> searchMapList = new ArrayList<>();

            if (searchMap.containsKey(searchEntry.getWord())) {
                searchMapList = searchMap.get(searchEntry.getWord());
                searchMap.put(searchEntry.getWord(), addEntries(searchMapList, searchEntry) );
            } else {
                searchMap.put(searchEntry.getWord(), addEntries(searchMapList, searchEntry) );
            }
        }
    }
    public List<PageEntry> addEntries(List<PageEntry> searchMapList,SearchEntry searchEntry) {
        searchMapList.add(new PageEntry(
                searchEntry.getFileName(),
                searchEntry.getPage(),
                searchEntry.getFreq()));
        return searchMapList;
    }

    @Override
    public List<PageEntry> search(String word) {
        return searchMap.containsKey(word)? searchMap.get(word): new ArrayList<>();
    }
}
