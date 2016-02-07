import java.util.Hashtable;

public class User implements Comparable<User>{

	private String username;	
	//not too sure on the size/fill-ratio for this hashtable
	private Hashtable<String,User> following = new Hashtable<String, User>();	
	
	//constructor given only user's name
	public User(String username){
		this.username = username;
	}	
	//return the user's name
	public String getUserName(){
		return username;
	}	
	//add a following user to this user
	public void addUserToFollow(User userToFollow){
		if (!exists(userToFollow)) following.put(userToFollow.getUserName(), userToFollow);
	}	
	//implemented method of Comparable Interface to compare
	//one user to another for sorting
	public int compareTo(User userToCompareTo) {
		// TODO Auto-generated method stub
		return this.getUserName().compareTo(userToCompareTo.getUserName());
	}	
	
	//PRIVATE METHODS
	//private method used to determine weather this user already follows another user
	public boolean exists(User userToCheck){
		User check = following.get(userToCheck.getUserName());
		//if check is not null, the user exists
		return check != null;
	}
}
