package com.hhgs.plantshows.service.impl;

import com.hhgs.plantshows.mapper.hh.BaseDeviceMapper;
import com.hhgs.plantshows.mapper.hh.DataCardingMapper;
import com.hhgs.plantshows.mapper.hh.PlantShowsMapper;
import com.hhgs.plantshows.mapper.hh.UitlMapper;
import com.hhgs.plantshows.model.DO.*;
import com.hhgs.plantshows.model.UtilModel.BasicDevice;
import com.hhgs.plantshows.service.BaseDeviceService;
import com.hhgs.plantshows.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BaseDeviceServiceImpl implements BaseDeviceService {

    @Autowired
    private BaseDeviceMapper baseDeviceMapper;

    @Autowired
    DataCardingMapper dataCardingMapper;

    @Autowired
    PlantShowsMapper plantShowsMapper;

    @Autowired
    UitlMapper uitlMapper;

    @Override
    public void batchInsert(List<BasicDevice> list) {
        baseDeviceMapper.batchInsert(list);
    }

    @Override
    public void batchInsertData(String plantName) {

        String tableName = plantShowsMapper.getPlantTableWithPlantName(plantName);

        List<PlantBigsys> plantBigsysList = dataCardingMapper.queryBigsys(plantName);


        for (PlantBigsys plantBigsys : plantBigsysList) {
            String bigsysName = plantBigsys.getBigsysName();
            int bigsysId = plantBigsys.getBigsysId();
            //获取小系统列表
            List<String> smallsysList = dataCardingMapper.querySmallsysByBigsys(tableName, bigsysName);

            List<BigsysSmallsys> bigsysSmallsysList = new ArrayList<>();
            List<SmallsysFirstsDevice> smallsysFirstsDeviceList = new ArrayList<>();
            List<FirstsDeviceSign> firstsDeviceSignList = new ArrayList<>();
            List<FirstsDeviceSecondsDevice> firstsDeviceSecondsDeviceList = new ArrayList<>();
            List<SecondsDeviceSign> secondsDeviceSignList = new ArrayList<>();
            List<SmallsysBracket> smallsysBracketList = new ArrayList<>();
            List<SmallsysComponent> smallsysComponentList = new ArrayList<>();


            //封装要插入的大系统所属小系统集合
            for (String smallsysName : smallsysList) {
                BigsysSmallsys bigsysSmallsys = new BigsysSmallsys();
                //给集合的每一个元素设置大系统id
                bigsysSmallsys.setBigsysId(bigsysId);
                //给集合的每一个元素设置小系统名称
                bigsysSmallsys.setSmallsysName(smallsysName);
                //给集合的每一个元素设置场站名称
                bigsysSmallsys.setPlantName(plantName);
                bigsysSmallsysList.add(bigsysSmallsys);
            }

            if(bigsysSmallsysList.size()>0) {
                uitlMapper.insertSmallsys(bigsysSmallsysList);
            }


            //封装要插入的小系统所属 设备集合/支架集合/组件集合
            for (String smallsysName : smallsysList) {
                //通过大系统名称和小系统名称获取所属 设备集合/支架集合/组件集合
                List<String> deviceList = dataCardingMapper.queryDeviceByBigsysAndSmallsys(tableName, bigsysName, smallsysName);
                List<String> bracketList = dataCardingMapper.queryBracketByBigsysAndSmallsys(tableName, bigsysName, smallsysName);
                List<String> componentList = dataCardingMapper.queryComponentByBigsysAndSmallsys(tableName, bigsysName, smallsysName);

                //获取该小系统id
                Integer smallsysId = dataCardingMapper.querySmallsysId(smallsysName, bigsysId);
                //设置即将插入的设备集合信息
                for (String deviceName : deviceList) {
                    SmallsysFirstsDevice smallsysFirstsDevice = new SmallsysFirstsDevice();
                    smallsysFirstsDevice.setSmallsysId(smallsysId);
                    smallsysFirstsDevice.setDeviceName(deviceName);
                    smallsysFirstsDevice.setPlantName(plantName);
                    smallsysFirstsDeviceList.add(smallsysFirstsDevice);
                }
                //设置即将插入的支架信息
                for (String bracketName : bracketList) {
                    SmallsysBracket smallsysBracket = new SmallsysBracket();
                    smallsysBracket.setSmallsysId(smallsysId);
                    smallsysBracket.setBracketName(bracketName);
                    smallsysBracket.setPlantName(plantName);
                    smallsysBracketList.add(smallsysBracket);
                }
                //设置即将插入的组件信息
                for (String componentName : componentList) {
                    SmallsysComponent smallsysComponent = new SmallsysComponent();
                    smallsysComponent.setSmallsysId(smallsysId);
                    smallsysComponent.setComponentName(componentName);
                    smallsysComponent.setPlantName(plantName);
                    smallsysComponentList.add(smallsysComponent);
                }
            }

            if(smallsysFirstsDeviceList.size()>0) {
                uitlMapper.insertFirstsDevice(smallsysFirstsDeviceList);
            }

            if(smallsysBracketList.size()>0) {
                uitlMapper.insertBracket(smallsysBracketList);
            }

            if(smallsysComponentList.size()>0) {
                uitlMapper.insertComponent(smallsysComponentList);
            }



            //给每个一级设备设置设备标识
            //给每个一级设备设置二级设备列表
            for (String smallsysName : smallsysList) {
                //通过大系统名称和小系统名称获取所属设备集合
                List<String> firstsDeviceList = dataCardingMapper.queryDeviceByBigsysAndSmallsys(tableName, bigsysName, smallsysName);
                for (String firstsDeviceName : firstsDeviceList) {
                    FirstsDeviceSign firstsDeviceSign = new FirstsDeviceSign();
                    //获取该小系统id
                    Integer smallsysId = dataCardingMapper.querySmallsysId(smallsysName, bigsysId);
                    //根据小系统id获取该小系统下的一级设备id
                    Integer firstsDeviceId = dataCardingMapper.queryFirstsDeviceId(firstsDeviceName, smallsysId);
                    String deviceSignName = dataCardingMapper.queryDeviceSignByBigsysAndSmallsysAndFirstsDeviceName(tableName, bigsysName, smallsysName, firstsDeviceName);

                    //获取一级设备下的二级设备集合
                    List<String> secondsDeviceList = dataCardingMapper.querySecondsDeviceByBigsysAndSmallsysAndFirstsDeviceName(tableName, bigsysName, smallsysName, firstsDeviceName);

                    if (firstsDeviceId != null) {
                        for (String secondsDeviceName : secondsDeviceList) {
                            FirstsDeviceSecondsDevice firstsDeviceSecondsDevice = new FirstsDeviceSecondsDevice();
                            firstsDeviceSecondsDevice.setFirstsDeviceId(firstsDeviceId);
                            firstsDeviceSecondsDevice.setSecondsDeviceName(secondsDeviceName);
                            firstsDeviceSecondsDevice.setPlantName(plantName);
                            firstsDeviceSecondsDeviceList.add(firstsDeviceSecondsDevice);
                        }

                        firstsDeviceSign.setDeviceId(firstsDeviceId);
                        firstsDeviceSign.setDeviceSignName(deviceSignName);
                        firstsDeviceSign.setPlantName(plantName);
                        firstsDeviceSignList.add(firstsDeviceSign);
                    }
                }
            }

            if (firstsDeviceSecondsDeviceList.size() > 0) {
                //大集合切割成小集合
                ListUtil<FirstsDeviceSecondsDevice> tempList = new ListUtil<>();
                List<List<FirstsDeviceSecondsDevice>> smList = tempList.bgListToSmList(firstsDeviceSecondsDeviceList, 1000);
                smList.forEach(ele ->
                        uitlMapper.insertFirstsDeviceSecondsDevice(ele)
                        );

            }

            if (firstsDeviceSignList.size() > 0) {
                uitlMapper.insertFirstsDeviceSign(firstsDeviceSignList);
            }

            //给每一个二级设备设置设备标识
            for (String smallsysName : smallsysList) {
                //通过大系统名称和小系统名称获取所属设备集合
                List<String> firstsDeviceList = dataCardingMapper.queryDeviceByBigsysAndSmallsys(tableName, bigsysName, smallsysName);
                for (String firstsDeviceName : firstsDeviceList) {
                    //获取该小系统id
                    Integer smallsysId = dataCardingMapper.querySmallsysId(smallsysName, bigsysId);
                    //根据小系统id获取该小系统下的一级设备id
                    Integer firstsDeviceId = dataCardingMapper.queryFirstsDeviceId(firstsDeviceName, smallsysId);
                    //获取一级设备下的二级设备集合
                    List<String> secondsDeviceList = dataCardingMapper.querySecondsDeviceByBigsysAndSmallsysAndFirstsDeviceName(tableName, bigsysName, smallsysName, firstsDeviceName);
                    if (firstsDeviceId != null) {
                        for (String secondsDeviceName : secondsDeviceList) {
                            Integer secondsDeviceId = dataCardingMapper.querySecondsDeviceId(secondsDeviceName, firstsDeviceId);
                            List<String> secondsSignNameList = dataCardingMapper.querySecondsDeviceSign(tableName, bigsysName, smallsysName, firstsDeviceName, secondsDeviceName);

                            if (secondsDeviceId != null) {
                                for (String secondsSignName : secondsSignNameList) {
                                    SecondsDeviceSign secondsDeviceSign = new SecondsDeviceSign();
                                    secondsDeviceSign.setDeviceId(secondsDeviceId);
                                    secondsDeviceSign.setDeviceSignName(secondsSignName);
                                    secondsDeviceSign.setPlantName(plantName);
                                    secondsDeviceSignList.add(secondsDeviceSign);
                                }
                            }
                        }
                    }
                }
            }

            if (secondsDeviceSignList.size() > 0) {
                uitlMapper.insertSecondsDeviceSign(secondsDeviceSignList);
            }

            //更新层级生成状态为已生成层级
            plantShowsMapper.updateHierarchyStatus(1, plantName);
        }
    }
}
