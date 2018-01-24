package pers.hanchao.hespringmvc.restful;

/**
 * <p>题目对象</p>
 * @author hanchao 2018/1/21 11:30
 **/
public class Question {
    /** 试题id */
    private Integer id;
    /** 题目 */
    private String title;
    /** 分数 */
    private Integer score;
    /** 答案 */
    private String answer;

    public Question(){}
    public Question(Integer id, String title, Integer score, String answer) {
        this.id = id;
        this.title = title;
        this.score = score;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", score=" + score +
                ", answer='" + answer + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
