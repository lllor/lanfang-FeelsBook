package com.example.lorena_z.feelingbooklanfang1;
//https://blog.csdn.net/Learner9023/article/details/8432508
import java.util.Comparator;

/**
 * This class is used to compare two date String.
 */
public class sortClass implements Comparator {
    public int compare(Object arg0, Object arg1) {
        BaseStyle user0 = (BaseStyle) arg0;
        BaseStyle user1 = (BaseStyle) arg1;
        int flag = user0.getDate().compareTo(user1.getDate());
        return flag;
    }
}