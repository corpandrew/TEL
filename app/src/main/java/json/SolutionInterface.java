package json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by corpa on Aug 24, 2016
 */
public interface SolutionInterface extends Serializable {

    String getHdr();

    String getTxt();

    String getId();

    String getName();

    List<String> getCategory();

    List<String> getTags();

    String getImage();

    String getCreated();

    String getTemplate();

    String getHref();

    String getHistDevHdr();

    String getHistDevTxt();

    String getHistDevHref();

    String getAvailabilityHdr();

    String getAvailabilityTxt();

    String getAvailabilityHref();

    String getSpecificationsHdr();

    String getSpecificationsTxt();

    String getSpecificationsHref();

    String getAdditionalinfoHdr();

    String getAdditionalinfoTxt();

    String getAdditionalinfoHref();

    String getContactHdr();

    String getContactTxt();

    String getContactHref();

    String getContactName();

    String getContactUrl();

    int getReferenceId();

    boolean getIsFavorite();

    void setFavorite(boolean favorite);

    String getPathToImage();

    List<String> getPublish();


}
