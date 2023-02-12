

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchMap {
    private final Map<String, List<SearchEntry>> SearchMap;

    public SearchMap() {
        this.SearchMap = new HashMap<>();
    }

    public Map<String, List<SearchEntry>> getSearchMap() {
        return SearchMap;
    }

    public void searchMapAdd(String word, SearchEntry entry) {
        List<SearchEntry> searchEngineArray = new ArrayList<>();
        if (SearchMap.containsKey(word)) {
            searchEngineArray = SearchMap.get(word);
        }
        searchEngineArray.add(entry);
        SearchMap.put(word, searchEngineArray);
    }

    @Override
    public String toString() {
        return "SearchMap{" +
                "searchMap=" + SearchMap +
                '}';
    }
}
