package corp.andrew.tel;

import android.graphics.drawable.Drawable;

/**
 * Created by corpa on 10/7/16.
 */

public class Developer {
    private String developerName;
    private String developerTitle;
    private String schoolName;
    private String developerBio;
    private Drawable developerPicture;

    public Developer(String developerName,String developerBio, Drawable developerPicture, String schoolName, String developerTitle) {
        this.developerName = developerName;
        this.developerBio = developerBio;
        this.developerPicture = developerPicture;
        this.schoolName = schoolName;
        this.developerTitle = developerTitle;
    }

    public String getPersonName() {
        return developerName;
    }

    public void setPersonName(String developerName) {
        this.developerName = developerName;
    }

    public String getBio() {
        return developerBio;
    }

    public void setBio(String developerBio) {
        this.developerBio = developerBio;
    }

    public Drawable getPersonPicture() {
        return developerPicture;
    }

    public void setPersonPicture(Drawable developerPicture) {
        this.developerPicture = developerPicture;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getTitle() {
        return developerTitle;
    }

    public void setTitle(String developerTitle) {
        this.developerTitle = developerTitle;
    }
}
