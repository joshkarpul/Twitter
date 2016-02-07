Instructions to use:
1. Run "Run.java".
2. Program arguments are set as "user.txt" and "tweet.txt" by default; however this can be changed from IDE settings.
3. The output is displayed within the console of the IDE.

Assumptions made:
1. User creation is handled elsewhere and all users within the "users.txt" file are valid users.
2. Comparable interface uses an acceptable efficient sort algorithm.
3. Tweets can only be made by existing users.
4. 

Design choices:
1. Hashtables are used for Users and User Feeds in order to increase accessibility efficiency.
2. ArrayLists are used for tweets in order to preserve the order in which they are read and for scalability reasons. 
   Specific tweets are assumed to not have to be searched for.
3. Interfaces "IUserData.java", "ITweetData.java" and "IDisplay.java" are used for scalability reasons in order to upgrade to
   different input/output sources without alteration of main code. (for example: changing from text file input to database input).
4. 

