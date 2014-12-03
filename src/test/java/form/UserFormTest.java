package form;


import org.hamcrest.Matchers;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class UserFormTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFormTest.class);

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    // nameだけのバリデーション
    @Test
    public void validateName_正常系() {
        UserForm userForm = new UserForm();

        userForm.setName("eiryu");
        Set<ConstraintViolation<UserForm>> violations = validator.validateProperty(userForm, "name");
        LOGGER.info("violations: " + violations);

        assertThat(violations, hasSize(0));
    }

    @Test
    public void validateName_異常系_入力されていない() {
        UserForm userForm = new UserForm();

        Set<ConstraintViolation<UserForm>> violations = validator.validateProperty(userForm, "name");
        LOGGER.info("violations: " + violations);

        assertThat(violations, hasSize(1));
    }


    // UserForm全体のバリデーション
    @Test
    public void validate_正常系() {
        UserForm userForm = new UserForm();
        userForm.setName("eiryu");
        userForm.setSex(Sex.MALE);
        userForm.setPregnant(false);

        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        LOGGER.info("violations: " + violations);

        assertThat(violations, hasSize(0));
    }

    @Test
    public void validate_異常系_名前と性別が入力されていない() {
        UserForm userForm = new UserForm();
        userForm.setPregnant(true);

        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        LOGGER.info("violations: " + violations);

        assertThat(violations, hasSize(2));
        for (ConstraintViolation<UserForm> violation : violations) {
            assertThat(violation.getPropertyPath().toString(), anyOf(is("name"), is("sex")));
        }

    }

    @Test
    public void validate_異常系_男性なのに妊娠していると入力() {
        UserForm userForm = new UserForm();
        userForm.setName("eiryu");
        userForm.setSex(Sex.MALE);
        userForm.setPregnant(true);

        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        LOGGER.info("violations: " + violations);

        assertThat(violations, hasSize(1));
        assertThat(violations.iterator().next().getPropertyPath().toString(), is("validPregnant"));
    }
}
