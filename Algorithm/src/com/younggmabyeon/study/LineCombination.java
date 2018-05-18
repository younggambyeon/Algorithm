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
 * 
 * 생각중...
 * 
 * 3! = 6
 * 
 * 우선, 3에서 나오는 패턴은 6개다. 4에서 나오는 패턴은 24개다. 노트에 3과 4의 팩토리얼을 나열해보니, 첫 번째 숫자에 규칙이 있는
 * 듯 싶다? 생각해보니, 패턴과 input 카운트로 첫 번째를 숫자를 알아내고, 그 뒤에 숫자들을 구하면 될 것 같다는 생각이 들었다.
 * 맞는지는 모르겠지만 우선 해보자..
 * 
 * 근데.. 어떻게하지...
 * 
 * 생각중...
 * 
 * 3을 기준으로 패턴은 6, 들어온 숫자는 5다.
 * 
 * 그렇다면 첫 번째는 3을 알아내야 한다. 우선 3은 첫 번째 숫자가 반복 패턴 2번이다.
 * 
 * 추측해보자. 5 / 2 = 2 + 나머지로 3을 만들어보자.
 * 
 * input / 첫 숫자 반복 패턴 = quotient(몫)
 * 
 * input % 첫 숫자 반복 패턴 = remainder(나머지)
 *
 * 첫 번째 숫자 = quotient + remainder
 * 
 * 실패다... 3은 되는데 4가 안된다..
 * 
 * 악!! 생각났다.. 이건 .. 올림으로 처리해보자. 나머지가 있을 때 1을 더 해준다.
 * 
 * 3 기준 => 5 / 2 = 2; 5 % 2 = 1;
 * 
 * 첫 번째 숫자 = quotient + result = (remainder > 0)? 1 : 0;
 * 
 * 4 기준 => 11 / 6 = 1; 11 % 6 = 5;
 * 
 * 첫 번째 숫자 = quotient + result = (remainder > 0)? 1 : 0;
 * 
 * 오오!! 된듯... 다음이 문제다... 어떻게 순서대로 바꾸지..
 * 
 * 헐.. 갑자기 이상한 규칙이 보인다. 4부터 두번째 숫자도 반복된다?
 * 
 * 5를 기준으로 노트에 적어보았다..120개의 패턴이 발생되고, 첫 번째 숫자만 24개의 패턴을 만든다. 두 번째 숫자는 패턴은 6개, 세
 * 번째 숫자는 패턴은 2개다. 그렇다면...이걸 어떻게 코드로 만들지..-.-;;
 * 
 * 생각중...
 * 
 * 3부터 반복 패턴을 구할 수 있다. 팩토리얼 결과 / n 으로
 * 
 * n이 3보다 클 때 3까지 하나 씩 줄이면서 패턴을 계산하자.
 *
 * 그리고 마지막 큰 숫자 2개만 스왑하면 될 것 같다..?
 * 
 * 못풀겠다.. 덜덜... 집가서 풀어야지
 *
 * 
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
