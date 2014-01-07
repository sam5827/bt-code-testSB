import java.io.*;

/**
 *  A class that contains the main method to initiate the matching program.
 *	This class takes arguments from the command line and passes them to the
 *	relevant methods of the program.
 *	ARGUMENT 1: The filename of the file containing the list of surnames.
 *	ARGUMENT 2+: Surnames you want to be phonetically matched to the surnames.
 *  @author  Samuel Brown
 *  @version 1.0
*/
public class Run
{
	/**
    *  Main method for this class. Called upon calling the class name
    *  from the command line.
    *  @param args The arguments supplied at the command line when calling the
    *  class name.
    */
	public static void main(String[] args)
	{
		try
		{
			// create IOManager object
			IOManager iom = new IOManager(new File(args[0]));
			// parse the filename
			iom.parseNameFile();
			
			// for every surname given on the command line
			for (int i = 1; i < args.length; i++)
			{
				// find the matches
				iom.findMatches(args[i]);
			}
		}
		catch (IOException ioe)
		{
			System.err.println("IO exception, enter the arguments again.");
            // collect args again from command line and recall main
            main(IOManager.getArgs());
		}
		catch (EmptyTextFileException etfe)
        {
            System.out.println(etfe.getMessage() + "Enter a file that contains a list of surnames.");
            // collect args again from command line and recall main
            main(IOManager.getArgs());
        }
		
	}
}