package com.example.Paysonix.validation.annotation;

import com.example.Paysonix.validation.validator.NonEmptyMapValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NonEmptyMapValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonEmptyMap {
    String message() default "All parameters must be present and non-empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
