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
     * Complete the 'areBracketsProperlyMatched' function below.
     *
     * The function is expected to return a BOOLEAN.
     * The function accepts STRING code_snippet as parameter.
     */

    public static boolean areBracketsProperlyMatched(String code_snippet) {
        // Write your code here
        Map<Character, Character> pairs = new HashMap<Character, Character>();
        pairs.put('{', '}');
        pairs.put('(', ')');
        pairs.put('[', ']');
        Stack<Character> braces = new Stack<Character>();
        for(int i=0; i<=code_snippet.length() -1; i++){
            var ch = code_snippet.charAt(i);
            if(pairs.containsKey(ch)){
                braces.add(ch);
            } else if (pairs.containsValue(ch)){
                if (braces.isEmpty()){
                    return false;
                }
                var stackKey = braces.pop();
                if(pairs.get(stackKey).charValue() != ch){
                    return false;
                }
            }
        }
        if (braces.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String code_snippet = bufferedReader.readLine();

        boolean result = Result.areBracketsProperlyMatched(code_snippet);

        System.out.println(result ? 1 : 0);

        bufferedReader.close();
    }
}

