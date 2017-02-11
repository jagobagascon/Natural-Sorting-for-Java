# Natural-Sorting-for-Java
A simple Java class that provides natural sorting for strings.

## Usage
```java
// If < JDK 8 use: Collections.sort

public void sortNatural(List<String> myList) {
	myList.sort(NaturalSort.naturalSortComparator);
}

public void sortNaturalIgnoreCase(List<String> myList) {
	myList.sort(NaturalSort.naturalSortIgnoreCaseComparator);
}
```
