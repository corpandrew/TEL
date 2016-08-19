package corp.andrew.tel;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import json.Solution;

/**
 * Created by corpa on Aug 19, 2016
 */

public class Sorting implements Serializable {

    List<Solution> allsolutionList;
    SharedPreferences sharedPreferences;

    public Sorting(List<Solution> allsolutionList, SharedPreferences sharedPreferences) {
        this.allsolutionList = allsolutionList;
        this.sharedPreferences = sharedPreferences;
    }

    public ListItemAdapter getSolutionList(String categories, Context context) {
        List<Solution> newSolutionList = new ArrayList<>();

        String[] splitCategories = categories.split(",");

        for (Solution s : allsolutionList) {
            for (String category : splitCategories) {
                if (s.getCategory().contains(category)) {
                    newSolutionList.add(s);
                }
            }
        }
        return new ListItemAdapter(context, 0, newSolutionList, sharedPreferences);
    }

    public ListItemAdapter getFavoritesList(Context context) {
        List<Solution> newSolutionList = new ArrayList<>();

        for (Solution s : allsolutionList) {
            if (sharedPreferences.getBoolean(s.getName(), false)) {
                newSolutionList.add(s);
            }
        }
        return new ListItemAdapter(context, 0, newSolutionList, sharedPreferences);
    }

    public ListItemAdapter getSearchedEntries(Context context, String text) {
        List<Solution> newSolutionsList = new ArrayList<>();
        String solutionName, companyName, solutionText;
        for (Solution s : allsolutionList) {
            solutionName = s.getName().toLowerCase();
            if (s.getContactName() != null)
                companyName = s.getContactName().toLowerCase();
            else
                companyName = "";
            solutionText = s.getTxt();

            if (solutionName.contains(text.toLowerCase()) || companyName.contains(text.toLowerCase()) || solutionText.contains(text))
                newSolutionsList.add(s);
        }
        return new ListItemAdapter(context, 0, newSolutionsList, sharedPreferences);
    }

}