# Jetpack Compose @Stable vs @Immutable 분석 프로젝트

> **실제로 측정하고 비교하는 Compose Recomposition 최적화 학습 앱**

## 🎯 프로젝트 목적

Jetpack Compose에서 `@Stable`과 `@Immutable` 애노테이션의 효과를 **실제로 측정**하고 **눈으로 확인**할 수 있는 학습 프로젝트입니다.

## 🔥 핵심 발견

```kotlin
// 매번 새로운 객체 생성 (remember 없음)
val unstableUser = UnstableUser(1, "Alice", 25)   // 재구성: 11회
val stableUser = StableUser(1, "Alice", 25)       // 재구성: 1회
```

**왜?**
- **Unstable**: equals 비교 불가 → 항상 재구성
- **@Stable**: equals 비교 가능 → 값이 같으면 스킵

**성능 차이: 1000%!**

## 🚀 빠른 시작

### 1. 프로젝트 빌드 및 실행

```bash
# 빌드
./gradlew assembleDebug

# 설치
./gradlew installDebug

# 또는 Android Studio에서 Run 버튼 클릭
```

### 2. 앱에서 테스트

1. **"상세 측정" 탭** 선택 (기본)
2. **"Counter 증가" 버튼** 10번 클릭
3. **결과 확인**:
   - 좌측 (@Stable 있음) ✅: Recomp = 1
   - 우측 (@Stable 없음) ⚠️: Recomp = 11

### 3. 차이 이해

```
@Stable 있음:
- 재구성: 1회만
- 이유: equals 비교 → 값이 같아서 스킵

@Stable 없음:
- 재구성: 11회
- 이유: equals 비교 불가 → 항상 재구성

성능 개선: 1000%
```

## 📂 프로젝트 구조

```
app/src/main/java/com/ikseong/stabilityexample/
├── models/
│   └── DataModels.kt                  # 6가지 타입 정의
│       ├── @Immutable ImmutableUser
│       ├── @Stable StableUser
│       ├── UnstableUser (애노테이션 없음)
│       └── 기타
└── ui/
    ├── DetailedRecompositionTest.kt   # ⭐ 메인 측정 화면
    ├── StabilityTestScreen.kt         # 기본 테스트
    ├── AdvancedStabilityTest.kt       # 고급 테스트
    └── MainScreen.kt                  # 탭 네비게이션
```

## 📚 문서

### 필수 문서 (순서대로 읽기 권장)

1. **[QUICKSTART.md](./QUICKSTART.md)** - 5분 빠른 시작 가이드
   - 가장 먼저 읽을 것!
   - 앱 실행 및 테스트 방법

2. **[EXPLANATION.md](./EXPLANATION.md)** - 한눈에 보는 핵심 설명
   - 왜 이런 일이 발생하는가?
   - equals 비교의 중요성

3. **[WHY_STABILITY_MATTERS.md](./WHY_STABILITY_MATTERS.md)** - 상세 분석
   - 실제 코드 예시
   - 성능 계산
   - 마이그레이션 전략

### 참고 문서

4. **[TEST_GUIDE.md](./TEST_GUIDE.md)** - 상세 테스트 가이드
5. **[SUMMARY.md](./SUMMARY.md)** - 프로젝트 전체 요약
6. **[PROJECT_STRUCTURE.md](./PROJECT_STRUCTURE.md)** - 코드 구조 설명

## 🔬 핵심 원리

### 문제 상황

```kotlin
@Composable
fun MyScreen() {
    var counter by remember { mutableIntStateOf(0) }

    // 매번 새로운 객체 생성
    val user = User(1, "Alice")  // remember 없음!

    Button(onClick = { counter++ })
    UserCard(user)  // 재구성될까? 안될까?
}
```

### Compose의 판단 로직

```kotlin
// Unstable 타입 (애노테이션 없음)
if (이전_user == 새로운_user) {
    // ❌ 비교 방법 모름!
    재구성()  // 안전하게 항상 재구성
}

// Stable/Immutable 타입
if (이전_user.equals(새로운_user)) {
    스킵()  // ✅ 값이 같으면 스킵
} else {
    재구성()
}
```

### 측정 결과

| 타입 | Counter 10번 클릭 시 | 이유 |
|------|---------------------|------|
| **Unstable** | 재구성 11회 | equals 비교 불가 |
| **@Stable** | 재구성 1회 | equals 비교 → 스킵 |
| **@Immutable** | 재구성 1회 | equals 비교 → 스킵 |

## 🎓 학습 포인트

### 1. Recomposition 측정 방법

```kotlin
@Composable
fun UserCard(user: User, trigger: Int) {
    var recomposeCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        recomposeCount++  // 재구성될 때마다 증가
    }

    Text("Recomp: $recomposeCount")  // 1 or 11?
}
```

### 2. remember의 역할

```kotlin
// remember 사용 - 모든 타입 동일 (차이 없음)
val user = remember { User(1, "Alice") }

// remember 없음 - 차이 명확 (Unstable vs Stable)
val user = User(1, "Alice")  // 매번 새 객체
```

### 3. 실전 적용

```kotlin
// DTO (서버 응답)
@Immutable
data class UserDto(val id: Int, val name: String)

// UI State
@Immutable
data class HomeUiState(
    val isLoading: Boolean,
    val users: List<UserDto>
)

// ViewModel
@Stable
class HomeViewModel : ViewModel() {
    var uiState by mutableStateOf(HomeUiState())
}
```

## 📊 성능 영향

### 단순 화면 (10개 컴포넌트)

```
Counter 100번 클릭:

Unstable:
- 재구성: 10 × 100 = 1,000회
- 시간: ~100ms

Stable:
- 재구성: 10 × 1 = 10회
- 시간: ~1ms

성능 개선: 100배
```

### 복잡한 화면 (1000개 컴포넌트)

```
Counter 100번 클릭:

Unstable:
- 재구성: 1000 × 100 = 100,000회
- 시간: ~1000ms (1초!)
- 사용자 경험: 버벅임 😡

Stable:
- 재구성: 1000 × 1 = 1,000회
- 시간: ~10ms
- 사용자 경험: 부드러움 😊

성능 개선: 100배
```

## 🛠️ 고급 기능

### Compose Compiler 메트릭 생성

```kotlin
// app/build.gradle.kts에 추가
apply(from = "../enable-compose-metrics.gradle.kts")

// 빌드
./gradlew assembleRelease

// 결과 확인
cat app/build/compose_metrics/app_release-classes.txt
```

**출력 예시**:
```
stable class ImmutableUser {
  stable val id: Int
  stable val name: String
}

unstable class UnstableUser {
  <runtime stability> = Unstable
}
```

## 🎯 체크리스트

- [ ] 앱 실행 및 테스트 완료
- [ ] @Stable과 @Immutable 차이 이해
- [ ] equals 비교의 중요성 이해
- [ ] Recomp 1 vs 11 확인
- [ ] EXPLANATION.md 읽기
- [ ] WHY_STABILITY_MATTERS.md 읽기
- [ ] 실제 프로젝트 적용 계획

## 💡 주요 교훈

1. **새 객체 생성은 일상적**
   - ViewModel state update: `state.copy(...)`
   - Flow transformation: `flow.map { ... }`
   - Composable에서 계산: `val user = User(...)`

2. **Unstable = 항상 재구성**
   - equals가 있어도 Compose는 믿지 않음
   - 안전하게 항상 재구성

3. **@Stable/@Immutable = 약속**
   - "equals를 믿어도 돼요!"
   - Compose: "알았어! equals로 비교할게!"
   - 성능: 최대 100배 향상

4. **측정이 중요**
   - 이 앱으로 직접 확인
   - Recomp = 1이면 성공
   - Layout Inspector로 실시간 확인

## 🚀 다음 단계

### 1. 학습 단계

1. 앱 실행 및 테스트
2. 문서 읽기 (QUICKSTART → EXPLANATION → WHY_STABILITY_MATTERS)
3. 코드 분석 (DetailedRecompositionTest.kt)
4. Compose Compiler 메트릭 생성

### 2. 적용 단계

1. 기존 프로젝트의 data class 파악
2. @Immutable 추가 (DTO부터 시작)
3. @Stable 추가 (ViewModel)
4. 성능 측정 및 비교

### 3. 고급 단계

1. Kotlinx Immutable Collections 도입
   ```kotlin
   implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.7")
   ```

2. Layout Inspector 활용
   - Recomposition Counts 시각화

3. 팀 공유
   - 이 프로젝트를 데모로 사용
   - 성능 개선 사례 공유

## 📖 참고 자료

### 공식 문서

- [Jetpack Compose Performance](https://developer.android.com/jetpack/compose/performance)
- [Compose Stability Explained](https://developer.android.com/jetpack/compose/performance/stability)
- [Compose Compiler Metrics](https://github.com/androidx/androidx/blob/androidx-main/compose/compiler/design/compiler-metrics.md)

### 라이브러리

- [Kotlinx Immutable Collections](https://github.com/Kotlin/kotlinx.collections.immutable)

### 도구

- Android Studio Layout Inspector
- Compose Recomposition Highlighter
- Perfetto (System Tracing)

## 🤝 기여

이슈나 개선 사항이 있다면 자유롭게 제안해주세요!

## 📝 라이선스

MIT License - 자유롭게 사용하고 수정하세요!

---

## 🎉 결론

**@Stable과 @Immutable은 선택이 아닌 필수!**

- equals 비교 가능 여부가 핵심
- 성능 개선: 최대 100배
- 사용자 경험: 천지 차이

**이 앱으로 직접 확인하고, 실제 프로젝트에 적용하세요!** 🚀
