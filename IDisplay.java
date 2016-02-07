import java.util.ArrayList;
import java.util.Hashtable;

public interface IDisplay {
	public Hashtable<String,ArrayList<Tweet>> getFeeds();
	public ArrayList<Tweet> getUserFeed(User user);
}
