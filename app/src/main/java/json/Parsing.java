package json;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import activities.MainActivity;

/**
 * Created by corpa on Aug 19, 2016
 */
public class Parsing {

    private static final String downloadString = "http://www.techxlab.org/pages.json";
    InputStream inputStream = null;
    private String version = "file.json";
    private MainActivity activity;
    private AssetManager assetManager;

    public Parsing(MainActivity activity) {
        this.activity = activity;
        assetManager = activity.getBaseContext().getResources().getAssets();
        loadJSONFromAsset(activity);
    }

    private String loadJSONFromAsset(Activity activity) {

        String json;
        final File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/" + version);

        try {
            FileInputStream is = new FileInputStream(file);

            Log.v("Testing", "File opened");
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public List<Solution> parseJson(Activity activity) {

        List<Solution> solutionList = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(activity));
            JSONArray solutionsArray = obj.getJSONArray("Solutions");

            for (int i = 0; i < solutionsArray.length(); i++) {
                JSONObject jo_inside = solutionsArray.getJSONObject(i);

                String _hdr_value = jo_inside.getString("_hdr");
                String _txt_value = jo_inside.getString("_txt");
                String id_value = jo_inside.getString("id");
                String name_value = jo_inside.getString("name");

                String category_value = jo_inside.getString("category");

                List<String> categories = new ArrayList<>();

                if (category_value.contains("[")) {
                    category_value = category_value.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"", "");
                    String[] cats = category_value.split(",");
                    Collections.addAll(categories, cats);
                } else {
                    categories.add(category_value);
                }

                String tags_value = "";

                List<String> tags = new ArrayList<>();

                if (jo_inside.has("tags"))
                    tags_value = jo_inside.getString("tags");

                if (tags_value.contains("[")) {
                    tags_value = tags_value.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"", "");
                    String[] tagsSplit = tags_value.split(",");
                    Collections.addAll(tags, tagsSplit);
                }
                String image_value = null;
                if (jo_inside.has("image")) {
                    image_value = jo_inside.getString("image");
                }
                String created_value = jo_inside.getString("created");
                String template_value = jo_inside.getString("template");

                String publish_value = "";

                List<String> publish = new ArrayList<>();

                if (jo_inside.has("publish"))
                    publish_value = jo_inside.getString("publish");

                if (jo_inside.has("publish")) {
                    publish_value = publish_value.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"", "");
                    String[] publishSplit = publish_value.split(",");
                    Collections.addAll(publish, publishSplit);
                }
                String _href_value = jo_inside.getString("_href");

                String historyanddevelopment_hdr_value = null;
                String historyanddevelopment_txt_value = null;
                String historyanddevelopment_href_value = null;

                if (jo_inside.has("#history-and-development")) {
                    JSONObject historyAndDev = jo_inside.getJSONObject("#history-and-development");
                    historyanddevelopment_hdr_value = historyAndDev.getString("_hdr");
                    historyanddevelopment_txt_value = historyAndDev.getString("_txt");
                    historyanddevelopment_href_value = historyAndDev.getString("_href");
                }

                String availability_hdr_value = null;
                String availability_txt_value = null;
                String availability_href_value = null;

                if (jo_inside.has("#availability")) {
                    JSONObject availibility = jo_inside.getJSONObject("#availability");
                    availability_hdr_value = availibility.getString("_hdr");
                    availability_txt_value = availibility.getString("_txt");
                    availability_href_value = availibility.getString("_href");
                }
                String specifications_hdr_value = null;
                String specifications_txt_value = null;
                String specifications_href_value = null;

                if (jo_inside.has("#specifications")) {
                    JSONObject specs = jo_inside.getJSONObject("#specifications");
                    specifications_hdr_value = specs.getString("_hdr");
                    specifications_txt_value = specs.getString("_txt");
                    specifications_href_value = specs.getString("_href");
                }
                String additionalinformation_hdr_value = null;
                String additionalinformation_txt_value = null;
                String additionalinformation_href_value = null;

                if (jo_inside.has("#additional-information")) {
                    JSONObject additionalInfo = jo_inside.getJSONObject("#additional-information");
                    additionalinformation_hdr_value = additionalInfo.getString("_hdr");
                    additionalinformation_txt_value = additionalInfo.getString("_txt");
                    additionalinformation_href_value = additionalInfo.getString("_href");
                }

                JSONObject contact = jo_inside.getJSONObject("#contact");

                String contact_url_value = null;
                String contact_name_value = null;

                String contact_hdr_value = contact.getString("_hdr");
                String contact_txt_value = contact.getString("_txt");
                if (contact.has("name"))
                    contact_name_value = contact.getString("name");
                if (contact.has("url"))
                    contact_url_value = contact.getString("url");
                String contact_href_value = contact.getString("_href");

                Solution solution = new Solution(_hdr_value, _txt_value, id_value, name_value, categories, tags, image_value, created_value, publish, template_value, _href_value, historyanddevelopment_hdr_value, historyanddevelopment_txt_value, historyanddevelopment_href_value, availability_hdr_value, availability_txt_value, availability_href_value, specifications_hdr_value, specifications_txt_value, specifications_href_value, additionalinformation_hdr_value, additionalinformation_txt_value, additionalinformation_href_value, contact_hdr_value, contact_txt_value, contact_href_value, contact_name_value, contact_url_value, i, pathToDrawable(image_value));

                if (solution.getPublish().contains("tel"))
                    solutionList.add(solution);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return solutionList;
    }

    private String pathToDrawable(String imageId) {
        if ((imageId != null) && assetExists(imageId)) {
            return imageId;
        } else {
            return "ast/e41sag8-coolbot-image-v2.jpg";
        }
    }

    private boolean assetExists(String pathInAssetsDir) {
        try {
            inputStream = assetManager.open(pathInAssetsDir);
            if (null != inputStream) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String getVersion() {
        return version;
    }

}
