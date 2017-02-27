import edu.princeton.cs.algs4.Queue;
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
		for (String s : dictionary)
		{
			words.add(s);
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an
	// Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board)
	{
		Queue<String> que = new Queue<String>();
		return que;
	}

	// Returns the score of the given word if it is in the dictionary, zero
	// otherwise.
	// (You can assume the word contains only the uppercase letters A through
	// Z.)
	public int scoreOf(String word)
	{
		if (!words.contains(word))
			return 0;

		if (word.length() <= 2)
			return 0;
		else if (word.length() <= 4)
			return 1;
		else if (word.length() <= 5)
			return 2;
		else if (word.length() <= 6)
			return 3;
		else if (word.length() <= 7)
			return 5;
		else
			return 11;
	}
}