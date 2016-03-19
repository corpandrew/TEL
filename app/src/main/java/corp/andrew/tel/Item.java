package corp.andrew.tel;

public class Item {

    private String solutionName;
    private String solutionCompany;
    private String imageName;
    private boolean isFavorite;

    public Item(String solutionName, String solutionCompany, String imageName, boolean isFavorite) {
        this.solutionName = solutionName;
        this.solutionCompany = solutionCompany;
        this.imageName = imageName;
        this.isFavorite = isFavorite;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

}
