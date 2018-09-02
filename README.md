# LOLSearchQQ
英雄联盟，ID查询码QQ

v1.0.1 
1.修复当查询不到QQ时，空指针异常导致请求得不到相应

V1.0.2 
1.添加定时刷新Token

V1.0.3
1. 查询记录，排序把最近查询放前，历史查询放在后
2. 添加加群连接
4. 用查询码查询该查询码已经查询的再次查询时直接显示QQ
 
V1.0.4
1. 修改查询timeout 为 5000ms
2. 添加通过后台修改 公告，而不是固定写死的公告。
3. 优化同时添加多个查询码时，会出现部分查询码添加失败问题。
4. 修改后台认证失败时返回到认证页面。
5. 优化页面交互。
warning: 
    message: template might not exist or might not be accessible by any of the configured Template Resolvers 
    solution:
        1.确定模板是否在默认templates文件夹里面，并且路径要和返回的View名字一致。
        2.return "/admin/code"; 这样写是不对的，应该把开头的斜杠去掉，改成：return "admin/code"。


### TODO
v0.1
1. 添加通过后台修改 公告，而不是固定写死的公告。(已完成)
2. 优化同时添加多个查询码时，会出现部分查询码添加失败问题。(已完成)
v0.2
1. 细化查询Code，如查询已使用的code，未使用的code等。