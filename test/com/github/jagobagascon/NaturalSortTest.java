package com.github.jagobagascon;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class NaturalSortTest {

	@Test
	public void testStrings() {
		List<String> test = Arrays.asList("f", "b", "c", "d", "e", "a");

		List<String> expectedResult = Arrays.asList("a", "b", "c", "d", "e", "f");
		testAndExpect(test, expectedResult);
	}

	@Test
	public void testList() {
		List<String> test = Arrays.asList("Item no. 1", "Item no. 2", "Item no. 3", "Item no. 4", "Item no. 4",
				"Item no. 6", "Item no. 7");

		List<String> expectedResult = Arrays.asList("Item no. 1", "Item no. 2", "Item no. 3", "Item no. 4",
				"Item no. 4", "Item no. 6", "Item no. 7");
		testAndExpect(test, expectedResult);
	}

	@Test
	public void testSameNumberDifferentAfterIt() {
		List<String> test = Arrays.asList("Item 1A", "Item 1B", "Item 1C", "Item 2A", "Item 2B", "Item 2C");

		List<String> expectedResult = Arrays.asList("Item 1A", "Item 1B", "Item 1C", "Item 2A", "Item 2B", "Item 2C");
		testAndExpect(test, expectedResult);
	}

	@Test
	public void testIgnoreCase() {
		List<String> test = Arrays.asList("Item 1A", "Item 1b", "Item 1C", "Item 2A", "Item 2B", "Item 2c");

		List<String> expectedResult = Arrays.asList("Item 1A", "Item 1b", "Item 1C", "Item 2A", "Item 2B", "Item 2c");
		testAndExpectIgnoreCase(test, expectedResult);
	}

	@Test
	public void testEmptyStrings() {
		List<String> test = Arrays.asList("00010", "0010", "010", "10", "001", "01", "1", "");
		List<String> expectedResult = Arrays.asList("", "1", "01", "001", "10", "010", "0010", "00010");
		testAndExpect(test, expectedResult);
	}

	@Test
	public void testMixedValues() {
		List<String> test = Arrays.asList("Item no. 1", "Item no. 01", "Item no. 10", "Item no. 2", "Item no. 3",
				"Item no. 11", "Item no. 010");
		List<String> expectedResult = Arrays.asList("Item no. 1", "Item no. 01", "Item no. 2", "Item no. 3",
				"Item no. 10", "Item no. 010", "Item no. 11");
		testAndExpect(test, expectedResult);
	}

	/**
	 * Test that if the same number is encountered with a different
	 * representation is found the shortest one will go first.
	 */
	@Test
	public void testEqualNumbersDifferentRepresentation() {
		List<String> test = Arrays.asList("00010", "0010", "010", "10", "001", "01", "1", "0");
		List<String> expectedResult = Arrays.asList("0", "1", "01", "001", "10", "010", "0010", "00010");

		testAndExpect(test, expectedResult);
	}

	@Test
	public void testBigNumbers() {
		List<String> test = Arrays.asList("123456789", "1", "123456789123456789123456789", "123456789123456789", "00000000000000000000000000000000000000000000000000000");
		List<String> expectedResult = Arrays.asList("00000000000000000000000000000000000000000000000000000", "1", "123456789", "123456789123456789", "123456789123456789123456789");

		testAndExpect(test, expectedResult);
	}

	private void testAndExpect(List<String> test, List<String> expectedResult) {
		testAndExpect(test, expectedResult, NaturalSort.naturalSortComparator);
	}

	private void testAndExpectIgnoreCase(List<String> test, List<String> expectedResult) {
		testAndExpect(test, expectedResult, NaturalSort.naturalSortIgnoreCaseComparator);
	}

	private void testAndExpect(List<String> test, List<String> expectedResult, Comparator<String> comparator) {
		long seed = System.nanoTime();
		Collections.shuffle(test, new Random(seed));
		Collections.sort(test, comparator);
		for (int i = 0; i < test.size(); i++) {
			Assert.assertEquals(expectedResult.get(i), test.get(i));
		}
	}

}
