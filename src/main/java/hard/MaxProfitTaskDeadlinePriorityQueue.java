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



class Task{
    int deadline;
    int profit;

    Task(int deadline, int profit) {
        this.deadline = deadline;
        this.profit = profit;
    }
}
class Result {

    /*
     * Complete the 'maximizeParallelTaskProfit' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. INTEGER_ARRAY deadlines
     *  4. INTEGER_ARRAY profits
     */


    public static long maximizeParallelTaskProfit(int n, int m, List<Integer> deadlines, List<Integer> profits) {
        // Write your code here
        List<Task> tasks = new ArrayList<>();
        int maxDeadline = 0;

        // Build task list
        for (int i = 0; i < n; i++) {
            tasks.add(new Task(deadlines.get(i), profits.get(i)));
            maxDeadline = Math.max(maxDeadline, deadlines.get(i));
        }

        // Sort by deadline descending
        tasks.sort((a, b) -> Integer.compare(b.deadline, a.deadline));

        // Max Heap (highest profit first)
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        long totalProfit = 0;
        int index = 0;

        // Process each time slot from latest to earliest
        for (int time = maxDeadline; time >= 1; time--) {

            // Add all tasks whose deadline equals this slot
            while (index < n && tasks.get(index).deadline == time) {
                maxHeap.offer(tasks.get(index).profit);
                index++;
            }

            // Schedule at most m tasks
            for (int server = 0; server < m && !maxHeap.isEmpty(); server++) {
                totalProfit += maxHeap.poll();
            }
        }

        return totalProfit;

    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        int deadlinesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> deadlines = IntStream.range(0, deadlinesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int profitsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> profits = IntStream.range(0, profitsCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        long result = Result.maximizeParallelTaskProfit(n, m, deadlines, profits);

        System.out.println(result);

        bufferedReader.close();
    }
}
