import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;
public class BooleanSearchEngine implements SearchEngine {
//    protected final String PDFsFolder = "./pdfs";
    protected Map<String, Integer> wordsList;
    SearchMap searchMap;

    public BooleanSearchEngine(List<String> stopWordsList, SearchMap searchMap, File folder) {
        this.searchMap =searchMap;

        for (File pdfFile : Objects.requireNonNull(folder.listFiles())) {
            wordsList = new HashMap<>();

            String fileName = pdfFile.getName();

            try (PdfDocument doc = new PdfDocument(new PdfReader(pdfFile))) {

                for (int page = 1; page <= doc.getNumberOfPages(); page++) { //doc.getNumberOfPages() заменить единицу после прогонов

                    String text = PdfTextExtractor.getTextFromPage(doc.getPage(page));
                    String[] words = (text.toLowerCase()).split("\\P{IsAlphabetic}+"); // получаю массив текстовый

                    wordsList = pageScanner(words, stopWordsList);

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

    @Override
    public List<PageEntry> search(String[] words) {
        List<SearchEntry> engineEntryListlist;
        List<PageEntry> pageEntryList = new ArrayList<>();
        for (String wordValue : words) {
            engineEntryListlist = searchMap.getSearchMap().get(wordValue);
            if (engineEntryListlist != null) {
                for (SearchEntry searchValue : engineEntryListlist) {
                    boolean flagUpdate = false;
                    for (int i = 0; i < pageEntryList.size(); i++) {
                        if (pageEntryList.get(i).getPdfName()
                                .equals(searchValue.getFileName())
                                && pageEntryList.get(i).getPage() == (searchValue.getPage())) {
                            int freq = pageEntryList.get(i).getCount();
                            pageEntryList.set(i, new PageEntry(searchValue.getFileName(),
                                    searchValue.getPage(), searchValue.getFreq() + freq));
                            flagUpdate = true;
                            break;
                        }
                    }
                    if (!flagUpdate) {
                        pageEntryList.add(new PageEntry(searchValue.getFileName(),
                                searchValue.getPage(), searchValue.getFreq()));
                    }
                }
            }
        }
        Collections.sort(pageEntryList);
        return pageEntryList;
    }
}
