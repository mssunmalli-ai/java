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
     * Complete the 'computeMaxRectangleAreaWithOneRemoval' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY heights as parameter.
     */

    public static long computeMaxRectangleAreaWithOneRemoval(List<Integer> heights) {
        // Write your code here
        Stack<Integer> stack = new Stack<>();
        long maxArea = 0;

        for (int i = 0; i <= heights.size(); i++) {

            int currentHeight = (i == heights.size()) ? 0 : heights.get(i);

            while (!stack.isEmpty() &&
                    currentHeight < heights.get(stack.peek())) {

                int height = heights.get(stack.pop());

                int width;

                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }

                maxArea = Math.max(maxArea, (long) height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int heightsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> heights = IntStream.range(0, heightsCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        long result = Result.computeMaxRectangleAreaWithOneRemoval(heights);

        System.out.println(result);

        bufferedReader.close();
    }
}

