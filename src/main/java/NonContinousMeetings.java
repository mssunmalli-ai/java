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
     * Complete the 'maximizeNonOverlappingMeetings' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY meetings as parameter.
     */

    public static int maximizeNonOverlappingMeetings(List<List<Integer>> meetings) {
        // Write your code here
        // pairs.sort(Comparator.comparingInt(pair -> pair.get(1)));
        if(meetings.isEmpty()){
            return 0;
        }
        meetings.sort((a,b) -> Integer.compare(a.get(1), b.get(1)));
        var nonOverlappingCount = 1;
        var lastEnd = meetings.get(0).get(1);
        // System.out.println(meetings);
        for(int i=1; i<=meetings.size() - 1; i++) {
            var  prevEnd = meetings.get(i-1).get(1);
            var currentStart = meetings.get(i).get(0);

            if(currentStart >= lastEnd){
                nonOverlappingCount++;
                // This is important to handle the scenario:
                // [1,3], [2,4], [3,5] , without this nonOverlappingCount will be 1 but the expected is nonOverlappingCount = 2
                lastEnd = meetings.get(i).get(1);
            }
        }
        return nonOverlappingCount;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int meetingsRows = Integer.parseInt(bufferedReader.readLine().trim());
        int meetingsColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> meetings = new ArrayList<>();

        IntStream.range(0, meetingsRows).forEach(i -> {
            try {
                meetings.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.maximizeNonOverlappingMeetings(meetings);

        System.out.println(result);

        bufferedReader.close();
    }
}
