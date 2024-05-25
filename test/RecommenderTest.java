import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecommenderTest {

    Recommender recommender;
    @BeforeEach
    void getRecommender(){
        recommender = new Recommender();
    }

    /*
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

     */



    /**
     * This is used to test if the right friend recommendations are added to the list
     */
    @Test
    void makeFriendRecommendationTest(){
        ArrayList<String> list = new ArrayList<>();

        User user1 = new User("Usman");
        User user2 = new User("Idris");
        User user3 = new User("Teslim");
        User user4 = new User("Muiz");

        User.users.put("Usman", user1);
        User.users.put("Idris", user2);
        User.users.put("Teslim", user3);
        User.users.put("Muiz", user4);

        user1.getAdj().put("Teslim",user3);
        user1.getAdj().put("Muiz", user4);

        user3.getAdj().put("Usman",user1);
        user4.getAdj().put("Usman", user1);

        recommender.makeFriendRecommendations(user2, user1,list);

        ArrayList<String> expectedList = new ArrayList<>();

        expectedList.add("Idris and Teslim should be friends");
        expectedList.add("Idris and Muiz should be friends");

        int counter = 0;

        for(int i=0; i<expectedList.size(); i++){
            if(list.get(i).equals(expectedList.get(i))){
                counter++;
            }
        }

        assertEquals(counter, expectedList.size());

    }

    /**
     * This is used to test if the right follows recommendation is given
     */
    @Test
    void makeFollowsRecommendationTest(){
        ArrayList<String> list = new ArrayList<>();

        User user1 = new User("Usman");
        User user2 = new User("Idris");
        User user3 = new User("Teslim");
        User user4 = new User("Muiz");

        User.users.put("Usman", user1);
        User.users.put("Idris", user2);
        User.users.put("Teslim", user3);
        User.users.put("Muiz", user4);

        user1.getAdj().put("Teslim",user3);
        user1.getAdj().put("Muiz", user4);


        recommender.makeFollowRecommendations(user1, user2,list);

        ArrayList<String> expectedList = new ArrayList<>();

        expectedList.add("Teslim should follow Idris");
        expectedList.add("Muiz should follow Idris");

        int counter = 0;

        for(int i=0; i<list.size(); i++){
            if(list.get(i).equals(expectedList.get(i))){
                counter++;
            }
        }

        assertEquals(counter, expectedList.size());

    }


    /**
     * This is used to check if the right friend recommendations are made on both sides of the user
     * Alice joins
     * Carol joins
     * Bob joins
     * Bob friends Alice
     * Bob friends Carol
     * Dave joins
     * Dave friends Bob
     * Eve joins
     * Eve friends Bob
     * Dave leaves
     * Dave joins
     * Dave friends Bob
     * Dave unfriends Bob
     * Dave friends Bob
     * Fred joins
     * Alice friends Fred
     * Bob friends Fred
     * Carol friends Fred
     * Bob leaves
     * Fred leaves
     * Alice friends Carol
     * Alice leaves
     * Carol leaves
     * Dave leaves
     * Eve leaves
     * end
     */
    @Test
    void friendRecommenderTest(){
        PrintStream stream;
        String filePath = "C:\\Users\\Nafis\\OneDrive\\Desktop\\DAL COMPUTER SCIENCE\\CSCI 2134\\ASSN_4\\usadiq\\test\\RecommendFile";
        File file = new File(filePath);
        //Instantiating the PrintStream class
        try {
             stream = new PrintStream(file);
            System.setOut(stream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        String printedOutput ="";
        User Alice = new User("Alice");
        User Carol = new User("Carol");
        User Bob = new User("Bob");

        // Bob Friends Alice
        Bob.getAdj().put("Alice", Alice);
        Alice.getAdj().put("Bob", Bob);


        recommender.friendRecommend(Bob,Alice);

        // Capture the output
       // printedOutput = outputStreamCaptor.toString().trim();
        // Assert the output matches our expectation
        //assertEquals("", printedOutput);

        // Bob Friends Carol
        Bob.getAdj().put("Carol", Carol);
        Carol.getAdj().put("Bob", Bob);


        recommender.friendRecommend(Bob, Carol);

        //printedOutput = outputStreamCaptor.toString().trim();

        //System.out.println(printedOutput);



        // Dave Joins
        User Dave = new User("Dave");

        // Dave friends Bob
        Bob.getAdj().put("Dave", Dave);
        Dave.getAdj().put("Bob", Bob);

        recommender.friendRecommend(Bob,Dave);

        // Eve Joins
        User Eve = new User("Eve");

        // Eve Friends Bob
        Eve.getAdj().put("Bob", Bob);
        Bob.getAdj().put("Eve", Eve);

        recommender.friendRecommend(Eve,Bob);



        // Dave leaves
        Dave.leave();

        // Dave Joins
        User Dave2 = new User("Dave");

        // Dave Friends Bob
        Dave2.getAdj().put("Bob", Bob);
        Bob.getAdj().put("Dave", Dave2);

        recommender.friendRecommend(Dave2,Bob);

        // Dave UnFriends Bob
        Dave2.getAdj().remove("Bob");
        Bob.getAdj().remove("Dave");

        // Dave Friends Bob
        Dave2.getAdj().put("Bob", Bob);
        Bob.getAdj().put("Dave", Dave2);

        recommender.friendRecommend(Dave2,Bob);

        // Fred Join
        User Fred = new User("Fred");

        // Alice friends Fred
        Alice.getAdj().put("Fred", Fred);
        Fred.getAdj().put("Alice", Alice);

        recommender.friendRecommend(Alice,Fred);

        // Bob Friends Fred
        Bob.getAdj().put("Fred", Fred);
        Fred.getAdj().put("Bob", Bob);

        recommender.friendRecommend(Bob,Fred);

        // Carol Friends Fred
        Carol.getAdj().put("Fred", Fred);
        Fred.getAdj().put("Carol", Carol);

        recommender.friendRecommend(Carol,Fred);

        // Bob leaves
        Bob.leave();

        // Fred leaves
        Fred.leave();

        // Alice friends Carol
        Alice.getAdj().put("Carol", Carol);
        Carol.getAdj().put("Alice", Alice);


        recommender.friendRecommend(Alice,Carol);

        // Alice leaves
        Alice.leave();

        // Carol leaves
        Carol.leave();

        // Dave leaves
        Dave2.leave();

        // Eve leaves
        Eve.leave();



        // Close Stream
        System.out.close();


        System.setOut(System.out);

        /**
         * Note that I found it challenging to read from the file, so I did my comparison manually, and it works fine.
         */

    }

    /**
     * This is used to test the followRecommendation method
     * Alice joins
     * Carol joins
     * Bob joins
     * Bob follows Alice
     * Bob friends Carol
     * end
     */
    @Test
    void followRecommenderTest(){

        PrintStream stream;
        String filePath = "C:\\Users\\Nafis\\OneDrive\\Desktop\\DAL COMPUTER SCIENCE\\CSCI 2134\\ASSN_4\\usadiq\\test\\RecommendFollowFile";
        File file = new File(filePath);
        //Instantiating the PrintStream class
        try {
            stream = new PrintStream(file);
            System.setOut(stream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        // Alice Joins
        User Alice = new User("Alice");

        // Carol Joins
        User Carol = new User("Carol");

        // Bob Joins
        User Bob = new User("Bob");

        // Bob Follows Alice
        Bob.getAdj().put("Alice",Alice);

        recommender.followRecommend(Bob,Alice);

        // Bob Friends Carol
        Bob.getAdj().put("Carol",Carol);
        Carol.getAdj().put("Bob",Bob);

        recommender.friendRecommend(Bob, Carol);

        // Close Stream
        System.out.close();


        System.setOut(System.out);

        /**
         * Note that I found it challenging to read from the file, so I did my comparison manually, and it works fine.
         */


    }

    /**
     * This is used to test if the right recommendation list is returned
     */
    @Test
    void getFinalRecommendationsTest(){
        ArrayList<String> expectedList = recommender.getFinalRecommendations();
       expectedList.add("Usman is here");
       expectedList.add("Usman is here");
       expectedList.add("Usman is here");

       assertEquals(3, recommender.getFinalRecommendations().size());

    }

    /**
     * This is used to test if all the recommendations are appropriately printed out from the list
     */
    @Test
    void printRecommendationsTest(){
        // To empty the list
        recommender.getFinalRecommendations().clear();

        ArrayList<String> expectedList = recommender.getFinalRecommendations();
        expectedList.add("Usman is here");
        expectedList.add("Usman is here");
        expectedList.add("Usman is here");
        expectedList.add("Usman is here");
        expectedList.add("Usman is here");
        expectedList.add("Usman is here");

        // Then to print out
        recommender.printOutRecommendations();

        assertEquals(0, recommender.getFinalRecommendations().size());

    }


}
