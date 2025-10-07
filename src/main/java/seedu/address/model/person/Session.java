package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's session number in TAConnect.
 * Guarantees: immutable; is valid as declared in {@link #isValidSession(String)}
 */
public class Session {


    public static final String MESSAGE_CONSTRAINTS =
            "Sessions should start with a capital letter followed by 1–2 digits (e.g., G17, F7).";
    public static final String VALIDATION_REGEX = "[A-Z]\\d{1,2}";
    public final String value;

    /**
     * Constructs a {@code Session}.
     *
     * @param session A valid session number.
     */
    public Session(String session) {
        requireNonNull(session);
        checkArgument(isValidSession(session), MESSAGE_CONSTRAINTS);
        this.value = session;
    }

    /**
     * Returns true if a given string is a valid session number.
     */
    public static boolean isValidSession(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Session)) {
            return false;
        }

        Session otherSession = (Session) other;
        return value.equals(otherSession.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
