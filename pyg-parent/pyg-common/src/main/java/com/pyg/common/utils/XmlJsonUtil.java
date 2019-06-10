package com.pyg.common.utils;

import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;


public class XmlJsonUtil {
    private static final Log log = LogFactory.getLog(XmlJsonUtil.class);
    public static <T> T getApplyMessageByJson(Object jsonMsg,Class<T> entityClass){
        T applyMessage=null;
        try{
            JSONObject jsonObject=JSONObject.fromObject(jsonMsg);
            applyMessage=(T)JSONObject.toBean(jsonObject,entityClass);
        }catch (Exception e){
            log.error("解析json异常---PaymentApplyServiceImpl(getApplyMessageByJson)",e);
        }
        return applyMessage;
    }
    public static <T> T getReturnMessage(T entityClass){
        JSONObject jsonObject=JSONObject.fromObject(entityClass);
        return (T)jsonObject.toString();
    }

    /**
     * 使用JAXB将XML转为对象
     *
     * @param <T>
     *            对象类型
     * @param xml
     *            待转换的文本
     * @param cls
     *            转换后的类
     * @return 对象
     * @throws JAXBException
     */
    public static <T> T xml2Object(String xml, Class<T> cls)
            throws JAXBException {
        StringReader reader = null;
        try {
            reader = new StringReader(xml);
            JAXBContext cxt = JAXBContext.newInstance(cls);
            Unmarshaller unmarshaller = cxt.createUnmarshaller();
            return (T) unmarshaller.unmarshal(reader);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
