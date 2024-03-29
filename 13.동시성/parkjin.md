# 동시성

## 동시성이 필요한 이유?
- 동시성은 **무엇**(What)과 **언제**(When)을 분리하여 결합을 없애는 전략
- **무엇**과 **언제**를 분리하면 구조적 관점으로 프로그램은 거대한 루프 하나가 아닌 작은 협력 프로그램 여럿으로 보이기 때문에 구조와 효율이 나아지고, 시스템을 이해하기 쉽고 문제를 분리하기도 쉬워짐
- 하지만 구조적 개선만을 위해 동시성을 채택하지 않고, 응답 시간과 작업 처리량 개선이라는 요구사항으로 인해 동시성 구현이 불가피한 경우도 있음

### 동시성의 미신과 오해
- 동시성은 항상 성능을 높여준다.
  - 대기 시간이 아주 길어 여러 스레드가 프로세서를 공유할 수 있거나, 여러 프로세서가 동시에 처리할 독립적인 계산이 충분히 많은 경우에만 성능이 높아짐
  - 위 경우는 일반적으로 발생하는 상황은 아님

- 동시성을 구현해도 설계는 변하지 않는다.
  - 단일 스레드 시스템과 다중 스레드 시스템은 설계가 판이하게 다름
  - **무엇**과 **언제**를 분리하면 시스템 구조는 크게 달라짐

- 컨테이너를 사용하면 동시성을 이해할 필요가 없다.
  - 컨테이너가 어떻게 동작하는지, 동시수정과 데드락 같은 문제를 피할 수 있는지 알아야 함 

### 동시성의 타당한 점
- 동시성은 다소 부하를 유발한다.
  - 성능 측멱에서 부하가 걸리며, 코드도 작성해야 함

- 동시성은 복자하다.
  - 간단한 문제라도 동시성은 복잡함

- 일반적으로 동시성 버그는 재현하기 어렵다.
  - 진정한 결함으로 간주되지 않고, 일회성 문제로 무시하기 쉬움

- 동시성을 구현하려면 흔히 근본적인 설계 전략을 재고해야 함

## 난관
<img width="200" src="https://user-images.githubusercontent.com/50200481/207062395-1e5130b4-bef2-48c5-aa38-1a7a1f130859.png">

> 출처: Clean Code 도서

- 다수의 스레드가 같은 변수를 동시에 참조하면 일부 경로에서 잘못된 결과를 내놓음
- 정확히는 JIT 컴파일러가 바이트 코드를 처리하는 방식과 자바 메모리 모델이 원자로 간주하는 최소 단위를 알아야함

## 동시성 방어 원칙

### 단일 책임 원칙 (SRP)

- 주어진 메서드/클래스/컴포넌트를 변경할 이유가 하나여야 한다는 원칙
- 동시성은 복잡성 하나만으로 따로 분리할 이유가 충분하므로 동시성 코드는 다른 코드와 분리해야 함

  - 동시성 코드는 독자적인 개발, 변경, 조율 주기가 있음
  - 동시성 코드에는 독자적인 난관이 있음, 다른 코드에서 겪는 난관과 다르며 훨씬 어려움
  - 잘못 구현한 동시성 코드는 별의별 방식으로 실패홤, 동시성 하나만으로 충분히 어려움

> 동시성 코드는 다른 코드와 분리하라

### 따름 정리: 자료 범위를 제한하라
- 객체 하나를 공유한 후 동일한 필드를 수정하던 다수의 스레드에서 서로 간섭하여 잘못된 결과를 내놓음
- 이런 문제를 해결하기 위해 객체를 사용하는 코드 내 **임계영역**을 **synchronized** 키워드로 보호하는 것을 권장함
- 공유자료를 수정하는 위치가 많다면,
  - 보호할 임계영역을 빼먹음
  - 모든 임계영역을 올바르게 보호했는지 확인하는 수고를 반복함
  - 찾아내기 어려운 버그가 더욱 찾기 어려워짐

> 자료를 캡슐화하라. 공유 자료를 최대한 줄여라.

### 따름 정리: 스레드는 가능한 독립적으로 구현하라
- 다른 스레드와 자료를 공유하지 않도록 구현
- 각 스레드는 하나의 클라이언트 요청을 처리
- 모든 정보는 비공개 출처에서 가져오며 로컬 변수에 저장

> 독자적인 스레드로, 가능하면 다른 프로세서에서, 돌려도 괜찮도록 독립적인 단위로 분할하라

## 라이브러리를 이해하라 
자바 5로 스레드 코드를 구현한다면,
- 스레드 환경에 안전한 Collection을 사용
- 서로 무관한 작업을 수행할 때는 executor 프레임워크를 사용
- 가능하다면 스레드가 차단(Blocking)되지 않는 방법을 사용
- 일부 클래스 라이브러리는 스레드에 안전하지 못함

### 스레드 환경에 안전한 컬렉션
- java.util.concurrent 패키지가 제공하는 클래스는 다중 스레드 환경에서 안전하며 성능도 좋음
- 동시 읽기/쓰기를 지원하며, 자주 사용하는 복합 연산을 다중 스레드 상에서 안전하게 만든 메서드로 제공함

> 언어가 제공하는 클래스를 검토하라

## 실행 모델을 이해하라
- 한정된 자원
  - 다중 스레드 환경에서 사용하는 자원으로, 크기나 숫자가 제한적임
  - 예로 데이터베이스 연결, 길이가 일정한 읽기/쓰기 버퍼 등

- 상호 배제
  - 한 번에 한 스레드만 공유 자료나 공유 자원을 사용할 수 있는 경우를 가리킴

- 기아 상태
  - 한 스레드나 여러 스레드가 굉장히 오랫동안 자원을 가리킴
  - 예로 항상 짧은 스레드에게 우선 순위를 준다면, 짧은 스레드가 지속적으로 이어질 경우, 스레드가 기아 상태에 빠짐

- 데드락
  - 여러 스레드가 서로 끝나기를 기다림
  - 모든 스레드가 각기 필요한 자원을 다른 스레드가 점유하는 바람에 어느쪽도 더 이상 진행하지 못함

- 라이브락
  - 락을 거는 단계에서 각 스레드가 서로를 방해
  - 스레드는 계속해서 진행하려 하나, 공명으로 인해 굉장히 오랫동안 진행하지 못함

### 생산자-소비자
- 하나 이상 **생산자 스레드**가 정보를 생성해 버퍼에 넣음, 빈 공간이 생길 때까지 기다리며 빈 공간이 생기면 정보를 채움
- 하나 이상 **소비자 스레드**가 정보를 가져와 사용함, 버퍼에 정보가 채워질 때까지 기다리며 버퍼에 정보가 있어야 가져옴
- 생산자 스레드: 버퍼에 정보 추가 -> "버퍼에 정보가 존재" -> 소비자 스레드에 시그널 전달
- 소비자 스레드: 버퍼를 읽음 -> "버퍼에 빈 공간이 존재" -> 생산자 스레드에 시그널 전달
- 즉, 잘못할 경우 생산자 스레드와 소비자 스레드 둘 다 진행 가능함에도 서로에게 시그널을 기다릴 가능성이 존재함

### 읽기-쓰기
- 읽기 스레드는 공유 자원을 사용, 쓰기 스레드가 해당 공유 자원을 갱신한다고 가정
- 이런 경우 처리율이 문제의 핵심으로 
- 처리율을 강조하면, 기아 현상이 생기거나 오래된 정보가 쌓임
- 갱신을 허용하면, 처리율에 영향을 미침
- 따라서 읽기 스레드의 요구와 쓰기 스레드의 요구를 적절히 만족시켜 처리율도 적당히 높이고 기아 상태도 방지하는 해법이 필요

### 식사하는 철학자들

> 각 알고리즘과 각 해법을 이해하라

## 동기화 메서드 사이에 존재하는 의존성을 이해하라

> 공유 객체 하나에는 메서드 하나만 사용하라

공유 객체 하나에 여러 메서드가 필요한 상황에는,
- 클라이언트에서 잠금
  - 클라이언트에서 첫 번째 메서드를 호출하기 전에 서버를 잠금
  - 마지막으로 호출할 때까지 잠금을 유지

- 서버에서 잠금
  - 서버에다 "서버를 잠그고 모든 메서드를 호출한 후 잠금을 해제하는" 메서드를 구현 후 클라이언트에서 호출

- 연결 서버
  - 잠금을 수행하는 중간 단계를 생성
  - '서버에서 잠금' 방식과 유사하나 원래 서버는 변경하지 않음

## 동기화하는 부분을 작게 만들어라

- synchronized 키워드를 사용하여 락을 설정하나 락은 스레드를 지연시키고 부하를 가중시킴
- 그러므로 synchronized 문을 남발하는 코드는 바람직하지 않음
- 임계영역은 반드시 보호해야 하기에 코드를 짤 때 임계영역 수를 최대한 줄여야 함
- 임계영역 수를 줄인답시고 임계영역을 하나로 구현하는 경우, 스레드 간 경쟁이 늘어나고 성능이 떨어짐

> 동기화하는 부분을 최대한 작게 만들어라

## 올바른 종료 코드는 구현하기 어렵다

> 종료 코드를 개발 초기부터 고민하고 동작하게 초기부터 구현하라. 생각보다 어려우므로 이미 나온 알고리즘을 검토하라.

## 스레드 코드 테스트하기
> 문제를 노출하는 테스트 케이스를 작성하라.    
> 프로그램 설정과 시스템 설정과 부하를 바꿔가며 자주 돌려라.   
> 테스트가 실패하면 원인을 추적하라.   
> 다시 돌렸더니 통과하더라는 이유로 그냥 넘어가면 절대로 안 된다.   

### 말이 안 되는 실패는 잠정적인 스레드 문제로 취급하라

> 시스템 실패를 '일회성'이라 치부하지 마라.

### 다중 스레드를 고려하지 않은 순차 코드부터 제대로 돌게 만들자

- 일반적인 방법으로 스레드가 호출하는 POJO를 만들어 테스트 진행
- POJO는 스레드를 모르기에 스레드 환경 밖에서 테스트가 가능함

> 스레드 환경 밖에서 생기는 버그와 스레드 환경에서 생기는 버그를 동시에 디버깅하지 마라.   
> 먼저 스레드 환경 밖에서 코드를 올바로 돌려라.

### 다중 스레드를 쓰는 코드 부분을 다양한 환경에 쉽게 끼워 넣을 수 있게 스레드 코드를 구현하라

> 다양한 설정에서 실행할 목적으로 다른 환경에 쉽게 끼워 넣을 수 있게 코드를 구현하라

### 다중 스레드를 쓰는 코드 부분을 상황에 맞게 조율할 수 있게 작성하라

- 다양한 설정으로 프로그램의 성능 측정 방법을 강구
- 스레드 개수를 조율하기 쉽게 코드를 구현
- 프로그램이 돌아가는 도중에 스레드 개수를 변경하는 방법도 고려
- 프로그램 처리율과 효율에 따라 스스로 스레드 개수를 조율하는 코드도 고민

### 프로세스 수보다 많은 스레드를 돌려보라

- 시스템이 스레드를 스와핑할 때도 문제가 발생
- 스와핑을 일으키려면 프로세스 수보다 많은 스레드를 사용
- 스와핑이 잦을수록 임계영역을 빼먹은 코드나 데드락을 일으키는 코드를 찾기 쉬워짐

### 다른 플랫폼에서 돌려보라

- 다중 스레드 코드는 플랫폼에 따라 다르게 돌아감
- 코드가 돌아갈 가능성이 있는 플랫폼 전부에서 테스트를 수행

> 처음부터 그리고 자주 모든 목표 플랫폼에서 코드를 돌려라.

### 코드에 보조 코드를 넣어 돌려라. 강제로 실패를 일으키게 해보라

- 예로 wait(), sleep(), yield(), priority()등과 같은 보조 코드를 추가해 코드가 실행되는 순서를 바꿔줌
- 각 메서드는 스레드가 실행되는 순서에 영향을 미치므로 버그가 드러날 가능성이 높아짐

### 직접 구현하기

보조 코드를 직접 추가할 경우 생기는 문제,
- 보조 코드를 삽입할 적정한 위치 선정
- 어떤 함수를 어디서 호출해야 적당할지
- 배포 환경에 보조 코드를 그대로 남겨둘 가능성
- 오류가 드러날지 드러나지 않을지 모르는 무작위적임

스레드를 전혀 모르는 POJO와 스레드를 제어하는 클래스로 분할하면 보조 코드를 추가할 위치를 찾기 쉬워짐

### 자동화

보조 코드를 자동으로 추가하려면 AOF, GGLIB, ASM 등과 같은 보조 도구를 사용

> 흔들기 기법을 사용해 오류를 찾아내라

### 결론
- 다중 스레드 코드는 올바로 구현하기 어려움
- SRP 준수, POJO를 사용해 스레드 코드와 분리
- 스레드 코드 테스트 시, 전적으로 스레드만 테스트 하도록
- 동시성 오류를 일으키는 잠정적 원인을 철저히 이해
- 사용하는 라이브러리와 기본 알고리즘을 이해
- 보호할 코드 영역을 찾아내는 방법과 특정 코드 영역을 잠그는 방법 이해
- 어떻게든 문제는 생기기에 반복해서 계속 테스트 해야 함
- 보조 코드를 추가하면 오류가 드러날 가능성이 크게 높아짐
