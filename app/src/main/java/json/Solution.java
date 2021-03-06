package json;

/**
 * Created by corpa on Aug 19, 2016
 */

import java.util.ArrayList;
import java.util.List;

public class Solution implements SolutionInterface{

    private int referenceId;

    private String pathToImage;

    private String hdr;
    private String txt;
    private String id;
    private String name;
    private List<String> category = new ArrayList<>();
    private List<String> tags;
    private String image;
    private String created;
    private String template;
    private List<String> publish;
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

    public Solution(String hdr, String txt, String id, String name, List<String> category, List<String> tags, String image, String created, List<String> publish, String template, String href, String histDevHdr, String histDevTxt, String histDevHref, String availabilityHdr, String availabilityTxt, String availabilityHref, String specificationsHdr, String specificationsTxt, String specificationsHref, String additionalinfoHdr, String additionalinfoTxt, String additionalinfoHref, String additionalinfoProductURL, String contactHdr, String contactTxt, String contactHref, String contactName, String contactUrl, int referenceId, String pathToImage) {
        this.hdr = hdr;
        this.txt = txt;
        this.id = id;
        this.name = name;
        this.category = category;
        this.tags = tags;
        this.image = image;
        this.created = created;
        this.publish = publish;
        this.template = template;
        this.href = href;
        this.histDevHdr = histDevHdr;
        this.histDevTxt = histDevTxt;
        this.histDevHref = histDevHref;
        this.availabilityHdr = availabilityHdr;
        this.availabilityTxt = availabilityTxt;
        this.availabilityHref = availabilityHref;
        this.specificationsHdr = specificationsHdr;
        this.specificationsTxt = specificationsTxt;
        this.specificationsHref = specificationsHref;
        this.additionalinfoHdr = additionalinfoHdr;
        this.additionalinfoTxt = additionalinfoTxt;
        this.additionalinfoHref = additionalinfoHref;
        this.additionalinfoProductURL = additionalinfoProductURL;
        this.contactHdr = contactHdr;
        this.contactTxt = contactTxt;
        this.contactHref = contactHref;
        this.contactName = contactName;
        this.contactUrl = contactUrl;
        this.referenceId = referenceId;
        this.pathToImage = pathToImage;

        String twoNewLines = "\n\n";

        if (txt != null && txt.endsWith(twoNewLines)) {
            this.txt = txt.substring(0, txt.length() - 1);
        }
        if (histDevTxt != null && histDevTxt.endsWith(twoNewLines)) {
            this.histDevTxt = histDevTxt.substring(0, histDevTxt.length() - 1);
        }

        if (availabilityTxt != null && availabilityTxt.endsWith(twoNewLines)) {
            this.availabilityTxt = availabilityTxt.substring(0, availabilityTxt.length() - 2);//todo find a more efficient way
        }

        if (specificationsTxt != null && specificationsTxt.endsWith(twoNewLines)) {
            this.specificationsTxt = specificationsTxt.substring(0, specificationsTxt.length() - 2);
        }

        if (additionalinfoTxt != null && additionalinfoTxt.endsWith(twoNewLines)) {
            this.additionalinfoTxt = additionalinfoTxt.substring(0, additionalinfoTxt.length() - 2);
        }
        if (contactTxt != null && contactTxt.endsWith("\\n\\n")) {
            this.contactTxt = contactTxt.substring(0, contactTxt.length() - 1);
        }
    }


    public String getHdr() {
        return hdr;
    }

    public String getTxt() {
        return txt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getCategory() {
        return category;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getImage() {
        return image;
    }

    public String getCreated() {
        return created;
    }

    public String getTemplate() {
        return template;
    }

    public String getHref() {
        return href;
    }

    public String getHistDevHdr() {
        return histDevHdr;
    }

    public String getHistDevTxt() {
        return histDevTxt;
    }

    public String getHistDevHref() {
        return histDevHref;
    }

    public String getAvailabilityHdr() {
        return availabilityHdr;
    }

    public String getAvailabilityTxt() {
        return availabilityTxt;
    }

    public String getAvailabilityHref() {
        return availabilityHref;
    }

    public String getSpecificationsHdr() {
        return specificationsHdr;
    }

    public String getSpecificationsTxt() {
        return specificationsTxt;
    }

    public String getSpecificationsHref() {
        return specificationsHref;
    }

    public String getAdditionalinfoHdr() {
        return additionalinfoHdr;
    }

    public String getAdditionalinfoTxt() {
        return additionalinfoTxt;
    }

    public String getAdditionalinfoHref() {
        return additionalinfoHref;
    }

    public String getAdditionalinfoProductURL() {
        return additionalinfoProductURL;
    }

    public String getContactHdr() {
        return contactHdr;
    }

    public String getContactTxt() {
        return contactTxt;
    }

    public String getContactHref() {
        return contactHref;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public List<String> getPublish() {
        return publish;
    }

}