package pers.hanchao.hespringmvc.validation;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>简单的bean validation和hibernate validator的例子</p>
 * @author hanchao 2018/1/20 14:47
 **/
@Controller
@RequestMapping("validation")
public class StudentManageController {

    /**
     * <p>注册学生信息</p>
     * @author hanchao 2018/1/20 14:47
     **/
    @PostMapping("/insert")
    @ResponseBody
    public JsonResult insert(@Validated(InsertGroup.class) @RequestBody Student student, BindingResult bindingResult){
        JsonResult jsonResult = new JsonResult();
        validate(bindingResult, jsonResult);
        System.out.println(jsonResult.toString());
        return jsonResult;
    }

    /**
     * <p>修改学生信息</p>
     * @author hanchao 2018/1/20 14:47
     **/
    @PostMapping("/update")
    @ResponseBody
    public JsonResult update(@Validated(UpdateGroup.class) @RequestBody Student student, BindingResult bindingResult){
        JsonResult jsonResult = new JsonResult();
        validate(bindingResult, jsonResult);
        System.out.println(jsonResult.toString());
        return jsonResult;
    }

    /**
     * <p>对bindingResult进行校验</p>
     * @author hanchao 2018/1/20 14:46
     **/
    private void validate(BindingResult bindingResult, JsonResult jsonResult) {
        if (bindingResult.hasErrors()){
            StringBuffer errors = new StringBuffer();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors){
                errors.append(objectError.getDefaultMessage() + "<br/>");
            }
            jsonResult.setCodeAndMessage("0",errors.toString());
        }
    }


}
