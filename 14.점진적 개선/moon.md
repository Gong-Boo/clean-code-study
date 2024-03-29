# 점진적인 개선

- 이번 챕터는 점진적은 개선을 보여주는 사례 연구다.

## Args 구현
- Args는 명령행 인수의 구문을 분석하는 유틸리니티이다.
- 이처럼 단순한 개념을 구현하는데 코드가 너무 많이 필요해 놀랄지도 모르겠다. 한가지 이유라면 자바를 사용한 탓 이다.
- 루비, 파이썬, 스몰토크 등과 같은 언어를 사용했다면 프로그램이 작아졌을것이다.

### 어떻게 짰느냐고?
- 깨끗하고 우아한 코드는 한 방에 뚝딱 내놓으리라 기대하지 않는다.
- 프로그래밍 과학보다 공예에 가깝다. 깨끗한 코드를 짜려면 먼저 지저분한 코드를 짠 뒤에 정리해야 한다는 의미다.

## Atgs: 1차 초안
- 맨 처음 짰던 Args클래스는 코드는 돌아가지만 엉망이다.
- 위에 코드는 명백히 미완성이다. 인스턴스 변수 개수만도 압도적이다.
- TILT와 같은 희한한 문자열, HashSets와 TreeSets, try-catch-catch 블록등 모두가 지저분한 코드에 기여하는 요인이다.
- 처음부터 지저분한 코드를 짜려는 생각은 없었다. 하지만 어느 순간 프로그램은 내 손을 벗어났다.
- Boolean만 지원했던 초기 버전은 이만큼 엉망 이지 않았다.
- 하지만 String과 Intenger라는 인수 유형 두개만 추가했을뿐인데 코드가 엄청나게 지전분해졌다.
- 유지와 보수가 적당히 수월했던 코드가 버그와 결함이 숨어있을지도 모른다는 상당히 의심스러운 코드로 뒤바뀌어버렸다.

### 그래서 멈췄다
- 계속 밀어붙이면 프로그램은 어떻게든 완성하겠지만 그랬다가는 너무 커서 손대기 어려운 골칫거리가 생겨날 참이었다.
- 코드 구조를 유지보수하기 좋은 상태로 만들려면 지금이 적기라 판단했다.
- 그래서 나는 기능을 더 이상 추가하지 않기로 결정하고 리팩터링을 시작했다.
  - 첫째, 인수 유형에 해당하는 HashMap을 선택하기 위해 스키마 요소의 구문을 분석한다.
  - 둘째, 명령행 인수에서 인수 유형을 분석해 진짜 유형으로 변환한다.
  - 셋째, getXXX 메서드를 구현해 호출자에게 진짜 유형을 반환한다.
- 그래서 ArgumentMarshaler라는 개념이 탄생했다.

## 점진적으로 개선하다.
- 프로그램을 망치는 가장 좋은 방법 중 하나는 개선이라는 이름 아래 구조를 크게 뒤집는 행위다.
- 어떤 프로그램은 그저 그런 개선에서 결코 회복하지 못한다. 개선전과 똑같이 프로그램을 돌리기가 아주 어렵기 때문이다.
- 그래서 TDD를 사용하였다.
- 언제든 실행이 가증한 자동화 테스트 슈트를 통해 자잘한 변경을 가하기 시작했다.
- 코드를 변경할때마다 ArgumentMarshaler 개념에 가까워졌다.

### 개선 
- 먼저 Boolean, String, Intenger ArumentMarshaler를 구현하였다.
- 그다음 가장 단순한 변경을 가했다. Boolean 인수를 저장하는 HashMap에서 Boolean 인수 유형을 ArumentMarshaler 유형으로 바꿨다.
- BooleanArumentMarshaler의 parse, get, set 부분도 수정하였다.
- Booldean이 아닌 ArgumentMarshaler를 null점검 해주는 방식으로 변경후 필요없어진 falseIfNull을 제거하였다.

### Int, String 인수
- Sring 인수를 추가하는 과정은 boolean 인수와 유사했다.
- 단 모든 논리를 ArgumentMarshaler 클래스에 곧바로 넣었다.
- 변경때 마다 테스트 케이스를 돌리며 실패하면 다음 변경으로 넘어가지 전에 오류를 수정했다.

### ArgumentArshaler 파생클래스로 기능 분산
- 첫번째로 setBoolean 함수를 BooleanArgumentMarshaler로 옮긴후 함수가 올바로 호출되는지 확인한다.
- AM클래스에 추상 메서드 set을 만들어준뒤 BooleanAM클래스에 set을 구현한다. 마지막으로 setBoolean 호출을 set호출로 바꿨다. 그뒤 AM에서 setBoolean메서드를 제거해줬다.
- 다음은 get메서드를 BooleanAM로 옮겼다.AM에서 get을 추상메서드로 구현 후 BooleanAM에서 구현했다.
- get, set을 모두 BooleanAM으로 옮긴후 booleanValue를 BooleanAM에 private 변수로 변경 하였다.
- Int, String인수 유형도 동일한 방식으로 변경했다. Int 유형은 parse에서 예외를 던질지도 몰라, 조금 더 복잡했다.

### HashMap 교체
- 알고림즘 처음에 나오는 맵 세개를 없앴다. AM로 맵을 만들어 원래 맵을 교체하고 관련 메서드를 변경했다.
- isBooleanArgs, isIntArgs, isStringArgs를 테스트를 진행하며 변경해주었다. 그래서 marshalers.get을 호출하는 코드를 모두 제거하였다.
- isxxxArg 메서드도 별도로 선언할 이유가 없어져 인라인 코드로 만들었다.
- 다음으로 set 함수에서 기존 HashMap을 marshalersHashMap으로 변경해 주었다. 이로써 흉한 예외 관리 코르를 setArgument 함수에 넣을 수 있었다.
- 다음 getBoolean에 classCastException을 잡아주었다. 이유는 인수 테스트 케이스를 FitNess에서 구현했기 때문이다.
- 이로써 맵을 제거하였다.

### set함수에서 유형 일일이 확인하는 코드 제거
- setArgument에서 AM.set만 호출하면 충분하게 만들고 싶었다.
- 즉 setXXX에서 해당 AM 파생 클래스로 내려야 한다는 뜻이었다.
- 하지만 setIntArgs를 보면 인스턴스 변수가 두 개가 쓰인다. 그러면 코드가 지저분해진다.
- 다행스럽게 해결책은 간단하다. args 배역을 list로 변환한 후 Iterator를 set함수로 전달하면 된다.
- 다음 변경은 10단계에 걸쳐 이뤄졌으며 단계마다 코드는 테스트를 통과했다.
- 보면 코드를 넣었다 뺐다 하는 사례가 아주 흔하다. 단계적으로 조금씩 변경하며 매번 테스트를 돌려야 하므로 코드를 여기저기 옮길 일이 많아진다.
- 리팩터링은 루빅 큐브와 비슷하다. 큰 목표 하나나를 이루기 위해 자잘한 단계를 수없이 거친다. 각 단계를 거쳐야 다음 단계가 가능하다.

### AM을 클래스에서 인터페이스로 변환
- ArgumentMarshaler에 set 함수가 있다면 직접 호출할 수 있다. 따라서 set 함수를 만들 시점이다.
- 먼저 AM에 새로운 추상 메서드를 추가한다. 그리고 각 파생 클래스에도 set메서드를 추가한다.
- 이제 setxxxArg를 제거해도 안전한다.
- 마지막으로 인수 유형을 일일이 확인하던 코드를 제거한다. 
- 그러고 AM을 인터페이스로 변환해준다.
- 이제부터 우리 구조에 새로운 인수 유형을 추가하기 쉬워졌다. 변경할 코드는 아주 적으며 나머지 시스템에 영향을 미치지 않는다.

### Double추가
- DoubleAM 클래스를 작성한다.
- 여기서 새로운 ErrCode가 필요하다. 또한 getDouble 함수도 필요하다.
- 다음 오류 처리 코드가 제대로 도는지 확인해본다.
- 게다가 ParseException을 던지지만 ParseException은 Args 클래스에 속하지 않는다. 그러므로 모든 예외를 모아 ArgsException 클래스를 만든후 독자 모듈로 옮긴다.

### 리팩토링 끝
- 대략 30차례로 나눠 조금씩 코드를 바꿨다. 물론 매 단계마다 코드는 테스트를 통과헀다.
- Args클래스에서는 주로 코드만 삭제했을 뿐이다. 상당한 코드를 args 클래스에서 argsException 클래스로 옮겼다.
- 또한 모든 AM 클래스도 각자 파일로 옮겼다.
- 소프트웨어 설계는 분할만 잘해도 품질이 크게 높아진다.
- 특별히 눈여겨볼 코드는 ArgsException의 errorMessage 메서드다. 이 메서드는 명백히 SRP 위반이었다.
- 하지만 그렇다고 ArgsException 클래스가 오류 메시지 형식을 처리해야 롷을까? 솔직하게 말해 이것은 절충안이다.

## 결론
- 그저 돌아가는 코드만으로는 부족하다. 돌아가는 코드가 심하게 망가지는 사례는 흔하다.
- 단순히 돌아가는 코드에 만족하는 프로그래머는 전문가 정신이 부족하다.
- 나쁜 코드보다 심각하게 프로젝트에 악영향을 미치는 요인도 없다. 나쁜 일정은 다시 짜면 된다. 나쁜 요구사항은 다시 정의하면 된다. 나쁜 팀 역학은 복구하면된다. 나쁜 코드는 썩어 문드러진다.
- 너무 서두르다가 이후로 영원히 자신들의 운명을 지배할 악성 코드라는 굴레를 짊어진다.
- 나쁜 코드도 깨끗한 코드로 개선할 수 있지만 상당한 시간과 인내심이 필요하다. 반면 처음부터 깨끗하게 유지하기란 상대적으로 쉽다.
- 그러므로 코드는 언제나 최대한 깔끔하고 단순하게 정리하자. 절대로 썩어가게 방치하면 안된다.
