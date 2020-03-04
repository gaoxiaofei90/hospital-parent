package com.llw.hospital.bs.common.ui.validator;

import java.lang.annotation.*;

/**
 * @ClassName: MethodSafe
 * @author cheng'e
 * @date 2016-12-7 下午2:04:52
 */
@Documented
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodSafe
{
}
