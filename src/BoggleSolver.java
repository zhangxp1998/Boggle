import java.util.Iterator;

import edu.princeton.cs.algs4.Queue;

public class BoggleSolver
{
	// Initializes the data structure using the given array of strings as the
	// dictionary.
	// (You can assume each word in the dictionary contains only the uppercase
	// letters A through Z.)
	Trie words;

	public BoggleSolver(String[] dictionary)
	{
		words = new Trie();
		for (String s : dictionary)
			words.add(s);
		
	}

	// Returns the set of all valid words in the given Boggle board, as an
	// Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board)
	{
		//Valid words
		Queue<String> strs = new Queue<String>();
		
		//
		Queue<Long> que = new Queue<Long>();
		for(int i = 0; i < board.rows(); i ++)
		{
			for(int j = 0; j < board.cols(); j ++)
			{
				char letter = board.getLetter(i, j);
				if(words.root.next[letter] != null)
					que.enqueue(((long)i << 32) | j);
				
			}
		}
		
		BoggleSolver.Trie.Node cur = words.root;
		
		while(!que.isEmpty())
		{
			//Number of elements in current level
			final int W = que.size();
			for(int i = 0; i < W; i ++)
			{
				long combined = que.dequeue();
				int r = (int) (combined >> 32);
				int c = (int) (combined & 0xFFFFFFFF);
				
			}
		}
		return strs;
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

	/******************************************************************************
	 * Compilation: javac TrieSET.java Execution: java TrieSET < words.txt
	 * Dependencies: StdIn.java Data files:
	 * http://algs4.cs.princeton.edu/52trie/shellsST.txt
	 *
	 * An set for extended ASCII strings, implemented using a 256-way trie.
	 *
	 * Sample client reads in a list of words from standard input and prints out
	 * each word, removing any duplicates.
	 *
	 ******************************************************************************/

	/**
	 * The {@code TrieSET} class represents an ordered set of strings over the
	 * extended ASCII alphabet. It supports the usual <em>add</em>,
	 * <em>contains</em>, and <em>delete</em> methods. It also provides
	 * character-based methods for finding the string in the set that is the
	 * <em>longest prefix</em> of a given prefix, finding all strings in the set
	 * that <em>start with</em> a given prefix, and finding all strings in the
	 * set that <em>match</em> a given pattern.
	 * <p>
	 * This implementation uses a 256-way trie. The <em>add</em>,
	 * <em>contains</em>, <em>delete</em>, and <em>longest prefix</em> methods
	 * take time proportional to the length of the key (in the worst case).
	 * Construction takes constant time.
	 * <p>
	 * For additional documentation, see
	 * <a href="http://algs4.cs.princeton.edu/52trie">Section 5.2</a> of
	 * <i>Algorithms in Java, 4th Edition</i> by Robert Sedgewick and Kevin
	 * Wayne.
	 *
	 * @author Robert Sedgewick
	 * @author Kevin Wayne
	 */
	static class Trie implements Iterable<String>
	{
		private static final int R = 256; // extended ASCII

		private Node root; // root of trie
		private int n; // number of keys in trie

		// R-way trie node
		private static class Node
		{
			private Node[] next = new Node[R];
			private boolean isString;
		}

		public Trie()
		{

		}

		/**
		 * Does the set contain the given key?
		 * 
		 * @param key
		 *            the key
		 * @return {@code true} if the set contains {@code key} and
		 *         {@code false} otherwise
		 * @throws NullPointerException
		 *             if {@code key} is {@code null}
		 */
		public boolean contains(String key)
		{
			Node x = get(root, key, 0);
			if (x == null)
				return false;
			return x.isString;
		}

		private Node get(Node x, String key, int d)
		{
			if (x == null)
				return null;
			if (d == key.length())
				return x;
			char c = key.charAt(d);
			return get(x.next[c], key, d + 1);
		}

		/**
		 * Adds the key to the set if it is not already present.
		 * 
		 * @param key
		 *            the key to add
		 * @throws NullPointerException
		 *             if {@code key} is {@code null}
		 */
		public void add(String key)
		{
			root = add(root, key, 0);
		}

		private Node add(Node x, String key, int d)
		{
			if (x == null)
				x = new Node();
			if (d == key.length())
			{
				if (!x.isString)
					n++;
				x.isString = true;
			} else
			{
				char c = key.charAt(d);
				x.next[c] = add(x.next[c], key, d + 1);
			}
			return x;
		}

		/**
		 * Returns the number of strings in the set.
		 * 
		 * @return the number of strings in the set
		 */
		public int size()
		{
			return n;
		}

		/**
		 * Is the set empty?
		 * 
		 * @return {@code true} if the set is empty, and {@code false} otherwise
		 */
		public boolean isEmpty()
		{
			return size() == 0;
		}

		/**
		 * Returns all of the keys in the set, as an iterator. To iterate over
		 * all of the keys in a set named {@code set}, use the foreach notation:
		 * {@code for (Key key : set)}.
		 * 
		 * @return an iterator to all of the keys in the set
		 */
		public Iterator<String> iterator()
		{
			return keysWithPrefix("").iterator();
		}

		/**
		 * Returns all of the keys in the set that start with {@code prefix}.
		 * 
		 * @param prefix
		 *            the prefix
		 * @return all of the keys in the set that start with {@code prefix}, as
		 *         an iterable
		 */
		public Iterable<String> keysWithPrefix(String prefix)
		{
			Queue<String> results = new Queue<String>();
			Node x = get(root, prefix, 0);
			collect(x, new StringBuilder(prefix), results);
			return results;
		}

		private void collect(Node x, StringBuilder prefix, Queue<String> results)
		{
			if (x == null)
				return;
			if (x.isString)
				results.enqueue(prefix.toString());
			for (char c = 0; c < R; c++)
			{
				prefix.append(c);
				collect(x.next[c], prefix, results);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		}

		/**
		 * Returns the string in the set that is the longest prefix of
		 * {@code query}, or {@code null}, if no such string.
		 * 
		 * @param query
		 *            the query string
		 * @return the string in the set that is the longest prefix of
		 *         {@code query}, or {@code null} if no such string
		 * @throws NullPointerException
		 *             if {@code query} is {@code null}
		 */
		public String longestPrefixOf(String query)
		{
			int length = longestPrefixOf(root, query, 0, -1);
			if (length == -1)
				return null;
			return query.substring(0, length);
		}

		// returns the length of the longest string key in the subtrie
		// rooted at x that is a prefix of the query string,
		// assuming the first d character match and we have already
		// found a prefix match of length length
		private int longestPrefixOf(Node x, String query, int d, int length)
		{
			if (x == null)
				return length;
			if (x.isString)
				length = d;
			if (d == query.length())
				return length;
			char c = query.charAt(d);
			return longestPrefixOf(x.next[c], query, d + 1, length);
		}
	}

	/******************************************************************************
	 * Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
	 *
	 * This file is part of algs4.jar, which accompanies the textbook
	 *
	 * Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
	 * Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
	 * http://algs4.cs.princeton.edu
	 *
	 *
	 * algs4.jar is free software: you can redistribute it and/or modify it
	 * under the terms of the GNU General Public License as published by the
	 * Free Software Foundation, either version 3 of the License, or (at your
	 * option) any later version.
	 *
	 * algs4.jar is distributed in the hope that it will be useful, but WITHOUT
	 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
	 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
	 * more details.
	 *
	 * You should have received a copy of the GNU General Public License along
	 * with algs4.jar. If not, see http://www.gnu.org/licenses.
	 ******************************************************************************/

}