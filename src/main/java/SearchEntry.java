public class SearchEntry {

    protected String fileName;
    protected int page;
    protected int freq;

    public SearchEntry(String fileName, int page, int freq) {
        this.fileName = fileName;
        this.page = page;
        this.freq = freq;
    }

    public String getFileName() {
        return fileName;
    }

    public int getPage() {
        return page;
    }

    public int getFreq() {
        return freq;
    }
    @Override
    public String toString() {
        return "SearchEngineEntry{" +
                "fileName='" + fileName + '\'' +
                ", pageNumber=" + page +
                ", freq=" + freq +
                '}';
    }

}
