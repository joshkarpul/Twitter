import java.util.ArrayList;

public interface ITweetData {
	public Tweet getTweet();
	public boolean hasTweet();
	
	//this method may be unneccesary, but used for testing purposes
	public ArrayList<Tweet> getTweets();
}
