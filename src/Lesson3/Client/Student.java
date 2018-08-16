package Client;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;

    public Student(String name) {
        System.out.println("Конструктор Client.Student");
        this.name = name;
    }
    public String getName(){
        return name;
    }
}