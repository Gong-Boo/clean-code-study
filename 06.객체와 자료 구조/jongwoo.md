# 객체와 자료구조

## 자료 추상화
  * 변수 사이에 함수라는 계층을 끼워 넣는다고 구현이 감춰지지는 않기 때문에 구현을 감추려면 추상화가 필요하다
  * 추상 인터페이스를 통해 사용자가 구현을 모른 채 자료의 핵심을 조작할 수 있어야 진정한 의미의 클래스이다.
  * 자료를 세세하게 공개하기 보다는 추상적인 개념으로 표현하는 편이 좋다. 인터페이스나 조회/설정 함수만으로는 추상화가 이뤄지지 않기 때문.

## 자료/객체 비대칭
  * 객체는 추상화 뒤로 자료를 숨긴 채 자료를 다루는 함수만 공개한다.
  * 객체와 자료구조는 근본적으로 양분된다
  * 절차적인 코드와 객체 지향코드의 차이점

    a. 절차적인 코드
      - 기존 자료 구조를 변경하지 않으면서 새 함수를 추가하기 쉽다
      - 새로운 자료구조를 추가하기 어렵다. 이를 위해서는 모든 함수를 고쳐야 한다

    b. 객체 지향 코드
      - 기존함수를 변경하지 않으면서 새 클래스를 추가하기 쉽다.
      - 새로운 함수를 추가하기 어렵다. 이를 위해서는 모든 클래스를 고쳐야 한다.

  * 결론. 절차 지향 코드에서 어려운 변경은 객체 지향 코드에서는 쉽고 반대로 객체 지향에서 어려운 변경은 절차 지향 코드에서는 쉽다.

## 디미터 법칙
  * 잘 알려진 휴리스틱으로 모듈은 자신이 조작하는객체의 속사정을 몰라야 한다는 법칙
  * 객체는 조회함수로 내부구조를 공개하면 안된다는 의미
  * 디미터 법칙의 주장
    - 클래스 C의 메서드 f는 다음과 같은 객체의 메서드만 호출해야한다.
      - 클래스 C
      - f가 생성한 객체
      - f 이수로 넘어온 객체
      - C 인스턴스 변수에 저장된 객체
     
    - 위 객체에서 허용된 메서드가 반환하는 객체의 메서드는 호출하면 안된다.

## 기차 충돌
  * 여러 기차가 한줄처럼 이어져 보이는 코드
  * 일반적으로 조잡하다 여겨지는 방식으로, 피하는 것이 좋다.

## 잡종 구조
  * 절반은 객체, 절반은 자료 구조로 나오는 잡종 구조인 경우가 있다.
  * 잡종 구조는 중요한 기능을 수행하는 함수도 있고, 공개 변수나 공개 조회/설정 함수도 있다. 공개 조회/설정 함수는 비공개 변수를 그대로 노출한다.
  * 새로운 함수는 물론이고 새로운 자료구조또한 추가하기 어렵다. 양쪽에서 단점만 모아놓은 구조이다.
  * 잡종구조는 되도록이면 피하는 편이 좋다.

## 구조체 감추기
  * 만약 ctxt, options scratchDir이 진짜 객체라면, 줄줄이 사탕으로 엮어서는 안된다. 객체라면 내부 구조를 감춰야 하기 떄문
  
  ```ctxt.getAbsolutePathOfScratchDirectoryOption();```
  ```ctx.getScratchDirectoryOption().getAbsolutePath()```
  
  - 첫번째 방법은 ctxt객체에 공개해야 하는 메서드가 너무 많아진다.
  - 두번째 방법은 getScratchDirectoryOption()이 객체가 아니라 자료 구조를 반환한다고 가정한다.
  - ctxt가 객체라면 뭔가를 하라고 해야지 속을 드러내라고 말하면 안된다.
 
## 자료 전달 객체
  * 자료 구조체의 전형적인 형태는 공개 변수만 있고 함수가 없는 클래스다
  * 이러한 자료 구조체를 때로는 자료 전달 객체(DTO)라 한다.
  * DTO는 데이터베이스와 통신하거나 소캣에서 받은 메시지 구문을 분석할 때 유용하다
  * DTO는 데이터베이스에 저장된 가공되지 않은 정보를 애플리케이션 코드에서 사용할 객체로 변환하는 일련의 단계에서 가장 처음으로 사용하는 구조체다.

## 활성 레코드
  * 활성 레코드는 DTO의 특수한 형태이다.
  * 데이터베이스 테이블이나 다른 소스에서 자료를 직접 변환한 결과
  * 공개 변수가 있거나 비공개 변수에 조회/설정 함수가 있는 자료 구조지만, save나 find와 같은 탐색 함수도 제공한다.
  * 활성 레코드에 비즈니스 규칙 메서드를 추가해 이런 자료구조를 객체로 취급하면 자료구조도 아니고 객체도 아닌 잡종구조가 나오기 때문에 이는 바람직하지 않다.

# 결론
  * 객체는 동작을 공개하고 자료를 숨긴다.
  * 자료구조는 별다른 동작없이 자료를 노출한다.
  * 시스템을 구현할때 새로운 자료타입을 추가하는 유연성이 필요하면 객체가 더 적합하다.
  * 새로운 동작을 추가하는 유연성이 필요하면 자료 구조와 절차적인 코드가 더 적합하다.
  * 우수한개발자는 편견없이 이 사실을 이해해 직면한 문제에 대해 최적의 해결책을 선택한다.
