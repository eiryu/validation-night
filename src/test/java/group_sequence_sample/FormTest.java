package group_sequence_sample;

import group_sequence_sample.enum_.Computer;
import group_sequence_sample.enum_.MobileDevice;
import group_sequence_sample.group_settings.CheckSequence;
import group_sequence_sample.group_settings.CorrelatedCheck;
import group_sequence_sample.group_settings.SingleCheck;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class FormTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormTest.class);

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void validate_単項目チェックでエラー() {
        Form form = new Form();
        form.setHasComputer(true);
//        form.setHasMobileDevices(true);
        form.setComputer(Computer.MAC);
        form.setMobileDevice(MobileDevice.IOS);

        // 単項目チェックでエラーになるため、相関チェックは一切行われない
        Set<ConstraintViolation<Form>> violations = validator.validate(form, CheckSequence.class);
        LOGGER.info("violations: " + violations);
    }
}
