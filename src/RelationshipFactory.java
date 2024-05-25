import java.util.InputMismatchException;

/**
 * @Author: Usman Sadiq
 * This is used to return an instance of the Relationship class that is either "FollowsRelationship" or "FriendRelationship"
 */
public class RelationshipFactory {

    public static int friendRelationshipCode = 0;
    public static int followsRelationshipCode = 1;
    Relationship relationshipToReturn;

    /**
     * Constructor
     */
    public RelationshipFactory(){
        relationshipToReturn = null;

    }

    /**
     * This is used to generate a code based on the relationship put in
     * That is returns "0" if the relationship is "friends" and returns "1" if the relationship is "follow"
     * This throws an InputMismatch Exception otherwise (But highly unlikely) due to preceded validations
     * @param relationshipType
     * @return
     */
    public int getRelationshipType(String relationshipType) throws InputMismatchException{
        if(relationshipType.equals("friends") || relationshipType.equals("unfriends")){
            return friendRelationshipCode;
        }
        else if(relationshipType.equals("follows") || relationshipType.equals("unfollows")){
            return followsRelationshipCode;
        }
        else{
            throw new InputMismatchException(relationshipType + " is not a valid relationship type");
        }
    }

    /**
     * This is the method that checks for the type of relationship class that is expected to be returned
     * @param relationshipType
     * @return Returns a FriendRelationship instance if the code returned generated is "0" but returns a FollowsRelationship instance if the code returned generated is "1"
     */
    public Relationship getRelationshipToReturn(String relationshipType) throws Exception {
        int codeReturned = this.getRelationshipType(relationshipType);

        if(codeReturned == friendRelationshipCode){
            relationshipToReturn = new FriendRelationship();
            return relationshipToReturn;
        }
        else if(codeReturned == followsRelationshipCode){
            relationshipToReturn = new FollowsRelationship();
            return relationshipToReturn;
        }
        throw new Exception("Highly unlikely to get this error");

    }



}
