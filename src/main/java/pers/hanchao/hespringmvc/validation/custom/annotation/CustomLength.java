package pers.hanchao.hespringmvc.validation.custom.annotation;

import pers.hanchao.hespringmvc.validation.custom.validator.CustomLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>校验字符串长度，中文按照charset进行计算</p>
 * @author hanchao 2018/1/20 15:40
 **/
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CustomLengthValidator.class)
@Documented
public @interface CustomLength {
	
	long min() default 0;
	long max() default Integer.MAX_VALUE; 
	String charset() default "gbk";
	
	String message() default "length must be between {min} and {max}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}