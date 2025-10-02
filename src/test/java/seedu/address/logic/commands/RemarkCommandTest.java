package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests for {@link RemarkCommand}.
 */
public class RemarkCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemark_success() throws CommandException {
        Index index = INDEX_SECOND_PERSON;
        Person targetPerson = model.getFilteredPersonList().get(index.getZeroBased());
        Remark newRemark = new Remark(VALID_REMARK_AMY);
        Person expectedPerson = new PersonBuilder(targetPerson).withRemark(newRemark.value).build();

        RemarkCommand command = new RemarkCommand(index, newRemark);
        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, Messages.format(expectedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(targetPerson, expectedPerson);

        CommandResult result = command.execute(model);

        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_deleteRemark_success() throws CommandException {
        Index index = INDEX_FIRST_PERSON;
        Person targetPerson = model.getFilteredPersonList().get(index.getZeroBased());
        Remark emptyRemark = new Remark("");
        Person expectedPerson = new PersonBuilder(targetPerson).withRemark(emptyRemark.value).build();

        RemarkCommand command = new RemarkCommand(index, emptyRemark);
        String expectedMessage = String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS,
                Messages.format(expectedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(targetPerson, expectedPerson);

        CommandResult result = command.execute(model);

        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        RemarkCommand command = new RemarkCommand(outOfBoundIndex, new Remark(""));

        CommandException commandException = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, commandException.getMessage());
    }
}
