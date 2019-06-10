package com.pyg.common.utils;


import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * redis工具类
 *
 * @author liubaoyu
 * @date 2015年4月2日 下午5:46:38
 */
@Component
public class RedisUtil implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

    private static ShardedJedisPool shardedJedisPool;

    // set方法成功标记
    private static final String SUCCESS = "OK";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        shardedJedisPool = (ShardedJedisPool) applicationContext.getBean("shardedJedisPool");
    }

    private static void returnResource(ShardedJedis shardedJedis) {
        if (null != shardedJedisPool) {
            shardedJedisPool.returnResource(shardedJedis);
        }
    }

    /**
     * 添加string
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean setString(String key, String value) {
        ShardedJedis shardedJedis = null;

        try {
            shardedJedis = shardedJedisPool.getResource();
            String result = shardedJedis.set(key, value);
            if (null != result && result.equals(SUCCESS)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("Set string error!", e);
            return false;
        } finally {
            returnResource(shardedJedis);
        }
    }

    /**
     * 添加string，并设置过期时间
     *
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    public static boolean setString(String key, int seconds, String value) {
        ShardedJedis shardedJedis = null;

        try {
            shardedJedis = shardedJedisPool.getResource();

            String result = shardedJedis.setex(key, seconds, value);
            if (null != result && result.equals(SUCCESS)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("Set string expire error!", e);
            return false;
        } finally {
            returnResource(shardedJedis);
        }
    }

    /**
     * 获取string
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        ShardedJedis shardedJedis = null;

        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.get(key);
        } catch (Exception e) {
            LOGGER.error("Get string error!", e);
            return null;
        } finally {
            returnResource(shardedJedis);
        }
    }

    /**
     * 添加bean
     *
     * @param key
     * @param bean
     * @return
     */
    public static boolean setBean(String key, Object bean) {
        ShardedJedis shardedJedis = null;

        try {
            shardedJedis = shardedJedisPool.getResource();
            Gson gson = new Gson();
            String result = shardedJedis.set(key, gson.toJson(bean));
            if (null != result && result.equals(SUCCESS)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("Set bean error!", e);
            return false;
        } finally {
            returnResource(shardedJedis);
        }
    }

    /**
     * 添加bean，并设置过期时间
     *
     * @param key
     * @param bean
     * @return
     */
    public static boolean setBean(String key, int seconds, Object bean) {
        ShardedJedis shardedJedis = null;

        try {
            shardedJedis = shardedJedisPool.getResource();
            Gson gson = new Gson();
            String result = shardedJedis.setex(key, seconds, gson.toJson(bean));
            if (null != result && result.equals(SUCCESS)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("Set bean expire error!", e);
            return false;
        } finally {
            returnResource(shardedJedis);
        }
    }

    /**
     * 获取bean
     *
     * @param key
     * @param clazz
     * @return
     */
    public static <T> T getBean(String key, Class<T> clazz) {
        ShardedJedis shardedJedis = null;

        try {
            shardedJedis = shardedJedisPool.getResource();
            String value = shardedJedis.get(key);
            Gson gson = new Gson();
            if (null != value) {
                return gson.fromJson(value, clazz);
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("Get bean error!", e);
            return null;
        } finally {
            returnResource(shardedJedis);
        }
    }

    /**
     * 添加beans
     *
     * @param key
     * @param bean
     * @return
     */
    public static boolean setBeans(String key, List<?> beans) {
        ShardedJedis shardedJedis = null;

        try {
            shardedJedis = shardedJedisPool.getResource();
            Gson gson = new Gson();
            String result = shardedJedis.set(key, gson.toJson(beans));
            if (null != result && result.equals(SUCCESS)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("Set beans error!", e);
            return false;
        } finally {
            returnResource(shardedJedis);
        }
    }


    /**
     * 获取beans
     *
     * @param key
     * @param type 例: Type type = new TypeToken<List<User>>(){}.getType();
     * @return
     */
    public static <T> List<T> getBeans(String key, Type type) {
        ShardedJedis shardedJedis = null;

        try {
            shardedJedis = shardedJedisPool.getResource();
            String value = shardedJedis.get(key);
            if (null != value) {
                Gson gson = new Gson();
                return gson.fromJson(value, type);
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("Get beans error!", e);
            return null;
        } finally {
            returnResource(shardedJedis);
        }
    }

    /**
     * 删除key
     */
    public static boolean removeKey(String key){
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.del(key);
            return true;
        } catch (Exception e) {
            LOGGER.error("Get beans error!", e);
            return false;
        } finally {
            returnResource(shardedJedis);
        }

    }
    
    /**
     * 判断key是否存在
     */
    public static Boolean exists(String key){
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.exists(key);
        } catch (Exception e) {
            LOGGER.error("exists:" + key + " 操作失败", e);
            return false;
        } finally {
            returnResource(shardedJedis);
        }
    }
    
    /**
     * <p>设置key value,如果key已经存在则返回0,nx==> not exist</p>
     * @param key
     * @param value
     * @return 成功返回1 如果存在 和 发生异常 返回 0
     */
    public static Long setnx(String key ,String value){
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.setnx(key, value);
        } catch (Exception e) {
            LOGGER.error("setnx:" + key + " 操作失败", e);
            return 0L;
        } finally {
            returnResource(shardedJedis);
        }
    }
    
    /**
     * <p>设置key value并制定这个键值的有效期</p>
     * @param key
     * @param value
     * @param seconds 单位:秒
     * @return 成功返回OK 失败和异常返回null
     */
    public static String setex(String key,String value,int seconds){
        ShardedJedis shardedJedis = null;
        String res = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            res = shardedJedis.setex(key, seconds, value);
        } catch (Exception e) {
            LOGGER.error("setex:" + key + " 操作失败", e);
        } finally {
            returnResource(shardedJedis);
        }
        return res;
    }
    
    /**
     * <p>设置key的有效期</p>
     * @param key
     * @param seconds
     * @return
     */
    public static Long expire(String key, int seconds){
        ShardedJedis shardedJedis = null;
        Long res = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            res = shardedJedis.expire(key, seconds);
        } catch (Exception e) {
            LOGGER.error("expire:" + key + " 操作失败", e);
        } finally {
            returnResource(shardedJedis);
        }
        return res;
    }
    
    /**
     * <p>通过key 对value进行加值+1操作,当value不是int类型时会返回错误,当key不存在是则value为1</p>
     * @param key
     * @return 加值后的结果
     */
    public static Long incr(String key){
        ShardedJedis shardedJedis = null;
        Long res = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            res = shardedJedis.incr(key);
        } catch (Exception e) {
            LOGGER.error("incr:" + key + " 操作失败", e);
        } finally {
            returnResource(shardedJedis);
        }
        return res;
    }
    
    /**
     * <p>通过key给加指定的integer值,如果key不存在,则这是integer为该值</p>
     * @param key
     * @param value
     * @return
     */
    public static Long incrBy(String key,Long integer){
        ShardedJedis shardedJedis = null;
        Long res = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            res = shardedJedis.incrBy(key, integer);
        } catch (Exception e) {
            LOGGER.error("integer:" + key + " 操作失败", e);
        } finally {
            returnResource(shardedJedis);
        }
        return res;
    }
    
    /**
     * <p>对key的值做减减操作,如果key不存在,则设置key为-1</p>
     * @param key
     * @return
     */
    public static Long decr(String key) {
        ShardedJedis shardedJedis = null;
        Long res = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            res = shardedJedis.decr(key);
        } catch (Exception e) {
            LOGGER.error("decr:" + key + " 操作失败", e);
        } finally {
            returnResource(shardedJedis);
        }
        return res;
    }
    
    /**
     * <p>减去指定的值</p>
     * @param key
     * @param integer
     * @return
     */
    public static Long decrBy(String key,Long integer){
        ShardedJedis shardedJedis = null;
        Long res = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            res = shardedJedis.decrBy(key, integer);
        } catch (Exception e) {
            LOGGER.error("decrBy:" + key + " 操作失败", e);
        } finally {
            returnResource(shardedJedis);
        }
        return res;
    }
    
    /**
     * <p>通过key给field设置指定的值,如果key不存在,则先创建</p>
     * @param key
     * @param field 字段
     * @param value
     * @return 如果存在返回0 异常返回null
     */
    public static Long hset(String key,String field,String value) {
        ShardedJedis shardedJedis = null;
        Long res = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            res = shardedJedis.hset(key, field, value);
        } catch (Exception e) {
            LOGGER.error("hset:" + key + " 操作失败", e);
        } finally {
            returnResource(shardedJedis);
        }
        return res;
    }
    
    /**
     * <p>通过key给field设置指定的值,如果key不存在则先创建,如果field已经存在,返回0</p>
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static Long hsetnx(String key,String field,String value){
        ShardedJedis shardedJedis = null;
        Long res = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            res = shardedJedis.hsetnx(key, field, value);
        } catch (Exception e) {
            LOGGER.error("hsetnx:" + key + " 操作失败", e);
        } finally {
            returnResource(shardedJedis);
        }
        return res;
    }
    
    /**
     * <p>通过key 和 field 获取指定的 value</p>
     * @param key
     * @param field
     * @return 没有返回null
     */
    public static String hget(String key, String field){
        ShardedJedis shardedJedis = null;
        String res = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            res = shardedJedis.hget(key, field);
        } catch (Exception e) {
            LOGGER.error("hget:" + key + " 操作失败", e);
        } finally {
            returnResource(shardedJedis);
        }
        return res;
    }
    
    /**
     * <p>通过key和field判断是否有指定的value存在</p>
     * @param key
     * @param field
     * @return
     */
    public static Boolean hexists(String key , String field){
        ShardedJedis shardedJedis = null;
        Boolean res = false;
        try {
            shardedJedis = shardedJedisPool.getResource();
            res = shardedJedis.hexists(key, field);
        } catch (Exception e) {
            LOGGER.error("hexists:" + key + " 操作失败", e);
        } finally {
            returnResource(shardedJedis);
        }
        return res;
    }
    
    /**
     * <p>通过key 删除指定的 field </p>
     * @param key
     * @param fields 可以是 一个 field 也可以是 一个数组
     * @return
     */
    public static Long hdel(String key , String...fields){
        ShardedJedis shardedJedis = null;
        Long res = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            res = shardedJedis.hdel(key, fields);
        } catch (Exception e) {
            LOGGER.error("hdel:" + key + " 操作失败", e);
        } finally {
            returnResource(shardedJedis);
        }
        return res;
    }
    
    /**
     * <p>通过key同时设置 hash的多个field</p>
     * @param key
     * @param hash
     * @return 返回OK 异常返回null
     */
    public static String hmset(String key, Map<String, String> hash){
        ShardedJedis shardedJedis = null;
        String res = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            res = shardedJedis.hmset(key, hash);
        } catch (Exception e) {
            LOGGER.error("hmset:" + key + " 操作失败", e);
        } finally {
            returnResource(shardedJedis);
        }
        return res;
    }
    
    /**
     * <p>通过key 和 fields 获取指定的value 如果没有对应的value则返回null</p>
     * @param key
     * @param fields 可以使 一个String 也可以是 String数组
     * @return 
     */
    public static List<String> hmget(String key, String...fields){
        ShardedJedis shardedJedis = null;
        List<String> res = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            res = shardedJedis.hmget(key, fields);
        } catch (Exception e) {
            LOGGER.error("hmset:" + key + " 操作失败", e);
        } finally {
            returnResource(shardedJedis);
        }
        return res;
    }

}
