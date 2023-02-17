package Engine;

public class SearchEntry implements Comparable<SearchEntry> {

    protected String word;
    protected String fileName;
    protected Integer page;
    protected Integer freq;

    public SearchEntry(String word, String fileName, int page, int freq) {
        this.word = word;
        this.fileName = fileName;
        this.page = page;
        this.freq = freq;
    }

    public String getWord() {
        return word;
    }

    public String getFileName() {
        return fileName;
    }

    public int getPage() {
        return page;
    }

    public Integer getFreq() {
        return freq;
    }

    @Override
    public int compareTo(SearchEntry o1) {
        int compareResult = o1.word.compareTo(this.word);

        if (compareResult == 0) {
            compareResult = o1.freq.compareTo(this.freq);
        }

        if (compareResult == 0) {
            compareResult = this.fileName.compareTo(o1.fileName);
        }

        if (compareResult == 0) {
            compareResult = this.page.compareTo(o1.getPage());
        }

        return compareResult;
    }

    @Override
    public String toString() {
        return "Engine.SearchEntry{" +
                "fileName='" + fileName + '\'' +
                "word= " + word + '\'' +
                ", pageNumber=" + page +
                ", freq=" + freq +
                '}';
    }
}
