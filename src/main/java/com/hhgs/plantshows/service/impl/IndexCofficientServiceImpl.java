package com.hhgs.plantshows.service.impl;

import com.hhgs.plantshows.mapper.hh.IndexCofficientMapper;
import com.hhgs.plantshows.model.DO.IndexCofficient;
import com.hhgs.plantshows.service.IndexCofficientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IndexCofficientServiceImpl implements IndexCofficientService {

    @Autowired
    private IndexCofficientMapper indexCofficientMapper;

    @Override
    public IndexCofficient queryIndexMessage() {

        IndexCofficient indexCofficient = indexCofficientMapper.queryIndexMessage();

        return indexCofficient;
    }

    @Override
    public int editData(IndexCofficient indexCofficient) {
        int i = indexCofficientMapper.editData(indexCofficient);
        return i;
    }
}
