package json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by corpa on 9/10/17.
 */

public class Contact {
    @SerializedName("_hdr")
    private String hdr;
    @SerializedName("_txt")
    private String txt;
    private String name;
    private String url;
    @SerializedName("_href")
    private String href;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
