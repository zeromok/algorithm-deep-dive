package daniel.algorithmdeepdive.pattern.search.binary;

public class BinarySearchMain {
	public static void main(String[] args) {
		int[] arr = new int[] {-10, -10, 2, 3, 3, 6, 7, 10, 10, 10};

		int targetIdx = BinarySearchV0.binarySearch(arr, -10);
		System.out.println("target의 인덱스: " + targetIdx);

		int targetOver = BinarySearchV0.lowerBound(arr, -10);
		System.out.println("target 이상인 값이 처음 나타나는 인덱스: " + targetOver);

		int targetExcess = BinarySearchV0.upperBound(arr, -10);
		System.out.println("target 초과인 값이 처음 나타나는 인덱스: " + targetExcess);

		int targetNum = BinarySearchV0.countOccurrences(arr, -10);
		System.out.println("target의 개수: " + targetNum);

	}
}
