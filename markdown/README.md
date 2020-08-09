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

## Stream

- sequence of elements supporting sequential and parallel aggregate operations
- 데이터를 담고 있는 저장소(컬렉션)이 아니다.
- Functional in nature, 스트림이 처리하는 데이터 소스를 변경하지 않는다.
- 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
- 무제한 일 수도 있다. (Short circuit 메서드를 사용해서 제한할 수 있다.)
- 중개 오퍼레이션은 근본적으로 lazy 하다.
- 손쉽게 병렬 처리할 수 있다.

### 스트림 파이프라인

- 0 또는 다수의 중개 오퍼레이션(intermediate operation)과 한 개의 종료 오퍼레이션(terminal operation)으로 구성한다.
- 스트림의 데이터 소스는 오직 터미널 오퍼레이션을 실행할 때만 처리한다.

### 중개 오퍼레이션(중간 연산)

- Stream 을 리턴한다.
- Stateless / Stateful 오퍼레이션으로 더 상세하게 구분할 수도 있다. (대부분은 Stateless 지만, distinct 나 sorted 처럼 이전 소스 데이터를 참조해야 하는 오퍼레이션은 Stateful 오퍼레이션이다.)
- filter, map, limit, skip, sorted ...

### 종료 오퍼레이션(최종 연산)

- Stream 을 리턴하지 않는다.,
- collect, allMatch, count, forEach, min, max ...

> https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
> 
> https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html

## Stream API

- 걸러내기
   - Filter(Predicate)
- 변경하기
   - Map(Function) 또는 FlatMap(Function)
   - ex) 각각의 post 인스턴스에서 String title 만 새로운 스트림으로
   - ex) List<Stream<String>> 을 String 의 스트림으로
- 생성하기
   - generate(Supplier) 또는 Iterate(T seed, UnaryOperator)
   - ex) 10부터 1씩 증가하는 무제한 숫자 스트림
   - ex) 랜덤 int 무제한 스트림
- 제한하기
   - limit(long) 또는 skip(loing)
   - ex) 최대 5개의 요소가 담긴 스트림을 리턴한다.
   - ex) 앞에서 3개를 뺀 나머지 스트림을 리턴한다.
- 스트림에 있는 데이터가 특정 조건을 만족하는지 확인
   - anyMatch(), allMatch(), noneMatch()
   - ex) k 로 시작하는 문자열이 있는지 확인한다. (true 또는 false 를 리턴)
   - ex) 스트림에 있는 모든 값이 10보다 작은지 확인한다.
- 개수세기
   - count
   - ex) 10보다 큰 수의 개수를 센다.
- 스트림을 데이터 하나로 뭉치기
   - reduce(identity, BiFunction), collect(), sum(), max()
   - ex) 모든 데이터를 하나의 List 또는 Set 에 옮겨 담기.

## Optional

- 자바 프로그래밍에서 NullPointerException 을 종종 보게 되는 이유
   - null 을 리턴하니까 && null 체크를 깜빡했으니까

- 메서드에서 작업 중 특별한 상황에서 값을 제대로 리턴할 수 없는 경우 선택할 수 있는 방법
   - 예외를 던진다. (비싸다. 스택트레이스를 찍어두니까.)
   - null 을 리턴한다. (비용 문제가 없지만 그 코드를 사용하는 클라이언트 코드가 주의해야한다.)
   - Optional 을 리턴한다. (클라이언트 코드에게 명시적으로 빈 값일 수도 있다는 걸 알려주고, 빈 값인 경우에 대한 처리를 강제한다.)
   
- Optional
   - 오직 값 한 개가 들어있을 수도 없을 수도 있는 컨테이너.
   
- `주의할 것`
   - 리턴값으로만 쓰기를 권장한다. (메서드 매개변수 타입, 맵의 키 타입, 인스턴스 필드 타입으로 쓰지 말자.)
   - Optioanl 을 리턴하는 메서드에서 null 을 리턴하지 말자.
   - Primitive 타입용 Optional 은 따로 있다. OptionalInt, OptionalLong 등
   - Collection, Map, Stream Array, Optional 은 Optional 로 감싸지 말것.
   

> https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
>
> https://www.oracle.com/technical-resources/articles/java/java8-optional.html
>
> 이팩티브 자바 3판, 아이템 55 적절한 경우 Optional을 리턴하라.

## Optional API

- Optional 만들기
   - Optional.of()
   - Optional.ofNullable()
   - Optional.empty()
   
- Optional 에 값이 있는지 없는지 확인하기
   - isPresent()
   - isEmpty() : 자바 11 부터 제공
 
 - Optional 에 있는 값 가져오기
   - get()
   - 만약에 비어있는 Optional 에서 무언가를 꺼낸다면?
   
 - Optional 에 값이 있는 경우에 그 값을 가지고 -- 를 하라.
   - ifPresent(Consumer)
   - ex) Spring 으로 시작하는 수업이 있으면 id 를 출력하라.
   
- Optional 에 값이 있으면 가져오고, 또한 T 에 전달된 행동을 리턴한다, 없으면 T 에 전달된 행동을 리턴하라.
   - orElse(T)
   - T 에 createNewClass 와 같은 메서드가 있다면, Optional 에 값이 있든 없든 T 위치에 있는 것은 무조건 실행된다.
   - ex) JPA 로 시작하는 수업이 없다면 비어있는 수업을 리턴하라.

- Optional 에 값이 있으면 가져오고 없는 경우에 -- 를 하라.
   - orElseGet(Supplier)
   - ex) JPA 로 시작하는 수없이 없다면 새로 만들어서 리턴하라.
 
 - Optional 에 값이 있으면 가져오고 없는 경우에 에러를 던져라
   - orElseThrow()
 
 - Optional 에 들어있는 값 걸러내기
   - Optional filter(Predicate)
   
 - Optional 에 들어있는 값 변환하기
   - Optional map(Function)
   - Optional flatMap(Function) : Optional 안에 들어있는 인스턴스가 Optional 인 경우에 사용하면 편하다.
   
## Date 와 Time API

- 자바 8에 새로운 날짜와 시간 API가 생긴 이유
   - 그전까지 사용하던 java.util.Date 클래스는 mutable 하기 때문에 thead safe 하지 않다.
   - 클래스 이름이 명확하지 않다. Date인데 시간까지 다룬다.
   - 버그 발생할 여지가 많다. (타입 안정성이 없고, 월이 0부터 시작한다거나..)
   - 날짜 시간 처리가 복잡한 애플리케이션에서는 보통 Joda Time 을 쓰곤했다.

- 자바 8에서 제공하는 Date-Time API
   - JSR-310 스팩의 구현체를 제공한다.
   -  디자인 철학
      - Clear
      - Fluent
      - Immutable
      - Extensible

- 주요 API
   - 기계용 시간 (machine time)과 인류용 시간(human time)으로 나눌 수 있다.
   - 기계용 시간은 EPOCK(1970년 1월 1일 0시 0분 0초)부터 현재까지의 타임스탬프를 표현한다.
   - 인류용 시간은 우리가 흔히 사용하는 연,월,일,시,분,초 등을 표현한다.
   - 타임스탬프는 Instant 를 사용한다.
   - 특정 날짜(LocalDate), 시간(LocalTime), 일시(LocalDateTime)를 사용할 수 있다.
   - 기간을 표현할 때는 Duration(시간 기반)과 Period(날짜 기반)를 사용할 수 있다.
   - DateTimeFormatter를 사용해서 일시를 특정한 문자열로 포매팅할 수 있다.

- 참고
   - https://codeblog.jonskeet.uk/2017/04/23/all-about-java-util-date/
      - 자바 8 이전에 사용했던 Date 관련 함수들이 어떤 것들이 불편한지 자세하게 
   - https://docs.oracle.com/javase/tutorial/datetime/overview/index.html
   - https://docs.oracle.com/javase/tutorial/datetime/iso/overview.htm

- 지금 이 순간을 기계 시간으로 표현하는 방법
   - Instant.now() : 현재 UTD (GMT) 를 리턴한다.
   - Universal Time Coordinated == Greenwich Mean Time

```java
Instant now = Instant.now();
System.out.println(now);
System.out.println(now.atZone(ZoneId.of("UTC")));
ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
System.out.println(zonedDateTime);
```

- 인류용 일시를 표현하는 방법
   - LocalDateTime.now() : 현재 시스템 Zone 에 해당하는 로컬 일시를 리턴한다.
   - LocalDateTime.of(int, Month, int, int, int, int) : 로컬의 특정 일시를 리턴한다.
   - ZonedDateTime.of(int, Month, int, int, int, int, ZoneId) : 특정 Zone 의 특정 일시를 리턴한다.
   
- 기간을 표현하는 방법
   - Period / Duration .between()

```java
Period between = Period.between(today, birthDay);
System.out.println(between.get(ChronoUnit.DAYS));
```

- Parsing or Formatting
   - 미리 정의해둔 포맷 참고
   - https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#predefined
   - LocalDateTime.parse(String, DateTimeFormatter);
   - DateTime

```java
DateTimeFormatter formatter =
DateTimeFormatter.ofPattern("MM/d/yyyy");
LocalDate date = LocalDate.parse("07/15/1982", formatter);
System.out.println(date);
System.out.println(today.format(formatter));
```

- 레거시 API 지원
   - GregorianCalendar와 Date 타입의 인스턴스를 Instant나 ZonedDateTime으로 변환 가능.
   - java.util.TimeZone에서 java.time.ZoneId로 상호 변환 가능.

```java
ZoneId newZoneAPI = TimeZone.getTimeZone("PST").toZoneId();
TimeZone legacyZoneAPI = TimeZone.getTimeZone(newZoneAPI);
Instant newInstant = new Date().toInstant();
Date legacyInstant = Date.from(newInstant);
```

## CompletableFuture

- Concurrent 소프트웨어
   - 동시에 여러 작업을 할 수 있는 소프트웨어
   - ex) 웹 브라우저로 유튜브를 보면서 키보드로 문서에 타이핑을 할 수 있다.
   - ex) 녹화를 하면서 인텔리 J 로 코딩을 하고 워드에 적어둔 문서를 보거나 수정할 수 있다.
   
- 자바에서 지원하는 Concurrent Programming
   - 멀티 프로세싱(ProcessBuilder)
   - 멀티쓰레드
   
- 자바 멀티쓰레드 프로그래밍  
   - Thread / Runnable
   
- Thread 상속

```java
public static void main(String[] args) {
   HelloThread helloThread = new HelloThread();
   helloThread.start();
   System.out.println("hello : " + Thread.currentThread().getName());
}

static class HelloThread extends Thread {
   @Override
   public void run() {
      System.out.println("world : " + Thread.currentThread().getName());
   }
}
```

- Runnable 구현 또는 람다

```java
Thread thread = new Thread(() -> System.out.println("world : " + Thread.currentThread().getName()));
thread.start();
System.out.println("hello : " + Thread.currentThread().getName());
```

- 스레드 주요 기능
   - 현재 쓰레드 멈춰두기(sleep) : 다른 쓰레드가 처리할 수 있도록 기회를 주지만 그렇다고 락을 놔주진 않는다.(잘못하면 데드락 걸림)
   - 다른 쓰레드 깨우기(interrupt) : 다른 쓰레드를 깨워서 interruptedException 을 발생 시킨다. 그 에러가 발생했을 때 할 일은 코딩하기 나름. 종료시킬 수도 있고, 계속 하던 일 할 수도 있다.
   - 다른 쓰레드 기다리기(join) : 다른 쓰레드가 끝날 때까지 기다린다.
   
> https://docs.oracle.com/javase/tutorial/essential/concurrency/
>
> https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#interrupt--

## Executors

- 고수준(High-Level) Concurrency 프로그래밍 
   - 쓰레드를 만ㄷ르고 관리하는 작업을 애플리케이션에서 분리
   - 그런 기능을 Executors 에게 위임
   
- Executors 가 하는 일
   - 쓰레드 만들기 : 애플리케이션이 사용할 쓰레드 풀을 만들어 관리한다.
   - 쓰레드 관리 : 쓰레드 생명 주기를 관리한다.
   - 작업 처리 및 실행 : 쓰레드로 실행할 작업을 제공할 수 있는 API 를 제공한다.
   
 - 주요 인터페이스
   - Executor : execute(Runnable)
   - ExecutorService : Executor 상속 받은 인터페이스로, Callable 도 실행할 수 있으며, Executor 를 종료 시키거나, 여러 Callable 을 동시에 실행하는 등의 기능을 제공한다.
   - ScheduledExecutorService : ExecutorService 를 상속 받은 인터페이스로 특정 시간 이후에 또는 주기적으로 작업을 실행할 수 있다.
   
 - ExecutorService 로 작업 실행하기
 
 ```java
ExecutorService executorService = Executors.newSingleThreadExecutor();
   executorService.submit(() -> {
   System.out.println("Hello :" + Thread.currentThread().getName());
});
```

- ExcutorService 로 멈추기

```java
executorService.shutdown(); // 처리중인 작업 기다렸다가 종료
executorService.shutdownNow(); // 당장 종료
```

- Fork Join Framework
   - ExecutorService 의 구현체로 손쉽게 멀티 프로세스를 활용할 수 있게끔 도와준다.
   
> https://docs.oracle.com/javase/tutorial/essential/concurrency/executors.html
