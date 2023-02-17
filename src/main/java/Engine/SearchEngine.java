package Engine;

import Engine.PageEntry;

import java.util.List;

public interface SearchEngine {
    List<PageEntry> search(String word);

}
