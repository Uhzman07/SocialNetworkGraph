import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {
    InputValidator inputValidator;

    @BeforeEach
    void newInstance(){
        inputValidator = new InputValidator();
    }

    /**
     * This is used to check if a valid line is passed
     */
    @Test
    void initialFilterTestValid(){
        String valid1 = "Usman friends Teslim";
        String valid2 = "Usman joins";
        boolean output1 = inputValidator.initialFilter(valid1.split(" "));
        boolean output2 = inputValidator.initialFilter(valid2.split(" "));
        assertFalse(output1 || output2);
    }

    /**
     * This is used to check if an invalid line of one word is recognised
     */
    @Test
    void initialFilterTestInvalid(){
        String invalid = "Usman";
        try{
            inputValidator.initialFilter(invalid.split(" "));
            assertFalse(true);
        }catch (InputMismatchException e){
            assertTrue(true);
        }
    }

    /**
     * This is used to test if the right code is returned when a series of commands are given
     */
    @Test
    void commandValidatorTestFriendship(){
        int friendshipCode = 2;
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Usman follows Teslim");
        strings.add("Usman unfollows Teslim");
        strings.add("Usman friends Teslim");
        strings.add("Usman unfriends Teslim");

        for(String each : strings){
            int code = inputValidator.commandValidator(each.split(" "));
            assertEquals(code, friendshipCode);
        }

    }

    /**
     * This is used to test if the right code is returned when a series of simple commands are passed in
     */
    @Test
    void commandValidatorTestSimple(){
        int simpleCode = 1;
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Usman joins");
        strings.add("Usman leaves");

        for(String each : strings){
            int code = inputValidator.commandValidator(each.split(" "));
            assertEquals(code, simpleCode);
        }
    }

    /**
     * This is used to check if the right code is passed when the invalid friendship command is passed in
     */
    @Test
    void commandValidatorTestFriendshipInvalid(){
        int breakerInteger = 99;
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Usman unfriend Teslim");

        for(String each : strings){
            int code = inputValidator.commandValidator(each.split(" "));
            assertEquals(code, breakerInteger);
        }

    }

    /**
     * This is used to test if the right code is returned when an invalid "joins" or "leaves" line is passed in
     */
    @Test
    void commandValidatorTestSimpleInvalid(){
        int breakerInteger = 99;
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Usman join");
        strings.add("Usman leave");

        for(String each : strings){
            int code = inputValidator.commandValidator(each.split(" "));
            assertEquals(code, breakerInteger);
        }
    }

    /**
     * This is used to test the valid size for a friendship command
     */
    @Test
    void validateSizeTestFriendshipValid(){
        int friendshipCode = 2;
        String command = "Usman friends Teslim";
        boolean returned = inputValidator.validateSize(command.split(" "), friendshipCode);
        assertTrue(returned);
    }

    /**
     * This is used to test the valid size for a friendship command
     */
    @Test
    void validateSizeTestFriendshipInvalid(){
        int friendshipCode = 2;
        String command = "Usman friends Teslim now";
        boolean returned = inputValidator.validateSize(command.split(" "), friendshipCode);
        assertFalse(returned);
    }


    /**
     * This is used to test the right size of input for the simple command
     */
    @Test
    void validateSizeTestSimpleValid(){
        int simpleCode = 1;
        String command = "Usman joins";
        boolean returned = inputValidator.validateSize(command.split(" "), simpleCode);
        assertTrue(returned);
    }

    /**
     * This is used to test the right size of input for the simple command
     *
     */
    @Test
    void validateSizeTestSimpleInvalid(){
        int simpleCode = 1;
        String command = "Usman joins now";
        boolean returned = inputValidator.validateSize(command.split(" "), simpleCode);
        assertFalse(returned);
    }

    /**
     * This is used to test the size of the input but given a wrong code
     */
    @Test
    void validateSizeTestWrongCode(){
        int wrongCode = 3;
        String command = "Usman friends Teslim";
        try{
            boolean returned = inputValidator.validateSize(command.split(" "), wrongCode);
            assertTrue(false);
        }catch (InputMismatchException e){
           assertTrue(true);
        }

    }

    /**
     * This is used to test for the different name for users
     */
    @Test
    void checkSameNameTestValid(){
        String valid = "Usman friends Teslim";
        boolean returned = inputValidator.checkSameName(valid.split(" "));
        assertFalse(returned);
    }

    /**
     * This is used to test for the same name of users
     */
    @Test
    void checkSameNameTestInvalid(){
        String invalid = "Teslim friends Teslim";
        boolean returned = inputValidator.checkSameName(invalid.split(" "));
        assertTrue(returned);
    }

    /**
     * This is used to test if a valid user is not null
     */
    @Test
    void checkUserNullTestValid(){
        User user = new User("Usman");
        try{
            inputValidator.checkUserNotNull(user);
            assertTrue(true);
        }catch (NullPointerException e){
            assertTrue(false);
        }
    }

    /**
     * This is used to check if the null pointer exception for the invalid user is caught
     */
    @Test
    void checkUserNullTestInvalid(){
        User user = null;
        try{
            inputValidator.checkUserNotNull(user);
            assertTrue(false);
        }catch (NullPointerException e){
            assertTrue(true);
        }
    }

    /**
     * This is used to test for both Users not been null
     */
    @Test
    void checkBothUsersNotNullTestBothValid(){
        User user1 = new User("Usman");
        User user2 = new User("Teslim");
        try{
            inputValidator.checkBothUsersNotNull(user1,user2);
            assertTrue(true);
        }catch (NullPointerException e){
            assertTrue(false);
        }
    }

    /**
     * This is used to test that a Null pointer exception is thrown when the first User is null
     */
    @Test
    void checkBothUsersNotNullTestFirstInvalid(){
        User user1 = null;
        User user2 = new User("Teslim");
        try{
            inputValidator.checkBothUsersNotNull(user1,user2);
            assertTrue(false);
        }catch (NullPointerException e){
            assertTrue(true);
        }
    }

    /**
     * This is used to check if a null pointer exception is caught when the second user been considered is null
     */
    @Test
    void checkBothUsersNotNullTestSecondInvalid(){
        User user1 = new User("Usman");
        User user2 = null;
        try{
            inputValidator.checkBothUsersNotNull(user1,user2);
            assertTrue(false);
        }catch (NullPointerException e){
            assertTrue(true);
        }
    }

    /**
     * This is used to check if a null pointer exception is caught if both users are null
     */
    @Test
    void checkBothUsersNotNullTestBothInvalid(){
        User user1 = null;
        User user2 = null;
        try{
            inputValidator.checkBothUsersNotNull(user1,user2);
            assertTrue(false);
        }catch (NullPointerException e){
            assertTrue(true);
        }
    }

    /**
     * This is used to test if the invalid message is well passed into the recommendations
     */
    @Test
    void printErrorMessageTest(){
        Recommender recommender = new Recommender();
        int initialSize = recommender.getFinalRecommendations().size();
        String expectedOutput = "Invalid line: Usman finds me";
        String errorInput = "Usman finds me";
        // After adding the message;
        inputValidator.printErrorMessage(errorInput.split(" "));

        // After removing the message
        int finalSize = recommender.getFinalRecommendations().size();

        assertEquals(initialSize, finalSize);
    }








}
