package com.hqjg.config.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Created by 50676 on 2016/9/11.
 */
public class BaseUtil {


    public static Log log = LogFactory.getLog(BaseUtil.class);

    /**
     * 检验是否存在key
     * @param map
     * @param key
     * @return
     */
    public static boolean containsKey(Map<String, Object> map, String key) {
        boolean flag = false;
        if(map != null && map.containsKey(key)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 校验key是否相等
     * @param key1
     * @param key2
     * @return
     */
    public static boolean equals(String key1, String key2) {
        boolean flag = false;
        log.info("key1 = " + key1 + "key2 = " + key2);
        if(key1 != null && key2 != null
                && key1.equals(key2)) {
            flag = true;
        }
        log.info("flag = " + flag);
        return flag;
    }


    /**
     * 校验 key值是否为空
     * @param key
     * @return
     */
    public static boolean isNotNull(String key) {
        boolean flag = true;
        if(key == null || "".equals(key)) {
            flag = false;
        }
        return flag;
    }
}
