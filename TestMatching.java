import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import phonetic_search.Matching;
import phonetic_search.Surname;

public class TestMatching {

	private ArrayList<Surname> names;
	private ArrayList<String> namesPreprocessed;
	private ArrayList<Surname> actual;
	private ArrayList<Integer> expectedWeights;
	private ArrayList<Surname> actualWeights;
	
	private Surname sur;
	private String preprocessedSurname;
	
	@Before
	public void initialise() throws IOException
	{
		// Create new array list of surname stub
		names = new ArrayList<Surname>();
		names.add(new Surname("Smith"));
		names.add(new Surname("Smyth"));
		names.add(new Surname("Smythe"));
		names.add(new Surname("Smid"));
		names.add(new Surname("Erikson"));
		names.add(new Surname("Tafsysen"));
		
		namesPreprocessed = new ArrayList<String>();
		namesPreprocessed.add("sm#t#");
		namesPreprocessed.add("sm#t#");
		namesPreprocessed.add("sm#t##");
		namesPreprocessed.add("sm#d");
		namesPreprocessed.add("er#ks#n");
		namesPreprocessed.add("t#fs#s#n");
		
		expectedWeights = new ArrayList<Integer>();
		expectedWeights.add(11);
		expectedWeights.add(11);
		expectedWeights.add(11);
		expectedWeights.add(11);
		expectedWeights.add(8);
		expectedWeights.add(16);
		
		sur = new Surname("Sammy");
		preprocessedSurname = "s#mm#";
	}
	
	@Test
	public void testPreprocess()
	{
		actual = Matching.preprocessList(names);
		assertEquals(namesPreprocessed.size(), actual.size());
		for (int i = 0; i<actual.size(); i++)
		{
			assertEquals(namesPreprocessed.get(i), actual.get(i).getSurnamePreprocessed());
		}
	}
	
	@Test
	public void testApplyWeights()
	{
		actual = Matching.preprocessList(names);
		actualWeights = Matching.applyWeights(actual);
		// TODO: Refactor this test, remove dependency on preprocessList method
		// create what they output
		int i = 0;
		for (Surname s : actualWeights)
		{
			assertEquals(s.getWeightSum(), expectedWeights.get(i).intValue());
			i++;
		}
	}
	
	@Test
	public void TestPreprocessSurname()
	{
		Matching.preprocessSurname(sur);
		assertEquals(preprocessedSurname, sur.getSurnamePreprocessed());
	}

	@Test
	public void testFindMatchedSurnames()
	{
		actual = Matching.preprocessList(names);
		actualWeights = Matching.applyWeights(actual);
		// TODO: Complete this test, remove dependency on above methods
		// create what they output
		//assertEquals(7, Matching.findMatchedSurnames(sur, ));
	}
}
