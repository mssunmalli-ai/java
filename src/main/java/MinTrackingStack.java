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
     * Complete the 'processCouponStackOperations' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY operations as parameter.
     */



    public static List<Integer> processCouponStackOperationsTrail1(List<String> operations) {
        // Write your code here
        Stack<Integer> requiredStack = new Stack<Integer>();
        List<Integer> result = new ArrayList<Integer>();
        Integer min = Integer.MAX_VALUE;
        for (String op: operations){
            // System.out.println("Operation:"+op);
            if (op.contains("push")){
                // System.out.println("Pushing.....");
                String digits = op.replaceAll("\\D", "");
                Integer numInOp = Integer.valueOf(digits);
                requiredStack.push(Integer.valueOf(digits));
                if(numInOp < min){
                    // System.out.println("Min Set to :"+ numInOp);
                    min = numInOp;
                    // System.out.println("Min Set to :"+ min);
                }
            } else if(op.contains("pop")) {
                if (!requiredStack.isEmpty()) {
                    int popedNum = requiredStack.pop();
                    if (popedNum == min){
                        min = requiredStack.peek();
                    }
                }
            } else if(op.contains("getMin")) {
                result.add(min);
            } else if(op.contains("top")) {
                result.add(requiredStack.peek());
            }
        }
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int operationsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> operations = IntStream.range(0, operationsCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        List<Integer> result = Result.processCouponStackOperationsTrail1(operations);

        System.out.println(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
        );

        bufferedReader.close();
    }
}

