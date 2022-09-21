package com.tkato.myKanBan.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringEnumerationValidator.class)
public @interface StringEnumeration {

    String message() default "{com.tkato.myKanBan.validator.StringEnumeration.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> enumClass();
}
