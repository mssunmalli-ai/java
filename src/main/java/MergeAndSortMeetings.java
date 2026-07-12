package main.java;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'mergeHighDefinitionIntervals' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts 2D_INTEGER_ARRAY intervals as parameter.
     */

    public static List<List<Integer>> mergeHighDefinitionIntervals(List<List<Integer>> intervals) {
        List<List<Integer>> result = new ArrayList<>();
        if(intervals.isEmpty() || intervals == null) {
            return result;
        }
        intervals.sort((a, b) -> Integer.compare(a.get(0), b.get(0)));
        result.add(new ArrayList<>(intervals.get(0)));

        for (int i =1; i<intervals.size(); i++) {
            List<Integer> curr = intervals.get(i);
            List<Integer> lastMerged = result.get(result.size()-1);

            if (curr.get(0) <= lastMerged.get(1)){
                lastMerged.set(1, Math.max(curr.get(1), lastMerged.get(1)));
            } else {
                result.add(new ArrayList<>(curr));
            }
        }
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int intervalsRows = Integer.parseInt(bufferedReader.readLine().trim());
        int intervalsColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> intervals = new ArrayList<>();

        IntStream.range(0, intervalsRows).forEach(i -> {
            try {
                intervals.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<Integer>> result = Result.mergeHighDefinitionIntervals(intervals);

        result.stream()
                .map(
                        r -> r.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                )
                .collect(toList())
                .forEach(System.out::println);

        bufferedReader.close();
    }
}
