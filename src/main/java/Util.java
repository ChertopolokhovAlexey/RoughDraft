import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class Util {

    public Util(){}
    public String gson(List<PageEntry> pageEntry) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        return gson.toJson(pageEntry);
    }



}
