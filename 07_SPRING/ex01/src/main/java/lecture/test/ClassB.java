package lecture.test;

import org.springframework.beans.factory.annotation.Autowired;

public class ClassB {

    @Autowired
    private final ClassA classA;

    public void doSomething() {
        System.out.println("Class B is Working");
    }
}
