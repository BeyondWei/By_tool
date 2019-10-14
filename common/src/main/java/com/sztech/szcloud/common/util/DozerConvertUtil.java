package com.sztech.szcloud.common.util;

import com.github.pagehelper.PageInfo;
import org.dozer.Mapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Company 杭州数政科技有限公司
 * @Author 有成
 * @Date 2019-05-22 15:20
 * @Description
 */
public class DozerConvertUtil {

    private static Mapper dozerMapper;

    public static void setDozerMapper(Mapper dozerMapper) {
        DozerConvertUtil.dozerMapper = dozerMapper;
    }

    public static <T, S> T convert(S s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        return dozerMapper.map(s, clz);
    }

    public static <T, S> List<T> convert(List<S> s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        for (S vs : s) {
            list.add(dozerMapper.map(vs, clz));
        }
        return list;
    }

    public static <T, S> Set<T> convert(Set<S> s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        Set<T> set = new HashSet<T>();
        for (S vs : s) {
            set.add(dozerMapper.map(vs, clz));
        }
        return set;
    }

    public static <T, S> T[] convert(S[] s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) Array.newInstance(clz, s.length);
        for (int i = 0; i < s.length; i++) {
            arr[i] = dozerMapper.map(s[i], clz);
        }
        return arr;
    }

    public static <T, S> PageInfo<T> convert(PageInfo<S> s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(s.getPageNum());
        pageInfo.setPageSize(s.getPageSize());
        pageInfo.setTotal(s.getTotal());
        List<T> list = new ArrayList<T>();
        for (S vs : s.getList()) {
            list.add(dozerMapper.map(vs, clz));
        }
        pageInfo.setList(list);
        return pageInfo;
    }
}
