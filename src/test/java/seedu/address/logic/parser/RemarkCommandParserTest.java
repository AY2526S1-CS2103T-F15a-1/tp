package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Remark;

public class RemarkCommandParserTest {

    private final RemarkCommandParser parser = new RemarkCommandParser();

    @Test
    public void parse_validArgs_returnsRemarkCommand() throws Exception {
        Remark expectedRemark = new Remark(VALID_REMARK_AMY);
        RemarkCommand command = parser.parse(" " + INDEX_FIRST_PERSON.getOneBased() + REMARK_DESC_AMY);
        assertEquals(new RemarkCommand(INDEX_FIRST_PERSON, expectedRemark), command);
    }

    @Test
    public void parse_missingRemarkPrefix_returnsRemarkCommandWithEmptyRemark() throws Exception {
        RemarkCommand command = parser.parse(" " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new RemarkCommand(INDEX_FIRST_PERSON, new Remark("")), command);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" a" + REMARK_DESC_AMY));
    }
}
