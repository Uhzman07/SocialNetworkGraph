import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * @Author: Usman Sadiq B00937444
 * This is the class that is tasked with collecting the input from the user
 */
public class InputValidator {

    // Variable
    private final int standardSizeWithFriendshipCommand = 3;
    private final int standardSizeWithoutFriendshipCommand = 2;
    private final int unacceptableWordsLength = 1;

    private final int breakerInteger = 99;

    private final int friendshipCode = 2;

    private final int simpleCode = 1;

    // This is the code that is used to check if a command is valid and specifically if it is a simple command or a friendship command
    private int validatorCode =  -1;

    // Constructor
    public InputValidator(){

    }

    /**
     * This is used to initially check if the inputLine is valid initially that is, it isn't just a line containing a simple word
     * @param inputLine
     * @return
     */
    public boolean initialFilter(String [] inputLine){
        if(inputLine.length <= unacceptableWordsLength){
            throw new InputMismatchException("Not possible to have a word on a line");
        }
        return false;
    }

    /**
     * This method checks the given command and decides if the command is targeted towards affecting the friendship like ("friends","follows","unfollows","unfriends")
     * This method also checks if the command given is valid and throws an exception otherwise
     * @param commands
     * @return 1 if the command is a simple one like "joins", "leaves" 2 if it is a friendship command like "follows", "unfollows", "friends", "unfriends"
     * It also throws an InputMismatchException if the  command is invalid
     */
    public int commandValidator(String[] commands) throws InputMismatchException{

        String command = commands[1];

        if(command.equals("unfollows") || command.equals("follows") || command.equals("friends") || command.equals("unfriends")){
            return friendshipCode;
        }
        else if(command.equals("joins") || command.equals("leaves")){
            return simpleCode;
        }
        else{
            printErrorMessage(commands);
            return breakerInteger;
        }

    }

    /**
     * This method checks an entire input line and validates if the right words are present based on the available commands
     * This method also throws an exception if the expected number of words in a line is exceeded.
     * @param inputLine
     * @param inputLineType
     * @return True if the size is valid based on the command and false otherwise
     */
    public boolean validateSize(String[] inputLine, int inputLineType) throws InputMismatchException{
        int inputLength = inputLine.length;
        // This is for the normal command
        if(inputLineType == simpleCode){
            if (inputLength == standardSizeWithoutFriendshipCommand){
                return true;
            }
            String message = "Invalid line: ";
            for(int i=0; i<inputLine.length; i++){
                message += inputLine[i] + " ";
            }
            printErrorMessage(inputLine);
            return false;

        }
        // This is for relationship command
        else if (inputLineType == friendshipCode){
            if(inputLength == standardSizeWithFriendshipCommand){
                return true;
            }
            printErrorMessage(inputLine);
            return false;
        }
        else{
            throw new InputMismatchException("This is weird but the signal for the type of input line shouldn't be "+ inputLineType+" it is expected to be 1 or 2");
        }
    }

    /**
     * This method checks if the input is not intended to perform a relationship function on the same user
     * @param inputLine
     * @return True if valid and false otherwise
     */
    public boolean checkSameName(String[] inputLine) throws InputMismatchException{
        if(inputLine[0].equals(inputLine[2])){
            printErrorMessage(inputLine);
            return true;
        }
        return false;
    }

    /**
     * This is used to check if the user to remove is not null and then throws a Null Pointer Exception otherwise
     * @param givenUser
     */
    public void checkUserNotNull(User givenUser) throws NullPointerException{
        if(givenUser == null){
            throw new NullPointerException("The given user is not present");
        }
    }

    public void checkBothUsersNotNull(User user1, User user2){
        if(user1 == null || user2 == null){
            throw new NullPointerException("None of the users are expected to be null");
        }
    }

    /**
     * This is used to print out the error message given
     * @param input
     */
    public void printErrorMessage(String[] input){
        String message = "Invalid line: ";
        for(int i=0; i< input.length-1; i++){
            message += input[i]+ " ";
        }
        message+= input[input.length-1];
        Recommender recommender = new Recommender();
        recommender.getFinalRecommendations().add(message);
        recommender.printOutRecommendations();
    }
}
