import edu.princeton.cs.algs4.TrieSET;

public class BoggleSolver
{
	// Initializes the data structure using the given array of strings as the
	// dictionary.
	// (You can assume each word in the dictionary contains only the uppercase
	// letters A through Z.)
	TrieSET words;
	public BoggleSolver(String[] dictionary)
	{
		words = new TrieSET();
		for(String s : dictionary)
		{
			words.add(s);
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an
	// Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board)
	{
		
	}

	// Returns the score of the given word if it is in the dictionary, zero
	// otherwise.
	// (You can assume the word contains only the uppercase letters A through
	// Z.)
	public int scoreOf(String word)
	{
		
	}
}