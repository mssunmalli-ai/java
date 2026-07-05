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
     * Complete the 'binarySearch' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY nums
     *  2. INTEGER target
     */

    public static int binarySearch(List<Integer> nums, int target) {
        // Write your code here
        // Binary Search
        //mid value in the required index that should be returned.
        if (nums.size() > 0){
            var low = 0;
            var high = nums.size() - 1;
            while (low <= high) {
                var mid = low + (high-low)/2; // This is to avoid large numbers. If it was low+high/2, low + high might end up in overflow error for int.
                if (nums.get(mid).equals(target)){
                    return mid;
                } else if(target > nums.get(mid)){ //This should be nums.get(mid)
                    low = mid+1;
                } else {
                    high = mid-1;
                }
            }
        }
        return -1;
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

        int result = Result.binarySearch(nums, target);

        System.out.println(result);

        bufferedReader.close();
    }
}
