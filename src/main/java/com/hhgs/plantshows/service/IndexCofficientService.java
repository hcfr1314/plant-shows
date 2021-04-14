package com.hhgs.plantshows.service;

import com.hhgs.plantshows.model.DO.IndexCofficient;

public interface IndexCofficientService {

    IndexCofficient queryIndexMessage();

    int editData (IndexCofficient indexCofficient);
}
