import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import org.omg.IOP.CodecPackage.FormatMismatch;

public class UsersFileReader implements IUserData {
	
	private Hashtable<String, User> users = new Hashtable<String, User>();
	
	//constructor that runs the extraction of users
	public UsersFileReader(String textFileName){		
		ReadFile(textFileName);
	}
	//method to override IUserData Interface method
	public Hashtable<String, User> getUsers() {
		return users;
	}	
	//Reads file and creates users hash-table
	private void ReadFile(String fileName){
		FileInputStream FIS 	= null;
		BufferedInputStream BIS = null;
		DataInputStream DIS 	= null;
		File file 				= null;
		//content stores the files content, each line being separated by a '@'
		file 		   = new File(fileName);
		String content = "";
		String [] splitContent = new String [2];
		
		String name;
		String [] following;
		
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
			    if (content.contains(" follows ")){
			    	//split the line into the user and a list of who they follow
			    	splitContent = content.split(" follows ");
			    	//create the main user
			    	name = splitContent[0];
			    	User newUser = new User(name);
			    	//deal with who they follow
			    	if(users.containsKey(name)){
			    		newUser = users.get(name);
			    	}else{
			    		newUser = new User(name);
			    	}			    
			    	//check if there is a list of users
			    	if (splitContent[1].contains(",")){
			    		//split the list of users by ","
				    	following = splitContent[1].split(", ");
				    	//
				    	for (int i = 0; i < following.length; i++) {
				    		User anotherNewUser = new User(following[i]);
				    		//check to see if the user to follow is already added
				    		if (!users.containsKey(anotherNewUser.getUserName())){
					    		users.put(anotherNewUser.getUserName(), anotherNewUser);
					    		newUser.addUserToFollow(anotherNewUser);
				    		}else{
				    			newUser.addUserToFollow(anotherNewUser);
				    		}
						}
			    	}else{//else just place the single user to follow in the database and link it to the main user
			    		if (splitContent[1] != null){
				    		User anotherNewUser = new User(splitContent[1]);
				    		users.put(anotherNewUser.getUserName(), anotherNewUser);
				    		newUser.addUserToFollow(anotherNewUser);
			    		}
			    	}
			    	//place the main user in the database if its new, otherwise it will already be there and have been updated
			    	users.put(newUser.getUserName(), newUser);

			    }else{
			    	System.out.println("ERROR - USER FILE IS NOT OF CORRECT FORMAT");
			    	throw new FormatMismatch("ERROR - USER FILE IS NOT OF CORRECT FORMAT");
			    }
		    }
		    // dispose stream resources;
		    FIS.close();
		    BIS.close();
		    DIS.close();
	    
		    
	    } 
		catch (FileNotFoundException e) 
		{
	    	e.printStackTrace();
	    } 
		catch (IOException e) 
		{
	    	e.printStackTrace();
	    }
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
