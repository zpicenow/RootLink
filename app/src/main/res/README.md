## 部署前端流程

### clone 前端代码
将前端代码 [rootlink](https://github.com/Means88/rootlink) 放于项目同级目录，如
```
- code
|- yeelink
|- rootlink
```

### 执行脚本
在前端目录下，执行 `npm run release`

### 其他情况
代码不在同级目录的，执行 `npm run release -- -b [后端代码路径]`。
脚本会判断两个项目的 `package.json` 中的 `version` 属性，如果后端代码不能兼容前端代码（[语义化版本号](http://semver.org/lang/zh-CN/)），会发出警告。
在开发阶段，`version` 一直使用 `1.0.0`，在发行之后，根据语义化版本号发行版本。


## 部署后端流程

### clone 前端代码
将后端代码 [yeelink](https://git.oschina.net/shanchuanc/yeelink.git) 放于项目同级目录，如
```
- code
|- yeelink
|- rootlink
```

### 执行脚本
- 在后端目录下，执行 `npm install`
- 启动项目 执行 `make proStart`
- 如果项目已经启动的前提下，想重启项目执行 `make proRestart`


# url :
# port :

### 注意：
* 所有路由，除了静态文件的请求，都需要加上/api,例如注册: /api/register*
* 修改了登录注册的路由*
* 博客的添加路由改变*
* 嵌入式设备要添加 key,无论是post还是get方法，都是在url添加key=

# 通用响应格式
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: STRING || OBJECT
}
```
 * status code:
 *   200-OK
 *   4xx-Invalid Request
 *   400-Bad Request 请求参数或请求格式错误
 *   401-Unauthorized 请求需要的用户状态不正确
 *   403-Forbidden 服务器禁止用户得到响应
 *   5xx-Server Error
 *   500-Internal Server Error


静态目录在目录public下

# 用户模块
## 登陆和注册和退出
### 注册接口:
**/register/getKey**//获取验证码
Method:GET
```json
RequestData:{
    email: STRING
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {}
}
```



**/register**
Method:POST
```json
RequestData:{
    username: STRING,
    password: STRING,
    email: STRING,
    code: STRING //验证码
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {}
}
```

### 登录接口:
**/login**
Method:POST
```json
RequestData:{
     username: STRING,／／无论是username还是email都是这个
     
     password: STRING
     rememberMe: boolean //(false)
}
或者
RequestData:{
     token: STRING //会过期，用于前端记住密码选项
     key: STRING， //用于嵌入式设备的权限登录，此key可通过/user/updateKey修改，否则永不过期
     rememberMe: boolean //(默认true)
}
```
响应格式:
cookies:LoginToken=......................
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {
    	LoginToken : STRING,
    	token: STRING //选择记住密码时不为null,最多记住30天,但是每次登录都会更新token
    }
}
```

### 退出登录接口:
**/logout**
Method:POST
```json
RequestData:{
    token: STRING //null(allow)
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {
    	loginStatus: false
    }
}
```

### 验证登录接口:
**/loginValidate**
Method:GET
```json
RequestData:{
	
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {loginStatus: true || false }
}
```

### 更改key接口: 用于嵌入式设备
**/user/updateKey**
Method:GET
```json
RequestData:{

}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {key:STRING}
}
```


### 获取key接口: 
**/user/getKey**
Method:GET
```json
RequestData:{

}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {key:STRING}
}
```

# 公告模块
### 获取接口:
**/blog/index**
Method:GET
```json
RequestData:{
     page: INT,//allowNull(true),默认为1,获取的第几页
     count: INT //allowNull(true),默认为10，获取的条数
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {list : `对象数组`}
}
```

### 添加接口:
**/admin/blog/add**
**管理员才有的功能：测试账号name3 密码123**
Method:POST
```json
RequestData:{
     text : String //allowNull(false) 
     time : String //allowNull(false) 
     label : String //allowNull(false) 
     title : String //allowNull(false) 
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {}
}
```


# 设备模块
### 获取接口:
**/device/all**
Method:GET
```json
RequestData:{
     
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {devices: `对象数组`}
}
```


### 获取单个设备接口:
**/device/get**
Method:GET
```json
RequestData:{
     deviceId: 
     
     
     //用于获取某设备下在某时期的所有记录
     startTime: //allowNUll(true),时间戳如1490615191847
     endTime: //allowNUll(true),时间戳,如1490615191847
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {}
}
```


### 添加接口:
**/device/add**
Method:POST
```json
RequestData:{
     name: STRING //allowNull(false),
     description: STRING //allowNull(true)
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {device: }
}
```

### 删除接口:
**/device/delete**
Method:POST
```json
RequestData:{
     deviceId:  //allowNull(false)
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {count: }
}
```

### 更新接口:
**/device/update**
Method:POST
```json
RequestData:{
     deviceId:  //allowNull(false)
     name: STRING,
     description: STRING
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {count: }
}
```



# 传感器模块
### 获取接口:
**/sensor/all**
Method:GET
```json
RequestData:{
     deviceId: INT //allowNull(false)
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {sensors: `对象数组`}
}
```


### 添加接口:
**/sensor/add**
Method:POST
```json
RequestData:{
     name: STRING //allowNull(false),
     description: STRING //allowNull(true),
     type: STRING //allowNull(false),
     deviceId: INT //allowNull(false),
     unit: STRING //单位 数值类型的单位是用户自己设定，只有一个，例如 摄氏度，GPS类型是经纬度（固定,不需要传参）
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {sensor: }
}
```


### 更新接口:
**/sensor/update**
Method:POST
```json
RequestData:{
     name: STRING //allowNull(false),
     description: STRING //allowNull(true),
     DeviceId: INT //allowNull(false),
     type: STRING //allowNull(false),
     unit: STRING //单位 数值类型的单位是用户自己设定，只有一个，例如 摄氏度，GPS类型是经纬度（固定,不需要传参）
     sensorId: STRING
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {sensor: }
}
```

### 删除接口:
**/sensor/add**
Method:POST
```json
RequestData:{
     sensorId: string //allowNull(false)
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {count: }
}
```


### 上传数据接口:
**/sensor/upload**
Method:POST
```json
RequestData:{
     sensorId: string//allowNull(false)
     value1: STRING
     value2: STRING
     ...
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: { }
}
```


### 获取数据接口:
**/sensor/getValue**
Method:GET
```json
RequestData:{
     sensorId: string//allowNull(false)
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: { }
}
```


# 触发器模块
### 获取某触发器状态接口:
**/trigger/status**
//如果是嵌入式设备 /trigger/status?q=1,前端不需要管
Method:GET
```json
RequestData:{
     triggerId: INT //allowNull(false),
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {trigger:}
}
```

### 添加触发器接口:
**/trigger/add**
Method:POST
```json
RequestData:{
     name: STRING //allowNull(false)
     status: INT(只能是1 或者 0)，默认是0

}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {trigger:}
}
```



### 获取某设备所有触发器接口:
**/trigger/all**
Method:GET
```json
RequestData:{
     deviceId: 

}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {triggers:}
}
```



### 删除触发器接口:
**/trigger/delete**
Method:GET
```json
RequestData:{
     triggerId:
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {}
}
```

### 更改触发器状态接口:
**/trigger/control**
Method:GET
```json
RequestData:{
     triggerId: INT //触发器的id
     status: INT//只能是1 或者 0
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {status:}
}
```



# 用户添加指令模块
### 添加指令接口:
**/directive/add**
Method:POST
```json
RequestData:{
     operation: String //allowNull(false),命令
	 
	 status: ,//0 或者 1
     triggerId: ,
     或者
     sensorId:
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {}
}
```

### 更新指令接口:
**/directive/update**
Method:POST
```json
RequestData:{
     	operation: String //allowNull(false),命令
     	
     	 status: ,//0 或者 1
     	 triggerId: ,
  		 或者
  		 sensorId: 
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {}
}
```

### 获取指令接口:
**/directive/all**
Method:GET
```json
RequestData:{

}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {

    }
}
```


### 删除指令接口:
**/directive/delete**
Method:POST
```json
RequestData:{
     operation: String //allowNull(false),命令
}
```
响应格式:
```json
Response:{
    error: STRING,
    status: INTERGER,
    msg: {}
}
```

### 添加图片
**/uploadImage**
Method:POST
```json
RequestData:{
     image: FILE ,
     name : String
}
```
