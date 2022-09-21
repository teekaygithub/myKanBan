package com.tkato.myKanBan.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StringEnumerationValidator implements ConstraintValidator<StringEnumeration, String> {

    private Set<String> AVAILABLE_ENUM_NAMES;

    @Override
    public void initialize(StringEnumeration stringEnumeration) {
        Class<? extends Enum> enumSelected = stringEnumeration.enumClass();
        AVAILABLE_ENUM_NAMES = (Set<String>)EnumSet.allOf(enumSelected).stream().map(e -> ((Enum<? extends Enum<?>>) e).name())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || AVAILABLE_ENUM_NAMES.contains(value) ? true : false;
    }
}
