import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class UserTest {
    private static String[] friendNames = {"Alice", "Bob", "Carol", "Dave", "Eve", "Frank", "Grace"};

    // clear the static users HashMap before each test
    @AfterEach
    void clearUsers() {
        User.users.clear();
    }

    // Test constructing a user adds them to the HashMap
    @Test
    void testConstructor() {
        User u = new User(friendNames[0]);
        assertEquals(friendNames[0], u.getName(), "Incorrect name");
        assertEquals(1, User.users.size(), "Incorrect size");
        assertEquals(u, User.users.get(friendNames[0]), "User not in HashMap");
    }

    /* Constructing two users with the same name should
     * add the first user to the HashMap but not the second.
     */
    @Test
    void testConstructorSameName() {
        User u = new User(friendNames[0]);
        User v = new User(friendNames[0]);
        assertEquals(friendNames[0], u.getName(), "Incorrect name");
        assertEquals(friendNames[0], v.getName(), "Incorrect name");
        assertEquals(1, User.users.size(), "Incorrect size");
        assertEquals(u, User.users.get(friendNames[0]), "User not in HashMap");
    }

    // Test constructing two users with different names adds both to the HashMap
    @Test
    void testConstructorDifferentName() {
        User u = new User(friendNames[0]);
        User v = new User(friendNames[1]);
        assertEquals(friendNames[0], u.getName(), "Incorrect name for u");
        assertEquals(friendNames[1], v.getName(), "Incorrect name for v");
        assertEquals(2, User.users.size(), "Incorrect size");
        assertEquals(u, User.users.get(friendNames[0]), "User u not in HashMap");
        assertEquals(v, User.users.get(friendNames[1]), "User v not in HashMap");
    }

    // Test finding a user that exists returns that user
    @Test
    void testFind() {
        User u = new User(friendNames[0]);
        User v = User.find(friendNames[0]);
        assertEquals(u, v, "find returned the wrong user");
    }

    // Test finding a user that does not exist returns null
    @Test
    void testFindNotExists() {
        User u = User.find(friendNames[0]);
        assertEquals(null, u, "find returned a user that does not exist");
    }

    // Test find returns the correct user when there are multiple users
    @Test
    void testFindMultiple() {
        User u = new User(friendNames[0]);
        User v = new User(friendNames[1]);
        User w = new User(friendNames[2]);
        User x = User.find(friendNames[1]);
        assertEquals(u, User.find(friendNames[0]), "find returned the wrong user for u");
        assertEquals(v, User.find(friendNames[1]), "find returned the wrong user for v");
        assertEquals(w, User.find(friendNames[2]), "find returned the wrong user for w");
    }


    // Test a single user leaving removes them from the HashMap
    @Test
    void testLeaveSingle() {
        User u = new User(friendNames[0]);
        u.leave();
        assertEquals(0, u.getAdj().size(), "u's adj has the wrong size");
        assertEquals(0, User.users.size(), "Incorrect size");
        assertEquals(null, User.users.get(friendNames[0]), "User not removed from HashMap");
    }

    /**
     * This is used to test if the right name is returned
     */
    @Test
    void getNameTest(){
        User u = new User(friendNames[0]);
        String name = u.getName();
        assertTrue(name.equals(friendNames[0]));
    }

    /**
     * This is used to test if the right adjacency list is returned
     */
    @Test
    void getAdj(){
        User u1 = new User(friendNames[0]);
        User u2 = new User(friendNames[1]);
        User u3 = new User(friendNames[2]);
        User u4 = new User(friendNames[3]);
        Relationship relationship = new FriendRelationship();
        relationship.launchRelationship(u1, u2);
        relationship.launchRelationship(u1, u3);
        relationship.launchRelationship(u1, u4);

        assertEquals(3, u1.getAdj().size());
    }



}
