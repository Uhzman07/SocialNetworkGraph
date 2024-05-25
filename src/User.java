import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

/* User.java
 *
 * This class represents a user in a social network. The code uses a static
 * HashMap to keep track of all users. The HashMap is keyed by the user's name
 * and can be searched using the find method.
 *
 * Users can:
 *  - friend another user
 * - unfriend another user
 * - leave the social network
 * - check if they are friends with another user
 */
public class User{
  /* static HashMap to keep track of all users. Static means that there is only
   * one copy of the HashMap for all instances of the class.
   */
  public static HashMap<String,User> users = new HashMap<String,User>();
  private String name;
  private HashMap<String,User> adj = new HashMap<String,User>();

  /* Constructor for the User class. The constructor takes a String, nm, which
   * is the name of the user. The constructor adds the user to the static
   * HashMap. Warning: if a user with the same name already exists, the
   * constructor will not add the new user to the HashMap.
   */
  public User( String nm ) {
    name = nm;
    if (!users.containsKey(name)) {
      users.put(name, this);
    }
  }

  /* find
   * Given a String, nm, this method returns the User with that name. If no
   * such user exists, the method returns null.
   */
  public static User find( String nm ) {
    return users.get(nm);
  }


  /* leave
   * This method removes the user from the social network. It removes the user
   * from the static HashMap and removes the user from all of their friends'
   * adj.
   */
  public void leave() {
    users.remove( name );
    for( User v : adj.values() ) {
      v.adj.remove( name );
    }
  }

  /**
   * This is used to get the name of the user
   * @return String representing the name of the user
   */
  public String getName(){
    return this.name;
  }

  /**
   * This is used to get the adj of a particular user
   * @return The Adjacency of a user
   */
  public HashMap<String,User> getAdj(){
    return this.adj;
  }

}
