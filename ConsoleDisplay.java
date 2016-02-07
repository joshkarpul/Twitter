import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;

public class ConsoleDisplay{
	
	public ConsoleDisplay(Hashtable<String,User> users ,Hashtable<String,ArrayList<Tweet>> feeds){
		//sorting		
		ArrayList<String> userNames= new ArrayList<String>();
		Enumeration<String> e = users.keys();
		while(e.hasMoreElements()){
			userNames.add(e.nextElement());
		}
		
		Collections.sort(userNames);
		
		for (int i = 0; i < userNames.size(); i++){
			String key = userNames.get(i);
			ArrayList<Tweet> usersTweetFeed = feeds.get(key);
			System.out.println(key);
			if(usersTweetFeed != null){
				for (int j = 0; j < usersTweetFeed.size(); j++) {
					System.out.println("\t@"+ usersTweetFeed.get(j).getUser().getUserName() +": " + usersTweetFeed.get(j).getTweet());
				}
			}
		}
	}
	
	public ConsoleDisplay(User user, ArrayList<Tweet> tweets){
		System.out.println(user.getUserName());
		if(tweets != null){
			for (int i = 0; i < tweets.size(); i++) {
				System.out.println("\t@"+ tweets.get(i).getUser().getUserName() +": " + tweets.get(i).getTweet());
			}
		}
	}
}
