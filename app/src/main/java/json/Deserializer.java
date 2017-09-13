package json;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by corpa on 9/12/17.
 */

public class Deserializer implements JsonDeserializer<SolutionNew>{
    @Override
    public SolutionNew deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject decodeObj = json.getAsJsonObject();
        Gson gson = new Gson();
        SolutionNew solutionNew = gson.fromJson(json, SolutionNew.class);
        List<String> categories = null;
        List<String> tags = null;
        List<String> publish = null;
        if(decodeObj.get("category").isJsonArray()){
            categories = gson.fromJson(decodeObj.get("category"), new TypeToken<List<String>>(){}.getType());
        } else {
            String single = gson.fromJson(decodeObj.get("category"), String.class);
            categories = new ArrayList<>();
            categories.add(single);
        }
        solutionNew.setCategory(categories);
        return solutionNew;
    }
}
