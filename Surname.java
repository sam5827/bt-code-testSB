/**
 *  A class to represent a Surname as the matching algorithm is applied to it.
 *  @author  Samuel Brown
 *  @version 1.0
*/
public class Surname
{
	private String surnameOriginal;
	private int weightSum;
	private String surnamePreprocessed;
	
	/**
	 * Surname Class constructor. Sets the surnameOriginal instance variable to
	 * the parameter passed.
	 * @param surname The original unmodified surname from the surnames text file.
	 */
	public Surname(String surname)
	{
		this.surnameOriginal = surname;
	}
	
	/**
	 * Accessor method for the surnamePreprocessed variable
	 * @return A string containing the value of the surnamePreprocessed variable which contains
	 * the surname after applying certain steps of the matching algorithm to it.
	 */
	public String getSurnamePreprocessed()
	{
		return surnamePreprocessed;
	}

	/**
	 * Accessor method for the surnameOriginal variable
	 * @return A string containing the value of the surnameOriginal variable which contains
	 * the original surname as passed form the command line or from the text file.
	 */
	public String getSurnameOriginal()
	{
		return surnameOriginal;
	}

	/**
	 * Accessor method for the weightSum variable
	 * @return An integer for the total weight for this surname as applied by
	 * the matching algorithm.
	 */
	public int getWeightSum()
	{
		return weightSum;
	}
	
	/**
	 * Mutator method for the surnamePreprocessed variable
	 * @param s A string containing the value of the surnamePreprocessed variable which contains
	 * the surname after applying certain steps of the matching algorithm to it.
	 */
	public void setSurnamePreprocessed(String s)
	{
		this.surnamePreprocessed = s;
	}

	/**
	 * Mutator method for the surnameOriginal variable
	 * @param surnameOriginal A string containing the value of the surnameOriginal variable which contains
	 * the original surname as passed form the command line or from the text file.
	 */
	public void setSurnameOriginal(String surnameOriginal)
	{
		this.surnameOriginal = surnameOriginal;
	}

	/**
	 * Mutator method for the weightSum variable
	 * @param weightSum An integer for the total weight for this surname as applied by
	 * the matching algorithm.
	 */
	public void setWeightSum(int weightSum)
	{
		this.weightSum = weightSum;
	}
}
