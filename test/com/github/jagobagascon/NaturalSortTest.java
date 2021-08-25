package com.github.jagobagascon;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class NaturalSortTest {

	@Test
	public void testStrings() {
		testSort(Arrays.asList("a", "b", "c", "d", "e", "f"));
	}

	@Test
	public void testList() {
		testSort(Arrays.asList("Item no. 1", "Item no. 2", "Item no. 3", "Item no. 4",
				"Item no. 4", "Item no. 6", "Item no. 7"));
	}

	@Test
	public void testSameNumberDifferentAfterIt() {
		testSort(Arrays.asList("Item 1A", "Item 1B", "Item 1C", "Item 2A", "Item 2B", "Item 2C"));
	}

	@Test
	public void testIgnoreCase() {
		testSortIgnoreCase(Arrays.asList("Item 1A", "Item 1b", "Item 1C", "Item 2A", "Item 2B", "Item 2c"));
	}

	@Test
	public void testEmptyStrings() {
		testSort(Arrays.asList("", "1", "01", "001", "10", "010", "0010", "00010"));
	}

	@Test
	public void testMixedValues() {
		testSort(Arrays.asList("Item no. 1", "Item no. 01", "Item no. 2", "Item no. 3",
				"Item no. 10", "Item no. 010", "Item no. 11"));
	}

	/**
	 * Test that if the same number is encountered with a different
	 * representation is found the shortest one will go first.
	 */
	@Test
	public void testEqualNumbersDifferentRepresentation() {
		testSort(Arrays.asList("0", "1", "01", "001", "10", "010", "0010", "00010"));
	}

	@Test
	public void testBigNumbers() {
		testSort(Arrays.asList("00000000000000000000000000000000000000000000000000000",
				"1", "123456789", "123456789123456789", "123456789123456789123456789"));
	}

	@Test
	public void testGeorgianAlphabetCaseInsensitive() {
		// Characters and explanation taken from https://stackoverflow.com/a/25513639
		String smallLatinI = "i";
		String smallDotlessI = "ı";
		String capitalDottedI = "İ";
		// Both characters have same uppercase counterpart, so they are equal in case-insensitive comparison
		Assert.assertEquals(0, NaturalSort.naturalSortIgnoreCaseComparator.compare(smallDotlessI, smallLatinI));
		// When converted to uppercase and then to lowercase, both characters give "I", so they are equal in case-insensitive comparison
		Assert.assertEquals(0, NaturalSort.naturalSortIgnoreCaseComparator.compare(capitalDottedI, smallDotlessI));

		// Check that case ignoring logic does not break regular comparisons.
		Assert.assertEquals(sign(smallDotlessI.compareTo(smallLatinI)), sign(NaturalSort.naturalSortComparator.compare(smallDotlessI, smallLatinI)));
		Assert.assertEquals(sign(capitalDottedI.compareTo(smallDotlessI)), sign(NaturalSort.naturalSortComparator.compare(capitalDottedI, smallDotlessI)));
	}

	private void testSort(List<String> expectedResult) {
		testSort(expectedResult, NaturalSort.naturalSortComparator);
	}

	private void testSortIgnoreCase(List<String> expectedResult) {
		testSort(expectedResult, NaturalSort.naturalSortIgnoreCaseComparator);
	}

	private void testSort(List<String> expectedResult, Comparator<String> comparator) {
		// Calculate seed depending only on test data to make tests deterministic and failures reproducible
		long seed = 0;

		// Avoid using List.hashCode here, not sure if it changes
		// between Java versions; String.hashCode definitely does not change
		for (String element : expectedResult) {
			seed += 31L * element.hashCode();
		}

		Random random = new Random(seed);

		int runCount = 64;

		List<String> test = new ArrayList<>(expectedResult);

		for (int run = 0; run < runCount; run++) {
			Collections.shuffle(test, random);
			Collections.sort(test, comparator);
			for (int i = 0; i < test.size(); i++) {
				String actual = test.get(i);
				String expected = expectedResult.get(i);
				if (!actual.equals(expected)) {
					String message = "Lists differ at index " + i + "\n" +
							"Actual element:   " + actual + "\n" +
							"Expected element: " + expected + "\n" +
							"Actual list:      " + test + "\n" +
							"Expected list:    " + expectedResult;
					throw new AssertionError(message);
				}
			}
		}
	}

	private static int sign(int x) {
		return Integer.compare(x, 0);
	}

}
