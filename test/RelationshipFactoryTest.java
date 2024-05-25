import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * RelationshipFactoryTest
 * @Author: Usman Sadiq B0937444
 * This is used to test if the proper Relationship instance is returned
 */
public class RelationshipFactoryTest {

    RelationshipFactory factory;

    private int friendRelationshipCode = 0;
    private int followsRelationshipCode = 1;
    @BeforeEach
    void getFactory(){
        factory = new RelationshipFactory();
    }

    /**
     * This is used to test when the command "friends" is passed in
     * A code for FriendsRelationship is supposed to be returned
     */
    @Test
    void getRelationshipTypeTestFriends(){
        String command = "friends";
        int returned = factory.getRelationshipType(command);
        assertEquals(returned, friendRelationshipCode);
    }

    /**
     * This is used to test when the command "unfriends" is passed in
     * A code for FriendsRelationship is supposed to be returned
     */
    @Test
    void getRelationshipTypeTestUnFriends(){
        String command = "unfriends";
        int returned = factory.getRelationshipType(command);
        assertEquals(returned, friendRelationshipCode);
    }

    /**
     * This is used to test when the command "follows" is passed in
     * A code FollowsRelationship is supposed to be returned
     */
    @Test
    void getRelationshipTypeTestFollows(){
        String command = "follows";
        int returned = factory.getRelationshipType(command);
        assertEquals(returned, followsRelationshipCode);
    }


    /**
     * This is used to test when the command "unfollows" is passed in
     * An code for FollowsRelationship is supposed to be returned
     */
    @Test
    void getRelationshipTypeTestUnFollows(){
        String command = "unfollows";
        int returned = factory.getRelationshipType(command);
        assertEquals(returned, followsRelationshipCode);
    }

    /**
     * This is used to test when the wrong command is passed in
     * An input mismatch exception is supposed to be returned
     */

    @Test
    void getRelationshipTypeTestWrongCommand(){
        String wrongCommand = "friend";
        try{
            factory.getRelationshipType(wrongCommand);
            assertTrue(false);
        }catch (InputMismatchException e){
            assertTrue(true);
        }

    }

    /**
     * This is used to test when the command "friends" is passed in
     * An instance for FriendsRelationship is supposed to be returned
     */
    @Test
    void getRelationshipToReturnFriends(){
        String command = "friends";
        Relationship returned = null;
        try {
            returned = factory.getRelationshipToReturn(command);
            boolean b = returned instanceof FriendRelationship;
            assertTrue(b);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * This is used to test when the command "unfriends" is passed in
     * An instance for FriendsRelationship is supposed to be returned
     */
    @Test
    void getRelationshipToReturnUnFriends(){
        String command = "unfriends";
        Relationship returned = null;
        try {
            returned = factory.getRelationshipToReturn(command);
            boolean b = returned instanceof FriendRelationship;
            assertTrue(b);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This is used to test when the command "follows" is passed in
     * An instance for FollowsRelationship is supposed to be returned
     */
    @Test
    void getRelationshipToReturnFollows(){
        String command = "follows";
        Relationship returned = null;
        try {
            returned = factory.getRelationshipToReturn(command);
            boolean b = returned instanceof FollowsRelationship;
            assertTrue(b);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This is used to test when the command "unfollows" is passed in
     * An instance for FollowsRelationship is supposed to be returned
     */
    @Test
    void getRelationshipToReturnUnFollows(){
        String command = "unfollows";
        Relationship returned = null;
        try {
            returned = factory.getRelationshipToReturn(command);
            boolean b = returned instanceof FollowsRelationship;
            assertTrue(b);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This is used to test when a wrong command is passed in
     * An InputMismatchException is supposed to be thrown
     */
    @Test
    void getRelationshipToReturnWrongCommand(){
        String command = "follow";
        Relationship returned = null;
        try {
            returned = factory.getRelationshipToReturn(command);
            boolean b = returned instanceof FollowsRelationship;
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }



}
