package pers.hanchao.hespringmvc.validation.custom.validator;

import java.io.UnsupportedEncodingException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import pers.hanchao.hespringmvc.validation.custom.annotation.CustomLength;

/**
 * <p>自定义校验规则诶</p>
 * @author hanchao 2018/1/20 17:04
 **/
public class CustomLengthValidator implements ConstraintValidator<CustomLength, String> {

	private static final Log log = LoggerFactory.make();
	private long min;
	private long max;
	private String charset;
	
	@Override
	public void initialize(CustomLength parameters) {
		//do nothing
		this.min = parameters.min();
		this.max = parameters.max();
		this.charset = parameters.charset();
		validateParameters();
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(null == value){
			value = "";
		}
		long length = 0;
		try {
			length = ((String)value).getBytes(charset).length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		boolean result = (length >= min) && (length <= max);
		log.info("CustomLength.validator:[value:" + (String)value + ",min:" + min + ",max:" + max + ",length:" + length + "],result:" + result);
		return (length >= min) && (length <= max);
	}
	private void validateParameters() {
	    if (this.min < 0) {
	      throw log.getMinCannotBeNegativeException();
	    }
	    if (this.max < 0) {
	      throw log.getMaxCannotBeNegativeException();
	    }
	    if (this.max < this.min)
	      throw log.getLengthCannotBeNegativeException();
	}
}