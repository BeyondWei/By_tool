package com.sztech.szcloud.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rest 返回结果解析工具
 *
 * @author 无垠
 */
@Slf4j
public class ParseUtil {


    /**
     * 将json中的对象全部解析出来，放入Map中便于获取参数
     * 注意：1、如果父级对象和子级对象有相同的Key值，将会被覆盖
     *
     * @return
     */
    public static Map<String, Object> parseJsonToMap(Map<String, Object> map) {
        Map<String, Object> restMap = new HashMap();
        for (String key : map.keySet()) {
            if (map.get(key) instanceof JSONObject) {
                Map<String, Object> jsonMap = parseJsonToMap(((JSONObject) map.get(key)).toJSONString());
                assert jsonMap != null;
                restMap.putAll(parseJsonToMap(jsonMap));
            } else if (map.get(key) instanceof JSONArray) {
                ArrayList<Map<String, Object>> arrayList = parseJsonToList(map.get(key).toString());
                for (Map<String, Object> map1: arrayList) {
                    restMap.putAll(parseJsonToMap(map1));
                }
            } else {
                restMap.put(key, map.get(key));
            }
        }
        return restMap;
    }

    /**
     * 对json格式的解析，返回Map，不能解析json数组的格式
     *
     * @param jsonString
     * @return
     */
    public static Map<String, Object> parseJsonToMap(String jsonString) {
        try {
            Map<String, Object> map = JSONObject.parseObject(jsonString, new TypeReference<Map<String, Object>>() {
            });
            return map;
        } catch (Exception e) {
            log.info("JsonToMap解析失败");
            return null;
        }
    }

    /**
     * 对json格式的解析，返回List
     *
     * @param jsonString
     * @return
     */
    public static ArrayList parseJsonToList(String jsonString) {
        ArrayList list = JSONObject.parseObject(jsonString, new ArrayList<Map<String, Object>>().getClass());
        return list;
    }


    /**
     * String 转 org.dom4j.Document
     *
     * @param xml
     * @return
     */
    private static Document strToDocument(String xml) {
        try {
            return DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            return null;
        }
    }

    /**
     * org.dom4j.Document 转  com.alibaba.fastjson.JSONObject
     *
     * @param xml
     * @return
     */
    private static JSONObject documentToJSONObject(String xml) {
        return elementToJSONObject(strToDocument(xml).getRootElement());
    }

    /**
     * org.dom4j.Element 转  com.alibaba.fastjson.JSONObject
     *
     * @param node
     * @return
     */
    private static JSONObject elementToJSONObject(Element node) {
        JSONObject result = new JSONObject();
        // 当前节点的名称、文本内容和属性
        List<Attribute> listAttr = node.attributes();
        for (Attribute attr : listAttr) {
            result.put(attr.getName(), attr.getValue());
        }
        // 递归遍历当前节点所有的子节点
        List<Element> listElement = node.elements();
        if (!listElement.isEmpty()) {
            for (Element e : listElement) {
                if (e.attributes().isEmpty() && e.elements().isEmpty()) {
                    result.put(e.getName(), e.getTextTrim());
                }// 沒有则将当前节点作为上级节点的属性对待
                else {
                    if (!result.containsKey(e.getName())) {
                        result.put(e.getName(), new JSONArray());
                    }
                    ((JSONArray) result.get(e.getName())).add(elementToJSONObject(e));
                }
            }
        }
        return result;
    }

    /**
     * 解析XML
     *
     * @param xmlString
     * @return
     */
    public static Map<String, Object> parseXMLToMap(String xmlString) {
        JSONObject jsonObject = documentToJSONObject(xmlString);
        Map<String, Object> map = parseJsonToMap(jsonObject.toJSONString());
        return map;
    }



}
