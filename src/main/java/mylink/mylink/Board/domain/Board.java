package mylink.mylink.Board.domain;

public class Board {
    private long index;
    private String name;
    private int age;
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

    public long getIndex() {
        return index;
    }
    public void setIndex(long index) {
        this.index = index;
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
    //Age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
