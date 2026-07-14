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
     * Complete the 'maxDistinctSubstringLengthInSessions' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING sessionString as parameter.
     */

    public static int maxDistinctSubstringLengthInSessions(String sessionString) {
        // Write your code here
        String [] sessions = sessionString.split("\\*");
        int maxlength = 0;
        for (String subSession: sessions) {
            Set <Character> chars = new HashSet<Character>();
            int left = 0;
            for (int right=0; right<subSession.length(); right++) {
                char c = subSession.charAt(right);
                if  (chars.contains(c)) {
                    chars.remove(c);
                    left++;
                }
                chars.add(c);
                maxlength = Math.max(maxlength, (right-left) +1);
            }
        }
        return maxlength;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String sessionString = bufferedReader.readLine();

        int result = Result.maxDistinctSubstringLengthInSessions(sessionString);

        System.out.println(result);

        bufferedReader.close();
    }
}
