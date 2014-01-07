import java.io.*;
import java.util.*;

/**
 *  A class for parsing a list of surnames from a text file and a surname
 *  passed at the command line. This class then finds the surnames from
 *  the list that match the given surname and displays the matches on the
 *  command line.
 *  @author  Samuel Brown
 *  @version 1.0
*/
public class IOManager
{
	private FileInputStream fis;
    private Reader fr;
    private LineNumberReader lnr;
    
    /** A list of names parsed from the text file to match against */
    private ArrayList<Surname> nameList;
    /** A list of the matches found */
    private ArrayList<String> matches;

    /**
     *  IOManager Class constructor. Creates a FileInputStream, InputStreamReader
     *  and a LineNumberReader object to read the file.
     *  @param f A File object for the text file to be used.
     *  @throws IOException Input/output exception thrown if there
     *  is an error with any input.
    */
	public IOManager(File f) throws IOException
	{
		// set instance variables for input/output management
		this.fis = new FileInputStream(f);
		this.fr = new InputStreamReader(fis, "UTF-8");
		this.lnr = new LineNumberReader(fr);
	}

    /**
     * A method that will parse the file containing the list of surnames
     * for the command line argument to be compared against.
     * @throws EmptyTextFileException Throws an EmptyTextFileException if the file is empty.
     * @throws IOException Throws an IOException if there is an error with the Line Number
     * Reader object used to read the next line of the text file.
    */
	public void parseNameFile() throws IOException, EmptyTextFileException
	{
		// create the array list to store the names
		nameList = new ArrayList<Surname>();
		// read next line and store in line string
		String line = lnr.readLine();
		// if the line is null, an empty file has been passed, throw new IOException
		if (line == null)
			throw new EmptyTextFileException();

		// while the next line is not null
		while (line != null)
		{
			// if the line is empty, or contains just white space
			if (!line.trim().equals(""))
			{
				// create a new Surname object with the surname on that line
				// in the text file.
				Surname next = new Surname(line);
				// add the new line as a new entry to the array list
				nameList.add(next);	
			}

			// read next line and store in line string
			line = lnr.readLine();
		}
		// preprocess the surnames
		nameList = Matching.preprocessList(nameList);
		// weight the surnames based on the number of equivalent chars in different sets
		nameList = Matching.applyWeights(nameList);
	}

	public ArrayList<Surname> getNameList()
	{
		return this.nameList;
	}

	/**
	 *	A public method that takes a surname and find the matches in the nameList. A
	 *	NullPointerException will be thrown if the parseNameFile method has not been
	 *	called beforecalling this method to parse the text file.
	 *	@param surname The surname you want to find matches for.
	*/
	public void findMatches(String surname)
	{	
		// create a surname object for the surname to be matched
		Surname clSurname = new Surname(surname);
		// find the matched surnames, in this case, ones with the same weight
		matches = Matching.findMatchedSurnames(clSurname, nameList);

		// print the matches to the command line
		printMatches(surname);
	}

	/**
	 *	A private method that takes a surname and prints the matches that have been
	 *	found by the findMatches method.
	 *	@param surname The surname you want to find matches for.
	*/
	private void printMatches(String surname)
	{
		// print the surname you want to find matches for
		System.out.print(surname + ": ");
		// for each match
		for (int i = 0; i < matches.size(); i++)
		{
			// print the match
			System.out.print(matches.get(i));
			// print a comma if there are more matches
			if (i < matches.size()-1)
				System.out.print(", ");
			else
				// print a new line
				System.out.println();
		}
	}

	/**
	*  A public static method to get the command line arguments again should a
	*  checked exception be thrown.
	*  @return  A string array containing the command line arguments to be
	*  supplied to the main method.
	*/
	public static String[] getArgs()
	{
		// create new string array to be returned
		ArrayList<String> args = new ArrayList<String>();
		try
		{
		    // create new buffered reader
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    // get the text filename
		    System.out.print("Please supply the .txt filename. Please include the extension: ");
		    args.add(br.readLine());
		    String question = "Y";
		    // get all the names
		    while (question.equals("Y"))
		    {
		    	System.out.print("Please supply the surname: ");
			    args.add(br.readLine());
			    System.out.print("Add another? (Y or N): ");
			    question = br.readLine().trim().toUpperCase();
		    }
		}
		catch (IOException ioe)
		{
		    // if an IOException occurs here then exit with error
		    System.out.println("IO error.");
		    System.out.println(ioe.getMessage());
		    System.exit(1);
		}
		// return the args in an array
		return args.toArray(new String[2]);
	} 
}