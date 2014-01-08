import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestIOManager {

	private IOManager fi;
	private File f;
	private ArrayList<Surname> names;
	private static final ArrayList<String> expectedMatches = new ArrayList<String>();
	
	@BeforeClass
	public static void initClassStart()
	{
		expectedMatches.add("Smith");
		expectedMatches.add("Smyth");
		expectedMatches.add("Smythe");
		expectedMatches.add("Smid");
		expectedMatches.add("Schmidt");
	}
	
	@Before
	public void initialise() throws IOException
	{	
		// create new file object from surnames text file
		f = new File("surnames.txt");
		// create new file input object from file
		fi = new IOManager(f);
		
		// Create new array list of surname stub
		names = new ArrayList<Surname>();
		names.add(new Surname("Smith"));
		names.add(new Surname("Smyth"));
		names.add(new Surname("Smythe"));
		names.add(new Surname("Smid"));
		names.add(new Surname("Schmidt"));
		names.add(new Surname("Smithers"));
		names.add(new Surname("Jonas"));
		names.add(new Surname("Johns"));
		names.add(new Surname("Johnson"));
		names.add(new Surname("Macdonald"));
		names.add(new Surname("Nest O'Malett"));
		names.add(new Surname("Ericsson"));
		names.add(new Surname("Erikson"));
		names.add(new Surname("Saunas"));
		names.add(new Surname("Van Damme"));
	}
	
	@After
	public void teardown()
	{
		fi = null;
		f = null;
	}
	
	@Test(expected=IOException.class)
	public void testConstructorIOException() throws IOException
	{
		// should throw exception
		fi = new IOManager(new File("Rubbish.txt"));
	}
	
	@Test
	public void testFindMatches()
	{
		// preprocess the surnames
		Matching.preprocessList(names); // already tested in TestMatching
		// weight the surnames
		Matching.applyWeights(names); // already tested in TestMatching
		// set nameList to equal this
		fi.nameList = names;
		// find the matches
		fi.findMatches("Smith");
		// check the matches are correct

		assertEquals(expectedMatches, fi.matches);
	}
	
	@Test
	public void testParseNameFileNotEmpty() throws IOException, EmptyTextFileException
	{
		// perform the parseNameFile method
		fi.parseNameFile();
		int i = 0; // counter
		// check the namelist variable to see if it surnameOriginal is correct
		for (Surname s : fi.nameList)
		{
			// check the surname in the array list of surnames generated from the file
			// is the one expected
			assertEquals(names.get(i).getSurnameOriginal(), s.getSurnameOriginal());
			i++;
		}
	}
	
	@Test(expected=EmptyTextFileException.class)
	public void testParseNameFileEmpty() throws IOException, EmptyTextFileException
	{
		// create new file object from surnames text file
		f = new File("empty.txt");
		// create new file input object from file
		fi = new IOManager(f);
		// perform the parseNameFile method, should throw exception
		fi.parseNameFile();
	}
}