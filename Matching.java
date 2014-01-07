import java.util.*;

/**
 *  A class for applying the matching algorithm to a list of surnames and
 *	to individual surnames.
 *  @author  Samuel Brown
 *  @version 1.0
*/
public class Matching
{
	
	private static final Character[] TIER1_ARR = {'a', 'e', 'i', 'o', 'u'};
	/** A set containing equivalent characters at the lowest level, 1: a, e, i, o, u */
	private static final Set<Character> TIER1_SET = new HashSet<Character>(Arrays.asList(TIER1_ARR));
	private static final Character[] TIER2_ARR = {'c', 'g', 'j', 'k', 'q', 's', 'x', 'y', 'z'};
	/** A set containing equivalent characters at level 2: c, g, j, k, q, s, x, y, z */
	private static final Set<Character> TIER2_SET = new HashSet<Character>(Arrays.asList(TIER2_ARR));
	private static final Character[] TIER3_ARR = {'b', 'f', 'p', 'v', 'w'};
	/** A set containing equivalent characters at level 3: b, f, p, v, w */
	private static final Set<Character> TIER3_SET = new HashSet<Character>(Arrays.asList(TIER3_ARR));
	private static final Character[] TIER4_ARR = {'d', 't'};
	/** A set containing equivalent characters at level 4: d, t */
	private static final Set<Character> TIER4_SET = new HashSet<Character>(Arrays.asList(TIER4_ARR));
	private static final Character[] TIER5_ARR = {'m', 'n'};
	/** A set containing equivalent characters at level 5: m, n */
	private static final Set<Character> TIER5_SET = new HashSet<Character>(Arrays.asList(TIER5_ARR));
	
	/**
     *  A public static method that applies steps 1-3 of the matching algorithm to 
	 *	the list of surnames obtained from the text file.
     *  @param arr An ArrayList of Surname objects that must have the surnameOriginal
     *	instance variable set.
	 *	@return An ArrayList of Surname objects that have the surnamePreprocessed instance
	 *	variable set.
    */
	public static ArrayList<Surname> preprocessList(ArrayList<Surname> arr)
	{
		// for each surname object
		for (Surname s : arr)
		{
			// apply steps 1-3 of the matching algorithm
			preprocessSurname(s);
		}
		// return the updated list of surname objects
		return arr;	
	}

	/**
     *  A public static method that applies steps 1-3 of the matching algorithm to
     *	Surname objects.
     *	STEP 1: Filter out non-alphabetic characters by using a regular expression.
     *	STEP 2: Ignore the case of characters in the surname by converting them to 
     *	lowercase.
     *	STEP 3: After the first letter, remove the letters A,E,I,H,O,U,W,Y.
     *  @param s A surname object that needs preprocessing.
    */
	public static void preprocessSurname(Surname s)
	{
		// get the original surname
		String surnameHolder = s.getSurnameOriginal();
		// remove non-alphabetical characters and convert to lower case
		surnameHolder = surnameHolder.replaceAll("[^a-zA-Z]", "").toLowerCase();
		// remove A,E,I,H,O,U,W,Y after the first letter
		surnameHolder = surnameHolder.substring(0,1) + surnameHolder.substring(1).replaceAll("[aeihouwy]", "");
		// set the preprocessed variable for this object
		s.setSurnamePreprocessed(surnameHolder);
	}

	/**
     *  A private method to find which set of equivalent characters the passed
     *	character belongs to.
     *	@param c The character to be considered
     *	@return An integer 1-5 inclusive representing the tier set which this
     *	character belongs to. If the passed character doesn't belong to any set,
     *	then return 0.
    */
	private static int getTier(char c)
	{
		// find which set of equivalent letters this character belongs to
		if (TIER1_SET.contains(c))
			return 1;
		else if (TIER2_SET.contains(c))
			return 2;
		else if (TIER3_SET.contains(c))
			return 3;
		else if (TIER4_SET.contains(c))
			return 4;
		else if (TIER5_SET.contains(c))
			return 5;
		else
			return 0; // all other characters (only L & R) get a weight of 0
	}
	
	/**
     *  A private method to weight a surname by getting the equivalent characters
     *	for each of the characters and summing the weights returned. This method
     *	applies steps 4 and 5 of the matching algorithm.
     *	STEP 4: Identify the equivalent characters. This is done by applying a 
     *	weighting scheme whereby each equivalent set of characters is given a
     *	varying weight. This weight can then be summed to weight the overall
     *	surname.
     *	STEP 5: Consecutive occurrences of characters belonging to equivalent sets
     *	are considered as a single occurence. This is checked upon applying a
     *	weight to a character. An assumption has been made here that if a disregarded
     *	character is in between 2 characters of the same class (E.g. 'SYS' in the made
     *	up surname 'tafSYSen' - 'Y' is disregarded and removed giving 'SS'), then
     *	the two S's will be counted as a single occurrence. 
     *	@param s The surname to be considered.
     *	@return An integer representing the total weight for this surname.
    */
	private static int weightSurname(String s)
	{
		// initialise weights variable
		int weightSum = 0;
		// boolean to disregard consecutive characters of the same class
		boolean skip = false; // false so the first letter is not disregarded

		// for each character in the surname, apply a weight to it
		for (int i = 0; i < s.length(); i++)
		{
			// get the current character
			char currentLetter = s.charAt(i);
			// get the current letters tier: which set of equivalent letters it belongs to
			int tier = getTier(currentLetter);

			// if we are not to skip, apply the weight to the character
			if (!skip)
				// apply weight to the character
				weightSum += tier;

			// if the next character is equal to the current characters' set, ignore it
			if (i < s.length()-1 && getTier(s.charAt(i+1)) == tier)
				skip = true;
			else
				skip = false; // else it must be different, so don't ignore it
		}
		// return the weight of the surname
		return weightSum;
	}
	
	/**
     *  A public static method that applies the weights to the surnames which
     *	considers characters of equivalent classes.
     *  @param arr An ArrayList of Surname objects that have the surnamePreprocessed
     *	instance variable set.
     *	@return The ArrayList taken as a parameter with the weightSum instance
     *	variable of each surname object set.
    */
	public static ArrayList<Surname> applyWeights(ArrayList<Surname> arr)
	{
		// for every surname in the text file
		for (Surname name : arr)
		{
			// set the weighting for that surname
			name.setWeightSum(weightSurname(name.getSurnamePreprocessed()));
		}
		// return the array that now has the weights applied to the surnames
		return arr;
	}

	/**
     *  A public static method that takes a surname that is to be matched against
     *	a list of surnames to find the phonetic match.
     *	@param clSurname A surname object for the surname to be matched.
     *  @param names An ArrayList of Surname objects that have the weightSum
     *	instance variable set.
     *	@return An ArrayList of matched surnames as Strings.
    */
	public static ArrayList<String> findMatchedSurnames(Surname clSurname, ArrayList<Surname> names)
	{
		// preprocess the name to be matched
     	preprocessSurname(clSurname);
     	// weight the surname to be matched
     	int clSurnameWeight = weightSurname(clSurname.getSurnamePreprocessed());
     	// create a new array list with the matches
     	ArrayList<String> matches = new ArrayList<String>();
     	// loop through the names
     	for (Surname s : names)
     	{
     		// add to matches list surnames that have the same weight
     		if (s.getWeightSum() == clSurnameWeight)
     			matches.add(s.getSurnameOriginal());
     	}

     	// return the list of names found
		return matches;
	}
}