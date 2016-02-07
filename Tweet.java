/* Allan Gray - Twitter Assignment
 * Author: Josh Karpul
 * Tweet Class
 * 
 * Tweets are made up by a user and the users tweet. 
 * This class deals with storing a link to a user 
 * who made the tweet as well as the tweet itself.
 */

public class Tweet {
	
	private User user;
	private String tweet;
	
	//class constructor
	public Tweet(User name, String tweet){
		this.user  = name;
		this.tweet = tweet;
	}	
	//returns a pointer to a user
	public User getUser(){
		return user;
	}
	//returns the tweet made by the user
	public String getTweet(){
		return tweet;
	}
}
