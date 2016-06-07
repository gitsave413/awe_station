package cg.beylhond.soft.entite;

/**
 * Classe representant un genre : soit m ou f
 * 
 * @author Delphin BONDONGO
 * @since mer.30.mars 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

public enum Genre
{
	MASCULIN(1),FEMININ(2);
	
	private int genre = 1;

	Genre(int genre)
	{
		 this.genre = genre;
	}

	/**
	 * @return the genre
	 */
	public int getGenre()
	{
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(int genre)
	{
		this.genre = genre;
	}
}
