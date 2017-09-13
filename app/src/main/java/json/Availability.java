package json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by corpa on 9/10/17.
 */

public class Availability {

    @SerializedName("_hdr")
    private String hdr;
    @SerializedName("_txt")
    private String txt;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
