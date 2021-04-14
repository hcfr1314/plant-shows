package com.hhgs.plantshows.common;

/**
 * 规范的异常信息
 */
public class MessageConstant {

    /**
     * 根据id查询不到对应的数据
     */
    public static final String QUERY_BY_ID_RESULT_IS_NULL="根据id查询不到对应数据，请联系技术人员";
    /**
     * id不能为空
     */
    public static final String ID_CAN_NOT_BE_NULL="主键id不能为空，请联系技术人员";
    /**
     * 父节点主键不能为空，请联系技术人员
     */
    public static final String PARENT_ID_CAN_NOT_BE_NULL="父节点主键不能为空，请联系技术人员";
    /**
     * 名称不能为空
     */
    public static final String NAME_CAN_NOT_BE_NULL="名称不能为空，请联系技术人员";
    /**
     * 名称重复，导致添加失败
     */
    public static final String   FAILED_TO_ADD_DUPLICATE_NAMES  ="名称重复，导致添加失败";
    /**
     * 名称重复，导致编辑失败
     */
    public static final String   FAILED_TO_EDIT_DUPLICATE_NAMES  ="名称重复，导致编辑失败";
    /**
     * 名称重复，验证失败
     */
    public static final String   FAILED_TO_CHEK_DUPLICATE_NAMES  ="名称重复，验证失败";


    /**
     * 英文名称重复，导致添加失败
     */
    public static final String   FAILED_TO_ADD_DUPLICATE_EN_NAMES  ="英文名称重复，导致添加失败";
    /**
     * 英文名称重复，导致编辑失败
     */
    public static final String   FAILED_TO_EDIT_DUPLICATE_EN_NAMES  ="英文名称重复，导致编辑失败";
    /**
     * 英文名称重复，验证失败
     */
    public static final String   FAILED_TO_CHEK_DUPLICATE_EN_NAMES  ="英文名称重复，验证失败";
    /**
     * 请选择模板
     */
    public static final String    PLEASE_SELECT_THE_TEMPLATE   ="请选择模板";
    /**
     * 导入异常：不支持的数据库类型
     */
    public static final String   EXCEPTIONS_FOR_UNSUPPORTED_DATABASE_TYPES   ="异常：不支持的数据库类型";
    /**
     * 字典id不能为空
     */
    public static final String   DICTIONARY_ID_CANNOT_BE_EMPTY   ="字典id不能为空";
    /**
     * 缺少必要的系统基础数据
     */
    public static final String   LACK_OF_NECESSARY_SYSTEM_BASIC_DATA   ="缺少必要的系统基础数据";
    /**
     * 分页异常
     */
    public static final String   PAGING_EXCEPTION   ="分页异常";
    /**
     * 数组长度异常
     */
    public static final String   ARRAY_LENGTH_ANOMALY   ="数组长度异常";

    /**
     * 系统字典只能添加三级
     */
    public static final String   SYSTEM_DICTIONARIES_CAN_ONLY_BE_ADDED_AT_THREE_LEVELS ="系统字典只能添加三级";

    public static final String   PLEASE_SELECT_THE_CORRECT_NODE_CONNECTION_OPERATION ="请选择正确节点接续操作";
    /**
     * 该类型的节点不支持添加子节点
     */
    public static final String   THIS_TYPE_OF_NODE_DOES_NOT_SUPPORT_ADDING_CHILD_NODES ="该类型的节点不支持添加子节点";
    /**
     * 该类型的节点不支持修改
     */
    public static final String   THIS_TYPE_OF_NODE_DOES_NOT_SUPPORT_EDIT ="该类型的节点不支持修改";
    /**
     * 节点类型不能为空 验证失败
     */
    public static final String   NODE_TYPE_CANNOT_FAIL_FOR_NULL_VALIDATION ="节点类型不能为空 验证失败";
    /**
     * 缺少必要的参数
     */
    public static final String   MISSING_REQUIRED_PARAMETERS ="缺少必要的参数";
    /**
     * 表类型相同
     */
    public static final String   TABLE_TYPES_ARE_THE_SAME ="表类型相同";
    /**
     * 请选择kks模板节点在加载数据
     */
    public static final String   PLEASE_SELECT_KKS_TEMPLATE_NODE_TO_LOAD_DATA ="请选择kks模板节点在加载数据";
    /**
     * 请在系统基础数据中配置对应的table
     */
    public static final String   PLEASE_CONFIGURE_THE_CORRESPONDING_TABLE_IN_THE_SYSTEM_BASIC_DATA ="请在系统基础数据中配置对应的table";
    /**
     * db 中不存在该表
     */
    public static final String   THIS_TABLE_DOES_NOT_EXIST_IN_DB ="db 中不存在该表";
    /**
     * kks数据为空，请加载系统设备模板生成数据
     */
    public static final String   KKS_DATA_IS_EMPTY_PLEASE_LOAD_THE_SYSTEM_DEVICE_TEMPLATE_TO_GENERATE_DATA ="kks数据为空，请加载系统设备模板生成数据";
    /**
     * 验证失败
     */
    public static final String    VALIDATION_FAILED  ="验证失败";
    /**
     * 系统-设备模板数据为空，请配置完后在加载
     */
    public static final String    THE_SYSTEM_DEVICE_TEMPLATE_DATA_IS_EMPTY_PLEASE_CONFIGURE_IT_AND_LOAD_IT  ="系统-设备模板数据为空，请配置完后在加载";

    /**
     * mysql函数递归失败
     */
    public static final String    MYSQL_FUNCTION_RECURSIVE_FAILURE  ="mysql函数递归失败";

    /**
     * 查询集合为空
     */
    public static final String    QUERY_COLLECT_IS_NULL  ="查询集合为空";
    /**
     * 子节点为空不能操作
     */
    public static final String    THE_CHILD_NODE_IS_EMPTY_AND_CANNOT_BE_OPERATED  ="子节点为空不能操作";
    /**
     * 类型参数错误
     */
    public static final String    TYPE_PARAMETER_ERROR  ="类型参数错误";
    /**
     * 类型参数为空
     */
    public static final String    TYPE_PARAMETER_IS_NULL  ="类型参数为空";
    /**
     * 复制次数大于0
     */
    public static final String NUMBER_OF_COPIES_GREATER_THAN_0="复制次数大于0";


    public static final String DUPLICATE_ABBREVIATION_NAME="缩略语名称重复";
    /**
     * 缩略语已经被引用，无法删除
     */
    public static final String THE_ABBREVIATION_HAS_BEEN_REFERENCED_AND_CANNOT_BE_DELETED="缩略语已经被引用，无法删除";
    /**
     * 缓存失效
     */
    public static final String  CACHE_INVALIDATION ="缓存失效";
    /**
     * 中文名称为空
     */
    public static final String  ENGLISH_NAME_IS_NULL ="中文名称为空";
    /**
     * 中文名称为空
     */
    public static final String  CHIANA_NAME_IS_NULL ="中文名称为空";
    /**
     * 逻辑路径为空
     */
    public static final String  LOGICAL_PATH_IS_NULL ="逻辑路径为空";
    /**
     * BCS字典被引用，不能删除
     */
    public static final String  BCS_REFERENCED_CANNOT_BE_DELETED ="BCS字典被引用，不能删除";
    /**
     * 设备型号不能为空
     */
    public static final String  DEVICE_MODEL_CANNOT_BE_EMPTY ="设备型号不能为空";
    /**
     * 设备类型不能为空
     */
    public static final String  DEVICE_TYPE_CANNOT_BE_EMPTY ="设备类型不能为空";
    /**
     * 该型号的部件已经添加 请勿重复添加
     */
    public static final String   PARTS_OF_THIS_MODEL_HAVE_BEEN_ADDED_PLEASE_DO_NOT_ADD_AGAIN  ="该型号的部件已经添加 请勿重复添加";
    /**
     * 字典中不存在，不允许添加
     */
    public static final String  DICTIONARY_DOES_NOT_EXIST_ADDING_IS_NOT_ALLOWED ="字典中不存在，不允许添加";
}
