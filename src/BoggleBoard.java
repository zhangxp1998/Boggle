import edu.princeton.cs.algs4.In;

public class BoggleBoard
{
	private final char[][] a;
	private final int m;
	private final int n;

	// Initializes a random 4-by-4 Boggle board.
	// (by rolling the Hasbro dice)
	public BoggleBoard()
	{
		this(4, 4);
	}

	// Initializes a random m-by-n Boggle board.
	// (using the frequency of letters in the English language)
	public BoggleBoard(int m, int n)
	{
		assert m > 0;
		assert n > 0;
		
		this.m = m;
		this.n = n;
		a = new char[m][n];
	}

	// Initializes a Boggle board from the specified filename.
	public BoggleBoard(String filename)
	{
		In in = new In(filename);
		m = in.readInt();
		n = in.readInt();
		a = new char[m][n];
		
		for(int i = 0; i < m; i ++)
		{
			for(int j = 0; j < n; j ++)
			{
				String s = in.readString();
				assert s.length() == 1;
				a[i][j] = s.charAt(0);
			}
		}
	}

	// Initializes a Boggle board from the 2d char array.
	// (with 'Q' representing the two-letter sequence "Qu")
	public BoggleBoard(char[][] a)
	{
		this.a = a;
		this.m = a.length;
		this.n = a[0].length;

		for (char[] arr : a)
		{
			assert arr.length == n;
		}
	}

	// Returns the number of rows.
	public int rows()
	{
		return m;
	}

	// Returns the number of columns.
	public int cols()
	{
		return n;
	}

	// Returns the letter in row i and column j.
	// (with 'Q' representing the two-letter sequence "Qu")
	public char getLetter(int i, int j)
	{
		return a[i][j];
	}

	// Returns a string representation of the board.
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (char[] arr : a)
		{
			for (char c : arr)
			{
				sb.append(c + " ");
			}
			sb.setCharAt(sb.length() - 1, '\n');
		}
		return sb.toString();
	}
}