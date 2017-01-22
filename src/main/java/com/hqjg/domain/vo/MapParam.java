package com.hqjg.domain.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 50676 on 2016/9/10.
 */
public class MapParam {
    private Map<String, Object> map;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "MapParam{" +
                "map=" + map +
                '}';
    }
}
