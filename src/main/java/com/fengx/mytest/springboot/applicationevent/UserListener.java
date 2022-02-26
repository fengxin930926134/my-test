package com.fengx.mytest.springboot.applicationevent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 这种方式是异步的
 *
 * 除了通过实现接口，还可以使用@EventListener 注解，实现对任意的方法都能监听事件。
 *
 * 在任意方法上标注@EventListener 注解，指定 classes，即需要处理的事件类型，一般就是 ApplicationEven 及其子类，可以设置多项。
 *
 *  //比如 @EventListener(classes = {ApplicationEvent.class}) ApplicationReadyEvent.class则是app启动事件
 */
@Component
public class UserListener implements ApplicationListener<ApplicationEvent> {
    // @Lazy Spring IoC （ApplicationContext） 容器一般都会在启动的时候实例化所有单实例 bean 。
    // 如果我们想要 Spring 在启动的时候延迟加载 bean，即在调用某个 bean 的时候再去初始化，那么就可以使用 @Lazy 注解。
    // 当出现循环依赖时，也可以添加@Lazy

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof UserEvent) {
            UserEvent userEvent = (UserEvent)event;

            int age = userEvent.getAge();
            String name = userEvent.getName();

            System.out.println("age " +age +" name " +name);
        }
    }
}