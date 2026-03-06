import java.util.*;

class TaskAssignment {

    public int minCost(int[][] cost) {

        int n = cost.length;
        int size = 1 << n;

        int[] dp = new int[size];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for(int mask=0; mask<size; mask++) {

            int task = Integer.bitCount(mask);

            for(int worker=0; worker<n; worker++) {

                if((mask & (1<<worker)) == 0) {

                    int newMask = mask | (1<<worker);

                    dp[newMask] = Math.min(
                            dp[newMask],
                            dp[mask] + cost[task][worker]
                    );
                }
            }
        }

        return dp[size-1];
    }
}
