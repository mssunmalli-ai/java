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
     * Complete the 'findFirstOccurrence' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY nums
     *  2. INTEGER target
     */

    public static int findFirstOccurrence(List<Integer> nums, int target) {
        // Write your code here
        int low = 0;
        int high = nums.size() - 1;
        var resultIndex = -1;
        if (nums.size() >= 1) {
            while(low <= high) {
                var mid = low + (high - low)/2;
                if (nums.get(mid).equals(target)){
                    resultIndex = mid;
                    high = mid - 1;
                } else if(target > nums.get(mid)) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return resultIndex;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int numsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> nums = IntStream.range(0, numsCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int target = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.findFirstOccurrence(nums, target);

        System.out.println(result);

        bufferedReader.close();
    }
}

