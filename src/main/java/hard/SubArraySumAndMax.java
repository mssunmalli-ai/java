package main.java.hard;

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
     * Complete the 'countSubarraysWithSumAndMaxAtMost' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY nums
     *  2. LONG_INTEGER k
     *  3. LONG_INTEGER M
     */

    public static long countSubarraysWithSumAndMaxAtMost(List<Integer> nums,
                                                         long k,
                                                         long M) {

        int subArrayStartIndex = 0;
        long numOfConSubArray = 0;

        while (subArrayStartIndex < nums.size()) {

            long sumSubArray = 0;
            long maxSubArray = Long.MIN_VALUE;

            for (int i = subArrayStartIndex; i < nums.size(); i++) {

                sumSubArray += nums.get(i);

                if (nums.get(i) > maxSubArray) {
                    maxSubArray = nums.get(i);
                }

                if (sumSubArray == k && maxSubArray <= M) {
                    numOfConSubArray++;
                }
            }

            subArrayStartIndex++;
        }

        return numOfConSubArray;
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

        long k = Long.parseLong(bufferedReader.readLine().trim());

        long M = Long.parseLong(bufferedReader.readLine().trim());

        long result = Result.countSubarraysWithSumAndMaxAtMost(nums, k, M);

        System.out.println(result);

        bufferedReader.close();
    }
}
