## 냄새와 휴리스틱
#### 기교
- 재간 있게 부리는 기술이나 솜씨. 테크닉.
#### 휴리스틱
- 불충분한 시간이나 정보로 인하여 합리적인 판단을 할 수 없거나, 체계적이면서 합리적인 판단이 굳이 필요하지 않은 상황에서 사람들이 빠르게 사용할 수 있게 보다 용이하게 구성된 간편추론의 방법이다.

---

### 주석 (Comment)
#### 1. 부적절한 정보는 포함시키지 마라
- 주석은 코드와 설계에 기술적인 설명을 부연하는 수단이다.
- 스터디 질문: 작성자, 최종 수정일, SPR 번호도 주석에 포함 안시키는 것이 맞지 않은가?

#### 2. 쓸모 없는 주석을 작성하지 마라

#### 3. 중복된 주석을 작성하지 마라
- 주석은 코드만으로 다하지 못하는 설명을 부언한다.

#### 4. 성의 없는 주석은 안된다
- 당연한 소리를 반복하지 않는다.
- 간결하고 명료하게 작성한다.

#### 5. 주석 처리된 코드를 만들지도 말고 만약 있다면 제거하라
- 소스 코드 관리 시스템이 기억하니까.

---

### 환경 (Enviornment)
#### 1. 한 단계로 빌드해야 한다 (책 오타)
- 한 명령으로 전체를 체크아웃해서 한 명령으로 빌드할 수 있어야 한다.

#### 2. 한 단계로 테스트해야 한다 (책 오타)

---

### 함수 (Function)
#### 1. 너무 많은 인수는 안된다
- 함수에서 인수 개수는 작을수록 좋다.

#### 2. 출력 인수를 사용하지 마라
- 직관을 정면으로 위배한다.
- 일반적으로 독자는 인수는 입력으로 간주한다.

``` java
appendFooter(s);
```

``` java
report.appendFooter();
```

#### 3. 플래그 인수를 사용하지 마라

#### 4. 죽은 함수는 삭제하라
- 소스 코드 관리 시스템이 모두 기억하므로 걱정할 필요 없다.

---

### 일반 (General)
#### 1. 한 소스 파일에 여러 언어를 사용하지 않도록 노력하라
- 현실적으로는 여러 언어가 불가피하다.
- 하지만 각별한 노력을 기울여 소스 파일에서 언어 수와 범위를 최대한 줄이도록 애써야 한다.

#### 2. 당연한 동작을 예측할 수 있도록 구현해라
- 최소 놀람의 원칙(The Principle of Least Surprise)에 의거해 함수나 클래스는 다른 프로그래머가 당연하게 여길 만한 동작과 기능을 제공해야 한다.
- 당연한 동작을 구현하지 않으면 코드를 읽거나 사용하는 사람이 더 이상 함수 이름만으로 함수 기능을 직관적으로 예상하기 어렵다.
- 저자를 신뢰하지 못하므로 코드를 일일이 살펴야 한다.

#### 3. 경계를 올바로 처리해라
- 흔히 개발자들은 머릿속에서 코드를 돌려보고 끝낸다.
- 자신의 직관에 의존할 뿐 모든 경계와 구석진 곳에서 코드를 증명하려 애쓰지 않는다.
- 부지런함을 대신할 지름길은 없다.
- 스스로의 직관에 의존하지 마라.
- 모든 경계 조건을 찾아내고, 모든 경계 조건을 테스트하는 테스트 케이스를 작성하라.

#### 4. 안전 절차를 무시하지 마라
- 안전 절차를 무시하면 위험하다.

#### 5. 중복을 만들지 마라
- DRY(Don't Repeat Yourself) 원칙이라고도 부른다.
- 익스트림 프로그래밍의 핵심 규칙 중 하나로, 선언한 후 "한 번, 단 한번만(Once and only once)" 이라 명명했다.
- 코드에서 중복을 발견할 때마다 추상화할 기회로 간주하라.
- 중복된 코드를 하위 루틴이나 다른 클래스로 분리하라.
- 여러 모듈에서 일련의 switch/case나 if/else 문으로 똑같은 조건을 거듭 확인하는 중복이다.
  + 이런 중복은 다형성(polymorphism)으로 대체해야 한다.
    * http://www.tcpschool.com/java/java_polymorphism_concept
- 알고리즘이 유사하나 코드가 서로 다른 중복이다.
  + TEMPLATE METHOD 패턴을 사용한다.
    * https://coding-factory.tistory.com/712
  + SATRATEGY 패턴을 사용한다.
    * https://brownbears.tistory.com/574

- OO 역시 모듈을 정리하고 중복을 제거하는 전략이다.
  + 스터디 질문: OO는 뭐임 대체?

- 소감
  + 이래서 디자인 패턴 공부가 필요한 것 같다.
  + 구조적인 개선이 필요할 때 디자인 패턴을 알지 못하면 더 나은 발전이 어려운 것이다.
  + 우리가 평소에 작성하던 코드에도 이미 디자인 패턴이 적용되어 있는데 우리는 모르고 있었다.

#### 6. 추상화 수준을 올바르게 해라
- 추상화는 저차원 상세 개념에서 고차원 일반 개념을 분리한다.
  + 저차원은 Deep 하게 고차원은 General 하게
- 모든 저차원 개념은 파생 클래스에 넣고, 모든 고차원 개념은 기초 클래스에 넣는다.
- 기초 클래스는 구현 정보에 무지해야 마땅하다.
- 고차원 개념과 저차원 개념을 섞어서는 안 된다.

#### 7. 기초 클래스가 파생 클래스에 의존해서는 안된다
- 고차원 기초 클래스 개념을 저차원 파생 클래스 개념으로부터 분리해 독립성을 보장하기 위해서다.
- 기초 클래스는 파생 클래스를 아예 몰라야 마땅하다.
- 간혹 파생 클래스 개수가 확실히 고정되었다면 기초 클래스에 파생 클래스를 선택하는 코드가 들어간다.
  + 안드로이드에서 Navigation을 Flag에 따라서 해주는 부분이 생각이 나네요~
- 기초 클래스와 파생 클래스를 다른 JAR 파일로 배포하는 편이 좋다.
- 변경이 시스템에 미치는 영향이 아주 작아지므로 현장에서 시스템을 유지보수하기가 한결 수월하게 된다.

#### 8. 과도한 정보를 제공하지 마라
- 잘 정의된 모듈은 인터페이스가 아주 작다.
  + 결합도(의존도)가 낮음
- 부실하게 정의된 모듈은 인터페이스가 구질구질하다.
  + 결합도가 높음
- 클래스가 제공하는 메서드 수는 작을수록 좋다.
- 함수가 아는 변수 수도 작을수록 좋다.
  + 스터디 질문: 이전에 응집도를 알아봤을 때는 응집도가 높은게 좋다고 하지 않았었나?
- 정보를 제한해 결합도를 낮춰라.

#### 9. 죽은 코드를 제거해라

#### 10. 수직 분리를 지켜라
- 변수와 함수는 사용되는 위치에 가깝게 정의해라.

#### 11. 일관성을 유지하라
- 어떤 개념을 특정 방식으로 구현했다면 유사한 개념도 같은 방식으로 구현해라.

#### 12. 잡동사니를 없애라
- 죽은 코드와 비슷한 내용이다.

#### 13. 인위적 결합을 하지 마라
- enum이 클래스에 속한다면 enum을 사용하는 코드가 특정 클래스를 알아야만 한다.
- 뚜렷한 목적 없이 변수, 상수, 함수를 당장 편한 위치에 넣어버린 결과다.

#### 14. 기능 욕심을 부리지 마라
- 클래스 메서드는 자기 클래스의 변수와 함수에 관심을 가져야지 다른 클래스의 변수와 함수에 관심을 가져서는 안된다.
- 다만 예외 경우도 존재한다.

#### 15. 선택적 인수를 사용하지 마라

#### 16. 모호한 의도를 야기하지 마라
- 코드를 짤 때는 의도를 최대한 분명히 밝힌다.

#### 17. 코드 배치를 똑바로 하자 (책 오타)
- 중요한 결정 중 하나가 코드를 배치하는 위치이다.
- 때로는 개발자가 영리하게 기능을 배치한다, 독자에게 직관적인 위치가 아니라 개발자에게 편한 함수에 배치한다.

#### 18. 부적절한 static 함수를 만들지 말자
- 특정 인스턴스와 관련 없는 static 메서드는 괜찮다.
- 함수를 재정의 할 가능성이 있는 메서드는 static 메서드로 정의하지 마라.
- static 함수로 정의해야겠다면 재정의할 가능성은 없는지 꼼꼼히 따져본다.

#### 19. 서술적 변수를 많이 활용하라
- 프로그램 가독성을 높이는 효과적인 방법 중 하나가 계산을 여러 단계로 나누고 중간 값으로 서술적인 변수 이름을 사용하는 방법이다.
- 계산을 몇 단계로 나누고 중간값에 좋은 변수 이름만 붙여도 해독하기 어렵던 모듈이 순식간에 읽기 쉬운 모듈로 탈바꿈한다.

#### 20. 이름과 기능이 잋리하는 함수를 작성해라
- 이름만으로 분명하지 않기에 구현을 살피거나 문서를 뒤적여야 한다면 더 좋은 일므으로 바꾸거나 아니면 더 좋은 이름을 붙이기 쉽도록 기능을 정리해야 한다.

#### 21. 알고리즘을 이해하라
- 실제 알고리즘을 고민하는 대신 여기저기 if 문과 플래그를 넣어보며 코드를 돌리는 탓이다.
- 구현이 끝났다고 선언하기 전에 함수가 돌아가는 방식을 확실히 이해하는지 확인하라.
- 작성자가 알고리즘이 올바르다는 사실을 알아야 한다.

#### 22. 논리적 의존성은 물리적으로 드러내라
- 한 모듈이 다른 모듈에 의존한다면 물리적인 의존성도 있어야 한다.
- 의존하는 모듈이 상대 모듈에 대해 논리적으로 의존하면 안된다.
  + A가 B에 있어야 하는걸 자기 자신이 가지고 있을 때 이를 논리적 의존성이라 한다.
  + Ex) B의 나이 정보를 A에 저장시켜 두고 A가 이를 사용한다.
    * A는 B의 나이 정보가 필요하다고 하더라도 이를 A에 저장해놓고 사용할 것까지는 없을텐데
- 의존하는 모든 정보를 명시적으로 요청하는 편이 좋다.
  + A가 B에 있는 것을 필요로 해서 B의 어떤 것을 요청할 때 이를 물리적 의존성이라 한다.
  + Ex) B의 나이 정보를 B에 저장시켜 두고 A가 B에게 나이 정보를 요청한다.
    * B의 나이 정보는 B에 저장시켜 두되 A도 필요하니 이를 활용함 (getAge 메서드)

#### 23. If/Else 혹은 Switch/Case 문보다 다형성을 사용하라
- 대다수 개발자가 switch 문을 사용하는 이유는 그 상황에서 가장 올바른 선택이기 보다는 당장 손쉬운 선택이기 때문이다.
- 그러므로 다형성을 먼저 고려해봐라.

#### 24. 표준 표기법을 따르라

#### 25. 매직 숫자는 명명된 상수로 교체하라
- 코드에서 숫자를 사용하지 말라는 규칙이다.
  + 명명된 상수 뒤로 숨기라는 의미이다.
  + 단 예외 상황도 존재한다.

#### 26. 정확하라
- 결정을 내리는 이유와 예외를 처리할 방법을 분명히 알아야 한다.
- 코드에서 모호성과 부정확은 의견차나 게으름의 결과다.

#### 27. 관례보다 구조를 사용하라
- enum 변수가 멋진 switch/case 문보다 추상 메서드가 있는 기초 클래스가 더 좋다.
- switch/case 문을 매번 똑같이 구현하게 강제하기는 어렵지만, 파생 클래스는 추상 메서드를 모두 구현하지 않으면 안 되기 때문이다.

#### 28. 조건을 캡슐화하라

#### 29. 부정 조건은 피하라

#### 30. 함수는 한 가지만 해야 한다
- 스터디 질문: 예시에서 나온 내용은 너무 극단적이지 않나?

#### 31. 숨겨진 시각적인 결합을 지향하라
- 시간적인 결합을 숨겨서는 안된다.
- 일종의 연결 소자를 생성해 시간적인 결합을 노출한다.

#### 32. 일관성을 유지하라
- 구조에 일관성이 없어 보인다면 남들이 맘대로 바꿔도 괜찮다고 생각한다.

#### 33. 경계 조건을 캡슐화하라

#### 34. 함수는 추상화 수준을 한 단계만 내려가야 한다
- 함수 내 모든 문장은 추상화 수준이 동일해야 한다.
- 그리고 그 추상화 수준은 함수 이름이 의미하는 작업보다 한 단계만 낮아야 한다.

#### 35. 설정 정보는 최상위 단계에 둬라
- 추상화 최상위 단계에 둬야 할 기본값 상수나 설정 관련 상수를 저차원 함수에 숨겨서는 안된다.
- 설정 관련 변수는 나머지 코드에 인수로 넘긴다.

#### 36. 추이적 탐색을 피하라
- 일반적으로 한 모듈은 주변 모듈을 모를수록 좋다.
- 자신이 직접 사용하는 모듈만 알아야 한다는 뜻이다.
- 내가 아는 모듈이 연이어 자신이 아는 모듈을 따라가며 시스템 전체를 휘저을 필요가 없다는 의미다.

---

### 자바 (Java)
#### 1. 긴 import 목록을 피하고 와일드 카드를 사용하라
- 와이들카드로 패키지를 지정하면 특정 클래스가 존재할 필요는 없다.
- 그러므로 모듈 간에 결합성이 낮아진다.
- 다만 와이들카드 import 문은 때로 이름 충돌이나 모호성을 초래한다.
  + 이름이 같으나 패키지가 다른 클래스는 명시적인 import문을 사용하거나 아니면 코드에서 클래스를 사용할 때 전체 경로를 명시한다.

#### 2. 상수는 상속하지 않는다
- 언어의 범위 규칙을 속이는 행위다.

#### 3. 상수 대신 Enum을 사용해라

---

### 이름 (Name)
#### 1. 서술적인 이름을 사용하라
- 소프트웨어 가독성의 90%는 이름이 결정한다.
- 신중하게 선택한 이름을 보고 독자는 모듈 내 다른 함수가 하는 일을 짐작한다.

#### 2. 적절한 추상화 수준에서 이름을 선택해라
- 구현을 드러내는 이름은 피하라.
- 작성 대상 클래스나 함수가 위치하는 추상화 수준을 반영하는 이름을 선택하라.

#### 3. 가능하다면 표준 명명법을 사용하라
- 프로젝트에 유효한 의미가 담긴 이름을 많이 사용할수록 독자가 코드를 이해하기 쉬워진다.

#### 4. 명확한 이름을 지어라

#### 5. 긴 범위는 긴 이름을 사용하라
- 이름 길이는 범위 길이에 비례해야 한다.

#### 6. 인코딩을 피하라
- 중복된 정보이며 독자만 혼란하게 만든다.

#### 7. 이름으로 부수 효과를 설명하라
- 실제로 여러 작업을 수행하는 함수에다 동사 하나만 달랑 사용하면 곤란하다.

---

### 테스트 (Test)

#### 1. 불충분한 테스트는 지양하라
- 테스트 케이스는 잠재적으로 깨질 만한 부분을 모두 테스트해야 한다.
- 테스트 케이스가 확인하지 않는 조건이나 검증하지 않는 계산이 있다면 그 테스트는 불완전하다.

#### 2. 커버리지 도구를 사용하라
- 커버리지 도구는 테스트가 빠트리는 공백을 알려준다.

#### 3. 사소한 테스트를 건너뛰지 마라
- 사소한 테스트가 제공하는 문서적 가치는 구현에 드는 비용을 넘어선다.

#### 4. 무시한 테스트는 모호함을 뜻한다
- 불분명한 요구사항은 테스트 케이스를 주석으로 처리하거나 테스트 케이스에 @Ignore를 붙여 표현한다.
  + 주석보다는 @Ignore와 @DisplayName에 설명을 달아주면 좋겠다

#### 5. 경계 조건을 테스트 하라

#### 6. 버그 주변은 철저히 테스트하라
- 버그는 서로 모이는 경향이 있다.
- 한 함수에서 버그를 발견했다면 그 함수를 철저히 테스트하는 편이 좋다.

#### 7. 실패 패턴을 살펴라
- 합리적인 순서로 정렬된 꼼꼼한 테스트 케이스는 실패 패턴을 드러낸다.

#### 8. 테스트 커버리지 패턴을 살펴라
- 통과하는 테스트가 실행하거나 실행하지 않는 코드를 살펴보면 실패하는 테스트 케이스의 실패 원인이 드러난다.

#### 9. 테스트는 빨라야 한다.
- 느리면 실행하지 않게 된다.

---

### 결론
- 가치 체계를 피력하는 것이 목표였다.
- 일군의 규칙만 따른다고 깨끗한 코드가 얻어지지 않는다.
- 휴리스틱 목록을 익힌다고 소프트웨어 장인이 되지는 못한다.
- 전문가 정신과 장인 정신은 가치에서 나온다.
- 그 가치에 기반한 규율과 절제가 필요하다.
