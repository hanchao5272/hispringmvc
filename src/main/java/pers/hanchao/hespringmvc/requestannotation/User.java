package pers.hanchao.hespringmvc.requestannotation;

public class User {
    private String name;
    private String sex;

    public User(){}
    public User(String name,String sex){
        this.name = name;
        this.sex = sex;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "user[name:" + name + ",sex:" + sex + "]";
    }
}
