package pers.hanchao.hespringmvc.validation;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import pers.hanchao.hespringmvc.validation.custom.annotation.CustomLength;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;
import java.util.Date;
/**
 * <p>javax.validation校验、hibernate.validator校验、分组校验、@GroupSequence分组顺序校验</p>
 * @author hanchao 2018/1/20 14:58
 **/
@GroupSequence({InsertGroup.class,UpdateGroup.class,Student.class})
public class Student {
    /////////////////////////////javax.validation/////////////////////////////
    //添加学生时，id必为空；修改学生时，id必须有值
    @Null(groups = InsertGroup.class,message = "{Null.student.id}")
    @NotNull(groups = UpdateGroup.class,message = "{NotNull.student.id}")
    private String id;

//    @Size(min = 2,max = 4,message = "{Size.student.name}",groups = {InsertGroup.class,UpdateGroup.class})
    @CustomLength(min = 2,max = 4,charset = "utf-8",message = "{CustomLength.student.name}",groups = {InsertGroup.class,UpdateGroup.class})
    private String name;//名字

    @AssertTrue(groups = InsertGroup.class, message = "{AssertTrue.student.newRegister}")
    @AssertFalse(groups = UpdateGroup.class, message = "{AssertTrue.student.newRegister}")
    private boolean newRegister;//是否新注册

    @Max(value = 100, message = "{Max.student.score}")
    @Min(value = 0, message = "{Min.student.score}")
    private int score;//分数[0-100]

    @DecimalMax(value = "30",inclusive = true, message = "{student.age}")
    @DecimalMin(value = "19",inclusive = false, message = "{student.age}")
    private String age;//年龄范围[20-30]

    @Digits(integer = 3,fraction = 2, message = "{Digits.student.weight}")
    private float weight;//体重格式[xxx.yy]

    @Past(message = "{Past.student.entrance}")
    private Date entrance;//入学时间

    @Future(message = "{Future.student.graduation}")
    private Date graduation;//毕业时间

    @Pattern(regexp = "^S2018[0-9]{4}$",flags = Pattern.Flag.CASE_INSENSITIVE, message = "{Pattern.student.number}")
    private String number;//学号形式 S20180000-S20189999 大小写敏感

    /////////////////////////////hibernate.validator/////////////////////////////

    @URL(message = "{URL.student.blog}")
    private String blog;//个人学生主页

    @Length(min = 1000,max = 5000 ,message = "{Length.student.bonus}")
    private String tuition;//学费

    @Range(min = 2000L,max = 4000L,message = "{Rang.student.bonus}")
    private String bonus;//奖金

    @CreditCardNumber(message = "{CreditCardNumber}")
    private String creditCard;//银行账号

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", newRegister=" + newRegister +
                ", score=" + score +
                ", age='" + age + '\'' +
                ", weight=" + weight +
                ", entrance=" + entrance +
                ", graduation=" + graduation +
                ", number='" + number + '\'' +
                ", blog='" + blog + '\'' +
                ", tuition='" + tuition + '\'' +
                ", bonus='" + bonus + '\'' +
                ", creditCard='" + creditCard + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNewRegister() {
        return newRegister;
    }

    public void setNewRegister(boolean newRegister) {
        this.newRegister = newRegister;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Date getEntrance() {
        return entrance;
    }

    public void setEntrance(Date entrance) {
        this.entrance = entrance;
    }

    public Date getGraduation() {
        return graduation;
    }

    public void setGraduation(Date graduation) {
        this.graduation = graduation;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getTuition() {
        return tuition;
    }

    public void setTuition(String tuition) {
        this.tuition = tuition;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
