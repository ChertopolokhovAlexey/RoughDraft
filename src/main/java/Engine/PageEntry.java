package Engine;

public class PageEntry implements Comparable<PageEntry> {
    protected  final String pdfName;
    protected  final Integer page;
    protected  final Integer count;

    public PageEntry(String pdfName, int page, int count) {
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
    }


    public String getPdfName() {
        return pdfName;
    }

    public int getPage() {
        return page;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public int compareTo(PageEntry o1) {
        int compareResult = o1.count.compareTo(this.count);

        if (compareResult == 0) {
            compareResult = this.pdfName.compareTo(o1.pdfName);
        }

        if (compareResult == 0) {
            compareResult = this.page.compareTo(o1.getPage());
        }

        return compareResult;
    }

    @Override
    public String toString() {
        return "Engine.PageEntry{" +
//                "word= " + word + '\'' +
                "pdfName='" + pdfName + '\'' +
                ", page=" + page +
                ", count=" + count +
                '}' + '\n';
    }
}
