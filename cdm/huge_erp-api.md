
**大地海洋-进销存管理系统 API**


**简介**：<p>大地海洋-进销存管理系统 API 1.0 操作文档</p>


**HOST**:localhost:7071

**联系人**:虎哥数字

**Version**:1.0

**接口路径**：/v2/api-docs


# 权限管理

## 新增权限


**接口描述**:


**接口地址**:`/auth/add`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime|   | query | false |string  |    |
|createdBy|   | query | false |integer  |    |
|creator|   | query | false |string  |    |
|parentId|   | query | false |integer  |    |
|resourceId|   | query | false |integer  |    |
|resourceName|   | query | false |string  |    |
|resourcePermission|   | query | false |string  |    |
|resourceStatus|   | query | false |integer  |    |
|resourceType|   | query | false |integer  |    |
|resourceUrl|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |object  |    |
|message|   |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取权限列表


**接口描述**:


**接口地址**:`/auth/getAuthorities`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|pageNo| pageNo  | query | false |string  |    |
|pageSize| pageSize  | query | false |string  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {
		"current": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"createdBy": 0,
				"creator": "",
				"parentId": 0,
				"resourceId": 0,
				"resourceName": "",
				"resourcePermission": "",
				"resourceStatus": 0,
				"resourceType": 0,
				"resourceUrl": "",
				"updateTime": ""
			}
		],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |IPage«ResourceVo»  | IPage«ResourceVo»   |
|message|   |string  |    |



**schema属性说明**




**IPage«ResourceVo»**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|current |    |integer(int64)  |    |
|pages |    |integer(int64)  |    |
|records |    |array  | ResourceVo   |
|searchCount |    |boolean  |    |
|size |    |integer(int64)  |    |
|total |    |integer(int64)  |    |

**ResourceVo**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|createTime |    |string(date-time)  |    |
|createdBy |    |integer(int64)  |    |
|creator |    |string  |    |
|parentId |    |integer(int64)  |    |
|resourceId |    |integer(int64)  |    |
|resourceName |    |string  |    |
|resourcePermission |    |string  |    |
|resourceStatus |    |integer(int32)  |    |
|resourceType |    |integer(int32)  |    |
|resourceUrl |    |string  |    |
|updateTime |    |string(date-time)  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response«IPage«ResourceVo»»|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 用户管理

## 新增用户


**接口描述**:


**接口地址**:`/user/add`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime|   | query | false |string  |    |
|createdBy|   | query | false |integer  |    |
|creator|   | query | false |string  |    |
|email|   | query | false |string  |    |
|isValid|   | query | false |boolean  |    |
|newPassword|   | query | false |string  |    |
|nickName|   | query | false |string  |    |
|password|   | query | false |string  |    |
|realName|   | query | false |string  |    |
|salt|   | query | false |string  |    |
|sex|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |
|userId|   | query | false |integer  |    |
|userPhone|   | query | true |string  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |object  |    |
|message|   |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## addUserRole


**接口描述**:


**接口地址**:`/user/addUserRole`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime|   | query | false |string  |    |
|createdBy|   | query | false |integer  |    |
|creator|   | query | false |string  |    |
|roleId|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |
|userId|   | query | false |integer  |    |
|userRoleId|   | query | false |integer  |    |
|userRoleStatus|   | query | false |integer  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |object  |    |
|message|   |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 修改密码


**接口描述**:


**接口地址**:`/user/changePassword`


**请求方式**：`PUT`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|newPassword| newPassword  | query | false |string  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |object  |    |
|message|   |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 删除用户


**接口描述**:


**接口地址**:`/user/deleteUser`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|userId| userId  | query | false |integer  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": true,
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |boolean  |    |
|message|   |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response«boolean»|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## freeAuth


**接口描述**:


**接口地址**:`/user/freeAuth`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |object  |    |
|message|   |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取用户


**接口描述**:


**接口地址**:`/user/getUser`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|userId| userId  | query | false |string  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {
		"createTime": "",
		"createdBy": 0,
		"creator": "",
		"email": "",
		"nickName": "",
		"realName": "",
		"sex": 0,
		"updateTime": "",
		"userId": 0,
		"userPhone": ""
	},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |UserViewVo  | UserViewVo   |
|message|   |string  |    |



**schema属性说明**




**UserViewVo**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|createTime |    |string(date-time)  |    |
|createdBy |    |integer(int64)  |    |
|creator |    |string  |    |
|email |    |string  |    |
|nickName |    |string  |    |
|realName |    |string  |    |
|sex |    |integer(int32)  |    |
|updateTime |    |string(date-time)  |    |
|userId |    |integer(int64)  |    |
|userPhone |    |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response«UserViewVo»|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## getUserResources


**接口描述**:


**接口地址**:`/user/getUserResources`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|userId| userId  | query | false |integer  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": [
		{}
	],
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |array  | Map«string,object»   |
|message|   |string  |    |



**schema属性说明**




**Map«string,object»**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response«List«Map«string,object»»»|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## getUserRoles


**接口描述**:


**接口地址**:`/user/getUserRoles`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|pageNo| pageNo  | query | false |string  |    |
|pageSize| pageSize  | query | false |string  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {
		"current": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"createdBy": 0,
				"creator": "",
				"roleId": 0,
				"updateTime": "",
				"userId": 0,
				"userRoleId": 0,
				"userRoleStatus": 0
			}
		],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |IPage«UserRoleVo»  | IPage«UserRoleVo»   |
|message|   |string  |    |



**schema属性说明**




**IPage«UserRoleVo»**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|current |    |integer(int64)  |    |
|pages |    |integer(int64)  |    |
|records |    |array  | UserRoleVo   |
|searchCount |    |boolean  |    |
|size |    |integer(int64)  |    |
|total |    |integer(int64)  |    |

**UserRoleVo**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|createTime |    |string(date-time)  |    |
|createdBy |    |integer(int64)  |    |
|creator |    |string  |    |
|roleId |    |integer(int64)  |    |
|updateTime |    |string(date-time)  |    |
|userId |    |integer(int64)  |    |
|userRoleId |    |integer(int64)  |    |
|userRoleStatus |    |integer(int32)  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response«IPage«UserRoleVo»»|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取用户列表


**接口描述**:


**接口地址**:`/user/getUsers`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|pageNo| pageNo  | query | false |string  |    |
|pageSize| pageSize  | query | false |string  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {
		"current": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"createdBy": 0,
				"creator": "",
				"email": "",
				"nickName": "",
				"realName": "",
				"sex": 0,
				"updateTime": "",
				"userId": 0,
				"userPhone": ""
			}
		],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |IPage«UserViewVo»  | IPage«UserViewVo»   |
|message|   |string  |    |



**schema属性说明**




**IPage«UserViewVo»**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|current |    |integer(int64)  |    |
|pages |    |integer(int64)  |    |
|records |    |array  | UserViewVo   |
|searchCount |    |boolean  |    |
|size |    |integer(int64)  |    |
|total |    |integer(int64)  |    |

**UserViewVo**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|createTime |    |string(date-time)  |    |
|createdBy |    |integer(int64)  |    |
|creator |    |string  |    |
|email |    |string  |    |
|nickName |    |string  |    |
|realName |    |string  |    |
|sex |    |integer(int32)  |    |
|updateTime |    |string(date-time)  |    |
|userId |    |integer(int64)  |    |
|userPhone |    |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response«IPage«UserViewVo»»|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 用户登录


**接口描述**:


**接口地址**:`/user/login`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|password| password  | query | false |string  |    |
|username| username  | query | false |string  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |object  |    |
|message|   |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 修改用户


**接口描述**:


**接口地址**:`/user/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime|   | query | false |string  |    |
|createdBy|   | query | false |integer  |    |
|creator|   | query | false |string  |    |
|email|   | query | false |string  |    |
|isValid|   | query | false |boolean  |    |
|newPassword|   | query | false |string  |    |
|nickName|   | query | false |string  |    |
|password|   | query | false |string  |    |
|realName|   | query | false |string  |    |
|salt|   | query | false |string  |    |
|sex|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |
|userId|   | query | false |integer  |    |
|userPhone|   | query | true |string  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |object  |    |
|message|   |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 角色管理
## 新增角色


**接口描述**:


**接口地址**:`/role/add`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime|   | query | false |string  |    |
|createdBy|   | query | false |integer  |    |
|creator|   | query | false |string  |    |
|roleId|   | query | false |integer  |    |
|roleKey|   | query | false |string  |    |
|roleName|   | query | false |string  |    |
|roleStatus|   | query | false |integer  |    |
|roleType|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |object  |    |
|message|   |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## addRoleResource


**接口描述**:


**接口地址**:`/role/addRoleResource`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime|   | query | false |string  |    |
|createdBy|   | query | false |integer  |    |
|creator|   | query | false |string  |    |
|resourceId|   | query | false |integer  |    |
|roleId|   | query | false |integer  |    |
|roleResourceId|   | query | false |integer  |    |
|roleResourceStatus|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |object  |    |
|message|   |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## getRoleResources


**接口描述**:


**接口地址**:`/role/getRoleResources`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|pageNo| pageNo  | query | false |string  |    |
|pageSize| pageSize  | query | false |string  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {
		"current": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"createdBy": 0,
				"creator": "",
				"resourceId": 0,
				"roleId": 0,
				"roleResourceId": 0,
				"roleResourceStatus": 0,
				"updateTime": ""
			}
		],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |IPage«RoleResourceVo»  | IPage«RoleResourceVo»   |
|message|   |string  |    |



**schema属性说明**




**IPage«RoleResourceVo»**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|current |    |integer(int64)  |    |
|pages |    |integer(int64)  |    |
|records |    |array  | RoleResourceVo   |
|searchCount |    |boolean  |    |
|size |    |integer(int64)  |    |
|total |    |integer(int64)  |    |

**RoleResourceVo**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|createTime |    |string(date-time)  |    |
|createdBy |    |integer(int64)  |    |
|creator |    |string  |    |
|resourceId |    |integer(int64)  |    |
|roleId |    |integer(int64)  |    |
|roleResourceId |    |integer(int64)  |    |
|roleResourceStatus |    |integer(int32)  |    |
|updateTime |    |string(date-time)  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response«IPage«RoleResourceVo»»|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取角色列表


**接口描述**:


**接口地址**:`/role/getRoles`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|pageNo| pageNo  | query | false |string  |    |
|pageSize| pageSize  | query | false |string  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {
		"current": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"createdBy": 0,
				"creator": "",
				"roleId": 0,
				"roleKey": "",
				"roleName": "",
				"roleStatus": 0,
				"roleType": 0,
				"updateTime": ""
			}
		],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"message": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |integer(int32)  | integer(int32)   |
|data|   |IPage«RoleVo»  | IPage«RoleVo»   |
|message|   |string  |    |



**schema属性说明**




**IPage«RoleVo»**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|current |    |integer(int64)  |    |
|pages |    |integer(int64)  |    |
|records |    |array  | RoleVo   |
|searchCount |    |boolean  |    |
|size |    |integer(int64)  |    |
|total |    |integer(int64)  |    |

**RoleVo**

| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | ------------------|--------|----------- |
|createTime |    |string(date-time)  |    |
|createdBy |    |integer(int64)  |    |
|creator |    |string  |    |
|roleId |    |integer(int64)  |    |
|roleKey |    |string  |    |
|roleName |    |string  |    |
|roleStatus |    |integer(int32)  |    |
|roleType |    |integer(int32)  |    |
|updateTime |    |string(date-time)  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Response«IPage«RoleVo»»|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
