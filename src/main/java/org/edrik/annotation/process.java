package org.edrik.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/8/21 16:01
 */
@Target({
    ElementType.TYPE,
    ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface process {
    String[] process() default {};
}
