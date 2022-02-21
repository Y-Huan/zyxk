package com.zyy.zyxk.common.constant;


public enum ErrorCode {
    SUCCESS(200, "操作成功"),

    USER_ADD_ERROR(10000, "用户添加失败"),
    USER_DELETE_ERROR(10001, "用户删除失败"),
    USER_UPDATE_ERROR(10002, "用户更新失败"),
    USER_GET_ERROR(10003, "用户获取失败"),
    USER_GET_LIST_ERROR(10004, "用户列表获取失败"),
    USER_UPDATE_ERROR_NO_ACCOUNT(10005,"用户更新失败，无此用户"),
    USER_ROLE_ADD_ERROR(10006,"用户角色关系添加失败"),
    USER_ROLE_LIST_ERROR(10007,"用户角色关系列表获取失败"),
    USER_RESOURCE_LIST_ERROR(10008,"用户资源列表获取失败"),
    USER_EXISTED(10009,"帐号已存在"),
    RESOURCE_ADD_ERROR(10010, "资源添加失败"),
    RESOURCE_LIST_ERROR(10011,"资源列表获取失败"),
    USER_LOGIN_ERROR(10012,"用户登录失败"),
    USER_REGISTER_ERROR(10013,"用户注册失败"),
    USER_UPDATE_ERROR_NO_USER_ID(10014,"用户更新失败: 用户id不能为空"),
    PASSWORD_OR_ACCOUNT_ERROR(100115, "密码或者账号错误！"),
    ACCOUNT_STOP_USING(10016, "账号已停用，请联系管理员!"),
    ACCOUNT_UNUSUAL(10017,"账户异常，请联系管理员！"),
    BAD_PARAM(10018,"此功能出现异常，请联系管理员！"),
    TOKEN_EXPIRE(10019,"用户身份过期,请重新登录!"),
    TOKEN_NOT_IN_REQUEST_HEADER(10020,"认证信息缺失,请重新登录尝试"),
    TOKEN_EXCEPTION(10021,"认证无效或过期，请重新登录尝试"),
    COPY_ERROR(10022,"拷贝对象属性出错" ),
    USER_NOT_EXISTS(10023,"用户不存在" ),
    ERROR_ACCOUNT(10024,"用户名或密码错误"),
    NO_AUTH(10025,"用户无权限访问此接口"),
    EMPTY_TOKEN(10026,"无认证凭证"),
    NO_PASSWORD(10027,"密码不能为空"),
    NO_USER_PHONE(10028,"手机号不能为空"),
    NO_NEW_PASSWORD(10029,"新密码不能为空"),
    NO_USER_ID(10030,"用户id不能为空"),
    NO_USERNAME(10031,"用户名不能为空"),
    No_Role_Name(10032,"角色名称不能为空"),
    No_Role_Id(10033,"角色id不能为空"),
    No_Authorities(10034,"角色所拥有的的权限不能为空"),
    SERVER_ERROR(10035, "服务器异常"),
    ROLE_ADD_ERROR(10036, "角色添加失败"),
    ROLE_LIST_ERROR(10037,"角色列表获取失败"),
    ROLE_RESOURCE_ADD_ERROR(10038,"角色资源关系添加失败"),
    Role_Auth_List_Get_Error(10039,"角色权限关系列表获取失败"),
    Role_Name_Existed(10040,"该角色已存在"),
    Role_Id_Invalid(10041,"无效的角色id"),
    Role_Delete_Error(10042,"角色删除失败"),
    Role_Auth_List_Update_Error(10043,"角色权限关系列表更新失败"),
    AUTHORITY_TYPE_NULL(10044,"权限类型为空"),
    AUTHORITY_NULL(10045,"未匹配到权限信息"),
    AUTHORITY_NAME_ERROR(10046,"权限名已存在"),
    ROLE_PLESE_ADD(10047,"请先创建角色"),
    Major_Name_existed(10048,"该专业名称已存在"),
    Major_Id_Invalid(10049,"无效的专业id"),
    No_Major_Name(10050,"专业名称不能为空"),
    SCHOOOL_NAME_NOT_NULL(10051,"学校名已存在"),
    Clase_Id_Invalid(10052,"无效的班级id"),
    No_Clase_Name(10053,"班级名称不能为空"),

    BIND_ERROR(99900, "参数校验失败"),
    CONSTRAINT_ERROR(99910, "全局异常"),
    JWT_ERROR(99920,"认证失败,请重新登录后尝试"),
    COMMON_ERROR(99999, "操作失败"),
    EXCL_NULL_ERROR(99930,"EXCL为空"),
    EXCL_TYPE_ERROR(99940,"文件格式错误");




    private int code;
    private String message;

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorCode getEnum(int code){
        for(ErrorCode item: ErrorCode.values()){
            if(code == item.getCode()){
                return item;
            }
        }

        return ErrorCode.COMMON_ERROR;
    }
}
