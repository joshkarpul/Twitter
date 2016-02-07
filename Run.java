import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class Run {

	public static void main (String [] args){
		if(args.length != 2) throw new NullPointerException("Arguments do not match the required inputs Format should be: exe %user file% %tweets file%");
		IUserData usersData   = new UsersFileReader(args[0]);
		ITweetData tweetsData = new TweetsFileReader(args[1]);
		
		//Hash Table of Users, with their name as the key
		Hashtable<String, User> users = usersData.getUsers();
		//ArrayList of tweets in the order in which they were tweeted
		ArrayList<Tweet> tweets = tweetsData.getTweets();
		
		IDisplay twitter = new Twitter (users,tweets);
		ConsoleDisplay display = new ConsoleDisplay(users ,twitter.getFeeds());
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
