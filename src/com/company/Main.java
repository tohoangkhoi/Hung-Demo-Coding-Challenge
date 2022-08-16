package com.company;

import java.util.HashMap;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        solution();
    }

    public static int solution() {
        String[] t = {"codility1a", "codility3", "codility2", "codility4basdasd", "codility4a"};
        String[] r = {"Wrong answer", "OK", "OK", "Runtime error", "OK"};

        HashMap<String, Boolean> testHashMap = getTestCaseResults(t, r);
        int passCount = 0;

        for (String key: testHashMap.keySet()) {
            if (testHashMap.get(key)) {
                passCount++;
            }
        }

        return (int) Math.floor((passCount * 100)/testHashMap.size());
    }


    // get the task name, so we can remove it from the String later on
    private static String getTaskName(String sampleTaskName) {
        StringBuilder taskName = new StringBuilder();

        for (int i = 0; i < sampleTaskName.length(); i++) {
            char c = sampleTaskName.charAt(i);
            if (!Character.isDigit(c)) {
                taskName.append(c);
            } else {
                return taskName.toString();
            }
        }

        System.out.println(taskName);
        return taskName.toString();
    }


    // return an array containing processed test case (e.g.: [1, a, "wrong"])
    private static String[] processTestCase(String test, String result) {
        String[] returnResult = new String[3];
        StringBuilder testGroup = new StringBuilder();

        String task = getTaskName(test);
        String processedTaskName = test.replace(task, "");

        for (int i = 0; i < processedTaskName.length(); i++) {
            char c = processedTaskName.charAt(i);
            if (Character.isDigit(c)) {
                testGroup.append(c);
            } else {
                break;
            }
        }

        returnResult[0] = testGroup.toString();
        returnResult[1] = processedTaskName.replace(testGroup, "");
        returnResult[2] = result;

        return returnResult;
    }


    //return a map containing test group and result (e.g.: 1, fail)
    private static HashMap<String, Boolean> getTestCaseResults(String[] T, String[] R) {
        HashMap<String, Boolean> testMap = new HashMap<>();
        String[] eachTest;

        for (int i = 0; i < T.length; i++) {
            eachTest = processTestCase(T[i], R[i]);

            boolean eachTestResult = (Objects.equals(eachTest[2], "OK"));
            if (testMap.containsKey(eachTest[0])) {
                // if one of the test in the test group fail, skip
                if (!testMap.get(eachTest[0])) {
                    continue;
                }
            }
            testMap.put(eachTest[0], eachTestResult);
        }
        return testMap;
    }
}
