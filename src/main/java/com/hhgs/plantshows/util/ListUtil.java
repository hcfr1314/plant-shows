package com.hhgs.plantshows.util;

import com.hhgs.plantshows.model.DO.ImportData;

import java.util.ArrayList;
import java.util.List;

public class  ListUtil<T> {


    public  List<List<T>> bgListToSmList(List<T> bigList, int subLength) {

        List<List<T>> finalList = new ArrayList<>();
        int size = bigList.size();
        int num = size % subLength == 0 ? size / subLength : (int) Math.floor(size / subLength + 1);
        for (int i = 0; i < num; i++) {
            int maxIndex = (i + 1) * subLength;
            int endIndex = maxIndex > size ? size : maxIndex;
            List<T> smallList = bigList.subList(subLength * i, endIndex);
            finalList.add(smallList);
        }
        return finalList;
    }
}
