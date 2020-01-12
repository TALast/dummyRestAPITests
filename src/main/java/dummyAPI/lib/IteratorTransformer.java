package dummyAPI.lib;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IteratorTransformer {
    // Function to get the List
    public static List<String> getListFromIterator(Iterator<String> iterator)
    {

        // Convert iterator to iterable
        Iterable<String> iterable = () -> iterator;

        // Create a List from the Iterable
        List<String> list = StreamSupport
            .stream(iterable.spliterator(), false)
            .collect(Collectors.toList());

        // Return the List
        return list;
    }

}
