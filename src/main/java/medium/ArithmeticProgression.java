package main.java.medium;

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
     * Complete the 'findLongestArithmeticProgression' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY arr
     *  2. INTEGER k
     */
    public static int findLongestArithmeticProgression(List<Integer> arr, int k) {
        // System.out.println("Sequence "+k);
        // System.out.println("Arr "+arr);
        // Write your code here
        if(arr.size()>0) {
            List<Integer> unique = arr.stream()
                    .distinct()
                    .collect(Collectors.toList());
            Collections.sort(unique);
            List<List<Integer>> result = new ArrayList<>();
            for (int i=0; i<unique.size(); i++){
                var pos = i;
                var startElement = unique.get(i);
                List<Integer> subSeq = new ArrayList<>();
                subSeq.add(startElement);
                for (int j=pos; j<unique.size(); j++){
                    var nextElement = j+k;
                    if(unique.contains(nextElement)) {
                        subSeq.add(nextElement);
                    }
                }
                result.add(subSeq);
            }
            List<Integer> seqSize = new ArrayList<Integer>();
            for (List<Integer> seq: result){
                seqSize.add(seq.size());
            }
            return Collections.max(seqSize);
        } else return 0;
    }
    //Method B
    public static int findLongestArithmeticProgression(List<Integer> arr, int k) {

        if (arr == null || arr.isEmpty()) {
            return 0;
        }

        Set<Integer> set = new HashSet<>(arr);

        int longest = 0;

        for (int num : set) {

            // Skip if this is not the start of a progression
            if (set.contains(num - k)) {
                continue;
            }

            int current = num;
            int length = 1;

            while (set.contains(current + k)) {
                current += k;
                length++;
            }

            longest = Math.max(longest, length);
        }

        return longest;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.findLongestArithmeticProgression(arr, k);

        System.out.println(result);

        bufferedReader.close();
    }
}
