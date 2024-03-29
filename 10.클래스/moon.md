# 클래스
- 이장에서는 깨끗한 클래스를 다룬다.

## 클래스 체계
- 클래스를 정의하는 표준 자바 관례에 따르면, 가장 먼저 변수 목록이 나온다.
- static, public 상수가 있다면 맨 처음에 나온다. 다음으로 private변수가 나오며, 이어서 비공개 인스턴스 변수가 나온다.
- 변수 목록 다음에는 공개 함수가 나온다. 비공개 함수는 자신을 호출하는 공개 함수 직후에 넣는다.

### 캡슐화
- 변수와 유틸리티 함수는 가능한 공개하지 않는 편이 낫지만 반드시 숨겨야 한다는 법칙도 없다.
- 때로는 변수나 유틸리티 함수를 테스트 코드에 접근을 허용하기도 한다.
- 같은 패키지안에서 테스트 코드가 함수를 호출하거나 변수를 사용해야 한다면 protected로 선언하거나 패키지 전체로 공개한다.
- 캡슐화를 풀어주는 결정은 언제나 최후의 수단이다.

## 클래스는 작아야 한다!
- 클래스를 설계할 때도, 함수와 마찬가지로, '작게'가 기본 규칙이다.
- 여기서 작은 클래스란 책임이 적은 클래스를 뜻한다.
- 클래스의 이름은 해당 클래스 책임을 기술해야한다.

### 단일 책임 원칙
- 단일 책임 원칙은 클래스나 모듈을 변경할 이유가 하나여야 한다는 원칙이다.

- before

``` java
public class SuperDashboard extends JFrame implements MetaDataUser
  public Component getLastFocusedComponet()
  public int getMajorVersionNumber()
  public int getBuildNumber()
```
- after

``` java
public class Version {
  public int getMajorVersionNumber()
  public int getBuildNumber()
}
```
- 큼직한 다목적 클래스는 당장 알 필요가 없는 사실까지 들이밀어 독자를 방해한다.
- 큰 클래스 몇개가 아니라 작은 클래스 여럿으로 이뤄진 시스템이 더 바람직하다.

### 응집도
- 클래스는 인스턴스 변수 수가 작아야 한다.
- 일반적으로 메서드가 변수를 더 많이 사용 할수록 메서드와 클래스는 응집도가 더 높다.
- 모든 인스턴스 변수를 메서드마다 사용하는 클래스는 응집도가 가장 높다.

- 응집도가 높은 클래스
``` kotlin
class A {
  var a = 0
  var b = 1

  fun getA = a
  
  fun addA() {
    a++
  }
  
  fun tempAB() {
    var temp = 0
    temp = a
    a = b
    b = temp
  }
}
```
### 응집도를 유지하면 작은 클래스 여럿이 나온다.
- 클래스가 응집혁을 잃는다면 쪼개라
- 큰 함수를 작은 함수 여럿으로 쪼개다 보면 종종 작은 클래스 여럿으로 쪼갤 기회가 생긴다. 그러면서 프로그램에 점점 더 처계가 잡히고 구조가 투명해진다.

## 변경하기 쉬운 클래스
- 대다수 시스템은 지속적인 변경이 가해진다. 그래서 깨끗한 시스템은 클래스를 체계적으로 정리해 변경에 수반하는 위험을 낮춘다. 

- before
``` kotlin
class Sql {
  fun Sql(table: String, columns: List<Cloumn>)
  fun create(): String
  fun insert(fields: Object): String
  fun select(column: Column): String
}
```

- after
```kotlin
abstract class Sql {
  fun Sql(table: String, columns: List<Column>)
  abstract fun generate()
}

class CreateSql: Sql {
  fun CreateSql(table: String, columns : List<Column>)
  override fun generate()
}
...
```
- 이런식으로 쪼개지 않을경우 SRP를 위한하게 되고 차후 새로운 기능을 추가할때도 간편해진다.

### 변경으로부터 격리
- 상세한 구현에 의존하는 클라이언트 클래스는 구현이 바뀌면 위험에 빠진다.
- 그래서 우리는 인터페이스와 추상 클래스를 사용해 구현이 미치는 영향을 격리한다.
- 상세한 구현에 의존하는 코드는 테스트가 어렵다.
- 테스트가 가능할 정도로 시스템 결합도를 낮추면 유연성과 재사용성도 더욱 높아진다.
- 결합도를 최소로 줄이면 자엽스럽게 또 다른 클래스 설계 원칙인 DIP를 따르는 클래스가 나온다.
