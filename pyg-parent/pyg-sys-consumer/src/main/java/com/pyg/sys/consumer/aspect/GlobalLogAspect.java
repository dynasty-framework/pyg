package com.pyg.sys.consumer.aspect;


import com.alibaba.fastjson.JSONObject;
import com.pyg.common.utils.UUIDFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;


/**
 * 
 * @Title: LogController.java
 * @Package net.yuanh.client.base
 * @Description: 全局操作日志记录切面
 * @author zhaotao
 * @date 2018年9月17日 上午11:32:36
 * @version V1.0
 */
@Aspect
@Component
public class GlobalLogAspect {

	@Autowired
	private JmsMessagingTemplate jmsTemplate;


	// 定义切入点表达式，指定对哪些包下的哪些方法进行增强，*代表任意内容，括号里的两个..代表所有参数
	@Pointcut("execution(public * com.pyg.sys.consumer.controller.*.*(..))")
	public void logPointCut() {}

	// 前置通知
	@Before("logPointCut()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
	}

	// returning的值和方法的参数名一致
	@AfterReturning(returning = "returnResult", pointcut = "logPointCut()")
	public void doAfterReturning(JoinPoint joinPoint, Object returnResult) throws Throwable {
	}


	// 日志环绕通知
	@SuppressWarnings("all")
	@Around("logPointCut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		//获取当前请求Request
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 定义方法返回值
		Object returnResult = null;
		String url = request.getRequestURL().toString();

		try {
			// 获取方法的返回值
			returnResult = pjp.proceed();
		} catch (Exception e) {

		} finally {
			JSONObject json = new JSONObject();
			json.put("logUuid", UUIDFactory.getUUIDStr());
			jmsTemplate.convertAndSend("log.info", json.toJSONString());

            return returnResult;
		}

	}


}
