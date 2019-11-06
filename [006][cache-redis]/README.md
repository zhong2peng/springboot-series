> 主要测试使用 `RedisTemplate` 操作 `Redis` 中的数据：
>
> - opsForValue：对应 String（字符串）
> - opsForZSet：对应 ZSet（有序集合）
> - opsForHash：对应 Hash（哈希）
> - opsForList：对应 List（列表）
> - opsForSet：对应 Set（集合）
> - opsForGeo：** 对应 GEO（地理位置）

## 参考

- spring-data-redis 官方文档：https://docs.spring.io/spring-data/redis/docs/2.1.6.RELEASE/reference/html/
- redis 文档：https://redis.io/documentation
- redis 中文文档：http://www.redis.cn/commands.html