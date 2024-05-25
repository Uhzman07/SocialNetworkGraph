import java.util.ArrayList;
import java.util.InputMismatchException;

public class FriendRelationship implements Relationship{


    private Recommender recommender;

    public FriendRelationship(){
        recommender = new Recommender();
    }


    /**
     * This is used to determine the type of action that is expected of the Relationship type that is either to friend or unfriend
     * @param action
     */
    @Override
    public void determineAction(String action, User firstUser, User secondUser) {
        if(action.equals("friends")){
            this.launchRelationship(firstUser,secondUser);
        }
        else if(action.equals("unfriends")){
            this.endRelationship(firstUser,secondUser);
        }
        else{
            throw new InputMismatchException();
        }
    }

    /**
     * Given two users, this method allows two users to become friends
     * @param firstUser
     * @param secondUser
     *  Friending adds the friendship to adj and to the other user's adj. Friending a user that is already a friend
     *  does not change the friendship.
     */
    @Override
    public void launchRelationship(User firstUser, User secondUser) {
        firstUser.getAdj().put(secondUser.getName(), secondUser);
        secondUser.getAdj().put(firstUser.getName(), firstUser);

        sendRecommendations(firstUser,secondUser);

    }

    /**
     * This is used to remove the Friendship relationship between two users that is to remove
     * the user from each other's adjacency list
     * @param firstUser
     * @param secondUser
     */
    @Override
    public void endRelationship(User firstUser, User secondUser) {
        firstUser.getAdj().remove(secondUser.getName());
        secondUser.getAdj().remove(firstUser.getName());
    }

    /**
     * This is used to check if two users are friends
     * @param firstUser
     * @param secondUser
     * @return
     */
    @Override
    public boolean checker(User firstUser, User secondUser) {
        boolean condition1 = firstUser.getAdj().containsKey(secondUser.getName());
        boolean condition2 = secondUser.getAdj().containsKey(firstUser.getName());

        return condition1 && condition2;
    }

    /**
     * This is used to pass the need for a recommendation to the FriendRecommender class.
     * @param user1
     * @param user2
     */
    public void sendRecommendations(User user1, User user2){
        recommender.friendRecommend(user1,user2);
    }
}
