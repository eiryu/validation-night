package group_sequence_sample.group_settings;

import javax.validation.GroupSequence;

/**
 * チェックの順序を示す
 */
@GroupSequence({SingleCheck.class, CorrelatedCheck.class})
public interface CheckSequence {
}
