# 냄새와 휴리스틱

## 주석 

#### 부적절한 정보    
  - 다른 시스템에 저장할 정보는 주석으로 적절하지 못하다.  예) 소스코드 관리 시스템, 버그 추적 시스템, 이슈 추적 시스템 기타기록 관리 시스템 등   
  - 주석은 코드 설계에 기술적인 설명을 부연하는 수단이다.

#### 쓸모없는 주석    
  - 주석은 빨리 낡는다.    
  - 쓸모 없어질 주석은 아예 달지 않는것이 좋다
  - 쓸모 없어진 주석은 재빨리 삭제하는 편이 좋다.

#### 중복된 주석    
  - 코드만으로 충반한데 구구절절 설명하는 주석이 중복된 주석이다

#### 성의 없는 주석   
  - 단어를 신중하게 선택하라    
  - 문법과 구두점을 올바르게 사용하라    
  - 주절대지 마라
  - 당연한 소리를 반복하지 마라    
  - 간단하고 명료하게 작성하라  

#### 주석처리된 코드    
  - 주석처리된 코드를 발견하면 즉시 지워라    
  - 소스 코드 관리시스템이 기억해줄거다

## 환경

#### 한 단계로 빌드해야 한다 
  - 여러 단계로 빌드하여 필요없는 파일들을 찾거나 소스코드 관리 시스템에서 이것저것 따로 체크아웃할 필요가 없어야 한다. 

#### 한 단계로 테스트 해야한다.

## 함수

#### 너무 많은 인수 
  - 함수에서 인수 개수는 아예 없거나 최대한 작아야 한다. 
#### 출력 인수
  - 직관을 정면으로 위배한다 
  - 함수에서 뭔가의 상태를 변경해야 한다면 함수가 속한 객체의 상태를 변경해야 한다.

#### 플래그 인수 
  - 플래그 인수는 혼란을 초래하므로 피해라

#### 죽은 함수 
  - 아무도 호출하지 않는 함수는 삭제하라 
  - 소스코드 관리 시스템이 기억해준다 


## 일반
#### 한 소스 파일에 여러 언어를 사용한다 
  - 소스파일 하나에 언어 하나만 사용하는 방식이 가장 좋다 
  - 한 소스 파일에서 언어 수와 범위를 줄이도록 애써야 한다

#### 당연하게 여길만한 동작과 기능을 제공하라 
  - 최소 놀람의 원칙(The Principle of Least Surprise)에 의거해 함수나 클래스는 다른 프로그래머가 당연하게 여길 만한 동작과 기능을 제공해야 한다. 

#### 경계를 올바로 처리하지 않는다 - 부지런함을 대신할 지름길은 없다. 
  - 경계조건, 구석진곳, 기벽, 예외등 모든것은 우아하고 직관적인 알고리즘을 좌초시킬 암초다. 
  - 모든 경계조건을 찾아내고, 테스트하는 테스트 케이스를 작성하라. 

#### 안전절차 무시 
  - 안전절차를 무시하지 마라. 
  
#### 중복
  - 익스트림 프로그래밍의 핵심 규칙 중 하나로, 선언한 후 "한 번, 단 한번만(Once and only once)" 이라 명명했다 
  - 코드에서 중복을 발견할때 마다 추상화할 기회로 간주하라.    
    ->추상화로 중복을 정리하면 설계 언어의 어휘가 늘어난다. 
  - 가장 뻔한 유형은 똑같은 코드가 여러차례 나오는 중복이다.    
    -> 간단한 함수로 교체하라 
  - 여러 모듈에서 일련의 switch/case 나 if/else 문으로 똑같은 조건을 거듭 확인하는 중복 유형    
    -> 다형성(polymorphism)으로 대체하라 
  - 알고리즘이 유사하나 서로 코드가 서로 다른 중복 유형    
    -> TEMPLATE METHOD 패턴이나 STRATEGY 패턴으로 중복을 제거하라. 
  - OO역시 모듈을 정리하고 중복을 제거하는 전력이다.    
    -> 이거뭐임 ?
  - 중복을 발견하면 없애라 

#### 추상화 수준이 올바르지 못하다. 
  - 추상화는 저차원 상세개념에서 고차원 일반개념을 분리한다. 
  - 모든 저차원 개념은 파생클래스에 넣고, 모든 고차원 개념은 기초 클래스에 넣는다. 
  - 고차원 개념과 저차원 개념을 섞어서는 안된다 

#### 기초 클래스가 파생 클래스에 의존한다 
  - 개념을 나누는 이유는 고차원 기초 클래스 개념을 저차원 파생클래스 개념으로부터 분리해 독립성을 보장시키기 위함이다 
  - 일반적으로 기초 클래스는 파생 클래스를 몰라야 하지만 간혹 파생클래스 개수가 확실히 고정되어 있다면 기초 클래스에 파생 클래스를 선택하는 코드가 들어간다 
  - 기초 클래스와 파생 클래스를 다른 JAR로 배포하라 

#### 과도한 정보 
  - 잘 정의된 모듈은 인터페이스가 아주 작다 
  - 부실하게 정의된 모듈은 인터페이스가 구질구질하다 
  - 잘 정의된 인터페이스는 많은 함수를 제공하지 않는다    
    -> 결합도가 낮다 
  - 부실하게 정의된 인터페이스는 반드시 호출해야하는 온갖 함수를 제공한다    
    -> 결합도가 높다 - 클래스나 모듈 인터페이스에 노출할 함수를 제한할 줄 알아야 한다. 
  - 클래스가 제공하는 메서드는 작을수록 좋다 

#### 죽은 코드 
  - 죽은 코드란 실행되지 않는 코드를 가르킨다. 
  - 죽은 코드에게는 적절한 장례식을 치뤄줘라 ( 지워라 ) 

#### 수직 분리 
- 변수와 함수는 가깝게 정의하라 

#### 일관성 부족 
- 어떤 개념을 특정방식으로 구현했다면 유사한 개념도 같은 방식으로 구현하라 

#### 잡동사니 
- 비어있는 기본 생성자를 없애라 

#### 인위적 결합 
- 서로 무관한 개념을 인위적으로 결합하지 않는다 
- 일반적인 enum이 특정 클래스에 속할 이유가 없다. enum이 클래스에 속한다면 enum을 사용하는 코드가 특정 클래스를 알아야만 한다.
- 함수, 상수, 변수를 선언할 때는 시간을 들여 올바른 위치를 고민한다. 

#### 기능 욕심 
- 클래스 메서드는 다른 클래스의 변수와 함수에 관심을 가져서는 안된다. 
- 메서드가 다른 객체의 참조자와 변경자를 사용해 그 객체를 조작한다면 메서드가 그 객체 클래스의 범위를 욕심내는 탓이다. 

#### 선택자 인수 
- 선택자 인수는 목적을 기억하기 어려울 뿐 아니라 각 선택자 인수가 여러 함수를 하나로 조합한다 
- 인수를 넘겨 동작을 선택하는 대신 새로운 함수를 만드는 편이 좋다 

#### 모호한 의도 
- 코드를 짤때는 의도를 최대한 분명히 밝힌다 

#### 잘못 지운 책임 
- 코드를 배치하는 위치도 중요하다 
- 코드는 독자가 자연스럽게 기대할 위치에 배치한다. 
- 때로는 개발자에게 편한 함수에 배치한다. 
- 함수이름에 따라 배치하는 방식도 있다. 

#### 부적절한 static함수
- 간혹 우리는 static으로 정의하면 안되는 함수를 static으로 정의한다 
- 함수를 재정의할 가능성이 존재하는 메서드는 static으로 정의하면 안된다. 
- 일반적으로 static함수보다 인스턴스 함수가 더 좋지만 반드시 static으로 정의해야 겠다면 재정의될 가능성은 없는지 꼼꼼히 따져본다. 

#### 서술적 변수 
- 프로그램 가독성을 높이는 가장 효과적인 방법중 하나가 계산을 여러 단계로 나누고 중간 값으로 서술적인 변수 이름을 사용하는 방법이다. 
- 계산을 몇 단계로 나누고 중간값에 좋은 변수 이름만 붙여도 해독하기 어렵던 모듈이 순식간에 읽기 쉬운 모듈로 탈바꿈한다. 

#### 이름과 기능이 일치하는 함수 
- 이름만으로 분명하지 않기에 구현을 살피거나 문서를 뒤적여야 한다면 더 좋은 이름으로 바꾸거나 아니면 더 좋은 이름을 붙이기 쉽도록 기능을 정리해야 한다. 

#### 알고리즘을 이해하라 
- 대다수 괴상한 코드는 알고리즘을 충분히 이해하지 않은 채 코드를 구현한 탓이다 
- 구현이 끝났다고 선언하기 전에 함수가 돌아가는 방식을 확실히 이해하는지 확인하라. 
- 알고리즘이 올바르다는 사실을 확인하고 이해하려면 기능이 뻔히 보일정도로 함수를 깔끔하고 명확하게 재구성하는 방법이 최고다 

#### 논리적 의존성은 물리적으로 드러내라 
- 한 모듈이 다른 모듈에 의존한다면 물리적인 의존성도 있어야 한다. 
- 의존하는 모듈이 상대 모듈에 대해 뭔가를 가정하면 안된다 
- 의존하는 모든 정보를 명시적으로 요청하는 편이 좋다 

#### If/else 또는 Switch/Case 문보다 다형성을 사용하라 
- Switch문을 사용하는 이유는 그 상황에서 가장 올바은 선택이 아니라 당장 손쉬운 선택이기 떄문이다 
- 유형모다 함수가 더 쉽게 변하는 경우는 드물다. -> 모든 Switch문을 의심하라 

#### 표준 표기법을 따르라 
- 팀에서 정한 표기법을 따라라 
- 사실상 코드가 제일 좋은 표준을 설명하는 문서이다 

#### 매직 숫자는 명명된 상수로 교체하라 
- 코드에서 숫자를 사용하지 말라는 규칙이다 
- 숫자는 명명된 상수 뒤로 숨기라는 의미 

#### 정확하라 
- 검색결과 중 첫번째 결과만 유일한 결과로 간주하는 행동은 순진하다 
- 코드에서 뭔가를 결정할 때는 정확히 결정한다. 
- 코드애서 모호성과 부정확은 의견차나 게으름의 결과다. 

#### 관례보다 구조를 사용하라 
- 설계 결정을 강제할 때는 규칙보다 관례를 사용한다. 

#### 조건을 캡슐화하라 

#### 부정 조건은 피하라 
- if(!a) 보다 if(a)를 기준으로 해라 

#### 함수는 한가지만 해야한다 
- 한 함수에서 루프를 돌며 그 안에서 조건문으로 필터링하는 함수 보다는 루프도는 함수따로, 필터링하는 함수 따로 만들어라 

#### 숨겨진 시간적인 결합 
- 시간적인 결합을 숨겨서는 안된다 
- 일종의 연결 소자를 생성해 시간적인 결합을 노출시켜라 

#### 일관성을 유지하라 
- 코드구조를 잡을때는 이유를 고민하라. 그리고 그 이유를 코드 구조로 명백히 표현하라 
- 구조에 일관성이 없어 보인다면 남들이 맘대로 바꿔도 괜찮다고 생각한다 

#### 경계 조건을 캡슐화하라 
- 경계 조건은 한곳에서 별도로 처리하라 

#### 함수는 추상화 수준을 한 단계만 내려가야 한다 
- 함수 내 모든 문장은 추상화 수준이 동일해야 한다. 그리고 그 추상화 수준은 함수 이름이 의미하는 작업보다 한 단계만 낮아야 한다. 

#### 설정 정보는 최상위 단계에 둬라 
- 추상화 최상위 단계에 둬야할 기본값 상수나 설정 관련 상수를 저차원 함수에 숨겨서는 안된다 - 설정 관련 상수는 최상위 단계에 둔다 - 설정 관련 변수는 나머지 코드에 인수로 넘긴다 

#### 추이적 탐색을 피하라 
- 일반적으로 한 모듈은 주변 모듈을 모를수록 좋다 ( 디미터의 법칙 )  
- 내가 사용하는 모듈이 내게 필요한 서비스를 모두 제공해야 한다

## 자바 

#### 긴 import목록을 피하고 와일드카드를 사용하라 
- 패키지에서 클래스를 둘 이상 사용한다면 와일드카드를 사용해 패키지 전체를 가져오라 ( * ) 
- import 문은 패키지를 단순히 검색 경로에 추가하므로 진정한 의존성이 생기지 않는다 
- 와일드카드 import문은 때로 이름 충돌이나 모호성을 초래한다    
  -> 이름이 같으나 패키지가 다른 클래스는 명시적인 import문을 사용하거나 아니면 코드에서 클래스를 사용할때 전체 경로를 명시한다 

#### 상수는 상속하지 않는다 
- 끔찍하다. 언어의 범위 규칙을 속이는 행위이다. 
- 대신 static import를 사용하라 

#### 상수 대 Enum 
- int는 코드를 잃어버리기도 한다. 하지만 enum은 그렇지 않다 
- 상수 대신 enum을 써라 

## 이름

#### 서술적인 이름을 사용하라 
- 성급하게 정하지 마라 
- 서술적인 이름을 신중하게 골라라 
- 소프트웨어 가독성의 90%는 이름이 경정한다 
- 신중하게 선택한 이름을 보고 독자는 모듈 내 다른 함수가 하는 일을 짐작한다 

#### 적절한 추상화 수준에서 이름을 선택하라 
- 구현을 드러내는 이름은 피하라 
- 작업대상 클래스나 함수가 위치하는 추상화 수준을 반영하는 이름을 선택하라 

#### 가능하다면 표준 명명법을 사용하라 
- 프로젝트에 유효한 의미가 담긴 이름을 많이 사용할수록 독자가 코드를 이해해기 쉬워진다 

#### 명확한 이름 
- 목적을 명확히 밝히는 이름을 선택하라 

#### 긴 범위는 긴 이름을 사용하라 
- 이름 길이는 범위 길이에 비례해야 한다. 

#### 인코딩을 피하라 
- 이름에 유형정보나 범위정보를 넣어서는 안된다 
- 중복된 정보이며 독자만 혼란하게 만든다 

#### 이름으로 부수효과를 설명하라 
- 실제로 여러 작업을 수행하는 함수에다 동사 하나만 달랑 사용하면 곤란하다 
- getA()라는 함수에서 이름에 A를 빼면 뭘 가져오는지 알수가 없다 

## 테스트

#### 불충분한 테스트 
- 테스트 케이스는 잠재적으로 깨질만한 부분을 모두 테스트해야한다. 

#### 커버리지는 도구를 사용하라 
- 커버리지 도구는 테스트가 빠뜨리는 공백을 알려준다 

#### 사소한 테스트를 건너뛰지 마라 
- 사소한 테스트가 제공하는 문서적 가치는 구현에 드는 비용을 넘어선다 

#### 무시한 테스트는 모호함을 뜻한다 
- 불분명한 요구사항은 테스트 케이스를 주석으로 처리하거나 테스트 케이스에 @Ignore를 붙여 표현한다. 

#### 경계 조건을 테스트하라 
- 각별히 신경써라 (경계 조건에서 나오는 실수가 흔하기 때문) 

#### 버그 주변은 철저히 테스트하라 
- 버그는 서로 모이는 경향이 있다 

#### 실패 패턴을 살펴라 
- 때로는 실패한 테스트케이스가 실패하는 패턴으로 문제를 진단할 수 있다. 
- 합리적인 순서로 정렬된 꼼꼼한 테스트 케이스는 실패 패턴을 드러낸다 

#### 테스트 커버리지 패턴을 살펴라 
- 통과하는 테스트가 실행하거나 실행하지 않는 코드를 살펴보면 실패하는 테스트 케이스의 실패 원인이 드러난다. 

#### 테스트는 빨라야 한다 
- 느린 테스트케이스는 실행하지 않게 된다.  


## 결론 
- 여기서 소개한 목록은 가치 체계를 피력할 뿐이다 
- 일군의 규칙만 따른다고 깨끗한 코드가 얻어지지 않는다 
- 휴리스틱 목록을 익힌다고 소프트웨어 장인이 되지는 못한다 
- 전문가 정신과 장인 정신은 가치에서 나온다. 그 가치에 기반한 규율과 결제가 필요하다.
