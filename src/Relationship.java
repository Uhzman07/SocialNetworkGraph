import java.util.ArrayList;

/**
 * This is the interface that contains the methods that are expected to be common to all relationships that are available
 */
public interface Relationship {

    /**
     * This is used to determine the action whether to end or start a relationship
     * @param action
     * @param firstUser
     * @param secondUser
     */
    public void determineAction(String action, User firstUser, User secondUser);

    /**
     * This is used to start up a relationship between two users
     * @param firstUser
     * @param secondUser
     */
    public void launchRelationship(User firstUser, User secondUser);

    /**
     * This is used to end an existing relationship between two users
     * @param firstUser
     * @param secondUser
     */
    public void endRelationship(User firstUser, User secondUser);

    /**
     * This is used to validate the presence of a relationship between two users
     * @param firstUser
     * @param secondUser
     * @return
     */
    public boolean checker(User firstUser, User secondUser);

    /**
     * This is used to send recommendations based on the available relationship
     * @param user1
     * @param user2
     */
    public void sendRecommendations(User user1, User user2);

}
