package com.younggmabyeon.study;

import java.util.Arrays;

/**
 * 문제 level - 5
 * ======================================--------------=====================================
 * 
 * N명의 사람이 있을 때, N명의 사람을 서로 다른 방법으로 줄을 세우는 방법은 N!개가 존재합니다.
 * 
 * 이 때 각각의 사람들에게 번호를 매겨서 줄을 서는 방법을 표시합니다. 예를들어 [1,2,3,4]는 1번 사람이 제일 앞에, 2번 사람이
 * 2두번째에... 서 있는 상태를 나타냅니다.
 * 
 * 이러한 각각의 방법을 사전순으로 정렬했을때 K번째 방법으로 줄을 세우는 방법을 찾아 보려고 합니다.
 * 
 * 예를 들어 3명의 사람이 있다면 총 6개의 방법은 다음과 같이 정렬할 수 있습니다.
 * 
 * 1번째 방법은 [1,2,3] 2번째 방법은 [1,3,2] 3번째 방법은 [2,1,3] 4번째 방법은 [2,3,1] 5번째 방법은
 * [3,1,2] 6번째 방법은 [3,2,1] 이 때 K가 5이면 [3,1,2]가 그 방법입니다.
 * 
 * 사람의 수 N과 순서 K를 입력받아 K번째 방법으로 줄을 세우는 setAlign 함수를 완성해 보세요. 예를 들어
 * setAlign(3,5)를 입력받는다면 [3,1,2]를 리턴해주면 됩니다.
 *
 * ======================================--------------=====================================

 */
public class LineCombination {

	public int[] setAlign(int n, long k) {

		int[] arrays = new int[n];
		int[] answer = new int[n];

		for (int i = 1; i <= n; i++) {
			arrays[i - 1] = i;
		}

		int firstNumber = getPatternCount(n, k);

		int answerIndex = 0;
		answer[answerIndex] = firstNumber;

		int remainderCount = n - 1;

		for (int i = remainderCount; i > 1; i--) {
			answerIndex += 1;

			if (i < 3) {
				int big = 0;
				int small = 0;
				int temp = 0;

				for (int j = 0; j < arrays.length; j++) {
					if (arrays[j] == firstNumber) {
						arrays[j] = 0;
					}

					int value = arrays[j];

					if (value > 0) {
						if (small == 0)
							small = value;

						if (small > 0)
							big = value;
					}

					if (big < small) {
						temp = big;
						big = small;
						small = temp;
					}
				}

				if (k % 2 == 0) {
					// 큰수 먼저
					answer[answerIndex] = big;
					answer[answerIndex + 1] = small;
					break;

				} else {
					// 작은 수 먼저
					answer[answerIndex] = small;
					answer[answerIndex + 1] = big;
					break;
				}

			} else {
				int index = getPatternCount(i, k);

				answer[answerIndex] = arrays[index];
				arrays[index] = 0;
			}

		}

		return answer;
	}

	public int getPatternCount(int n, long k) {
		int factorial = getFactorial(n);
		int firstPatternCount = factorial / n;

		int quotient = (int) (k / firstPatternCount);
		int remainder = (int) (k % firstPatternCount);

		return remainder > 0 ? quotient + 1 : quotient;
	}

	public int getFactorial(int n) {
		int factorial = 1;

		for (int i = 2; i <= n; i++) {
			factorial = factorial * i;
		}

		return factorial;
	}

	// 아래는 테스트로 출력해 보기 위한 코드입니다.
	public static void main(String[] args) {
		LineCombination lc = new LineCombination();
		System.out.println(Arrays.toString(lc.setAlign(8, 26851 )));
	}
}
