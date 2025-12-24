# Algorithm Deep Dive

> **"문제를 풀다가 막혔을 때, 그 기법을 파고들기 위한 공간"**

## 💡 왜 이 프로젝트가 존재하는가

알고리즘 문제를 풀다 보면 특정 기법이 부족하다는 걸 깨닫는 순간이 온다. 그럴 때 온라인 저지에서 계속 문제를 풀기보다는, **그 기법 자체를 집중적으로 연습**하는 게 더 효율적이다.

이 프로젝트는 바로 그런 순간을 위한 **기법 집중 연습 환경**이다.

### 핵심 원칙

1. **본질 중심 학습**: 단순히 "동작하는 코드"가 아니라 **"왜 그렇게 동작하는지"** 이해하기
2. **반복 훈련**: 패턴을 몸에 익힐 때까지 의도적으로 반복 구현
3. **실행 흐름 추적**: Practice 클래스로 알고리즘의 각 단계가 어떻게 진행되는지 관찰
4. **독립적 실험**: 외부 문제에 구애받지 않고 순수하게 기법만 연습

## 🔄 사용 워크플로우

### 패턴 1: 기법 부족 → 집중 연습
```
1. 백준/기타 플랫폼에서 문제 풀이
          ↓
2. "이 기법이 약하다" 깨달음 (예: DP, 이분탐색)
          ↓
3. algorithm-deep-dive로 전환
          ↓
4. pattern/*.java 코드 읽기 → practice/*.java 실행 흐름 관찰
          ↓
5. 이해도가 올라간 상태로 문제 풀이 복귀
```

**실제 예시:**
```java
// 1. 이분탐색 문제에서 LowerBound 개념이 헷갈림
// 2. pattern/search/binary/LowerBound.java 코드 읽기
// 3. practice/LowerBoundPractice.java 실행
//    → "arr[mid] >= target"일 때 어떻게 움직이는지 단계별 관찰
// 4. "아! Lower는 이상(>=), Upper는 초과(>)구나" 체득
// 5. 원래 문제로 복귀해서 해결
```

### 패턴 2: 새 기법 첫 학습 → 대표 문제로 기록
```
1. 문제를 풀다가 처음 보는 알고리즘 기법 발견
          ↓
2. 그 기법을 배우며 문제 해결 (삽질 → 이해 → 최적화)
          ↓
3. 독립 레포 생성 + 블로그 작성 ("이 기법의 대표 문제")
          ↓
4. algorithm-deep-dive/pattern/에 순수 기법 패턴 추출
          ↓
5. practice/에 실험 클래스 작성 (실행 흐름 관찰용)
```

**실제 사례:**
- **처음 배운 기법**: 파라메트릭 서치
- **대표 문제**: [백준 1654 - 랜선 자르기](https://github.com/zeromok/ps-baekjoon-1654-parametric-search)
- **추출된 패턴**: [`Parametric.java`](src/main/java/daniel/algorithmdeepdive/pattern/search/binary/Parametric.java)
- **실험 클래스**: [`ParametricPractice.java`](src/main/java/daniel/algorithmdeepdive/pattern/search/binary/practice/ParametricPractice.java)

## 🗂️ 프로젝트 구조

```
src/main/java/daniel/algorithmdeepdive/
│
├── pattern/              # 완성된 알고리즘 패턴
│   ├── dp/              # Dynamic Programming
│   ├── bruteforce/      # 완전탐색 (조합, 순열)
│   ├── greedy/          # 그리디
│   ├── sort/            # 정렬
│   └── search/
│       └── binary/      # 이분탐색
│           ├── ExactSearch.java    # 정확히 일치
│           ├── LowerBound.java     # target 이상
│           ├── UpperBound.java     # target 초과
│           ├── Parametric.java     # 파라메트릭 서치
│           └── practice/           # 실행 흐름 관찰
│               ├── ExactSearchPractice.java
│               ├── LowerBoundPractice.java
│               └── ParametricPractice.java
│
├── dataStructure/       # 자료구조 직접 구현
│   ├── graph/          # 그래프 (인접행렬 vs 인접리스트)
│   └── heap/           # 힙과 우선순위 큐
│
└── mathematics/         # 수학 개념
    ├── directionvector/ # 방향벡터 (2D 그리드 탐색)
    ├── GCDAndLCM.java
    └── FloatingPoint.java
```

### 구조 설계 원칙

**pattern/**: 순수 패턴 코드 (재사용 가능)
- 주석 최소화 (이미 이해했다고 가정)
- 핵심 로직만 간결하게

**practice/**: 실행 흐름 관찰 (이해를 위한 실험)
- 단계별 trace 출력
- left, mid, right 변화 과정 시각화
- 패턴들의 차이점 비교 실험

## 📚 주요 학습 내용

### Binary Search (이분탐색)
- **핵심 질문**: "ExactSearch, LowerBound, UpperBound의 차이는?"
- **학습 포인트**: 
  - `left <= right` vs `left < right` 차이
  - `arr[mid] >= target` vs `arr[mid] > target` 한 글자 차이의 의미
  - Practice 클래스로 실행 흐름 직접 관찰

### Dynamic Programming
- **핵심 질문**: "Top-down(재귀+메모)과 Bottom-up(반복+테이블) 중 언제 무엇을 쓸까?"
- **학습 포인트**: 메모이제이션의 작동 원리, 상태 전이 과정 추적

### 그래프 자료구조
- **핵심 질문**: "인접행렬과 인접리스트, 왜 대부분 인접리스트를 쓸까?"
- **학습 포인트**: 메모리 효율성, 실제 문제에서의 V와 E의 크기

### 방향 벡터 (Direction Vector)
- **핵심 질문**: "상하좌우 이동을 왜 dx/dy 배열로 표현할까?"
- **학습 포인트**: 코드 중복 제거, BFS/DFS와의 결합

### 그래프 탐색 (DFS vs BFS)
- **핵심 질문**: "Stack 기반 DFS와 Queue 기반 BFS의 실행 흐름 차이는?"
- **학습 포인트**: 자료구조가 알고리즘 행동을 어떻게 결정하는지

## 🔥 Problem Solving Showcase

> 처음 배운 알고리즘 기법과 그 대표 문제

### Parametric Search (파라메트릭 서치)
- **대표 문제**: [백준 1654 - 랜선 자르기](https://github.com/zeromok/ps-baekjoon-1654-parametric-search)
- **핵심 개념**: 최적화 문제를 결정 문제로 변환하여 이분탐색 적용
- **학습 과정**:
  - 완전탐색 시도 (O(21조) - 시간초과)
  - 파라메트릭 서치 개념 학습
  - 결정 함수 설계 → 이분탐색 적용 (O(30))
  - int 오버플로우 해결 (long 사용)
- **📝 블로그**: [파라메트릭 서치란?](https://b-mokk.tistory.com/84)
- **🔗 패턴 코드**: [`Parametric.java`](src/main/java/daniel/algorithmdeepdive/pattern/search/binary/Parametric.java)
- **🔬 실험 클래스**: [`ParametricPractice.java`](src/main/java/daniel/algorithmdeepdive/pattern/search/binary/practice/ParametricPractice.java)

---

## 💭 학습 철학

> "패턴을 외우지 말고, 패턴이 왜 작동하는지 이해하라"

이 프로젝트는 알고리즘 "정답 코드 모음집"이 아니다.  
기법을 **몸에 익히고, 원리를 체득하기 위한 연습장**이다.

**Practice 클래스의 존재 이유:**
- 코드만 읽어서는 "왜?"를 알 수 없다
- 실행하며 변수가 어떻게 변하는지 눈으로 확인해야 체득된다
- 디버거 대신 trace 출력으로 흐름을 명확히 관찰한다
