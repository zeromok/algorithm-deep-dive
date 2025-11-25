package daniel.algorithmdeepdive.search.binary;

import java.util.Comparator;
import java.util.List;

/// # BinarySearchV3 - 제네릭 & 재사용성
///
/// ## V2 대비 개선 사항
/// - 제네릭 지원 (모든 Comparable 타입)
/// - Comparator 커스텀 비교
/// - Predicate 조건 기반 검색
/// - List 인터페이스 지원
public class BinarySearchV3 {

	/// ### Lower Bound (제네릭)
	/// @param list 정렬된 리스트
	/// @param target 기준 값
	/// @return target 이상인 첫 위치
	public static <T extends Comparable<T>> int lowerBound(List<T> list, T target) {
		if (list == null) {
			throw new IllegalArgumentException("list 는 null 일 수 없습니다.");
		}

		int left = 0;
		int right = list.size();

		while (left < right) {
			int mid = left + (right - left) / 2;
			if (list.get(mid).compareTo(target) >= 0) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return left;
	}

	/// ### Lower Bound (Comparator 지원)
	/// 커스텀 정렬 기준 사용 가능
	///
	/// @param list 정렬된 리스트
	/// @param target 기준 값
	/// @param comp 비교자
	/// @return target 이상인 첫 위치
	public static <T> int lowerBound(List<T> list, T target, Comparator<? super T> comp) {
		if (list == null) {
			throw new IllegalArgumentException("list 는 null 일 수 없습니다.");
		}
		if (comp == null) {
			throw new IllegalArgumentException("comparator 는 null 일 수 없습니다.");
		}

		int left = 0;
		int right = list.size();

		while (left < right) {
			int mid = left + (right - left) / 2;
			if (comp.compare(list.get(mid), target) >= 0) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}


	/// ### Upper Bound (제네릭)
	/// @param list 정렬된 리스트
	/// @param target 기준 값
	/// @return target 이상인 첫 위치
	public static <T extends Comparable<T>> int upperBound(List<T> list, T target) {
		if (list == null) {
			throw new IllegalArgumentException("리스트는 null 일 수 없습니다.");
		}

		int left = 0;
		int right = list.size();

		while (left < right) {
			int mid = left + (right - left) / 2;
			if (list.get(mid).compareTo(target) > 0) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return left;
	}

	/// ### Upper Bound (Comparator 지원)
	/// 커스텀 정렬 기준 사용 가능
	///
	/// @param list 정렬된 리스트
	/// @param target 기준 값
	/// @param comparator 비교자
	/// @return target 이상인 첫 위치
	public static <T> int upperBound(List<T> list, T target, Comparator<? super T> comparator) {
		if (list == null) {
			throw new IllegalArgumentException("list 는 null 일 수 없습니다.");
		}
		if (comparator == null) {
			throw new IllegalArgumentException("comparator 는 null 일 수 없습니다.");
		}

		int left = 0;
		int right = list.size();

		while (left < right) {
			int mid = left + (right - left) / 2;
			if (comparator.compare(list.get(mid), target) > 0) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	/// ### 범위 검색 (제네릭)
	/// start 이상 end 이하인 모든 원소 찾기
	///
	/// @param list 정렬된 리스트
	/// @param start 시작 값 (inclusive)
	/// @param end 끝 값 (inclusive)
	/// @param comparator 비교자
	/// @return 해당 범위의 부분 리스트
	public static <T> List<T> findRange(List<T> list, T start, T end, Comparator<? super T> comparator) {
		int startIdx = lowerBound(list, start, comparator);
		int endIdx = upperBound(list, end, comparator);

		if (startIdx >= list.size()) {
			return List.of();
		}

		return list.subList(startIdx, endIdx);
	}

	/// ### 개수 세기 (제네릭)
	/// @param list 정렬된 리스트
	/// @param target 셀 값
	/// @return target 의 개수
	public static <T extends Comparable<T>> int countOccurrences(List<T> list, T target) {
		int lower = lowerBound(list, target);

		if (lower == list.size() || list.get(lower).compareTo(target) != 0) {
			return 0;
		}

		int upper = upperBound(list, target);

		return Math.max(0, upper - lower);
	}
}
