package com.github.jagobagascon;

import java.math.BigInteger;
import java.util.Comparator;

/**
 * Contains comparators and compare methods for natural string sorting
 * (https://en.wikipedia.org/wiki/Natural_sort_order)
 * 
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
		return compare(aString.toLowerCase(), bString.toLowerCase());
	}

	public static int compare(String aString, String bString) {

		char[] v1 = aString.toCharArray();
		char[] v2 = bString.toCharArray();
		int len1 = v1.length;
		int len2 = v2.length;
		int lim = Math.min(len1, len2);

		int k = 0;
		while (k < lim) {
			char c1 = v1[k];
			char c2 = v2[k];

			if (Character.isDigit(c1) && Character.isDigit(c2)) {
				int currentIndex1 = k + 1;
				// k => digit
				while (currentIndex1 < len1) {
					c1 = v1[currentIndex1];
					if (!Character.isDigit(c1)) {
						break;
					}
					currentIndex1++;
				} // [k, i) => number
				String number1String = aString.substring(k, currentIndex1);

				int currentIndex2 = k + 1;
				// k => digit
				while (currentIndex2 < len2) {
					c2 = v2[currentIndex2];
					if (!Character.isDigit(c2)) {
						break;
					}
					currentIndex2++;
				} // [k, j) => number
				String number2String = bString.substring(k, currentIndex2);

				int maxNumberLength = Math.max(number1String.length(), number2String.length());
				Number number1 = parseNumber(number1String, maxNumberLength);
				Number number2 = parseNumber(number2String, maxNumberLength);
				int numberComparisonResult = compareNumbers(number1, number2);

				/*
				 * If the numbers are different we do not care about the rest of
				 * the string: return immediately.
				 */
				if (numberComparisonResult != 0) {
					return numberComparisonResult;
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

			} else if (c1 != c2) {
				return c1 - c2;
			}
			k++;
		}
		return len1 - len2;
	}

	private static Number parseNumber(String s, int maxLength) {
		if (maxLength < 10) {
			return Integer.parseInt(s);
		}

		if (maxLength <= 19) {
			return Long.parseLong(s);
		}

		return new BigInteger(s);
	}

	@SuppressWarnings("unchecked")
	private static int compareNumbers(Number a, Number b) {
		return ((Comparable<Number>) a).compareTo(b);
	}

}