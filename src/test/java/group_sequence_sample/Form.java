package group_sequence_sample;

import group_sequence_sample.enum_.Computer;
import group_sequence_sample.enum_.MobileDevice;
import group_sequence_sample.group_settings.CorrelatedCheck;
import group_sequence_sample.group_settings.SingleCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.GroupSequence;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

// ここに@GroupSequenceを付けるとDefault groupのバリデーション順序を規定できる。このクラス自身も含むこと
//@GroupSequence({Form.class, SingleCheck.class, CorrelatedCheck.class})
public class Form {

    private static final Logger LOGGER = LoggerFactory.getLogger(Form.class);

    @NotNull(groups = SingleCheck.class)
    private Boolean hasComputer;

    @NotNull(groups = SingleCheck.class)
    private Boolean hasMobileDevices;

    private Computer computer;
    private MobileDevice mobileDevice;

    public Form() {
    }

    @AssertTrue(groups = CorrelatedCheck.class)
    public boolean isValidComputer() {
        LOGGER.info("checking Computer");
        return hasComputer ? (computer != null) : (computer == null);
    }

    @AssertTrue(groups = CorrelatedCheck.class)
    public boolean isValidMobileDevice() {
        LOGGER.info("checking MobileDevice");
        return hasMobileDevices ? (mobileDevice != null) : (mobileDevice == null);
    }

    public Boolean getHasComputer() {
        return hasComputer;
    }

    public void setHasComputer(Boolean hasComputer) {
        this.hasComputer = hasComputer;
    }

    public Boolean getHasMobileDevices() {
        return hasMobileDevices;
    }

    public void setHasMobileDevices(Boolean hasMobileDevices) {
        this.hasMobileDevices = hasMobileDevices;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public MobileDevice getMobileDevice() {
        return mobileDevice;
    }

    public void setMobileDevice(MobileDevice mobileDevice) {
        this.mobileDevice = mobileDevice;
    }
}
