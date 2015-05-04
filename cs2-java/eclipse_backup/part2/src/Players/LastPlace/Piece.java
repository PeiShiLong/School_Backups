package Players.LastPlace;

/**
 * $Id:$
 * $Revisions:$
 * 
 * 
 * @author Jon O'Brien, Dustin Martin
 * This is a class that represents the Pieces, it holds the player value (1 or 2)
 * and it holds the size of the piece(1-4)
 */
public class Piece {
	private int playerValue; //The ID of the player.
	private int pieceSize; //The size of the piece.
	public Piece( int playerValue, int pieceSize ){
		this.playerValue = playerValue;
		this.pieceSize = pieceSize;
	}
	
	/**
	 * @return The size of the piece.
	 */
	public int getSize(){
		return this.pieceSize;
	}
	/**
	 * @return The players ID.
	 */
	public int getPlayer(){
		return this.playerValue;
	}
	/**
	 * A String representation of the piece.
	 */
	public String toString(){
		return String.valueOf(pieceSize) + "(" + String.valueOf(playerValue) + ")";
	}

}
