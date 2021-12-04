# API接口文档

## 前后端接口

### 查询服务:

#### /seckill/getAll

```json
[
    {
        title: '潘婷氨基酸乳液修洗护套装',
        disc: '限量抢购300件',
        price: '9.9',
        pic: './upload/goods-img-1.jpg'
    },
]
```

#### /detail?productId=1

```json
{
    seckillId: '1',
    seckillInventory: '123',
    startTime: '2021-12-3 10:00:00',
    endTime: '2021-12-3 12:00:00',
    title: '潘婷氨基酸乳液修洗护套装',
    disc: '限量抢购300件',
    price: '9.9',
    pic: './upload/goods-img-1.jpg'
}
```

#### /inventory?seckillId=1

```json
{
    seckillInventory:'123'
}
```

#### /queryOrder?orderId=1

```json
{
    data:{
        orderStatus: 'success' || 'waiting' || 'failure',
    },
}
```

### 订单服务:/order

#### /create

post

```json
{
    seckillId: '1',
    userId: '123',
    amount: '2',
}
```

response

```json
{
    data:{
        orderId: '1',
    },
}
```

## 后台管理接口

### /create

post

```json
{
    title: 'abc',
    disc: 'abc',
    price: 123,
    inventory: 123,
    startTime: date,
    endTime: date,
    pic: file
}
```

response

```json
{
    
}
```

