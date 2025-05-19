package com.hanteo.board.problem2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class Problem2Test {

    public int solution(int[] coins, int sum) {
        // dp[i]는 합계 i를 만들 수 있는 방법의 수
        int[] dp = new int[sum + 1];

        // 합계가 0인 경우는 아무 동전도 선택하지 않는 방법 1가지
        dp[0] = 1;

        // 각 동전에 대해 dp 배열 갱신
        for (int coin : coins) {
            // 현재 동전부터 목표 합계까지 순회
            for (int i = coin; i <= sum; i++) {
                // 현재 동전을 사용하여 i-coin 합계를 만든 방법의 수를 더함
                dp[i] += dp[i - coin];
            }
        }

        // 최종적으로 목표 합계를 만드는 방법의 수 반환
        return dp[sum];
    }

    @Test
    void test1() {
        int answer1 = solution(new int[]{1, 2, 3}, 4);
        int answer2 = solution(new int[]{2, 5, 3, 6}, 10);

        assertThat(answer1).isEqualTo(4);
        assertThat(answer2).isEqualTo(5);
    }
}
