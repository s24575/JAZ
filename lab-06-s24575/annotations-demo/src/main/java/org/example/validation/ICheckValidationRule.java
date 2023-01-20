package org.example.validation;

public interface ICheckValidationRule {
    <T> void validate(ValidationResult<T> result);
}
