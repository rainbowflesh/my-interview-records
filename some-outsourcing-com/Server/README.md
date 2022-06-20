# LiteBlog

后端

TODO:

1. 后台登录 √
2. 发布文章 (markdown，纯文本， 富文本都可) √
3. 可以匿名评论，点赞 √
4. 页面缓存 √
5. 支持全站全文搜索 √

![meme](../../asset/http_status_code_meme.jpeg)

## API

### 登录

- post
- url: /v1/login
    - body
        - username: string
        - password: string

### 登出

- /v1/logout
    - 没做

### 获取文章列表

- get

- url: /v1/blogs

### 获取单个文章

- get
- url: /v1/blog/{id}
    - id 为博客的 id
    - 实例：/v1/blog/1
        - 响应: {"code": 200,"msg": "操作成功","data": {"id": 1,"user_id": null,"title": "1","description":"111:content","content": "111","created": "2022-06-19","status": 1,"remark": "233","votes": 0
  }
}

### 全文搜索

- post
- url: /v1/blog/search
    - body
        - title: string
        - content: string
        - description: string
            - 响应: {"code": 200,"msg": "操作成功","data": [{"id": 3,"title": "1","description": "2","content": "3"}]}

### 发送文章

- post
- url: /v1/blog/post
    - body
        - user_id: int
        - title: string
        - description': string
        - content: string
            - 响应: {"code": 200,"msg": "操作成功","data": [{"id": 3,"title": "1","description": "2","content": "3"}]}

### 评论

- post
- url: /v1/blog/comment
    - body
        - id: int
        - remark: string
            - 注释: id 是文章 id, remark 是评论内容

### 点赞

- post
- url: /v1/blog/votes
    - body
        - id: int
        - votes: int
            - 注释: id 是文章 id, votes 是点赞数值
