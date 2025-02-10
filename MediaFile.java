/**
 * This class represents a media file.
 * It contains the title, artist, file type and the duration of the file in seconds
 */
public class MediaFile {

	//Instance Variables
	/**
	 * The title of the MediaFile
	 */
	private String title;
	/**
	 * The artist of the MediaFile
	 */
	private String artist;
	/**
	 * The file type of the MediaFile
	 */
	private String fileType;
	/**
	 * The duration of the MediaFile in seconds
	 */
	private int durationSeconds;
	
	//Constructor
	/**
	 * Constructor which creates an instance of MediaFile with the given title, artist, duration and file type
	 * @param title
	 * @param artist
	 * @param durationSeconds
	 * @param fileType
	 */
	public MediaFile(String title, String artist, int durationSeconds, String fileType) {
		this.title = title;
		this.artist = artist;
		this.fileType = fileType;
		this.durationSeconds = durationSeconds;
	}
	
	//Methods 
	
	/**
	 * Getter method for the title of MediaFile
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Getter method for the artist of MediaFile
	 * @return artist
	 */
	public String getArtist() {
		return artist;
	}
	
	/**
	 * Getter method for the file type of MediaFile
	 * @return fileType
	 */
	public String getfileType() {
		return fileType;
	}
	
	/**
	 * Getter method for the duration of MediaFile, in seconds
	 * @return durationSeconds
	 */
	public int getDurationSeconds() {
		return durationSeconds;
	}
	
	/**
	 * 
	 * @return A string representation of MediaFile as the title - artist (min:secs)
	 */
	public String toString() {
		int tempMins = durationSeconds / 60;
		int tempSecs = durationSeconds % 60;
		if(tempSecs > 9) {
			return title + " - " + artist + " (" + tempMins + ":" + tempSecs + ")";
		} else {
			return title + " - " + artist + " (" + tempMins + ":" + 0 + tempSecs + ")";
		}
	}
}
