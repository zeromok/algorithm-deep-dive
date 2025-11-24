package daniel.algorithmdeepdive.bruteforce.problems.boj2798;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SolutionV00 {

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

		int resultSum = getResultSum(cards, M);
		bw.write(resultSum + "");

		br.close();
		bw.flush();
		bw.close();
	}

	private static int getResultSum(int[] cards, int m) {
		int resultSum = 0;
		for (int i = 0; i < cards.length - 2; i++) {
			for (int j = i + 1; j < cards.length - 1; j++) {
				for (int k = j + 1; k < cards.length; k++) {
					int cardSum = cards[i] + cards[j] + cards[k];
					if (resultSum < cardSum && cardSum <= m) {
						resultSum = cardSum;
					}
				}
			}
		}
		return resultSum;
	}

}
