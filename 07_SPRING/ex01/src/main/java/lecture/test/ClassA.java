package lecture.test;

import org.springframework.beans.factory.annotation.Autowired;

public class ClassA {
//    @Autowired
    private ClassB classB;

    #Autowired //
    public void doSomething() {
        System.out.println("Class A is Working");
    }
}
