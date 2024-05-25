import java.util.ArrayList;
import java.util.Scanner;

//import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @Author: Usman Sadiq B00937444
 * This is the class that contains the main method
 * This class collects the inputs from the user, validates the input, and then perform action based on the input
 */

public class FriendRecommender {

    // Variables Declaration
    public static InputValidator validator;

    public static RelationshipFactory relationshipFactory;

    public static int friendshipLengthSignal = 2;

    // To initialize
    public static int commandChecker = -1;

    private static int integerBreaker = 99;


    /* main
     * This method is used to read input from the user and print the output of the
     * friend recommendations. The input is read from the standard input and the
     * output is printed to the standard output.
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        compute(scanner);
        /*

        Recommender recommender = new Recommender();
        for(String message: recommender.getFinalRecommendations()){
            System.out.println(message);
        }

         */

    }

    /* compute
     * This method takes a Scanner, input, and returns an ArrayList of Strings.
     * The Scanner contains the input from the user. The method reads the input
     * and then passes in to the appropriate relationship class to perform an action.
     */
    public static void compute( Scanner input ) throws Exception {
        // Instantiate Validator
        validator = new InputValidator();

        // To instantiate the Relationship Factory
        relationshipFactory = new RelationshipFactory();

        ArrayList<String> finalRecommendationList = new ArrayList<String>();

        for(String lineInput = input.nextLine(); !lineInput.equals( "end" ); lineInput = input.nextLine()) {

            // Variable Declaration
            String firstName = "";
            String lastName = "";
            String command = "";

            // To initialize the User class
            User user1 = null;
            User user2 = null;

            // This array stores names and commands required; its standard size should be 3 when with friendship command and 2 when without friendship command
            String [] commands = lineInput.split(" ");

            if(!validator.initialFilter(commands)){
                firstName = commands[0];



                command = commands[1];

                // To check the type of command
                commandChecker = validator.commandValidator(commands);

                if(commandChecker == integerBreaker ){
                    break;
                }


                if(validator.validateSize(commands,commandChecker)){

                    // To collect the last name if the command is a friendship command
                    if(commandChecker == friendshipLengthSignal){

                        // Then to validate that the names are not the same
                        if(!validator.checkSameName(commands)){
                            lastName = commands[2];

                            user1 = User.users.get(firstName);
                            user2 = User.users.get(lastName);

                            // To check if both users are not null
                            validator.checkBothUsersNotNull(user1,user2);

                            // To get the type of relationship speculated by the user
                            Relationship relationshipType;
                            relationshipType = relationshipFactory.getRelationshipToReturn(command);

                            relationshipType.determineAction(command, user1, user2);
                        }
                        else{
                            break;
                        }
                    }
                    else{ // This is the normal command
                        if(command.equals("joins")){
                            // To ensure that "User1" is null initially
                            assert(user1 == null);

                            // First User
                            user1 = new User(firstName);

                            //System.out.println(user1.getName());
                        }
                        else if (command.equals("leaves")){

                            // To check if the user is not null
                            user1 = User.users.get(firstName);
                            validator.checkUserNotNull(user1);

                            // To leave the User list
                            user1.leave();
                        }
                    }

                }
                else{
                    break;
                }

            }


        }

    }


}
