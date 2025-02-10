
/**
 * This class represents a list of Playlists  as a singly linked list.
 * It contains the head of the list and the number of Playlists in the list
 * 
 * @author Jairdan C
 * @version 1.1
 * @since Oct 30, 2024
 */
public class PlaylistManager {

	//Instance Variables 
	/**
	 * Start of the list, the Singly Linked Node with the generic type assigned to the Playlist object type
	 */
	private SLLNode<Playlist> head;
	/**
	 * The size of the Singly Linked list, number of Playlists
	 */
	private int numPlaylists;
	
	//Constructor
	/**
	 * Constructor which creates an instance of PlaylistManager with no head node and size of 0
	 */
	public PlaylistManager() {
		head = null;
		numPlaylists = 0;
	}
	
	//Methods
	
	/**
	 * Adds a Singly Linked Node to the list containing the given Playlist to the end of the list and increments the numPlaylists by 1, 
	 * the given Playlist cannot have the same name as a Playlist already in the list
	 * 
	 * @param newPlaylist The Playlist object that will be contained in the added Singly Linked Node
	 * @throws PlayerException is thrown when the given Playlist is not readable or is null (Error 01), or if there is a node with a Playlist
	 * object in the list that that has the same name as the given Playlist name
	 */
	public void addPlaylist(Playlist newPlaylist) throws PlayerException {
		
		SLLNode<Playlist> newNode;
		SLLNode<Playlist> temp;
		
		//Checks null condition
		if (newPlaylist == null) {
			throw new PlayerException("Error 01: Given Playlist is not readable or is null");
		} else {
			newNode = new SLLNode<>(newPlaylist); 
		}

		//Case where PlaylistManager is empty
		if (numPlaylists == 0) {
			head = newNode;
		} else { //Checks internal nodes 
			temp = head;
			while (temp.getNext() != null) {
				if(temp.getElement().getName().equals(newPlaylist.getName())) {
					throw new PlayerException("Playlist already exists");
				} else {
					temp = temp.getNext();
				}
			}
			//Checks final node
			if (temp.getElement().getName().equals(newPlaylist.getName())) {
				throw new PlayerException("Playlist already exists");
			}
			//Adds node to the end of the list
			temp.setNext(newNode);

		}
		numPlaylists++;	
	}
	
	/**
	 * Removes the Singly Linked Node from the list containing the Playlist with the given name, decreases numPlaylists by 1
	 * 
	 * @param listName The name of the Playlist object that will be contained in the removed Singly Linked Node
	 * @return The Playlist object contained in the removed Singly Linked Node
	 * @throws PlayerException is thrown when the list is empty (Error 03), or when the no Playlist in the list has a name equal to given
	 * name (Error 04) 
	 */
	public Playlist removePlaylist(String listName) throws PlayerException {
		
		SLLNode<Playlist> curr = head; 
		SLLNode<Playlist> prev;
		boolean flag = true;
		
		//when list is empty
		if (curr == null) {
			throw new PlayerException("Error 03: The list is empty");
		}
		//check that there are two or more elements
		
		//When head is the title
		if(curr.getElement().getName().equals(listName)) {
			head = head.getNext();
			flag = false;
			numPlaylists--;
		}
		
		curr = curr.getNext();
		prev = head;
		
		//Check internal nodes and catches the tail case with npe exception
		while (curr != null && flag) {
			 if(curr.getElement().getName().equals(listName)){
				prev.setNext(curr.getNext());
				flag = false;
				numPlaylists--;
			 } else {
				 curr = curr.getNext();
				 prev = prev.getNext();
			 }
		}
		
		//Throw exception if not found and return otherwise
		if(flag) {
			throw new PlayerException("Error 04: The title is not in the list");
		} else {
			return curr.getElement();
		}
	}
	
	/**
	 * Getter method for the Playlist object in the Singly Linked List with name equal to the given name
	 * 
	 * @param listName The name of the Playlist that will searched for and returned
	 * @return Playlist object with the corresponding name
	 * @throws PlayerException is thrown if there is no Playlist in the list with name equal to the given name (Error 04)
	 */
	public Playlist getPlaylist(String listName) throws PlayerException {
		
		SLLNode<Playlist> temp = head;
		
		while(temp != null) {
			if (temp.getElement().getName().equals(listName)) {
				return temp.getElement();
			} 
			temp = temp.getNext();
		}
		
		throw new PlayerException("Error 04: The playlist is not in the list");
	}
	
	/**
	 * Adds a Doubly Linked Node containing the given MediaFile to the Playlist object contained in the Singly Linked Node with name equal 
	 * to the given Playlist name
	 * @param playlistName The name of the Playlist that will be searched for and modified
	 * @param media The MediaFile the will added to the Playlist object
	 * @throws PlayerException is thrown if no Playlist with the given name is in the Singly Linked List (Error 04), or if there is already
	 * a Doubly Linked Node containing a MediaFile with the same name as the given MediaFile in the Playlist (Error 02)
	 */
	public void addMediaToPlaylist(String playlistName, MediaFile media) throws PlayerException {
		getPlaylist(playlistName).addMedia(media);
	}
	
	/**
	 * Removes a Doubly Linked Node containing the MediaFile with title equal to the given title from the Playlist object contained in the
	 * Singly Linked Name with name equal to the given Playlist name
	 * @param playlistName The name of the Playlist that will be searched for and modified
	 * @param mediaTitle The title of the MediaFile that will be removed
	 * @throws PlayerException is thrown if no Playlist with the given name is in the Singly Linked List (Error 04), or if there is 
	 * no MediaFile with title equal to the given title to remove (Error 04)
	 */
	public MediaFile removeMediaFromPlaylist(String playlistName, String mediaTitle) throws PlayerException {
		return getPlaylist(playlistName).removeMedia(mediaTitle);
	}
	
	/**
	 * 
	 * @returns a string representation of the PlaylistManager with a header and the the toString() of each Playlist in the Singly Linked Nodes
	 */
	public String toString() {
		String returnVal = "Playlist Manager: \n";
		SLLNode<Playlist> temp = head;
		
		while (temp != null) {
			returnVal += temp.getElement().toString();
			temp = temp.getNext();
		}
		
		return returnVal;
	}
	
}
