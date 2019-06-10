package com.pyg.common.utils;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 操作JavaBean
 * @author Devil
 * @mail 562157205@qq.com
 * @date 2017年5月27日 上午9:49:32
 */
public class BeanUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);
    
    /**
     * Bean --> Map : 利用Introspector和PropertyDescriptor 将Bean --> Map
     * @param obj bean
     * @param properties 展示的属性名
     * @return
     */
    public static Map<String, Object> transBean2Map(Object obj, String...properties) {
        if (obj == null) {  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> propertyList = Arrays.asList(properties);
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
                //过滤class属性  
                if (!key.equals("class")) {
                    if (CollectionUtils.isNotEmpty(propertyList)) {
                        if (propertyList.contains(key)) {
                            //得到property对应的getter方法  
                            Method getter = property.getReadMethod();  
                            Object value = getter.invoke(obj);  
                            map.put(key, value);  
                        }
                    } else {
                        //得到property对应的getter方法  
                        Method getter = property.getReadMethod();  
                        Object value = getter.invoke(obj);  
                        map.put(key, value);  
                    }
                }  
  
            }  
        } catch (Exception e) { 
            LOGGER.error("transBean2Map转换失败");
            e.printStackTrace();  
        }  
        return map;
    }
    
    /**
     * Map --> Bean : 利用Introspector,PropertyDescriptor实现 Map --> Bean
     * @param map 字段map
     * @param obj bean
     */
    public static void transMap2Bean(Map<String, Object> map, Object obj) {  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
                if (map.containsKey(key)) {  
                    Object value = map.get(key);  
                    //得到property对应的setter方法  
                    Method setter = property.getWriteMethod();  
                    setter.invoke(obj, value);  
                }  
            }  
        } catch (Exception e) {  
            LOGGER.error("transMap2Bean转换失败");
            e.printStackTrace();  
        }  
        return;  
    } 
    
    public static void main(String[] args) {
        Test test = new Test();
        String[] properties = {"name"};
        Map<String, Object> resultMap = BeanUtil.transBean2Map(test, properties);
        resultMap.forEach((k,v)->{System.out.println(k + " = " + v);});
    }
    
    static class Test {
        private String name;
        private Date createdAt;
        private boolean isDeleted;
        
        /**
         * @return the name
         */
        public String getName() {
            return name;
        }
        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
        /**
         * @return the createdAt
         */
        public Date getCreatedAt() {
            return createdAt;
        }
        /**
         * @param createdAt the createdAt to set
         */
        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }
        /**
         * @return the isDeleted
         */
        public boolean isDeleted() {
            return isDeleted;
        }
        /**
         * @param isDeleted the isDeleted to set
         */
        public void setDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
        }
    }
    
}
