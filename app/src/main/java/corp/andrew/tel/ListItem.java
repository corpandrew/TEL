package corp.andrew.tel;

import android.app.Activity;

/**
 * Created by corpa on 3/19/2016.
 */
public class ListItem extends Activity {

    private String solutionName;
    private String solutionCompany;
    private int imageId;
    private boolean isFavorite;

    public ListItem(String solutionName, String solutionCompany, int imageName, boolean isFavorite) {
        this.solutionName = solutionName;
        this.solutionCompany = solutionCompany;
        this.imageId = imageName;
        this.isFavorite = isFavorite;
    }

    public ListItem(String solutionName, String solutionCompany, int imageName) {
        this.solutionName = solutionName;
        this.solutionCompany = solutionCompany;
        this.imageId = imageName;
        this.isFavorite = false;
    }

    public String getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName;
    }

    public String getSolutionCompany() {
        return solutionCompany;
    }

    public void setSolutionCompany(String solutionCompany) {
        this.solutionCompany = solutionCompany;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageName(int imageId) {
        this.imageId = imageId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

}
