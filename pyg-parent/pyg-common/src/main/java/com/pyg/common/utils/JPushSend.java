package com.pyg.common.utils;

import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by chenlong on 2015/6/8.
 */
public class JPushSend {
    private static final Logger log = LoggerFactory.getLogger(JPushSend.class);
    
    //客户端
    private static final String APP_KEY = "aa36ef430e87ff45004ad618";
    private static final String MASTER_SECRET = "cf059ac8b25937a5ce52cbcf";
    
    //物流端
    private static final String TRANS_APP_KEY = "6372489d97fe3b07001d865c";
    private static final String TRANS_MASTER_SECRET = "c1f69d05b23b549fb20a8706";
    
    static final PushClient pushClient = new PushClient(MASTER_SECRET, APP_KEY);
    static final PushClient pushTransportClient = new PushClient(TRANS_MASTER_SECRET, TRANS_APP_KEY);
    /**  安卓 **/
    public static PushResult send(Set<String> sendUserId, String msg) {
        try {
            PushPayload.Builder builder = PushPayload.newBuilder();
            Platform platform = Platform.all();
            builder.setPlatform(platform);
            Audience audience;
            if (sendUserId == null || sendUserId.size() == 0) {
                audience = Audience.all();
            } else {
                audience = Audience.alias(sendUserId);
            }
            builder.setAudience(audience);
            Notification notification = Notification.alert(msg);
            builder.setNotification(notification);
            PushPayload pushPayload = builder.build();
            return pushClient.sendPush(pushPayload);
        } catch (Exception e) {
            log.info("========== 发送失败 ==========");
        }
        return null;
    }
    /**
     * 推送消息  以下所有方法参数都参考该方法
     * @param sendUserId 接收人ID
     * @param msg 消息内容
     * @param orderId 订单ID或者消息ID
     * @param status 状态 1订单2采购订单3系统消息
     * @return
     */
    public static PushResult send(Set<String> sendUserId, String msg, String orderId, String status) {
        try {
            PushPayload.Builder builder = PushPayload.newBuilder();
            Platform platform = Platform.all();
            builder.setPlatform(platform);
            Audience audience;
            if (sendUserId == null || sendUserId.size() == 0) {
                audience = Audience.all();
            } else {
                audience = Audience.alias(sendUserId);
            }
            builder.setAudience(audience);
            Map map = new HashMap();
            map.put("orderId",orderId);
            map.put("status", status);
            Notification notification = Notification.android(msg, null, map);
            builder.setNotification(notification);
            PushPayload pushPayload = builder.build();
            return pushClient.sendPush(pushPayload);
        } catch (Exception e) {
            log.info("========== 发送失败 ==========");
        }
        return null;
    }

    public static PushResult send(String sendUserId, String msg) {
        Set<String> userids = new HashSet<String>();
        userids.add(sendUserId);
        return send(userids, msg);
    }
    public static PushResult send(String sendUserId, String msg, String orderId, String status) {
        Set<String> userids = new HashSet<String>();
        userids.add(sendUserId);
        return send(userids, msg, orderId, status);
    }

    public static PushResult send(String msg, String orderId, String status) {
        return send(new HashSet<String>(), msg, orderId, status);
    }
    /** IOS **/
    public static PushResult sendIOS(Set<String> sendUserId, String msg) {
        try {
            PushPayload.Builder builder = PushPayload.newBuilder();
            Platform platform = Platform.all();
            builder.setPlatform(platform);
            Audience audience;
            if (sendUserId == null || sendUserId.size() == 0) {
                audience = Audience.all();
            } else {
                audience = Audience.alias(sendUserId);
            }
            builder.setAudience(audience);
            Notification notification = Notification.alert(msg);
            Message message = Message.content(msg);
            builder.setMessage(message);
            Options options = Options.newBuilder().setApnsProduction(false).build();
            builder.setOptions(options);
            builder.setNotification(notification);
            PushPayload pushPayload = builder.build();
            return pushClient.sendPush(pushPayload);
        } catch (Exception e) {
            log.info("========== 发送失败 ==========");
        }
        return null;
    }

    public static PushResult sendIOS(Set<String> sendUserId, String msg, String orderId, String status) {
        try {
            PushPayload.Builder builder = PushPayload.newBuilder();
            Platform platform = Platform.all();
            builder.setPlatform(platform);
            Audience audience;
            if (sendUserId == null || sendUserId.size() == 0) {
                audience = Audience.all();
            } else {
                audience = Audience.alias(sendUserId);
            }
            builder.setAudience(audience);
            Map map = new HashMap();
            map.put("orderId",orderId);
            map.put("status", status);
            Notification notification = Notification.ios(msg, map);
            builder.setNotification(notification);
            Message message = Message.content(msg);
            builder.setMessage(message);
            Options options = Options.newBuilder().setApnsProduction(false).build();
            builder.setOptions(options);
            PushPayload pushPayload = builder.build();
            return pushClient.sendPush(pushPayload);
        } catch (Exception e) {
            log.info("========== 发送失败 ==========");
        }
        return null;
    }

    public static PushResult sendIOS(String sendUserId, String msg) {
        Set<String> userids = new HashSet<String>();
        userids.add(sendUserId);
        return sendIOS(userids, msg);
    }
    public static PushResult sendIOS(String sendUserId, String msg, String orderId, String status) {
        Set<String> userids = new HashSet<String>();
        userids.add(sendUserId);
        return sendIOS(userids, msg, orderId, status);
    }

    public static PushResult sendIOS(String msg, String orderId, String status) {
        return send(new HashSet<String>(), msg, orderId, status);
    }
}
