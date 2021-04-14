package com.hhgs.plantshows.service.impl;
import com.hhgs.plantshows.mapper.hh.BaseAttrOfPointMapper;
import com.hhgs.plantshows.mapper.hh.BasicDeviceModelMapper;
import com.hhgs.plantshows.model.BO.BaseDeviceModelAndAttr;
import com.hhgs.plantshows.model.DO.BaseDeviceModel;
import com.hhgs.plantshows.model.DO.BasicAttribute;
import com.hhgs.plantshows.model.UtilModel.BasicAttributeModel;
import com.hhgs.plantshows.service.BaseAttrOfPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BaseAttrOfPointServiceImpl implements BaseAttrOfPointService {

    @Autowired
    private BaseAttrOfPointMapper mapper;

    @Autowired
    private BasicDeviceModelMapper basicDeviceModelMapper;

    @Override
    public List<BasicAttributeModel> queryByType(int i) {
        return mapper.queryByType(i);
    }

    @Override
    public int addAttrToDevice(String deviceName, int deviceType, BasicAttribute attr) {

        //首先查询该
        List<BaseDeviceModelAndAttr> list = basicDeviceModelMapper.queryByType(deviceType, deviceName);

        if (list == null || list.size() == 0) {
            //没有该设备，添加设备
            return -1;
        }

        String attrNameCN = attr.getAttributeNameCN();
        String attrNameEn = attr.getAttributeNameEN();

        List<BaseDeviceModelAndAttr> cnList = list.stream().filter(ele -> attrNameCN.equals(ele.getAttributeNameCN())).collect(Collectors.toList());
        List<BaseDeviceModelAndAttr> enList = list.stream().filter(ele -> attrNameEn.equals(ele.getAttributeNameEN())).collect(Collectors.toList());

        int attrId = 0;

        if (cnList.size() > 0 || enList.size() > 0) {
            //return -2;
            // 属性中文名称或者英文名称重复,使用库中已有的设备属性
            attrId = enList.get(0).getAttributeID();

        }else {

            //添加一条属性数据
            mapper.insertAttr(attr);
            attrId = attr.getId();
        }


        //添加一条设备数据
        List<BaseDeviceModelAndAttr> noAttrIdList = list.stream().filter(ele -> ele.getAttributeID() == -1).collect(Collectors.toList());

        BaseDeviceModel model = new BaseDeviceModel();
        model.setDeviceName(deviceName);
        model.setAttributeID(attrId);
        model.setDeviceType(deviceType);
        model.setEffective(1);

        if (noAttrIdList != null && noAttrIdList.size() > 0) {
            return basicDeviceModelMapper.editData(model);
        } else {
            return basicDeviceModelMapper.addData(model);
        }

    }

}
