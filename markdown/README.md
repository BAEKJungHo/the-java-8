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
