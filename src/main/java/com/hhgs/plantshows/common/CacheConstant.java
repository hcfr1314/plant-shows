package com.hhgs.plantshows.common;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类仅用于保存需要从缓存中获取值的常量
 */
@Component
public class CacheConstant {

    /***********************************************************SYSTEM_DATA部分**************************************

     /* *
     * 字典的节点类型  sign  dic  title
     */
    public static List<String> SYSTEM_DIC_NODE_TYPE=new ArrayList<>();
    public static List<String> SYSTEM_DIC_NODE_TYPE_BAK=new ArrayList<>();

    /**
     * BCS属性类型
     */
    public static Map<String,String> BCS_ATTR_TYPE_MAP=new HashMap<>();
    /**
     * 设备类型和字典类型对应关系key
     */
    public static Map<String,String> DIC_TYPE_RELATION_MAP=new HashMap<>();


    public static HttpSession session;

    /**
     * 每页最大条数
     */
    public static int maxPageSize=100;
    /**
     * 每页最小条数
     */
    public static int minPageSize=1;
    /**
     * 最大页码数
     */
    public static int maxPageNum=1000;
    /**
     * 最小页面数
     */
    public static int minPageNum=1;
    /**
     * 根据数据对象查询 默认每页的大小
     */
    public static int avatarPageNum=0;
    public static int avatarPageSize=20000;
    /**
     * 后台访问avatar需要用户名和密码
     */
    public static String avatarUserName="admin";
    public static String avatarPassWord="123456";

    /**
     * 当前系统数据库类型
     */
    public static String DB_TYPE="MYSQL";
    /**
     * 当前系统数据库的schema
     */
    public static String DB_SCHEMA="kks_bcs";

    /**
     * KKS的table_id
     */
    public static String KKS_ID="KKS_ID";
    /**
     * BCS的table_id
     */
    public static String BCS_ID="BCS_ID";
    /**
     * KKS相关的函数
     */
    public static String KKS_FUNCTION_ID="KKS_FUNCTION_ID";



    /**
     * ARRAY_LENGTH
     */
    public static Integer ARRAY_LENGTH=12;

    /**
     * SYSTEM_DEVICE_TEMPLATE_ARRAY_LENGTH
     */
    public static Integer SYSTEM_DEVICE_TEMPLATE_ARRAY_LENGTH=25;
    /**
     * 服务器启动多久回执行
     */
    public static Integer CRON_DURING=10;
    /**
     * 数据库批量提交  每次提交的数量
     * 我在本机受环境限制  每次提交5000  服务器上会快点
     */
    public static Integer DB_SUBMIT_SIZE=5000;
    /**
     * 数据库批量删除  每次提交的数量
     * 我在本机受环境限制  每次提交500  服务器上会快点
     */
    public static Integer DB_DELETE_SIZE=500;
    /**
     * 数据库查询  in参数个数
     */
    public static Integer DB_IN_SIZE=1024;

    /**
     * 数据库批次查询量
     */
    public static Integer DB_FIND_SIZE=10000;

    /**
     * 批量更新个数
     */
    public static Integer DB_UPDATE_SIZE=1024;

    /**
     * MYSQL递归返回的长度
     */
    public static Integer MYSQL_FUNCTION_RETURN_LENGTH=3000;
    /*
     * 从avatar同步用户的url
     * select username as jobNumber,fullname as name,userid as avatarId from ddaas_users
     * all_user_info
     */
    public static String TABLE_NAME="tableName";


    public static String SYSNC_USER_FROM_AVATAR_URL="http://172.15.106.220/avatar/data/queryMetaObjData?metaObjectName=all_user_info";

    public static String LOGIN_TO_AVATAR_URL="http://172.15.106.220/avatar/sys/userrole/loginCheck";
    /**
     * 拓扑层级别
     */
    public static Integer TOPOLOGY_LAYER_LEVEL=5;
    /**
     * 工艺标识
     */
    public static String PROCESS_IDENTIFICATION_ID="88229ebc50d14f7ba3f4d9d5450a7c31";


    public static String ACTION = "动作";

    public static String RESET = "复位";



}
