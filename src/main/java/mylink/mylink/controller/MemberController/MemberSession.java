package mylink.mylink.controller.MemberController;

import lombok.Getter;

@Getter
public class MemberSession {

    private Long id;
    private String name;
    private int age;
    private String sex;
    private String department;
    private String address;
    private String password;

    public void setMemberSession(Long id, String name, int age, String sex, String department, String address, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.department = department;
        this.address = address;
        this.password = password;
    }
}
