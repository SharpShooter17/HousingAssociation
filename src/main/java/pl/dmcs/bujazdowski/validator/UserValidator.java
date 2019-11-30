package pl.dmcs.bujazdowski.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.dmcs.bujazdowski.domain.UserAppI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements Validator {

    private EmailValidator emailValidator = EmailValidator.getInstance();

    private final Pattern telephonePattern = Pattern.compile("(\\+\\d{2})(\\d{9})");

    @Override
    public boolean supports(Class<?> aClass) {
        return UserAppI.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object user, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "firstName", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "email", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "telephone", "error.field.required");

        UserAppI userAppI = (UserAppI) user;

        if (errors.getErrorCount() == 0) {
            if (!emailValidator.isValid(userAppI.getEmail())) {
                errors.rejectValue("email", "error.email.invalid");
            }
            if (!telephonePattern.matcher(userAppI.getTelephone()).matches()) {
                errors.rejectValue("telephone", "error.telephone.invalid");
            }
        }
    }
}
