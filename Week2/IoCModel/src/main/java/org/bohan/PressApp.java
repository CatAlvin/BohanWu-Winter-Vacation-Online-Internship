package org.bohan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.bohan.ioc.Press;
public class PressApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Press press = context.getBean(Press.class); // 获取press对象
        System.out.println(press);
    }
}