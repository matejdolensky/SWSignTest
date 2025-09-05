package api;

import com.poiji.annotation.ExcelCellName;
import lombok.Data;


@Data
public class TestEntry {

    // Control part of the data
    @ExcelCellName("control.id")
    private String testId;

    @ExcelCellName("control.testName")
    private String testName;

    // Preconditions(input data)
    @ExcelCellName("precondition.userName")
    private String username;

    @ExcelCellName("precondition.userSecret")
    private String userSecret;

    // Expected results

    @ExcelCellName("expected.jwtResponseCode")
    private int expectedJwtResponseCode;

    @ExcelCellName("expected.clientResponseCode")
    private int expectedClientResponseCode;


    // Required no-arg constructor
    public TestEntry() {
    }

    public String getUsername() {
        return username;
    }

    public String getUserSecret() {
        return userSecret;
    }

    public int getExpectedJwtResponseCode() {
        return expectedJwtResponseCode;
    }

    public int getExpectedClientResponseCode() {
        return expectedClientResponseCode;
    }

    public String getTestId() {
        return testId;
    }

    public String getTestName() {
        return testName;
    }
}
