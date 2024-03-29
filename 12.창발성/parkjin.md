# 창발성
> 하위 계층에는 없는 특성이나 행동이 상위 계층에서 자발적으로 돌연히 출현하는 현상

## 창발적 설계로 깔끔한 코드를 구현하자
켄트 백이 제시한 단순한 설계 규칙 네가지,
- 모든 테스트를 실행
- 중복 제거
- 프로그래머의 의도 표현
- 클래스와 메서드 수 최소화

## 단순한 설계 규칙 1: 모든 테스트를 실행하라
- 테스트를 통해 시스템이 의도한 대로 동작하는지 검증해야 함
- 검증이 불가능한 시스템은 절대 출시하면 안됨
- 테스트가 가능한 시스템을 만들려고 애쓰면 설계 품질이 높아짐

## 단순한 설계 규칙 2~4: 리팩토링
- 테스트 케이스를 모든 작성했다면, 점진적으로 코드를 리팩토링해도 괜찮음
- 설계 품질을 높이기 위한 기법을 적용해도 괜찮고, 단순한 설계 규칙 중 나머지 3가지를 적용해도 괜찮은 단계임

## 중복을 없애라
| 중복 | 중복 제거 |
| -- | -- |
| <img width="300" src="https://user-images.githubusercontent.com/50200481/206236342-2d7dcff2-bd64-4415-a4a8-f11613c3aab5.png"> | <img width="300"  src="https://user-images.githubusercontent.com/50200481/206236489-2cd0e99f-2518-4b95-8939-0ed804601eb4.png"> |
> 출처: Clean Code 도서

- 중복은 추가 작업, 추가 위험, 불필요한 복잡도를 뜻함
- TEMPLATE METHOD 패턴은 고차원 중복을 제거할 목적으로 사용함

## 표현하라
- 개발자의 의도를 분명히 표현해야 함
- 명백해야 코드를 이해하기 쉽고, 결함이 줄어 유지보수 비용이 적게 들어감

의도를 분명히 표현한 코드를 짜기 위해,
- 좋은 이름을 선택
- 함수와 클래스의 크기를 가능한 축소
- 표준 명칭을 사용
- 단위 테스트 케이스를 꼼꼼히 작성
- 가장 중요한 것은 노력

## 클래스와 메서드 수를 최소로 줄여라
- 중복을 제거하고, 의도를 표현하고, SRP를 준수한다는 기본적인 개념도 극단으로 치달으면 득보다 실이 많아짐
- 따라서, 함수와 클래스 수를 최대한 줄이는 것이 좋음
- 다만, 이 규칙은 간단한 설계 규칙 중 가장 우선순위가 낮음
- 즉, 테스트 케이스를 만들고 중복을 제거하고 의도를 표현하는 작업이 더 중요함

## 결론
경험이 최고의 단순한 개발 비법이지만, 단순한 설계 규칙을 통해 오랜 경험 후에야 익힐 우수한 기법과 원칙을 단번에 활용 가능함