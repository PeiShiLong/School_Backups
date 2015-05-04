/*
 * Suits.java
 *
 * Version:
 * $Id: Suits.java,v 1.1 2013/03/27 23:23:15 njm7461 Exp $
 *
 * Revisions:
 * $Log: Suits.java,v $
 * Revision 1.1  2013/03/27 23:23:15  njm7461
 * Initial Commit
 *
 */

/**
 * An enum representing the suits in a normal poker deck
 *
 * @author paw: AUTHOR_FULL_NAME_HERE
 */

public enum Suits { 
	CLUBS ( 'C' ), 
	DIAMONDS ( 'D' ) , 
	HEARTS ( 'H' ),
	SPADES ( 'S' );

   /**
    * a constant for the total number of suits
    */
    public static final int NUM_SUITS = 4;
    private final char shortName;

   /**
    * initialize the suit enums, 
    *
    * @param    n       short name for the suit
    */
    Suits( char n ){
	shortName = n;
    }

   /**
    * accessor for the name
    *
    * @return   a char with the short name for this suit
    */
    public char getShortName(){
	return shortName;
    }
}

