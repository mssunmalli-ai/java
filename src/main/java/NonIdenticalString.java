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


//https://www.hackerrank.com/contests/software-engineer-prep-kit/challenges/check-non-identical-string-rotation/problem?isFullScreen=true
class Result {

    /*
     * Complete the 'isNonTrivialRotation' function below.
     *
     * The function is expected to return a BOOLEAN.
     * The function accepts following parameters:
     *  1. STRING s1
     *  2. STRING s2
     */

    public static boolean isNonTrivialRotation(String s1, String s2) {
        // Write your code here
        if (s1 == null || s2 == null) {
            return false;
        }

        if (s1.length() <= 1 || s1.length() != s2.length()) {
            return false;
        }

        // must not be identical
        if (s1.equals(s2)) {
            return false;
        }

        return (s1 + s1).contains(s2);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s1 = bufferedReader.readLine();

        String s2 = bufferedReader.readLine();

        boolean result = Result.isNonTrivialRotation(s1, s2);

        System.out.println(result ? 1 : 0);

        bufferedReader.close();
    }
}
