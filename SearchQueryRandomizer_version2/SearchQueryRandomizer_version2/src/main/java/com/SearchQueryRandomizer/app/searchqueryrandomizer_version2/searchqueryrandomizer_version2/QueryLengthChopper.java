package com.SearchQueryRandomizer.app.searchqueryrandomizer_version2.searchqueryrandomizer_version2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Haoran on 2015-08-16.
 */
public class QueryLengthChopper {

    public String chopQuery(String query, int option){
        if (query.equals("")){
            return query;
        }
        ArrayList<String> strs;
        strs = new ArrayList<String>(Arrays.asList(query.split(" ")));
        switch (option){
            case 0:{
                break;
            }
            case 1:{
                strs = chopQueryHelper(strs, 25);
                break;
            }
            case 2:{
                strs = chopQueryHelper(strs, 50);
                break;
            }
        }

        String str = "";
        for (String string: strs){
            str += string + " ";
        }
        return str;
    }

    public ArrayList<String> chopQueryHelper(ArrayList<String> strs, int percentOfWordsToDelete){
        int length = strs.size();
        ArrayList<Integer> arrayOfOnesAndZeroes = new ArrayList<Integer>(length);
        int numOfZeroes = Math.round(length*((float) percentOfWordsToDelete/100));
        int numOfOnes = length - numOfZeroes;

        fillArrayWithOnesAndZeroes(arrayOfOnesAndZeroes, numOfZeroes, 0);
        fillArrayWithOnesAndZeroes(arrayOfOnesAndZeroes, numOfOnes, 1);
        Collections.shuffle(arrayOfOnesAndZeroes);

        QueryOptions firstHalf = new QueryOptions(strs, arrayOfOnesAndZeroes, 0, length/2);
        QueryOptions secondHalf = new QueryOptions(strs, arrayOfOnesAndZeroes, length - length/2, length);

        MapperThread thread1 = new MapperThread(firstHalf);
        MapperThread thread2 = new MapperThread(secondHalf);
        thread1.run();
        thread2.run();

        ArrayList<String> returnArray = new ArrayList<String>();
        returnArray.addAll(firstHalf.newQuery);
        returnArray.addAll(secondHalf.newQuery);

        return returnArray;
    }

    public void fillArrayWithOnesAndZeroes(ArrayList<Integer> arrayOfOnesAndZeroes, int number, int oneOrZero){
        if (number == 0){
            return;
        }
        for (int i=1; i<=number; i++){
            arrayOfOnesAndZeroes.add(oneOrZero);
        }
    }

//    Old Python Code
    /*
    from random import shuffle

    file = open('titles2.txt', 'r')

    large_list = []
    for line in file:
        line_split = line.split()
        large_list.append(line_split)

    #print (large_list)

    final_list_75 = []
    final_list_50 = []
    for small_list in large_list:
        length = len(small_list)
        three_quarter = (length//4)*3
        quarter = length - three_quarter
        first_half = length//2
        second_half = length - first_half

        three_quarter_list = []
        for i in range(three_quarter):
            three_quarter_list.append(1)
        for i in range(quarter):
            three_quarter_list.append(0)
        shuffle(three_quarter_list)

        half_list = []
        for i in range(first_half):
            half_list.append(1)
        for i in range(second_half):
            half_list.append(0)
        shuffle(half_list)

        final_list_item_75 = []
        for i in range(len(small_list)):
            if three_quarter_list[i] == 1:
                final_list_item_75.append(small_list[i])
        final_list_75.append(final_list_item_75)

        final_list_item_50 = []
        for i in range(len(small_list)):
            if half_list[i] == 1:
                final_list_item_50.append(small_list[i])
        final_list_50.append(final_list_item_50)

    #print (final_list_75)
    final_list_75_str = []
    for single_list in final_list_75:
        string = ''
        for item in single_list:
            string = string + ' ' + item
        final_list_75_str.append(string)

    #print (final_list_50)
    final_list_50_str = []
    for single_list in final_list_50:
        string = ''
        for item in single_list:
            string = string + ' ' + item
        final_list_50_str.append(string)

    for string in final_list_75_str:
        print (string)
    print ('=====')
    for string in final_list_50_str:
        print (string)
*/
    public static void main(String[] args) {
        QueryLengthChopper q = new QueryLengthChopper();
        String get100 = q.chopQuery("a b c d e f g h i j", 0);
        String get75 = q.chopQuery("a b c d e f g h i j", 1);
        String get50 = q.chopQuery("a b c d e f g h i j", 2);

        System.out.println(get100);
        System.out.println(get75);
        System.out.println(get50);
    }

}

