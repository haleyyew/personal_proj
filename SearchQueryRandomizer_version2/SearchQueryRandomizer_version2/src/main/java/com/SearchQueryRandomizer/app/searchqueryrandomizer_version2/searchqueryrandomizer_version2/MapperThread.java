package com.SearchQueryRandomizer.app.searchqueryrandomizer_version2.searchqueryrandomizer_version2;

/**
 * Created by Haoran on 2015-08-17.
 */
public class MapperThread implements Runnable{
    QueryOptions options;
    public MapperThread(QueryOptions options){
        this.options = options;
    }
    @Override
    public void run()
    {
        for (int i = options.startingIndex; i < options.endingIndex; i++ ){
            if (options.mapping.get(i) == 1){
                options.newQuery.add(options.originalQuery.get(i));
            }
        }
    }
}
