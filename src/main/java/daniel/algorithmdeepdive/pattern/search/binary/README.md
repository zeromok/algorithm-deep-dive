# Binary Search Patterns

> 이분탐색의 핵심 패턴 4가지

## 패턴 선택 가이드

| 상황 | 사용 패턴 | 예시 |
|------|----------|------|
| "X가 존재하나?" | `ExactSearch` | 백준 1920 |
| "X 이상인 첫 위치?" | `LowerBound` | 삽입 위치 찾기 |
| "X 초과인 첫 위치?" | `UpperBound` | 범위 끝점 |
| "조건 만족 최댓값?" | `Parametric` | 백준 1654 |

## 핵심 차이점

### 종료 조건
```java
// ExactSearch
while (left <= right)  // <= 사용

// LowerBound, UpperBound
while (left < right)   // < 사용
```

### 비교 조건
```java
// LowerBound: 이상
if (arr[mid] >= target)

// UpperBound: 초과
if (arr[mid] > target)  // 부등호만 다름!
```

## 학습 방법

### 1단계: 패턴 읽기
각 `.java` 파일의 코드 이해

### 2단계: 실행 흐름 관찰
`practice/` 폴더의 실험 클래스 실행

```bash
# ExactSearch 흐름 관찰
ExactSearchPractice.main()

# LowerBound vs UpperBound 비교
LowerBoundPractice.main()

# Parametric Search 동작 원리
ParametricPractice.main()
```

### 3단계: 직접 실험
practice 클래스를 수정하며 다양한 케이스 테스트

## 파일 구조

```
binary/
├── ExactSearch.java              # 정확히 일치하는 값
├── LowerBound.java                # target 이상
├── UpperBound.java                # target 초과
├── Parametric.java                # 최적화 문제
│
└── practice/                      # 실험장
    ├── ExactSearchPractice.java  # 단계별 trace
    ├── LowerBoundPractice.java   # Lower vs Upper 비교
    └── ParametricPractice.java   # 결정 함수 관찰
```

## 패턴 조합

복잡한 쿼리는 여러 패턴 조합:

```java
// 특정 값의 개수
int count = UpperBound.search(arr, target) - LowerBound.search(arr, target);

// 범위 찾기
int start = LowerBound.search(arr, target);
int end = UpperBound.search(arr, target) - 1;
```
