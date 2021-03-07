package io.zero.db.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Activity {
    String code() default "ACT000";

    String type() default "";

    String operator() default "";

    String action() default "";

    String[] objectIds() default {};

    String parentId() default "";

    String[] messages() default {};

}
