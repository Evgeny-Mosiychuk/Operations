package com.example.Paysonix.validation.validator;

import com.example.Paysonix.validation.annotation.NonEmptyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

public class NonEmptyMapValidator implements ConstraintValidator<NonEmptyMap, Map<String, String>> {
    private static final Logger logger = LoggerFactory.getLogger(NonEmptyMapValidator.class);

    @Override
    public boolean isValid(Map<String, String> map, ConstraintValidatorContext constraintValidatorContext) {
        if (map == null || map.isEmpty()) {
            logger.warn("Validation failed: Map is null or empty");
            return false;
        }

        boolean hasEmptyValues = map.values().stream()
                .anyMatch(v -> v == null || v.trim().isEmpty());
        if (hasEmptyValues) {
            logger.warn("Validation failed: Map contains empty or null values. Provided values {}:", map);
            return false;
        }
        return true;
    }
}
