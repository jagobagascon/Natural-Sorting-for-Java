# Natural Sorting for Java
A simple Java class that provides natural sorting for strings.

## Usage
Step 1 Add the GitHub Maven repository to your build file
```xml
<repositories>
	<repository>
		<id>github</id>
		<url>https://maven.pkg.github.com/jagobagascon</url>
		<snapshots>
			<enabled>true</enabled>
		</snapshots>
	</repository>
</repositories>
```
Step 2 Add the dependency
```xml
<dependency>
    <groupId>com.github.jagobagascon</groupId>
    <artifactId>Natural-Sorting-for-Java</artifactId>
    <version>1.2.0</version>
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

## Notes

The library recognizes non-ASCII decimal digits when comparing numbers (for example, Thai digits or full-width digits). Numbers consisting of mixed digit sets or languages are compared as a whole. See [this discussion](https://github.com/jagobagascon/Natural-Sorting-for-Java/issues/9) for more info.
