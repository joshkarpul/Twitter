import java.util.ArrayList;
import java.util.Hashtable;

public class Twitter implements IDisplay{
	//this data structure will act as each users feed
	private Hashtable<String,ArrayList<Tweet>> feeds = new Hashtable<String, ArrayList<Tweet>>();
	private Hashtable<String,User> users;
	
	//constructor method when dealing with one tweet at a time
	public Twitter (Hashtable<String,User> users){
		this.users = users;
	}
	//constructor method given a list of tweets
	public Twitter (Hashtable<String,User> users,ArrayList<Tweet> tweets){
		buildFeeds(users,tweets);
	}
	
	//private method for when given a list of tweets
	private void buildFeeds(Hashtable<String, User> users, ArrayList<Tweet> tweets) {
		for (int i = 0; i < tweets.size(); i++) {
			//get the tweet and the user
			Tweet currentTweet = tweets.get(i);
			String userName = currentTweet.getUser().getUserName();
			
			//First deal with the users who tweeted 
			User mainUser = users.get(userName);
			addUserToFeeds(mainUser,currentTweet);
			
			//Now to deal with all users who follow the tweeting user
			for(String key: users.keySet()){
				//if this user follows the tweeting user
				if (users.get(key).exists(mainUser)){
					addToFeedOfUser(users.get(key), currentTweet);
				}							
			}
		}
	}
	//adding a tweet to a user within the feeds hashtable
	private void addToFeedOfUser(User user, Tweet tweet){
		if (feeds.containsKey(user.getUserName())){
			feeds.get(user.getUserName()).add(tweet);
		}else{
			addUserToFeeds(user, tweet);
		}
	}
	//just add a user to the feed
	private void addUserToFeeds(User user){
		if (!feeds.containsKey(user.getUserName())){
			ArrayList<Tweet> newUsersFeed = new ArrayList<Tweet>();
			feeds.put(user.getUserName(), newUsersFeed);
		}
	}
	//add a user along with a tweet to the feed
	private void addUserToFeeds(User user, Tweet tweet){
		//check if they are already within the feed
		if (!feeds.containsKey(user.getUserName())){
			ArrayList<Tweet> newUsersFeed = new ArrayList<Tweet>();
			newUsersFeed.add(tweet);
			feeds.put(user.getUserName(), newUsersFeed);
		}else{
			feeds.get(user.getUserName()).add(tweet);
		}
	}
	
	public Hashtable<String, ArrayList<Tweet>> getFeeds() {
		return feeds;
	}

	public ArrayList<Tweet> getUserFeed(User user) {
		return feeds.get(user.getUserName());
	}

}
