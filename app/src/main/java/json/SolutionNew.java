package json;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by corpa on 9/10/17.
 */

public class SolutionNew implements Serializable {

    private int referenceId;
    private String pathToImage;

    @SerializedName("_hdr")
    private String hdr;
    @SerializedName("_txt")
    private String txt;
    private String id;
    private String name;
    //TODO STRING OR LIST
    private List<String> category = new ArrayList<>();
    //TODO STRING OR LIST
    private List<String> tags;
    private String image;
    private String video;
    @SerializedName("user_id")
    private String userId;
    private String created;
    private String template;
    //TODO STRING OR LIST
    private List<String> publish;
    @SerializedName("shorturl")
    private String shortUrl;
    @SerializedName("_href")
    private String href;

    @SerializedName("#history-and-development")
    private HistoryAndDevelopment historyAndDevelopment;

    @SerializedName("#availability")
    private Availability availability;

    @SerializedName("#specifications")
    private Specifications specifications;

    @SerializedName("#additional-information")
    private AdditionalInformation additionalInformation;

    @SerializedName("#contact")
    private Contact contact;

    private boolean isFavorite;

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public String getHdr() {
        return hdr;
    }

    public void setHdr(String hdr) {
        this.hdr = hdr;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<String> getPublish() {
        return publish;
    }

    public void setPublish(List<String> publish) {
        this.publish = publish;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public HistoryAndDevelopment getHistoryAndDevelopment() {
        return historyAndDevelopment;
    }

    public void setHistoryAndDevelopment(HistoryAndDevelopment historyAndDevelopment) {
        this.historyAndDevelopment = historyAndDevelopment;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public Specifications getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Specifications specifications) {
        this.specifications = specifications;
    }

    public AdditionalInformation getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
