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
     * Complete the 'searchRotatedTimestamps' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY nums
     *  2. INTEGER target
     */

    public static int searchRotatedTimestamps(List<Integer> nums, int target) {
        // Write your code here
        // System.out.println("nums"+nums);
        // System.out.println("Target"+target);
        if(nums.size() > 0){
            var low =0;
            var high = nums.size() -1;
            while(low <= high) {
                var mid = low + (high-low)/2;
                if(nums.get(mid) == target){
                    return mid;
                }
                if(nums.get(low) <= nums.get(mid)) {
                    //Left is sorted

                    if(target >= nums.get(low) && target <nums.get(mid)){
                        high = mid -1;
                    } else {
                        low = mid +1;
                    }
                } else{
                    //Right is sorted
                    if (target > nums.get(mid) && target <=nums.get(high)){
                        low = mid + 1;
                    } else {
                        high = mid -1;
                    }
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

        int result = Result.searchRotatedTimestamps(nums, target);

        System.out.println(result);

        bufferedReader.close();
    }
}
