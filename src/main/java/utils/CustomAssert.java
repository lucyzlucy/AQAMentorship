package utils;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

import java.util.ArrayList;
import java.util.List;

public class CustomAssert extends Assertion {
    private List<AssertionError> assertionExceptions = new ArrayList<>();

    protected void doAssert(IAssert<?> var1) {
        try {
            super.doAssert(var1);
        } catch (AssertionError e) {
            assertionExceptions.add(e);
        }
    }

    public void assertAll() {
        if (!assertionExceptions.isEmpty()) {
            StringBuilder errors = new StringBuilder("Asserts that failed:\n");
            for (AssertionError e : assertionExceptions) {
                errors.append(e.getMessage()).append("\n");
            }
            throw new AssertionError(errors.toString());
        }
    }
}
