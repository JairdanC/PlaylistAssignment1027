
public class TestAsmt2 {
    public static void main(String[] args) {
	PlaylistManager manager = null;
	Playlist rockPlaylist = null, popPlaylist = null;
	MediaFile song1 = null, song2 = null, song3 = null, song4 = null, song5 = null;
		
        try {
            	// Create a PlaylistManager
            	manager = new PlaylistManager();

            	// Create and add playlists
            	rockPlaylist = new Playlist("Rock Classics");
            	popPlaylist = new Playlist("Pop Hits");

            	// Create and add media files to playlists
            	song1 = new MediaFile("Stairway to Heaven", "Led Zeppelin", 482, "mp3");
            	song2 = new MediaFile("Rhapsody", "Queen", 364, "mp3");
            	song3 = new MediaFile("Shape of You", "Ed Sheeran", 233, "mp3");
            	song4 = new MediaFile("Uptown Funk", "Mark Ronson ft. Bruno Mars", 270, "mp3");
            	song5 = new MediaFile("Song","Someone", 121,"mp4");
			
		System.out.println("Test 1 passed");
	}
	catch (Exception e) {
		System.out.println("Test 1 failed");
	}
			
	boolean testPassed = true;
	try {
		if (!song1.getTitle().equals("Stairway to Heaven") || !song2.getArtist().equals("Queen") ||
			(song3.getDurationSeconds() != 233)) testPassed = false;
		if (!song2.toString().equals("Rhapsody - Queen (6:04)")) testPassed = false;
		if (testPassed) System.out.println("Test 2 passed");
		else System.out.println("Test 2 failed");
	}
	catch (Exception e) {
		System.out.println("Test 2 failed");
	}		
		
	testPassed = true;
	try {
        	manager.addPlaylist(rockPlaylist);
        	manager.addPlaylist(popPlaylist);			

        	manager.addMediaToPlaylist("Rock Classics", song1);
        	manager.addMediaToPlaylist("Rock Classics", song2);
        	manager.addMediaToPlaylist("Pop Hits", song3);
       		manager.addMediaToPlaylist("Pop Hits", song4);
       		manager.addMediaToPlaylist("Pop Hits", song5);       		
		System.out.println("Test 3 passed");
	}
	catch (Exception e) {
		System.out.println("Test 3 failed");
	}		

	testPassed = true;
	Playlist list = null;
	try {
		    list = manager.getPlaylist("No List");
		System.out.println("Test 4 failed");
	}
	catch (PlayerException e) {
		try {
			list = manager.getPlaylist("Rock Classics");
			if ((list.getSize() != 2) || (!list.getName().equals("Rock Classics"))) 
				testPassed = false;
			if (testPassed) System.out.println("Test 4 passed");
			else System.out.println("Test 4 failed");
		}
		catch (Exception f) {System.out.println("Test 4 failed");}
	}
	catch (Exception e) {
		System.out.println("Test 4 failed");
	}	

	testPassed = true;
	try {
		String s = list.toString();
		if (!s.contains("Playlist:") || (!s.contains("Rock Classics")) ||
			(!s.contains("Stairway to Heaven")) || (!s.contains("2. Rhapsody"))) testPassed = false;
		Playlist emptyList = new Playlist("empty");
		s = emptyList.toString();
		if (!s.startsWith("Playlist: empty")) testPassed = false;
		if (s.length() > 20) testPassed = false;
		if (testPassed) System.out.println("Test 5 passed");
		else System.out.println("Test 5 failed");
	}
	catch (Exception e) {
		System.out.println("Test 5 failed");
	}			
		
	testPassed = true;
	try {
            
            	Playlist popPlaylistRef = manager.getPlaylist("Pop Hits");
            	if (!popPlaylistRef.getNextMedia(null).getTitle().equals("Shape of You"))
			testPassed = false;
			
           	if (!popPlaylistRef.getNextMedia("Shape of You").getTitle().equals("Uptown Funk"))
			testPassed = false;
            
		if (!popPlaylistRef.getPreviousMedia("Uptown Funk").getTitle().equals("Shape of You"))
			testPassed = false;
			
		if (testPassed) System.out.println("Test 6 passed");
		else System.out.println("Test 6 failed");			
	}
	catch (Exception e) {
		System.out.println("Test 6 failed");
	}	

	testPassed = true;		
	try {
            	manager.removeMediaFromPlaylist("Rock Classics", "Bohemian Rhapsody");
            	testPassed = false;
        }
        catch (PlayerException e) {
        	try {
        		manager.removeMediaFromPlaylist("Rock Classics", "Rhapsody");
            		list = manager.getPlaylist("Rock Classics");
            		if (list.getSize() != 1) testPassed = false; 		
            	}
            	catch (Exception ex) {testPassed = false;}       
        }
        catch (Exception e) {testPassed = false;}
        if (testPassed) System.out.println("Test 7 passed");
        else System.out.println("Test 7 failed");
            	
        testPassed = true;

        // Attempt to add a duplicate playlist 
        try {
 		manager.addPlaylist(new Playlist("My List"));
                manager.addPlaylist(new Playlist("Other list"));
                manager.addPlaylist(new Playlist("My List"));
                testPassed = false;
        } 
        catch (PlayerException e) {}
        catch (Exception f) {testPassed = false;}	
        if (testPassed) System.out.println("Test 8 passed");
        else System.out.println("Test 8 failed");            		

        // Attempt to add a duplicate media file 
        testPassed = true;
        try {
                manager.addMediaToPlaylist("Pop Hits", song3);
                testPassed = false;
        }
        catch (PlayerException f) {
        	try {
        		manager.addMediaToPlaylist("Pop Hits", song5);
        	}
        	catch (PlayerException pex) {}
        	catch (Exception ex) {testPassed = false;}
        } 
        catch (Exception e) {
                testPassed = false;
        }
        if (testPassed) System.out.println("Test 9 passed");
        else System.out.println("Test 9 failed");
 
        Playlist[] play = new Playlist[10];
             
 	testPassed = true;
	try {
            manager = new PlaylistManager();

            for (int i = 0; i < 10; ++i) {
            	play[i] = new Playlist("List"+i);
            	manager.addPlaylist(play[i]);
           }	
           for (int i = 0; i < 10; ++i)
           	for (int j = 0; j < 3; ++j) {
           		play[i].addMedia(new MediaFile("Song"+j,"Auth"+j,j,"mpnew"));
           		manager.addMediaToPlaylist("List"+i,new MediaFile("Song"+(j+3),"Auth"+(j+3),j+3,"mp"));
           	}
           Playlist testList;
           for (int i = 0; i < 10; ++i) 
           	testList = manager.getPlaylist("List"+i);
           if (testPassed) System.out.println("Test 10 passed");
           else System.out.println("Test 10 failed");
	}
	catch (Exception e) {
		System.out.println("Test 10 failed");
	}	
	
 	testPassed = true;
 	String[] titles = {"Song0","Song3","Song4", "Song5", "Song1", "Song2", "Song0"};
 	String[] prevTitles = {"Song3", "Song4", "Song0", "Song1", "Song2", "Song5"};
	try {
	    String title;
            for (int i = 0; i < 6; ++i) {
            	title = play[0].getNextMedia("Song"+i).getTitle();
            	if (!title.equals(titles[i+1])) testPassed = false;
            }
            title = play[0].getNextMedia(null).getTitle();

            if (!title.equals("Song0")) testPassed = false;
            for (int i = 5; i > 0; --i) {
            	title = play[0].getPreviousMedia("Song"+i).getTitle(); 	
            	if (!title.equals(prevTitles[i-1])) testPassed = false;
            }
            title = play[0].getPreviousMedia(null).getTitle(); 
            if (!title.equals("Song5")) testPassed = false;
            
            if (testPassed) System.out.println("Test 11 passed");
            else System.out.println("Test 11 failed");	
	}
	catch (Exception e) {
		System.out.println("Test 11 failed");
	}		
	
    }
}
