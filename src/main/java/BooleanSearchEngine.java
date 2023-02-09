import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BooleanSearchEngine implements SearchEngine {
    protected final String PDFsFolder = "./pdfs";
    protected final String STOP_WORDS = "stop-ru.txt";
    //???

    protected Map<String, Integer> wordsList = new HashMap<>();

    public BooleanSearchEngine(File pdfsDir) throws IOException {

        //String[] stopWords =

        for (File pdfFile : Objects.requireNonNull(new File(PDFsFolder).listFiles())) {

            String fileName = pdfFile.getName();
            System.out.println(fileName);


            PdfDocument doc = new PdfDocument(new PdfReader(pdfFile.getPath()));
            int pages = doc.getNumberOfPages();

            for (int i = 1; i == pages; i++) {
                System.out.println("page " + i);
//
//                String text = PdfTextExtractor.getTextFromPage(doc.getPage(i));
//                String[] words = (text.toLowerCase()).split("\\P{IsAlphabetic}+"); // получаю массив текстовый
//             45   int count = 1;
//
//                for (String word : words) {
//                    // if стоп слово cont
//
//                    if (!wordsList.containsKey(word)) {
//                        wordsList.get(word);
//                        wordsList.put(word, count);
//                    }
//                    if (wordsList.containsKey(word)) {
//                        wordsList.put(word, count++);
//                    }
//
//                }
//                System.out.println(wordsList.toString());
            }
            doc.close();
        }

        //String fileName = pdfsDir.getName();
//Чтобы создать объект пдф-документа, нужно указать объект File этого документа следующим классам:
        //var doc = new PdfDocument(new PdfReader(pdfsDir));
//Чтобы получить объект одной страницы документа, нужно вызвать:
        //doc.getPage(1);
        //doc.getNumberOfPages();//количество страниц в документе
//Чтобы получить текст со страницы, используйте
        //var text = PdfTextExtractor.getTextFromPage(doc.getPage(1));
//Чтобы разбить текст на слова (а они в этих документах разделены могут быть не только пробелами), используйте
        //var words = text.split("\\P{IsAlphabetic}+"); // получаю массив текстовый

        //PdfReader reader = new PdfReader(pdfsDir.getPath());


        // прочтите тут все pdf и сохраните нужные данные,
        // тк во время поиска сервер не должен уже читать файлы
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
