import java.util.ArrayList;
import java.util.InputMismatchException;

public class FollowsRelationship implements Relationship{


    private Recommender recommender;

    /**
     * Empty Constructor
     */
    public FollowsRelationship(){
        recommender = new Recommender();
    }


    /**
     * This is used to pass the action to the user
     * @param action
     * @param firstUser
     * @param secondUser
     */
    @Override
    public void determineAction(String action, User firstUser, User secondUser) {
        if(action.equals("follows")){
            this.launchRelationship(firstUser,secondUser);
        }
        else if(action.equals("unfollows")){
            this.endRelationship(firstUser,secondUser);
        }
        else{
            throw new InputMismatchException();
        }

    }

    /**
     * This is used to make a particular follow another that is, add the second user in the adjacency list of the first user
     * This is a one-way relationship
     * @param firstUser
     * @param secondUser
     */
    @Override
    public void launchRelationship(User firstUser, User secondUser) {
        firstUser.getAdj().put(secondUser.getName(), secondUser);
        sendRecommendations(firstUser, secondUser);
    }

    /**
     * This is used to remove a friend from the adjacency list of the other
     * @param firstUser
     * @param secondUser
     */
    @Override
    public void endRelationship(User firstUser, User secondUser) {
        firstUser.getAdj().remove(secondUser.getName());
    }

    /**
     * This is used to check if a particular user follows another user
     * @param firstUser
     * @param secondUser
     * @return True if the user follows the other user and false otherwise
     */
    @Override
    public boolean checker(User firstUser, User secondUser) {
        boolean condition1 = firstUser.getAdj().containsKey(secondUser.getName());
        boolean condition2 = !secondUser.getAdj().containsKey(firstUser.getName());

        return condition1 && condition2;
    }

    /**
     * This is used to send follow recommendations class based on the users
     * @param user1
     * @param user2
     */
    @Override
    public void sendRecommendations(User user1, User user2) {
        recommender.followRecommend(user1,user2);
    }
}
