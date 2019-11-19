package com.example.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * ServletRequestListener用于监听用户请求
 * ServletRequestAttributeListener用于监听request范围内属性的变化
 */
@WebListener
public class ApplicationServletRequestListener implements ServletRequestListener, ServletRequestAttributeListener {
    private static Logger logger = LoggerFactory.getLogger(ApplicationServletRequestListener.class);

    /**
     * ServletRequestAttributeListener接口,向request范围内添加属性时触发
     *
     * @param servletRequestAttributeEvent
     */
    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {

        String name = servletRequestAttributeEvent.getName();
        Object value = servletRequestAttributeEvent.getValue();

        logger.info("向request范围内添加了名为:" + name + ",值为:" + value + "的属性!");
    }

    /**
     * ServletRequestAttributeListener接口, 从request范围内删除某个属性时触发
     *
     * @param servletRequestAttributeEvent
     */
    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        String name = servletRequestAttributeEvent.getName();
        Object value = servletRequestAttributeEvent.getValue();

        logger.info("向request范围内删除了名为:" + name + ",值为:" + value + "的属性!");
    }

    /**
     * ServletRequestAttributeListener接口, 替换request范围内某个属性值时触发
     *
     * @param servletRequestAttributeEvent
     */
    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        String name = servletRequestAttributeEvent.getName();
        Object value = servletRequestAttributeEvent.getValue();

        logger.info("向request范围内替换了名为:" + name + ",值为:" + value + "的属性!");
    }

    /**
     * ServletRequestListener接口,用户请求结束，被销毁时触发
     *
     * @param servletRequestEvent
     */
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        String ip = request.getRemoteAddr();
        logger.info("----->IP为:" + ip + "的用户发送到" + request.getRequestURI() + "的请求结束!");
    }

    /**
     * ServletRequestListener接口, 用户请求到达，被初始化时触发
     *
     * @param servletRequestEvent
     */
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        String ip = request.getRemoteAddr();
        logger.info("----->IP为:" + ip + "的用户发送到" + request.getRequestURI() + "的请求被初始化!");
    }
}
