package corp.andrew.tel;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import json.Solution;

/**
 * Created by corpa on 3/21/2016.
 */

public class Sorting {
    List<Solution> allsolutionList;

    public Sorting(List<Solution> allsolutionList) {
        this.allsolutionList = allsolutionList;
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
        return new ListItemAdapter(context, 0, newSolutionList);
    }

    public ListItemAdapter getFavoritesList(Context context) {
        List<Solution> newSolutionList = new ArrayList<>();

        for (Solution s : allsolutionList) {
            if (s.getIsFavorite()) {
                newSolutionList.add(s);
            }
        }
        return new ListItemAdapter(context, 0, newSolutionList);
    }

    public ListItemAdapter getSearchedEntries(Context context, String text) {
        List<Solution> newSolutionsList = new ArrayList<>();
        String solutionName = null;
        String companyName = null;
        for(Solution s: allsolutionList) {
            solutionName = s.getName().toLowerCase();
            if(s.getContactName() != null)
                companyName = s.getContactName().toLowerCase();
            else
                companyName = "";
            if(solutionName.contains(text.toLowerCase()) || companyName.contains(text.toLowerCase()))
                newSolutionsList.add(s);
        }
        return new ListItemAdapter(context,0,newSolutionsList);
    }

}