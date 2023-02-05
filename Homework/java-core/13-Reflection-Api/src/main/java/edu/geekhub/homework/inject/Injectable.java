package edu.geekhub.homework.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.RUNTIME)
public @interface Injectable {
    public String propertyName() default "";
}
