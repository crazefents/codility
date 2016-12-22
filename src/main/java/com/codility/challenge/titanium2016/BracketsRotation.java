package com.codility.challenge.titanium2016;

import java.util.BitSet;

/**
 * https://codility.com/programmers/task/brackets_rotation/
 */
public class BracketsRotation {

    private static final char BRACKET_OPEN = '(';

    private static final char BRACKET_CLOSE = ')';

    public int solution(String s, int k) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        if (k < 0) {
            throw new IllegalArgumentException("Budget k is invalid");
        }

        final int n = s.length();
        final char[] chars = s.toCharArray();

        int result = 0;

        final BitSet modifications = new BitSet(n);

        int balance = 0;

        int detectionLeft = 0;
        int detectionRight = 0;
        int detectionCost = 0;

        while (result < n - detectionLeft) {
            int budget = k - detectionCost;

            for (int p = detectionRight; p < n; p++) {
                final char c = chars[p];

                if (c == BRACKET_OPEN) {
                    if (balance + 1 <= n - p - 1) {
                        balance++;
                    } else if (budget > 0) {
                        modifications.set(p);
                        chars[p] = BRACKET_CLOSE;
                        balance--;
                        budget--;
                    } else {
                        break;
                    }
                } else if (c == BRACKET_CLOSE) {
                    if (balance > 0) {
                        balance--;
                    } else if (budget > 0) {
                        modifications.set(p);
                        chars[p] = BRACKET_OPEN;
                        balance++;
                        budget--;
                    } else {
                        break;
                    }
                } else {
                    throw new IllegalArgumentException("Unknown char: " + c);
                }

                if (balance == 0) {
                    detectionRight = p + 1;
                    detectionCost = k - budget;
                    result = Math.max(result, detectionRight - detectionLeft);
                }
            }
        }

        return result;
    }


}