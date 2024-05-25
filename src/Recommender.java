import java.util.*;

/* FriendRecommender.java
 *
  * This class is used to make friend recommendations for users of a social
  * network. The code is incomplete and contains bugs.
  *
*/
public class Recommender {


  private static ArrayList<String> finalRecommendations = new ArrayList<>();

  /**
   * Empty Constructor
   */
  public Recommender(){

  }

  /**
   * This is used to make call the makeFriendRecommendation method based on both sides of the users
   * This also sorts the results from both sides
   * @param u
   * @param f
   */
  public void friendRecommend( User u, User f) {

    //System.out.println(u.getName()+""+f.getName());
    ArrayList<String> tmp = new ArrayList<String>();

    if(!new FollowsRelationship().checker(u,f)){
      makeFriendRecommendations( u, f, tmp );
      makeFriendRecommendations( f, u, tmp);
    }

    Collections.sort( tmp );
    String prev = null;
    for( String s : tmp ) {
      if( !s.equals( prev ) ) {
        finalRecommendations.add( s );
        prev = s;
      }
    }

    printOutRecommendations();
  }

  /**
   * followRecommend
   * This is used to call to make follow Recommendations based one user
   * @param u
   * @param f
   */
  public void followRecommend( User u, User f) {
    ArrayList<String> tmp = new ArrayList<String>();
    // To check if the users are not friends already
    if(!new FriendRelationship().checker(u,f)){
      makeFollowRecommendations( u, f, tmp );
    }

    Collections.sort( tmp );
    String prev = null;
    for( String s : tmp ) {
      if( !s.equals( prev ) ) {
        finalRecommendations.add( s );
        prev = s;
      }
    }

    printOutRecommendations();
  }


  /**
   *  makeRecommendations
   *    * Given two users, u and f, and an ArrayList of Strings, al, this method
   *    * will recommend new friends for u based on the friends of f. The
   *    * recommendations are added to al. The recommendations are of the form
   *    * "A and B should be friends" where A and B are the names of the users and
   *    * A comes before B in sorted order. The method does not return anything so
   *    * the output is passed back in al.
   * @param u
   * @param f
   * @param al
   */
  public void makeFriendRecommendations( User u, User f, ArrayList<String> al ) {

    if(!new FollowsRelationship().checker(u,f)){
      for( User v : f.getAdj().values() ) {
        if( (u != v) && !new FriendRelationship().checker(u, v) && !new FollowsRelationship().checker(f,v) ) {
          // Then to check a possible Follow Relationship
          if( v.getName().compareTo( u.getName()) < 0 ) {
            al.add( v.getName() + " and " + u.getName() + " should be friends" );
          } else {
            al.add( u.getName() + " and " + v.getName() + " should be friends" );
          }
        }
        else{
          if(new FollowsRelationship().checker(f,v)){
            al.add( u.getName() + " should follow " + v.getName());
          }
        }
      }
    }



  }

  /**
   * This is used to make follow Recommendations
   * @param u
   * @param f
   * @param al
   */
  public void makeFollowRecommendations( User u, User f, ArrayList<String> al){
      for( User v : u.getAdj().values() ) {
        if( (f != v) && !new FollowsRelationship().checker(v, f)) {
          al.add( v.getName() + " should follow " + f.getName() );

        }
      }
  }

  /**
   * This is used to print out the recommendations available in the general Array list
   * This also removes the recommendations after printing them out
   */
  public void printOutRecommendations() {
    if (!finalRecommendations.isEmpty()) {
      Iterator<String> iterator = finalRecommendations.iterator();
      while (iterator.hasNext()) {
        String message = iterator.next();
        System.out.println(message);
        iterator.remove(); // Safely remove the current element
      }
    }
  }

  /**
   * This is used to get the general Recommendation Array list
   * @return ArrayList<String >
   */
  public ArrayList<String > getFinalRecommendations(){
    return finalRecommendations;
  }

}
