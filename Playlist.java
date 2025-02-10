/**
 * This class represents a playlist as a doubly linked list.
 * It contains the name, start and end of the playlist, and the size of the playlist
 * 
 * @author Jairdan C
 * @version 1.1
 * @since Oct 30, 2024
 */
public class Playlist {
	
	//Instance Variables
	/**
	 * The name of the Playlist
	 */
	private String name;
	/**
	 * Start of the list, the Doubly Linked Node with generic type assigned to a MediaFile object type
	 */
	private DLLNode<MediaFile> head;
	/*
	 * End of the list, the Doubly Linked Node with generic type assigned to a MediaFile object type
	 */
	private DLLNode<MediaFile> tail;
	/**
	 * The size of the Playlist, number of songs
	 */
	private int size;
	
	
	//Constructor
	/**
	 * Constructor which creates an instance of Playlist with the given name, size of 0 and no nodes assigned
	 * @param name
	 */
	public Playlist(String name) {
		this.name = name;
		head = null;
		tail = null;
		size = 0;
	}
	
	//Methods
	
	/**
	 * Adds a Doubly Linked Node containing the given MediaFile to the end of the list and increases the size of the Playlist by 1,
	 * the given media cannot contain the same title as a MediaFile already in the list
	 * 
	 * @param media The Playlist object that will be contained in the added Doubly Linked Node
	 * @throws PlayerException is thrown when the given MediaFile is not readable or is null (Error 01), or if there is a node 
	 * with a MediaFile object in the list that has the same title as the given MediaFile (Error 02)
	 */
	public void addMedia(MediaFile media) throws PlayerException {
		
		//Checks null condition
		if (media == null) {
			throw new PlayerException("Error 01: Given MediaFile is not readable or is null");
		}
		
		//Checks if file is already in the playlist, by title
		DLLNode<MediaFile> temp = head;
		while(temp != null) {
			if (temp.getElement().getTitle().equals(media.getTitle())) {
				throw new PlayerException("Error 02: Title already exists in playlist");
			} else {
				temp = temp.getNext();
			}
		}
		
		//Creates and adds a new node containing media to the end of the list, also handles the case where size = 0
		DLLNode<MediaFile> newNode = new DLLNode<>(media);
		if (size == 0) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNext(newNode);
			newNode.setPrevious(tail);
			tail = newNode;
			
		}
		//Increase the size of the list
		size++;	
	}
	
	/**
	 * Removes the Doubly Linked Node which contains the MediaFile that has a title matching the given title from the list, decreases 
	 * the size of Playlist by 1
	 * 
	 * @param title The title that matches the MediaFile which will be removed
	 * @return The MediaFile which was removed from the list
	 * @throws PlayerException is thrown when the list is empty (Error 03), or when there is no node containing a MediaFile that does not
	 * have a corresponding title to the given title (Error 04)
	 */
	public MediaFile removeMedia(String title) throws PlayerException {
		
		DLLNode<MediaFile> temp = head; 
		boolean flag = true;
		
		//Throws exception when list is empty
		if (temp == null) {
			throw new PlayerException("Error 03: The list is empty");
		}
		
		//When head is the title
		if(head.getElement().getTitle().equals(title)) {
			head.getNext().setPrevious(null);
			head = head.getNext();
			flag = false;
			size--;
		}
		
		//Check internal nodes and catches the tail case with npe exception
		while (temp != null && flag) {
			 if(temp.getElement().getTitle().equals(title)){
				 temp.getPrevious().setNext(temp.getNext());
				 try {
				 	temp.getNext().setPrevious(temp.getPrevious());
				} catch(NullPointerException npe) {
					tail = temp.getPrevious();
				}
				flag = false;
				size--;
			 } else {
				 temp = temp.getNext();
			 }
		}
		
		//Throw exception if not found and return otherwise
		if(flag) {
			throw new PlayerException("Error 04: The title is not in the list");
		} else {
			return temp.getElement();
		}
	}
	
	
	/**
	 * Getter method for the MediaFile in the next Doubly Linked Node from the node containing the MediaFile with the given title, 
	 * returns the MediaFile in head if currentTitle is null or the currentTitle is in the tail node
	 * 
	 * @param currentTitle Title of the current MediaFile
	 * @return The MediaFile in the next Doubly Linked Node
	 * @throws PlayerException is thrown when the list is empty (Error 03), or when the given currentTitle is not in the list (Error 04)
	 */
	public MediaFile getNextMedia(String currentTitle) throws PlayerException {
		
		DLLNode<MediaFile> temp = head;
		
		//If the list is empty
		if (temp == null) {
			throw new PlayerException("Error 03: The list is empty");
		}
		
		//If the current title is null
		if (currentTitle == null) {
			return head.getElement();
		}
		
		//Checks all nodes and returns the next element when currentTitle is found
		while(temp != null) {
			if(temp.getElement().getTitle().equals(currentTitle)){
				//Handles tail case
				if (temp.getNext() == null) {
					return head.getElement();
				} else {
					return temp.getNext().getElement();
				}
			 } else {
				 temp = temp.getNext();
			 }
		}
		
		//Throws if not found
		throw new PlayerException("Error 04: Current title is not in the list");
		
	}

	
	/**
	 * Getter method for the MediaFile in the previous Doubly Linked Node from the node containing the MediaFile with the given title, 
	 * returns the MediaFile in head if currentTitle is null or the currentTitle is in the head node
	 * 
	 * @param currentTitle Title of the current MediaFile
	 * @return The MediaFile in the previous Doubly Linked Node
	 * @throws PlayerException is thrown when the list is empty (Error 03), or when the given currentTitle is not in the list (Error 04)
	 */
	public MediaFile getPreviousMedia(String currentTitle) throws PlayerException {

		DLLNode<MediaFile> temp = head;
		
		//if the list is empty
		if (temp == null) {
			throw new PlayerException("Error 03: The list is empty");
		}
		
		//if the current title is null
		if (currentTitle == null) {
			return tail.getElement();
		}
		
		while(temp != null) {
			if(temp.getElement().getTitle().equals(currentTitle)){
				if (temp.getPrevious() == null) {
					return tail.getElement();
				} else {
					return temp.getPrevious().getElement();
				}
			 } else {
				 temp = temp.getNext();
			 }
		}
		
		throw new PlayerException("Error 04: Current title is not in the list");
		
	}

	/**
	 * Getter method for the size of the Playlist
	 * 
	 * @return Size of the Playlist
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Getter method for the name of the Playlist
	 * 
	 * @return Name of the Playlist
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return a string representation of the Playlist with a header and a numbered list containing each MediaFile title
	 */
	public String toString() {
		
		String returnVal = "Playlist: " + name +"\n";
		DLLNode<MediaFile> temp = head;
		
		for (int i = 1; i <= size; i++) {
			returnVal += i + ". " + temp.getElement().getTitle() + "\n";
			temp = temp.getNext();
		}
		
		return returnVal;
	}
}
