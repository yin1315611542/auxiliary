package cn.goldencis.auxiliary.infrastructure.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringUtil.class);

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringUtil.context = context;
    }

    /**
     * 获取 Spring Bean
     *
     * @param clazz 类
     * @param <T>   泛型
     * @return 对象
     */
    public static <T> T getBean(Class<T> clazz) {
        if (clazz == null) {
            return null;
        }
        return context.getBean(clazz);
    }

    /**
     * 获取 Spring Bean
     *
     * @param bean 名称
     * @param <T>  泛型
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String bean) {
        if (bean == null) {
            return null;
        }
        return (T) context.getBean(bean);
    }

    /**
     * 获取 Spring Bean
     *
     * @param beanName 名称
     * @param clazz    类
     * @param <T>      泛型
     * @return 对象
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        if (null == beanName || beanName.trim().isEmpty()) {
            return null;
        }
        if (clazz == null) {
            return null;
        }
        return context.getBean(beanName, clazz);
    }

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    public static ApplicationContext getContext() {
        if (context == null) {
            throw new RuntimeException("There has no Spring ApplicationContext!");
        }
        return context;
    }

    /**
     * 发布事件
     *
     * @param event 事件
     */
    public static void publishEvent(ApplicationEvent event) {
        if (context == null) {
            return;
        }
        try {
            context.publishEvent(event);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}