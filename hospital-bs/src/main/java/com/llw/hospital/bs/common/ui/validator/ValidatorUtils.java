package com.llw.hospital.bs.common.ui.validator;




import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;


import java.util.Set;

/**
 * hibernate-validator校验工具类
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

   
    public static String validateEntity(Object object, Class<?>... groups)
            {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> constraint = (ConstraintViolation<Object>) constraintViolations.iterator().next();
           return constraint.getMessage();
        }
        return "";
    }
}
