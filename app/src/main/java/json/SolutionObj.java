package json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by corpa on 9/11/17.
 */

public class SolutionObj implements Serializable {
    @SerializedName("Solutions")
    private ArrayList<SolutionNew> solutions;

    public ArrayList<SolutionNew> getSolutions() {
        return solutions;
    }
}
