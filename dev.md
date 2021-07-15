# 后端开发文档

## API文档

### 认证 API

|    功能描述    |      url      | method |                           参数说明                           |
| :------------: | :-----------: | :----: | :----------------------------------------------------------: |
| 微信小程序登陆 | /api/login/wx |  POST  | code 表示调用 wx.login 得到的授权码<br />NJUID 表示登陆用户的学号 |
| QQ 小程序登陆  | /api/login/qq |  POST  |                             同上                             |



### 用户API

|   功能描述   |          url           | 参数说明 |
| :----------: | :--------------------: | :------: |
|   身份认证   |    /api/user/login     |          |
| 获取用户信息 |  /api/user/user/{id}   |          |
|  管理员认证  | /api/user/isadmin/{id} |          |



### 物品API

|     功能描述     |        url        | method |                           参数说明                           |
| :--------------: | :---------------: | :----: | :----------------------------------------------------------: |
|   获取物品列表   |    /api/items     |  GET   | page 表示页数,<br />user_id 表示发布者,<br />description 表示物品描述<br /> tags 表示标签，为 List\<String>。 |
|     发布失物     |    /api/items     |  POST  |                         物品信息表单                         |
|   获取失物详情   |  /api/items/{id}  |  GET   |                                                              |
|     删除物品     |  /api/items/{id}  | DELETE |                       需要验证用户身份                       |
| 获取当日物品清单 | /api/items/today  |  GET   |                                                              |
|       感谢       | /api/items/thank  |  POST  | thanked_id 表示被感谢的用户id<br />sender_id 表示发出感谢的用户id（后端是否能直接获取？） |
|       举报       | /api/items/report |  POST  | sender_id 表示发起举报的用户 id<br />item_id 表示被举报的物品id<br />msg 表示举报理由 |
|   获取标签清单   |  /api/items/tags  |  GET   |                                                              |



### 图片API

| 功能描述 |          url           | method |            参数说明             |          返回值          |
| :------: | :--------------------: | :----: | :-----------------------------: | :----------------------: |
| 上传照片 |      /api/images       |  POST  |       file 存储上传的图片       | 图片在服务器上的真实名字 |
| 下载照片 | /api/images/{fileName} |  GET   | fileName 表示要下载的图片的名字 |           照片           |
| 删除照片 | /api/images/{fileName} | DELETE | fileName 表示要删除的图片的名字 |    删除成功/删除失败     |



### 公告API

|     功能描述      | method |       url        |                           参数说明                           |
| :---------------: | :----: | :--------------: | :----------------------------------------------------------: |
|   获取所有公告    |  GET   |    api/posts     | page 表示页数<br />description 表示物品描述（进行搜索）<br />days 表示获取最近days 天的公告 |
|   获取公告详情    |  GET   |  api/posts/{id}  |                           id 表示                            |
| 获取最近公告（x） |  GET   | api/posts/recent |                days 表示获取最近days 天的公告                |