package Engine;

import com.google.gson.*;
import java.util.List;

public class Util {

    public String gson(List<PageEntry> pageEntry) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        return gson.toJson(pageEntry);
    }
}
