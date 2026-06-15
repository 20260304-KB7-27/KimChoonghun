package lecture.test;

import lecture.after.AppConfig;
import lecture.after.NotiService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /*
        BeanCurrentlyIncreationExcetiom
        생성자 주입시 순환 참조 문제가 발생 할 경우 예외 발생
     */
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ClassA classA = context.getBean("ClassA.class");

        System.out.println("입력")
        ;
    }
}
