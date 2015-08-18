package com.SearchQueryRandomizer.app.searchqueryrandomizer_version2.searchqueryrandomizer_version2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Haoran on 2015-08-17.
 */
public class QueryLengthChopperTest2 {

    @Test
    public void testChopQuery() throws Exception {
        QueryLengthChopper q = new QueryLengthChopper();
        String get100 = q.chopQuery("a b c d e f g h i j", 0);
        String get75 = q.chopQuery("a b c d e f g h i j", 1);
        String get50 = q.chopQuery("a b c d e f g h i j", 2);

        System.out.println(get100);
        System.out.println(get75);
        System.out.println(get50);

        fail();

    }
}