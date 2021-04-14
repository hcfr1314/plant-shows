package com.hhgs.plantshows.service;

import java.util.List;

public interface SaveDataFromHbaseToCSV {

    void saveDataToCSV(String plantName, List<String> orgIds, String startTime, String endTime);
}
