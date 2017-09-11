package json;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corpa on 9/10/17.
 */

public class SolutionNew {

    private int referenceId;
    private String pathToImage;

    @SerializedName("_hdr")
    private String hdr;
    @SerializedName("_txt")
    private String txt;
    private String id;
    private String name;
    @SerializedName("_hdr")
    private List<String> category = new ArrayList<>();
    private List<String> tags;
    @SerializedName("_hdr")
    private String image;
    @SerializedName("_hdr")
    private String created;
    @SerializedName("_hdr")
    private String template;
    @SerializedName("_hdr")
    private List<String> publish;
    @SerializedName("_hdr")
    private String href;

    private String histDevHdr;
    private String histDevTxt;
    private String histDevHref;

    private String availabilityHdr;
    private String availabilityTxt;
    private String availabilityHref;

    private String specificationsHdr;
    private String specificationsTxt;
    private String specificationsHref;

    private String additionalinfoHdr;
    private String additionalinfoTxt;
    private String additionalinfoHref;
    private String additionalinfoProductURL;

    private String contactHdr;
    private String contactTxt;
    private String contactHref;
    private String contactName;
    private String contactUrl;

    private boolean isFavorite;

}
