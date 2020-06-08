package com.dlyong.yichu.security.service;

public interface RedisService {

    /**
     * 存储属性
     * @param key
     * @param value
     * @param time
     */
    void set(String key,String value,Long time);

    /**
     * 存储属性
     * @param key
     * @param value
     */
    void set(String key,String value);

    /**
     * 获取数据
     * @param key
     * @return
     */
    String get(String key);
    /*todo*/

}
