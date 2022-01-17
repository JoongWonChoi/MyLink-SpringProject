package mylink.mylink.Controller.BoardController;


import org.springframework.stereotype.Component;

@Component
public class BoardForm {
    private String name;
    private Integer age;
    private String sex;
    private String department;
    private String title;
    private String body;

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    //Title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    //Name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //age
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }


    //Sex
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    //Department
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
}
