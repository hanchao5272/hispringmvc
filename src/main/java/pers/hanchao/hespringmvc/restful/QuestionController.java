package pers.hanchao.hespringmvc.restful;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Restful风格URL的简单实例</p>
 * @author hanchao 2018/1/21 11:30
 **/
@RestController
@RequestMapping("restful")
public class QuestionController {

    /**
     * <p>定义一个Question的列表，用来模拟数据集</p>
     * @author hanchao 2018/1/20 23:15
     **/
    @ModelAttribute
    public Question[] init(Model model){
        Question[] questions = new Question[10];
        String title = "题目";
        int score = 90;
        String answer = "答案";
        for(int i = 0 ; i < 10 ; i ++){
            questions[i] = new Question(i,title + i,score + i,answer + i);
        }
        model.addAttribute("questions",questions);
        return questions;
    }

    /**
     * <p>[GET]获取一个题目</p>
     * @author hanchao 2018/1/20 22:08
     **/
    @GetMapping("/question/{id}")
    public MyJsonResult getQuestion(@PathVariable Integer id, @ModelAttribute("questions") Question[] questions) {
        MyJsonResult myJsonResult = new MyJsonResult();
        //查找题目，找到则返回
        for (Question question : questions){
            if (id.equals(question.getId())){
                myJsonResult.setQuestions(new Question[]{question});
                break;
            }
        }
        return myJsonResult;
    }

    /**
     * <p>[GET]获取全部的题目</p>
     * @author hanchao 2018/1/20 23:50
     **/
    @GetMapping("/question/")
    public MyJsonResult getAll(@ModelAttribute("questions") Question[] questions) {
        MyJsonResult myJsonResult = new MyJsonResult(questions);
        return myJsonResult;
    }

    /**
     * <p>[POST]新增一个题目</p>
     * @author hanchao 2018/1/21 10:10
     **/
    @PostMapping("/question/")
    public MyJsonResult postQuestion(@RequestBody Question question,@ModelAttribute("questions") Question[] questions) {
        MyJsonResult myJsonResult = new MyJsonResult();
        boolean isExist = false;
        //查找题目，如果找到，则表明已经存在，不能再添加
        for (int i = 0 ; i < questions.length; i ++){
            Question qi = questions[i];
            if (question.getId().equals(qi.getId())){
                myJsonResult.setCodeAndMessage("0","改题目已经存在，不能再添加！");
                isExist = true;
                break;
            }
        }
        //如果题目不存在，则新增题目
        if (!isExist){
            Question[] newQuestions = new Question[questions.length + 1];
            newQuestions[0] = question;
            System.arraycopy(questions,0,newQuestions,1,questions.length);
            myJsonResult.setQuestions(newQuestions);
        }
        return myJsonResult;
    }

    /**
     * <p>[PUT]替换一个题目（全部修改）</p>
     * @author hanchao 2018/1/21 10:44
     **/
    @PutMapping("/question/")
    public MyJsonResult putQuestion(@RequestBody Question question,@ModelAttribute("questions") Question[] questions) {
        MyJsonResult myJsonResult = new MyJsonResult();
        boolean isExist = false;
        //查找题目，如果找到，直接替换全部属性
        for (int i = 0 ; i < questions.length; i ++){
            Question qi = questions[i];
            if (question.getId().equals(qi.getId())){
                questions[i] = question;
                myJsonResult.setQuestions(questions);
                isExist = true;
                break;
            }
        }
        //如果找不到，则提示不存在此题目
        if (!isExist){
            myJsonResult.setCodeAndMessage("0","不存在此题目，无法替换！");
        }
        return myJsonResult;
    }

    /**
     * <p>[PATCH]修改一个题目,修改不为空的属性值</p>
     * @author hanchao 2018/1/21 11:15
     **/
    @PatchMapping("/question/")
    public MyJsonResult patchQuestion(@RequestBody Question question,@ModelAttribute("questions") Question[] questions) {
        MyJsonResult myJsonResult = new MyJsonResult();
        boolean isExist = false;
        //查找题目，如果找到，替换不为空的属性
        for (int i = 0 ; i < questions.length; i ++){
            Question qi = questions[i];
            if (question.getId().equals(qi.getId())){
                if (null != question.getTitle() && !"".equals(question.getTitle())){
                    questions[i].setTitle(question.getTitle());
                }
                if (null != question.getAnswer() && !"".equals(question.getAnswer())){
                    questions[i].setAnswer(question.getAnswer());
                }
                if (null != question.getScore() && !"".equals(question.getScore())){
                    questions[i].setScore(question.getScore());
                }
                myJsonResult.setQuestions(questions);
                isExist = true;
                break;
            }
        }
        //如果找不到，则提示不存在此题目
        if (!isExist){
            myJsonResult.setCodeAndMessage("0","不存在此题目，无法替换！");
        }
        return myJsonResult;
    }

    /**
     * <p>[DELETE]删除一个题目</p>
     * @author hanchao 2018/1/21 11:21
     **/
    @DeleteMapping("/question/{id}")
    public MyJsonResult deleteQuestion(@PathVariable Integer id,@ModelAttribute("questions") Question[] questions ) {
        MyJsonResult myJsonResult = new MyJsonResult();
        boolean isExist = false;
        //查找题目，如果找到，直接删除
        for (int i = 0 ; i < questions.length; i ++){
            Question qi = questions[i];
            if (id.equals(qi.getId())){
                questions[i] = null;
                myJsonResult.setQuestions(questions);
                isExist = true;
                break;
            }
        }
        //如果找不到，则提示不存在此题目
        if (!isExist){
            myJsonResult.setCodeAndMessage("0","不存在此题目，无法删除！");
        }
        return myJsonResult;

    }

    /**
     * <p>[DELETE]删除所有题目</p>
     * @author hanchao 2018/1/21 11:24
     **/
    @DeleteMapping("/question/")
    public MyJsonResult deleteAllQuestion() {
        MyJsonResult myJsonResult = new MyJsonResult();
        return myJsonResult;
    }
}
