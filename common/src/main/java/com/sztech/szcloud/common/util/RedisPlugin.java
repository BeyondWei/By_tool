package com.sztech.szcloud.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Description
 * @Author WanLi
 * @Date 2019/04/22 16:11
 */
@Slf4j
public class RedisPlugin {

    private static RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("redisTemplate");

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public static boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public static long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public static boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public static void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public static Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取key对应的对象
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T get(String key, Class<T> clazz) {
        String s = StringUtil.objectToString(get(key));
        if (!StringUtils.isEmpty(s)) {
            return JSON.toJavaObject(JSON.parseObject(s), clazz);
        }
        return null;
    }

    /**
     * 获取hash对应的对象
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T hGet(String key, String field, Class<T> clazz) {
        String value = StringUtil.objectToString(redisTemplate.opsForHash().get(key, field));
        return StringUtils.isEmpty(value) ? null : JSONObject.parseObject(value, (Type) clazz);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public static boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public static boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public static Object hGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public static boolean hSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public static boolean hSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public static void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public static boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * 获取key对应所有字段的值
     *
     * @param key
     * @return
     */
    public static Set<Object> hkeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取redis操作模板
     *
     * @return
     */
    public static RedisTemplate redisTemplate() {
        return redisTemplate;
    }

    /**
     * 获取分布式锁
     *
     * @param keyName
     * @param time    秒
     * @return
     */
    public static boolean getRedisLock(@NotNull String keyName, @NotNull String value, @NotNull Integer time) {
        //判断是否可以直接获取锁资源(可以重入)
        String lockValue = StringUtil.objectToString(redisTemplate.opsForValue().get(keyName));
        if (value.equals(lockValue)) {
            return true;
        }
        if (redisTemplate.opsForValue().setIfAbsent(keyName, value, time, TimeUnit.SECONDS)) {
            //创建守护线程为其续命
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep((time * 1000 / 3L));
                            redisTemplate.expire(keyName, time, TimeUnit.SECONDS);
                        } catch (Exception e) {
                            log.info("停止续命");
                        }
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.start();
            return true;
        }
        return false;
    }

    /**
     * 解锁
     */
    public static void unLock(@NotNull String keyName, @NotNull String value) throws Exception {
        String lockValue = StringUtil.objectToString(redisTemplate.opsForValue().get(keyName));
        if (lockValue == null || lockValue.equals(value)) {
            redisTemplate.delete(keyName);
            log.info("释放锁");
        } else {
            throw new Exception("无法解锁");
        }
    }

    /**
     * 尝试获取锁
     *
     * @param keyName
     * @param keyValue
     * @param time
     * @param tryTimes 尝试次数
     * @return
     */
    public static boolean tryRedisLock(@NotNull String keyName, @NotNull String keyValue, @NotNull Integer time,
                                       Integer tryTimes) {
        if (tryTimes != null) {
            while (true) {
                boolean redisLock = getRedisLock(keyName, keyValue, time);
                if (redisLock) {
                    return true;
                } else {
                    tryTimes--;
                    if (tryTimes == 0) {
                        return false;
                    }
                }
            }
        } else {
            return getRedisLock(keyName, keyValue, time);
        }
    }


}