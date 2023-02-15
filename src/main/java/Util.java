import com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Util {

    public String gson(List<PageEntry> pageEntry) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        return gson.toJson(pageEntry);
    }

    public void toJsonFile(List<PageEntry> result) {
        try {
            FileWriter writer = new FileWriter("result.json");
            writer.write(result.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fromJsonFile(String request) {
        File file = new File("result.json");
        JsonParser parser = new JsonParser();
        try {
            Object object = parser.parse(new FileReader(file));
            JsonObject jsonObject = (JsonObject) object;
            JsonArray jsonArray = (JsonArray) jsonObject.get(request);
            String result = jsonArray.toString();
        } catch (IOException e) {e.printStackTrace();}

    }

}
