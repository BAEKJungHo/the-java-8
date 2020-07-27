# 더 자바 : Java 8

## 함수형 인터페이스와 람다 표현식

- `함수형 인터페이스 (Functional Interface)`
   - 추상 메서드를 딱 하나만 가지고 있는 인터페이스 = SAM(Single Abstract Method) 인터페이스
   - 여러개의 디폴트 메서드가 있어도 추상 메서드가 하나이면 함수형 인터페이스이다.
   - @FunctionalInterface 어노테이션을 가지고 있는 인터페이스

- `람다 표현식(Lambda Expressions)`
   - 함수형 인터페이스의 인스턴스를 만드는 방법으로 쓰일 수 있다.
   - 코드를 줄일 수 있다.
   - 메서드 매개변수, 리턴 타입, 변수로 만들어 사용할 수도 있다.
 
- `자바에서 함수형 프로그래밍`
  - 함수를 First class Object 로 사용할 수 있다.
    - 순수 함수(Pure Function)
    - 사이드 이펙트가 없다.(None Side Effect) (함수 밖에 있는 값을 변경하지 않는다.)
    - 상태가 없다. (함수 밖에 있는 값을 사용하지 않는다.)
  - 고차 함수(Higher-Order Function)
    - 함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수도 있다.
  - 불변성(Immutability)

## Java가 기본으로 제공하는 함수형 인터페이스

- java.lang.function 패키지
   - Function<T, R>
      - T 타입을 받아서 R 타입을 리턴하는 함수 인터페이스
      - R apply(T t)
      - 함수 조합용 메서드
         - andThen
         - compose
   - BiFunction<T, U, R>
      - 두 개의 값 (T, U) 를 받아서 R 타입을 리턴하는 함수 인터페이스
      - R apply(T t, U u)
   - Consumer<T>
      - T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
      - void Accept(T t)
      - 함수 조합용 메서드
      - andThen
   - Supplier<T>
      - T 타입의 값을 제공하는 함수 인터페이스
      - T get()
   - Predicate<T>
      - T 타입의 값을 받아서 boolean 을 리턴하는 함수 인터페이스
      - boolean test(T t)
      - 함수 조합용 메서드
         - And
         - Or
         - Negate
   - UnaryOperator<T>
      - Function<T, R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 함수 인터페이스
   - BinaryOperator<T>
      - BiFunction<T, U, R>의 특수한 형태로, 동일한 타입의 입렵값 두개를 받아 리턴하는 함수 인터페이스

## 람다 표현식

- 람다
   - (매개변수 리스트) -> { 바디 };
- 매개변수 리스트
   - 인자가 없을 때 : ()
   - 인자가 한 개일 때 : (one) 또는 one
   - 인자가 여러개 일 때 : (one, two) 
   - 인자의 타입은 생략 가능, 컴파일러가 추론(infer) 하지만 명시할 수도 있다. (Integer one, Integer two)
- 바디
   - 화살표 오른쪽에 함수의 본문을 정의한다.
   - 여러 줄인 경우에 중괄호({})를 사용해서 묶는다.
   - 한 줄인 경우네는 생략 가능, return 도 생략 가능
- 변수 캡처(Varaiable Capture)
   - 로컬 변수 캡처
      - final 이거나 effective final 인 경우에만 참조할 수 있다.
      - 그렇지 않을 경우 concurrency 문제가 생길 수 있어서 컴파일러가 방지한다.
   - effective final
      - 이것도 역시 자바 8 부터 지원하는 기능으로 사실상 final 인 변수
      - final 키워드 사용하지 않은 변수를 익명 클래스 구현체 또는 람다에서 참조할 수 있다.
   - 익명 클래스 구현체와 달리 `쉐도윙` 하지 않는다.
      - 익명 클래스는 새로 스콥을 만들지만, 람다는 람다를 감싸고 있는 스콥과 같다.
 
> https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html#shadowing

## 메서드 레퍼런스

람다가 하는 일이 기존 메서드 또는 생성자를 호출하는 거라면, 메서드 레퍼런스를 사용해서 매우 간결하게 표현할 수 있따.

```java
- 클래스::메서드 // 정적(static) 메서드 참조
- 객체래퍼런스::메서드 // 인스턴스 메서드 참조
- 클래스::인스턴스메서드 // 임의 객체의 인스턴스 메서드 참조
   - a의클래스:인스턴스메서드 // 매개변수의 메서드 참조 = (a, b) -> a.a메소드(b)
- 클래스::new // 생성자 참조 = (a, b) -> {return new 클래스(a,b);}
```

## 인터페이스 디폴트 메서드와 스태틱 메서드

- 디폴트 메서드(default method)
   - 인터페이스에 메서드 선언이 아니라 구현체를 제공하는 방법
   - 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새 기능을 추가할 수 있다.
   - 기본 메서드는 구현체가 모르게 추가된 기능으로 그만큼 리스크가 있다.
      - 컴파일 에러는 아니지만 구현체에 따라 런타임 에러가 발생할 수 있다.
      - 반드시 문서화 할 것(`@implSpec` javadoc 태그 사용)
   - Object 가 제공하는 기능(equals, hasCode)은 기본 메서드로 제공할 수 없다.
      - 구현체가 재정의해야 한다.
   - 본인이 수정할 수 있는 인터페이스에만 기본 메서드를 제공할 수 있다.
   - 인터페이스를 상속받는 인터페이스에서 다시 추상 메서드로 변경할 수 있다.
   - 인터페이스 구현체가 재정의 할 수도 있다.
   
- 스태틱 메서드
   - 해당 타입 관련 헬퍼 또는 유틸리티 메서드를 제공할 때 인터페이스에 스태틱 메서드를 제공할 수 있다.
   
> https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html
>
> https://docs.oracle.com/javase/tutorial/java/IandI/nogrow.html

## 자바 8에서 추가한 디폴트 메서드로 인한 API 변화

- Iterable 디폴트 메서드
   - forEach()
   - spliterator()
- Collection 디폴트 메서드
   - stream() / parallelStream()
   - removeSelf(Predicate)
   - spliterator()
- Comparator 디폴트 메서드 및 스태틱 메서드
   - reversed()
   - thenComparing()
   - static reverseOrder() / naturalOrder()
   - static nullsFirst() / nullsLast()
   - static comparing()
   
## 디폴트 메서드를 쓰면서 얻는 이점

Java 8 이 나오기 이전에는 아래와 같은 방식을 사용했다.

- FooInterface 
   - a 추상 메서드
   - b 추상 메서드
   - c 추상 메서드
- FooAbstract
   - FooInteface 를 구현하고, 바디가 비어있는 메서드를 구현한다.
   - a 메서드
   - b 메서드
   - c 메서드
- A class
   - a 메서드 오버라이딩
- B class
   - b 메서드 오버라이딩
- ...
   
즉, 원하는 메서드만 사용하게 하기위해서 이런 방식을 썼는데, 이렇게 쓰면 단점이 상속을 사용하기 때문에, 추상 메서드만 상속받고 나머지는 상속받지 못한다.

Java 8 이후 부터는 디폴트 메서드가 등장해서 아래와 같아진다.

- FooInterface
   - a 디폴트 메서드
   - b 디폴트 메서드
   - c 디폴트 메서드
- A class, B class, C class 에서 FooInterface 를 구현

이렇게 되면 인터페이스를 구현한것이기 때문에, 상속으로부터 자유로워 진다. 이러한 특징을 `비침투성(none-invasive)` 이라고 하고, 스프링에서는 이러한 접근 방식을 좋아한다.

> 즉, 디폴트 메서드를 사용하면서 장점은 상속으로부터 자유로워지고, 코드도 간결해진다.

자바 8 이전에는 스프링 시큐리티에서 WebMvcConfigurerAdapter 를 사용하였는데 스프링 5.0 부터는 자바 8을 사용해야해서 WebMvcConfigurerAdapter 가 deprecated 되었다. 
대신 WebMvcConfigurerAdapter 에서 추상 메서드로 제공한 메서드들을 WebMvcConfigurer 에서 default 메서드로 제공한다.
