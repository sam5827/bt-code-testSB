import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import phonetic_search.IOManager;
import phonetic_search.Surname;

public class TestFileInput {

	private IOManager fi;
	private File f;
	private ArrayList<Surname> names;
	
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
	
	@Test
	public void testGetArgs()
	{
		// TODO: Complete of the getArgs method
		// How to simulate the buffered reader...?
	}
	
	@Test
	public void testFindMatches()
	{
		// TODO: Complete test for the findMatches method
		// MAY NOT BE NECESSARY AS IT IS MORE OF SYSTEMS TEST TO
		// MAKE SURE IT PRINTS TO CMD CORRECTLY...
	}
	
	@Test
	public void testParseNameFile()
	{
		// TODO: Complete test for the parseNameFile method
		// test to see if the nameList contains the correct original
		// surnames, preprocessList and applyWeights methods are tested
		// in the TestMatching class.
	}
	
//	@Test
//	public void testParseLine() throws IOException {
//		// test can only be run if the parseNameFile method in the IOManager
//		// class is set to be public static. This was done but changed to accomodate
//		// implementation changes.
//		fi.parseNameFile();
//		ArrayList<Surname> actual = fi.getNameList();
//		assertEquals(names.size(), actual.size());
//		for (int i = 0; i<actual.size(); i++)
//		{
//			assertEquals(names.get(i).getSurnameOriginal(),actual.get(i).getSurnameOriginal());
//		}
//		
//	}
	
	

}
