import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMatching {

	private static final ArrayList<Surname> namesNotPreprocessed = new ArrayList<Surname>(); // only surnameOriginal set
	private static final ArrayList<Surname> namesFullPreprocessed = new ArrayList<Surname>();; // a preprocessed list of surname objects
	private static final ArrayList<Surname> namesPreprocessedWithWeights = new ArrayList<Surname>();; // surnames that have been preprocessed with correct weights
	private ArrayList<Surname> actual; // used for actual result
	
	private static final Surname sur = new Surname("Sammy");;
	private static final String preprocessedSurname = "smm";;
	
	@BeforeClass
	public static void initialise()
	{
		// Create new array list of surname stub
		// only have surnameOriginal set
		//namesNotPreprocessed = new ArrayList<Surname>();
		namesNotPreprocessed.add(new Surname("Smith"));
		namesNotPreprocessed.add(new Surname("Smyth"));
		namesNotPreprocessed.add(new Surname("Smythe"));
		namesNotPreprocessed.add(new Surname("Smid"));
		namesNotPreprocessed.add(new Surname("Erikson"));
		namesNotPreprocessed.add(new Surname("Tafsysen"));
		
		//namesFullPreprocessed = new ArrayList<Surname>();
		namesFullPreprocessed.add(new Surname("Smith"));
		namesFullPreprocessed.get(0).setSurnamePreprocessed("smt");
		namesFullPreprocessed.add(new Surname("Smyth"));
		namesFullPreprocessed.get(1).setSurnamePreprocessed("smt");
		namesFullPreprocessed.add(new Surname("Smythe"));
		namesFullPreprocessed.get(2).setSurnamePreprocessed("smt");
		namesFullPreprocessed.add(new Surname("Smid"));
		namesFullPreprocessed.get(3).setSurnamePreprocessed("smd");
		namesFullPreprocessed.add(new Surname("Erikson"));
		namesFullPreprocessed.get(4).setSurnamePreprocessed("erksn");
		namesFullPreprocessed.add(new Surname("Tafsysen"));
		namesFullPreprocessed.get(5).setSurnamePreprocessed("tfssn");
		
		//namesPreprocessedWithWeights = new ArrayList<Surname>();
		namesPreprocessedWithWeights.add(new Surname("Smith"));
		namesPreprocessedWithWeights.get(0).setSurnamePreprocessed("smt");
		namesPreprocessedWithWeights.get(0).setWeightSum(11);
		namesPreprocessedWithWeights.add(new Surname("Smyth"));
		namesPreprocessedWithWeights.get(1).setSurnamePreprocessed("smt");
		namesPreprocessedWithWeights.get(1).setWeightSum(11);
		namesPreprocessedWithWeights.add(new Surname("Smythe"));
		namesPreprocessedWithWeights.get(2).setSurnamePreprocessed("smt");
		namesPreprocessedWithWeights.get(2).setWeightSum(11);
		namesPreprocessedWithWeights.add(new Surname("Smid"));
		namesPreprocessedWithWeights.get(3).setSurnamePreprocessed("smd");
		namesPreprocessedWithWeights.get(3).setWeightSum(11);
		namesPreprocessedWithWeights.add(new Surname("Erikson"));
		namesPreprocessedWithWeights.get(4).setSurnamePreprocessed("erksn");
		namesPreprocessedWithWeights.get(4).setWeightSum(8);
		namesPreprocessedWithWeights.add(new Surname("Tafsysen"));
		namesPreprocessedWithWeights.get(5).setSurnamePreprocessed("tfssn");
		namesPreprocessedWithWeights.get(5).setWeightSum(14);
	}
	
	@After
	public void teardown()
	{
		actual.clear();
	}
	
	@Test
	public void testPreprocess()
	{
		// perform the preprocessing, steps 1-3 of the matching algorithm
		actual = Matching.preprocessList(namesNotPreprocessed);
		
		// tests the size of the array list is correct
		assertEquals(namesNotPreprocessed.size(), actual.size());
		
		// for each of the surnames
		for (int i = 0; i<actual.size(); i++)
		{
			// check the actual preprocessed surname equals the expected one
			assertEquals(namesFullPreprocessed.get(i).getSurnamePreprocessed(), actual.get(i).getSurnamePreprocessed());
		}
	}
	
	@Test
	public void testApplyWeights()
	{
		// perform the applyWeights method on the preprocessed surnames
		actual = Matching.applyWeights(namesFullPreprocessed);
		int i = 0; // counter for expected result array list
		
		// for each surname in the actual results
		for (Surname s : actual)
		{
			// check the actual weight against the expected weight
			assertEquals(namesPreprocessedWithWeights.get(i).getWeightSum(), s.getWeightSum());
			i++; // increment the counter
		}
	}
	
	@Test
	public void TestPreprocessSurname()
	{
		// so it can be teared down - needed for other tests
		actual = new ArrayList<Surname>();
		// perform the preprocessing of a surname
		Matching.preprocessSurname(sur);
		// check if it returns the expected result
		assertEquals(preprocessedSurname, sur.getSurnamePreprocessed());
	}

	@Test
	public void testFindMatchedSurnames()
	{
		// so it can be teared down - needed for other tests
		actual = new ArrayList<Surname>();
		// the surname to be matched
		Surname toBeMatched = new Surname("Schmidt");
		// the actual matches
		ArrayList<String> expectedMatches = new ArrayList<String>();
		expectedMatches.add("Smith");
		expectedMatches.add("Smyth");
		expectedMatches.add("Smythe");
		expectedMatches.add("Smid");
		
		// perform the find matching method
		ArrayList<String> actualMatches = Matching.findMatchedSurnames(toBeMatched, namesPreprocessedWithWeights);
		// for each of the matches in the actual results
		assertEquals(expectedMatches, actualMatches);
	}
}
