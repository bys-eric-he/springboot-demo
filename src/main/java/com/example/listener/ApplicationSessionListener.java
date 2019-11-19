package com.example.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

/**
 * HttpSessionListener 监听用户的Session的创建和销毁
 * HttpSessionAttributeListener 监听HttpSession范围内Session属性的变化
 */
@WebListener
public class ApplicationSessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    private static Logger logger = LoggerFactory.getLogger(ApplicationSessionListener.class);

    /**
     * 向Session范围内添加属性时触发
     *
     * @param httpSessionBindingEvent
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        Object value = httpSessionBindingEvent.getValue();

        logger.info("--->向Session范围内添加了名为:" + name + ",值为:" + value + "的属性!");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        Object value = httpSessionBindingEvent.getValue();

        logger.info("--->向Session范围内添移除了名为:" + name + ",值为:" + value + "的属性!");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        Object value = httpSessionBindingEvent.getValue();

        logger.info("--->向Session范围内替换了名为:" + name + ",值为:" + value + "的属性!");
    }

    /**
     * Session被创建时触发
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        String sessionId = session.getId();
        logger.info("---->Session:" + sessionId + "被创建....");
    }

    /**
     * Session被销毁时触发
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        String sessionId = session.getId();
        logger.info("---->Session:" + sessionId + "被销毁....");
    }
}
