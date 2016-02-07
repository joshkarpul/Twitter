import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TweetsFileReader implements ITweetData {
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	private int tweetsIndex = -1;
	
	//constructor that runs the extraction of tweets
	public TweetsFileReader(String textFileName){
		ReadFile(textFileName);
	}
	//method to override ITweetData Interface method
	public Tweet getTweet() {
		tweetsIndex++;
		return tweets.get(tweetsIndex);
	}
	
	public boolean hasTweet(){
		return (tweetsIndex + 1) < tweets.size();
	}

	public ArrayList<Tweet> getTweets(){
		return tweets;
	}
	
	//Reads File and creates an ArrayList of tweets
	private void ReadFile(String fileName){
		FileInputStream FIS 	= null;
		BufferedInputStream BIS = null;
		DataInputStream DIS 	= null;
		File file 				= null;
		//content stores the files content, each line being separated by a '@'
		file 		   = new File(fileName);
		
		String content = "";
		String [] splitContent = new String [2];
		
		try{
			//Setup Input Streams
			FIS = new FileInputStream(file);
		    BIS = new BufferedInputStream(FIS);
		    DIS = new DataInputStream(BIS);
			
		    //do the following while the file contains more lines of text
		    while (DIS.available() != 0){
		    	//read a line of text
			    content = DIS.readLine();
			    //confirm that the line is of the correct format
			    if (content.contains("> ")){
			    	//split the line into the user and a list of who they follow
			    	splitContent = content.split("> ");
			    	//create the tweet
			    	if (splitContent[1].length() > 140) throw new IllegalArgumentException("Tweet length is too large ( > 140 characters");
			    	Tweet tweet = new Tweet(new User(splitContent[0]), splitContent[1]);
			    	tweets.add(tweet);
			    }else{
			    	System.out.println("ERROR - USER FILE IS NOT OF CORRECT FORMAT");
			    }
		    }
		    // dispose stream resources;
		    FIS.close();
		    BIS.close();
		    DIS.close();		    
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	
	}
}
