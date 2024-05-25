import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This is used to test the FollowsRelationship class
 */
public class FollowsRelationshipTest {

    Relationship relationship;
    @BeforeEach
    void getRelationship(){
        relationship = new FollowsRelationship();
    }

    /**
     * This is used to test if two Users are appropriately added as followers
     */
    @Test
    void launchRelationshipTest(){
        User user1 = new User("Usman");
        User user2 = new User("Teslim");

        relationship.launchRelationship(user1, user2);

        boolean one = user1.getAdj().containsKey(user2.getName());
        boolean two = !user2.getAdj().containsKey(user1.getName());

        assertTrue(one && two);
    }

    /**
     * This is used to test if two Users that have a follows relationship are not affected by a repeated follows command
     */
    @Test
    void launchRelationshipTestRepeated(){
        User user1 = new User("Usman");
        User user2 = new User("Teslim");
        User user3 = new User("Idris");

        relationship.launchRelationship(user1, user2);

        // Different Person
        relationship.launchRelationship(user1, user3);

        int initialSize1 = user1.getAdj().size();
        int initialSize2 = user2.getAdj().size();

        relationship.launchRelationship(user1,user2);

        int finalSize1 = user1.getAdj().size();
        int finalSize2 = user2.getAdj().size();

        assertTrue(initialSize1==finalSize1 && initialSize2 == finalSize2);
    }


    /**
     * This is to test if the user is actually removed from the other user's adjacency list
     */
    @Test
    void endRelationshipTestFollows(){
        User user1 = new User("Usman");
        User user2 = new User("Teslim");
        User user3 = new User("Idris");

        relationship.launchRelationship(user1, user2);

        // Different friend
        relationship.launchRelationship(user1, user3);

        int initialSize1 = user1.getAdj().size();
        int initialSize2 = user2.getAdj().size();

        relationship.endRelationship(user1,user2);

        int finalSize1 = user1.getAdj().size();
        int finalSize2 = user2.getAdj().size();

        assertTrue(initialSize1-1==finalSize1 && initialSize2 == finalSize2);
    }

    /**
     * This is to test if the Users are not affected by an "unfriends" command when the Users never followed each other
     */
    @Test
    void endRelationshipTestNotFollows(){
        User user1 = new User("Usman");
        User user2 = new User("Teslim");
        User user3 = new User("Idris");
        User user4 = new User("Muiz");

        relationship.launchRelationship(user1, user4);

        // Different friend
        relationship.launchRelationship(user1, user3);

        relationship.launchRelationship(user2, user3);
        relationship.launchRelationship(user1, user4);

        int initialSize1 = user1.getAdj().size();
        int initialSize2 = user2.getAdj().size();

        relationship.endRelationship(user1,user2);

        int finalSize1 = user1.getAdj().size();
        int finalSize2 = user2.getAdj().size();

        assertTrue(initialSize1==finalSize1 && initialSize2 == finalSize2);
    }

    /**
     * This is used to test if "true" is returned if there is a follow relationship between users
     */
    @Test
    void checkerTestFollows(){
        User user1 = new User("Usman");
        User user2 = new User("Teslim");
        User user3 = new User("Idris");
        User user4 = new User("Muiz");

        relationship.launchRelationship(user1, user4);

        boolean returned = relationship.checker(user1,user4);

        assertTrue(returned);
    }

    /**
     * This is used to check for two users that are not followers
     */
    @Test
    void checkerTestNotFollows(){
        User user1 = new User("Usman");
        User user2 = new User("Teslim");
        User user3 = new User("Idris");
        User user4 = new User("Muiz");

        relationship.launchRelationship(user1, user4);

        boolean returned = relationship.checker(user1,user2);

        assertFalse(returned);
    }

    /**
     * This is used to check if the right action is taken when the command is "follows"
     */
    @Test
    void determineActionTestFollows(){
        String command = "follows";
        User user1 = new User("Usman");
        User user2 = new User("Teslim");
        User user3 = new User("Idris");
        User user4 = new User("Muiz");

        int initialSize1 = user1.getAdj().size();
        int initialSize2 = user2.getAdj().size();

        relationship.determineAction(command, user1, user2);

        int finalSize1 = user1.getAdj().size();
        int finalSize2 = user2.getAdj().size();

        assertTrue(finalSize1==initialSize1+1 && finalSize2 == initialSize2);
    }

    /**
     * This is used to test if the right action is taken to end the relationship if the command "unfollows" is passed in
     */
    @Test
    void determineActionTestUnFollows(){

        String command = "unfollows";

        User user1 = new User("Usman");
        User user2 = new User("Teslim");
        User user3 = new User("Idris");

        relationship.launchRelationship(user1, user2);

        // Different friend
        relationship.launchRelationship(user1, user3);

        int initialSize1 = user1.getAdj().size();
        int initialSize2 = user2.getAdj().size();

        relationship.determineAction(command, user1, user2);

        int finalSize1 = user1.getAdj().size();
        int finalSize2 = user2.getAdj().size();

        assertTrue(initialSize1-1==finalSize1 && initialSize2 == finalSize2);
    }

    /**
     * This is used to test if an Input mismatch Exception is caught for when the wrong command is passed in
     */
    @Test
    void testInvalidInput(){
        User user1 = new User("Usman");
        User user2 = new User("Teslim");
        User user3 = new User("Idris");
        String command = "follow";

        try{
            relationship.determineAction(command, user1, user2);
            assertTrue(false);

        }catch (InputMismatchException e){
            assertTrue(true);
        }

    }

    /**
     * This is used to test if the recommendations is sent the appropriate way
     */
    @Test
    void sendRecommendationsTest(){
        User user1 = new User("Usman");
        User user2 = new User("Teslim");
        User user3 = new User("Idris");

        Recommender recommender = new Recommender();
        // To add random message to the recommendations
        recommender.getFinalRecommendations().add("Let's go");
        relationship.launchRelationship(user1, user2);

        int initialSize = recommender.getFinalRecommendations().size();

        // Since we are certain that some messages will be passed to the recommendations ArrayList
        relationship.sendRecommendations(user1, user2);

        int finalSize = recommender.getFinalRecommendations().size();

        assertTrue(finalSize == initialSize);

    }








}
