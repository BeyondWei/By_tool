package com.sztech.szcloud.common.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * spring boot 2 默认使用 lettuce 而不是 jedis
 * 参考： http://wsghawk.com/2018/03/19/Redis
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.cache.redis.time-to-live?:60000}")
    private String ttl;


    /**
     * 默认情况下，key 值存在问题，当方法入参相同时，key 值也相同，这样会造成不同的方法读取相同的缓存，从而造成异常
     * 修改后的 key 值为 className + methodName + 参数值列表，可以支持使用 @Cacheable 时不指定 Key
     *
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName()).append("#");
            sb.append(method.getName()).append("#");
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }


    /**
     * 配置 CacheManager
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    @SuppressWarnings("unchecked")
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
        // RedisCache 需要一个 RedisCacheWriter 来实现读写 Redisnü'r
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        // SerializationPair 用于 Java 对象和 Redis 之间的序列化和反序列化
        // Spring Boot 默认采用 JdkSerializationRedisSerializer 的二进制数据序列化方式
        // 使用该方式，保存在 redis 中的值是人类无法阅读的乱码，并且该 Serializer 要求目标类必须实现 Serializable 接口
        // 本示例中，使用 StringRedisSerializer 来序列化和反序列化 redis 的 key 值
        RedisSerializationContext.SerializationPair keySerializationPair = RedisSerializationContext.SerializationPair
                .fromSerializer(new StringRedisSerializer());
        // 使用 Jackson2JsonRedisSerializer 来序列化和反序列化 redis 的 value 值
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisSerializationContext.SerializationPair valueSerializationPair = RedisSerializationContext.SerializationPair
                .fromSerializer(jackson2JsonRedisSerializer);
        // 构造一个 RedisCache 的配置对象，设置缓存过期时间和 Key、Value 的序列化机制
        // 设置 生命周期 ttl
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMillis(Long.parseLong(ttl)))
                .serializeKeysWith(keySerializationPair).serializeValuesWith(valueSerializationPair);

        RedisCacheManager redisCacheManager = new RedisCacheManager(writer, config);
        redisCacheManager.setTransactionAware(true);
        return redisCacheManager;

    }

    /**
     * Redis操作模板类
     * @return
     */
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
