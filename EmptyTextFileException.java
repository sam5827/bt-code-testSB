/**
*  Checked exception class EmptyTextFileException. Used if the file
*  the user provides contains no surnames.
*  @author  Samuel Brown
*  @version 1.0
*/
public class EmptyTextFileException extends Exception
{
    /**
    *  EmptyTextFileException Class constructor. Supplies an argument to the superclass
    *  so when getMessage method of Exception is called, it prints the argument
    *  supplied to the command line.
    */
    public EmptyTextFileException()
    {
        // supply superclass with default exception message
        super("Empty text file supplied, no surnames have been found.");
    }
}
