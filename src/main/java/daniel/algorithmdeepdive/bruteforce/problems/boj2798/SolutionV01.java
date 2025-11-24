package daniel.algorithmdeepdive.bruteforce.problems.boj2798;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SolutionV01 {

	public static void main(String[] args) throws Exception {
		solution();
	}
	
	public static void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] cards = new int[N];
		StringTokenizer cardNums = new StringTokenizer(br.readLine());
		for (int i = 0; i < cards.length; i++) {
			cards[i] = Integer.parseInt(cardNums.nextToken());
		}

		int resultSum = getResultSum(cards, M, 0, 0, 0);
		bw.write(resultSum + "");

		br.close();
		bw.flush();
		bw.close();
	}

	private static int getResultSum(int[] arr, int target, int start, int depth, int curr) {
		if (depth == 3) {
			if (curr <= target) {
				return curr;
			}
			return 0;
		}

		if (start >= arr.length) {
			return 0;
		}

		int maxSum = 0;
		for (int i = start; i < arr.length; i++) {
			int sum = getResultSum(arr, target, i + 1, depth + 1, curr + arr[i]);
			maxSum = Math.max(maxSum, sum);
		}

		return maxSum;
	}

}
