package com.SearchQueryRandomizer.app.searchqueryrandomizer_version2.searchqueryrandomizer_version2;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Haoran on 2015-08-17.
 */
public class QueryLengthChopperTest extends TestCase {


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

    public void testChopQueryHelper() throws Exception {
        fail();
    }

    public void testFillArrayWithOnesAndZeroes() throws Exception {
        fail();
    }

    @Test
    public void testChopQuery1() throws Exception {

    }
}