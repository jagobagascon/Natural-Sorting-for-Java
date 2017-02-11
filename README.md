# Natural Sorting for Java
A simple Java class that provides natural sorting for strings.

## Usage
Step 1 Add the JitPack repository to your build file
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```
Step 2 Add the dependency
```xml
<dependency>
    <groupId>com.github.jagobagascon</groupId>
    <artifactId>Natural-Sorting-for-Java</artifactId>
    <version>1.0.0</version>
</dependency>
```
Step 3 Use the provided comparators to sort your lists
```java
// If < JDK 8 use: Collections.sort

public void sortNatural(List<String> myList) {
	myList.sort(NaturalSort.naturalSortComparator);
}

public void sortNaturalIgnoreCase(List<String> myList) {
	myList.sort(NaturalSort.naturalSortIgnoreCaseComparator);
}
```
