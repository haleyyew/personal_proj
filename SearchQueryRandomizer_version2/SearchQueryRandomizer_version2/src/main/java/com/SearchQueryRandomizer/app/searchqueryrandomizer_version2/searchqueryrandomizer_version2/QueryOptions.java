package com.SearchQueryRandomizer.app.searchqueryrandomizer_version2.searchqueryrandomizer_version2;

import java.util.ArrayList;

/**
 * Created by Haoran on 2015-08-17.
 */
public class QueryOptions {
    public ArrayList<String> originalQuery;
    public ArrayList<Integer> mapping;
    public ArrayList<String> newQuery = new ArrayList<String>();
    public int startingIndex;
    public int endingIndex;
    public QueryOptions(ArrayList<String> query, ArrayList<Integer> map, int start, int end) {
        originalQuery = query;
        mapping = map;
        startingIndex = start;
        endingIndex = end;
    }
}
