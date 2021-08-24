package com.github.jagobagascon;

import java.util.Comparator;

/**
 * Contains comparators and comparison methods for
 * <a href="https://en.wikipedia.org/wiki/Natural_sort_order">natural string sorting</a>.
 * @author Jagoba Gasc&oacute;n
 */
public class NaturalSort {

	private NaturalSort() {
	}

	public static final Comparator<String> naturalSortComparator = new Comparator<String>() {

		public int compare(String o1, String o2) {
			return NaturalSort.compare(o1, o2);
		}
	};

	public static final Comparator<String> naturalSortIgnoreCaseComparator = new Comparator<String>() {

		public int compare(String o1, String o2) {
			return NaturalSort.compareIgnoreCase(o1, o2);
		}
	};

	public static int compareIgnoreCase(String aString, String bString) {
		return compare(aString, bString, true);
	}

	public static int compare(String aString, String bString) {
		return compare(aString, bString, false);
	}

	private static int compare(String aString, String bString, boolean ignoreCase) {
		int len1 = aString.length();
		int len2 = bString.length();
		int lim = Math.min(len1, len2);

		int k = 0;
		while (k < lim) {
			char c1 = aString.charAt(k);
			char c2 = bString.charAt(k);

			if (Character.isDigit(c1) && Character.isDigit(c2)) {
				int currentIndex1 = k + 1;
				// k => digit
				while (currentIndex1 < len1) {
					c1 = aString.charAt(currentIndex1);
					if (!Character.isDigit(c1)) {
						break;
					}
					currentIndex1++;
				} // [k, i) => number

				int currentIndex2 = k + 1;
				// k => digit
				while (currentIndex2 < len2) {
					c2 = bString.charAt(currentIndex2);
					if (!Character.isDigit(c2)) {
						break;
					}
					currentIndex2++;
				} // [k, j) => number

				/*
				 * If the numbers are different we do not care about the rest of
				 * the string: return immediately.
				 */
				int cmp = compareNumericStrings(k, aString, currentIndex1 - k, bString, currentIndex2 - k);
				if (cmp != 0) {
					return cmp;
				} else {
					/*
					 * If the number representation is the same but strings have
					 * different amount of digits (1 and 01) the shortest goes
					 * first.
					 */
					if (currentIndex1 != currentIndex2) {
						return currentIndex1 - currentIndex2;
					}

					// Everything is the same, continue where we left
					k = currentIndex1 - 1;
				}

			} else {
				if (ignoreCase) {
					c1 = Character.toLowerCase(c1);
					c2 = Character.toLowerCase(c2);
				}

				if (c1 != c2) {
					return c1 - c2;
				}
			}
			k++;
		}
		return len1 - len2;
	}

	private static int compareNumericStrings(int from, String n1, int n1Len, String n2, int n2Len) {
		int res = 0;

		int maxLen = Math.max(n1Len, n2Len);

		// Compare strings from right to left
		for (int i = 1; i <= maxLen; i++) {
			// Assume the char is a '0' in case the string is shorter
			char c1 = '0';
			char c2 = '0';

			if (i <= n1Len) {
				c1 = n1.charAt(from + n1Len - i);
			}

			if (i <= n2Len) {
				c2 = n2.charAt(from + n2Len - i);
			}

			// Only update res if they are different
			if (c1 != c2) {
				res = c1 - c2; // Char comparison is fine as 0 < 1 < ... < 9
			}
		}

		return res;
	}

}
