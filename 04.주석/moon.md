# 주석

"나쁜 코드에 주석을 달지마라 새로 짜라" - 브라이언 W 커니핸, P.J.플라우저

- 잘달린 주석은 어떤 정보보다 유용하지만 오래되고 조잡한 주석은 해악을 미친다.
- 주석은 순수하게 선하지 못하며 기껏해야 필요악이며, 프로그래밍 언어로 표현할 능력이 없었을 뿐이다.
- 주석이 필요한 상황에 처한다면 코드로 정말 표현할수 없는가에 대해 곰곰히 생각하고 사용해야한다.
- 주석은 오래될수록 유지하고 보수하기란 현실적으로 어려워 코드와 멀어진다.
- 필자는 주석을 엄격하게 관리할빠엔 코드를 깜끌하게 정리하고 표현력을 강화하는 방법을 선호한다.
- 부정확한 주석은 아예 없는 주석보다 훨씬 더 나쁘다.
- 진실은 코드에만 존재한다.

## 주석은 나쁜 코드를 보완하지 못한다.
- 주석이 추가되는 일반적인 이유는 코드 품질이 나쁘기 때문아다.
- 표현력이 풍부하고 깔끔하며 주석이 거의 없는 코드가, 복잡하고 어수선하며 주석이 많이 달린 코드보다 훨씬 좋다.
- 주석으로 설명 하려 애쓰는 대신 코드를 깨끗이 치우는데 시간을 보내라.

## 코드로 의도를 포현하라!

```java
// 직원에게 복지 혜택을 받을 자격이 있는지 검사한다.
if ((employee.flags & HOURLY_FLAG) && (employee.age > 65))
```

```java
if (employee.isEligibleForFullBenefits())
```

위에 두 예제 코드 처럼 조금만 생각하면 주석 없이 코드로 대다수 의도를 표현할 수 있다. 많은 경우 주석으로 달려는 설명을 함수로 만들어도 좋다.

## 좋은 주석
어떤 주석은 필요하거나 유익하다. 하지만 정말로 좋은 주석은, 주석을 달지 않을 방법을 찾아낸 주석이다.
1. 법적인 주석
- 때로는 회사가 정립한 규현 표준에 맞춰 법적인 이유로 특정 주석을 넣으라고 명시한다.
- 예를 들어 각 소스 파일 첫머리에 주석이 들어가는 저작권 정보와 소유권 정보는 필요하고도 타당하다
```java
// Copyright (C) 2003,2004,2005 by Object Mentor, Inc, All rights reserved.
// GNU General Public License 버전 2 이상을 따르는 조건으로 배포한다.
```
2. 정보를 제공하는 주석
- 때로는 기본적인 정보를 주석으로 제공하면 편리하며, 밑에는 그 예시 이다.
```java
// 테스트 중인 Responder 인스턴스를 반환한다.
protected abs Responder reponderInstance();
```
```java
// kk:mm:ss EEE, MMM, dd, yyyy형식이다
Pattern timeMatcher = pattern.compile(...)
```
3. 의도를 설명하는 주석
- 코드를 짠 저자의 의도를 분명히 드러내는 코드가 이에 해당한다.
4. 의미를 명료하게 밝히는 주석
- 때때로 모호한 인수나 반환값은 그 의미를 읽기 좋게 표현하면 이해하기 쉬워진다.
```java
WikiPagePath a = PathParser.parse("PageA");
WIkiPagePath ab = PathParser.parse("PageA.PAGEB");
WikiPagePath b = PathParser.parse("PageB");
WIkiPagePath aa = PathParser.parse("PageA.PAGEA");

assertTrue(a.compareTo(a) == 0); // a == a
assertTrue(a.compareTo(b) != 0); // a != b
assertTrue(ab.compareTo(ab) == 0); // ab == ab
assertTrue(a.compareTo(b) == -1); // a < b
```
5. 결과를 경고하는 주석
6. TODO 주석
- 때로는 앞으로 할 일을 //TODO 주석으로 남겨두면 좋다.
```java
// TODO-MdM 현재 필요하지 않다.
// 체크아웃 모델을 도입하면 함수가 필요 없다.
```
이처럼 TODO를 사용하면 IDE에서 찾아주기에 주석을 잊어버릴 염려는 없다.
7. 중요성을 강조하는 주석
- 자칫 중요하지 않다고 여겨질 뭔가의 중요성을 강조하기 위해사더 주석은 사용한다.
10. 공개 API에서 Javadocs
- 설명이 잘 된 공개 API는 참으로, 유용하고 만족스럽다.표준 자바 라이브러리에 사용된 Javadocs가 좋은 예다.
- 그래도 어느 주석과 마찬가지로 javadocs역시 그릇된 정보를 전달할 가능성이 존재한다.

## 나쁜 주석
대다수 주석이 이 범주에 속한다. 일반적으로 대다수 주석은 허술한 코드를 지탱하거나, 엉성한 코드를 변명하거나, 미숙한 결정을 합리화 하는등 프로그래머가 주절거리는 독백에서 크게 벗어나지 못한다.
1. 주절거리는 주석
- 특별한 이유 없이 의무감이나 혹은 프로세스에서 하라고 하니깐 마지못해 주석을 단다면 전적으로 시간 낭비이다.
```java
try {
...
} catch(IOException e) {
 // 속성 파일이 없다면 기본값을 모두 메모리로 읽어 들였다는 의미다.
}
```
- catch 블록에 있는 주석은 무슨뜻일까? 확실히 저자에게야 의미가 있겠지만 그 의미가 다른 사람에게 전해지지 않는다.
- 답을 알아낼려면 다른 코드를 뒤져보는 수밖에 없다. 이해가 안되어 다른 모듈까지 뒤져야 하는 주석은 독자와 소통하지 못하는 바이트 낭비일 뿐 이다.
2. 같은 이야기를 중복하는 주석
주석이 코드 내용을 그대로 중복하여, 자칫하면 코드보다 주석을 읽는 시간이 더 오래 걸린다.
```kotlin
// a를 리턴하는 함수이다.
fun returnA() = return A
```
3. 오해할 여지가 있는 주석
- 때때로 의도는 좋았으나 프로그래머가 딱 맞을 정도로 엄밀하게 주석을 잘지 못하기도 한다.
- 주석에 담긴 살짝 잘못된 정보로 인해 다른 프로그래머가 경솔하게 코드를 짤수도 있다.
4. 의무적으로 다는 주석
- 모든 함수에 javadocs를 달거나 모든 변수에 주석을 달아야 한다는 규칙은 어리석기 그지없다.
- 이런 주석은 코드를 복잡하게 만들며 거짓말을 퍼뜨리고 혼동과 무질서를 초래한다.
```java
/**
*
* @param title CD 제목
* @param author CD 저자
* @param track CD 트랙숫자
*/
public void addCD(String title, String author, int tracks) {
  CD cd = new CD();
  cd.title = title;
  cd.author = author;
  cd.tracks = tracks;
  cdList.add(cd);
}
```
5. 이력을 기록하는 주석
- 때때로 사람들은 모듈을 편집할때마다 모듈 첫머리에 주석을 추가한다. 
- 그리하여 모듈 첫 머리 주석은 지금까지 모듈에 가한 변경을 모두 기록하는 일종의 로그가 된다.
```java
* 변경 이력(11-Oct-2001부터)
* -------------------------
* 11-Oct-2001 : 패키지 생성
*               클래스 생성
* 05-Nov-2001 : 어쩌구
*               저쩌구
* 12-Nov-2001 : 운동
*               가야됨
* 05-Dec-2001 - 하이하이
```
- 예전에는 모든 모듈 첫머리에 변경 이력을 기록하고 관리하는 관례가 바람직 하였다.
- 하지만 현재에는 소스 관리 시스템이 존재하여 혼란만 가중할 뿐이다.
6. 있으나 마나 한 주석
- 때때로 있으나 마나 한 주석을 접한다. 쉽게 말해 너무 당연한 사실을 언급하며 새로운 정보를 제공하지 못한다.
```kotlin
//이것은 코틀린 함수입니다.
fun abc : A {
  //if문이다
  if(b == a) {
    // 팀버찍기
    Timber.d("${b==a}")
  }
}
```
- 위와 같은 주석은 지나친 참견이라 개발자가 주석을 무시하는 습관에 빠진다.
- 코드를 읽으며 자동으로 주석을 건너뛴다. 결국은 코드가 바뀌면서 주석은 거짓말로 변한다.
7. 무서운 잡음
- 때로는 javadocs도 잡음이다. 다음은 잘 알려진 오픈 소스 라이브러리에서 가져온 코드이다. 아래 나오는 javadoc는 어떤 목적을 수행할까? 
- 답: 없다, 주석을 제공해야 한다는 잘못된 욕심으로 탄생한 잡음일 뿐인다.
```java
// The name.
private String name;

// The Version
private String version;

//The licenceName
private String licenceName;
```
8. 함수나 변수로 표현할 수 있다면 주석을 달지마라.
9. 위치를 표시하는 주석
- 때때로 프로그래머는 소스 파일에서 특정 위치를 표시하려 주석을 사용한다.
```java
// Actions --------------------------------------
```
- 극히 드물지만 위와 같은 배너 아래 특정 기능을 모아놓으면 유용한 경우도 있다.
- 하지만 일반적으로 위와 같은 주석은 가독성만 낮추므로 제거해야 마땅하다.
10. 닫는 괄호에 다는 주석
- 때로는 프로그래머들이 닫는 괄호에 특수한 주석을 달아놓는다.
- 중복이 심하고 장황한 함수라면 의미가 있을지도 모르지만 작고 캡슐화된 함수에는 잡음일 뿐이다.
```java
public class wc {
  public static void main(String[] args) {
    ...
    while() {
      try {
        ...
      }// try
      catch () {
        ...
      }// catch
    } // while
  } // main fun
} // Class
```
11. 공로를 돌리거나 저자를 표시하는 주석
```java
  // moon이 추가함
```
- 소스 코드 관리 시스템에서 누가 언제 무엇을 추가했는지 다 알수있기에 필요없는 주석이다.
12. 주석으로 처리한 코드
```java
int a = 2;
//int b = 3;
//int c = 12;
//int d = 11;
```
- 이와같이 주석으로 처리된 코드는 다른 사람이 지우기를 주저한다. 이유가 있어 남겨놓았으리라, 중요하니깐 지우면 안된다.
- 그래서 계속 해서 쓸모 없는 코드가 점차 쌓여간다.
13. HTML 주석
- HTML 주석은 IDE에서 조차 보기 불편하다
14. 전역 정보
- 주석을 달아야 한다면 근처에 있는 코드만 기술하라
15. 모호한 관계
- 주석과 주석이 설명하는 코드는 둘 사이가 명백해야 한다.
16. 함수 헤더
17. 비공개 코드에서 Javadocs
- 공개 API는 Javadocs가 유용하지만 공개하지 않을 코드라면 Javadocs는 쓸모없다.
