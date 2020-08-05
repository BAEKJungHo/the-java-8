package me.weave.java8to11;

import me.weave.java8to11.domain.OnlineClass;
import me.weave.java8to11.domain.Progress;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class OptionalAPITest {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api", false));

        // optional 을 return 하는 종료형 operation
        Optional<OnlineClass> onlineClass = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        boolean present = onlineClass.isPresent();
        boolean present1 = onlineClass.isEmpty(); // isEmpty() 는 자바 11 부터 제공한다.

        OnlineClass onlineClass1 = onlineClass.get(); // get 으로 값을 꺼낼 수 있다.
        // get 을 사용할 때 값이 있으면 꺼내 쓸 수 있지만,  값이 없으면 NoSuchElementException 이 발생한다.
        // 따라서 아래와 같이 수정한다.
        // 비추천 : get() + isPresent()
        if(onlineClass.isPresent()) {
            OnlineClass onlineClass2 = onlineClass.get();
            System.out.println(onlineClass2.getTitle());
        }

        // 하지만 위 방식은 추천하는 방식이 아니다. (즉, isPresent 는 추천하지 않는다.)
        // Optional 에 뭔가가 있어서 해야한다면, 아래에 소개하는 방식들을 추천한다.

        // 1. ifPresent : 값이 있으면 그것을 가지고 OO 을 하라
        onlineClass.ifPresent(oc -> System.out.println(oc.getTitle()));

        // 2. orElse : 값이 있으면 가져오고 orElse 에 넘긴 값을 리턴하라, 없으면 orElse 에 넘긴 값을 리턴하라.
        OnlineClass onlineClass3 = onlineClass.orElse(createNewClass()); // 즉, 값이 있으면 가져오고 + 괄호 안의 값을 리턴한다.
        System.out.println(onlineClass3.getTitle());

        // 3. orElseGet : 값이 있으면 가져오고, 없으면 orElseGet 에 넘긴 값을 리턴하라.
        // 동적인 행동은 orElseGet  이 적합하고, 상수와 같이 정적인 애들은 orElse 가 적합하다.
        OnlineClass onlineClass4 = onlineClass.orElseGet(OptionalAPITest::createNewClass);
        System.out.println(onlineClass3.getTitle());

        // 4. orElseThrow : 값이 있으면 가져오고, 없으면 NoSuchElementException 을 던져라.
        // 만약 원하는 에러가 있다면 Supplier 에 제공해주면된다.
        OnlineClass onlineClass5 = onlineClass.orElseThrow(() -> {
            return new IllegalArgumentException();
        });
        OnlineClass onlineClass6 = onlineClass.orElseThrow(IllegalStateException::new);

        // 5. filter : 값을 걸러내는 옵션인데 (값이 있다는 가정하에 동작한다.) 값이 없는 경우에는 아무일도 일어나지 않는다. '
        // filter 결과는 Optional 이다.
        Optional<OnlineClass> onlineClass7 = onlineClass
                .filter(Predicate.not(OnlineClass::isClosed));
        System.out.println(onlineClass7.isEmpty()); // 값이 비어있으면 true

        // 6. map
        Optional<Integer> integer = onlineClass.map(OnlineClass::getId);
        System.out.println(integer.isPresent()); // 값이 있으면 true

        // 만약 map 으로 꺼내는 타입이 optional 이면 ?
        // 양파 껍질 까듯이 까내야한다.
        Optional<Optional<Progress>> progress = onlineClass.map(OnlineClass::getProgress2);
        Optional<Progress> progress1 = progress.orElseThrow();
        progress1.orElseThrow();

        // 7. flatMap : 위 경우를 해결 할 수 있다.
        Optional<Progress> progress2 = onlineClass.flatMap(OnlineClass::getProgress2);
        progress2.orElseThrow();

        // 스트림의 flatMap 은 인풋은 한 개인데, 아웃풋이 여러개인 경우에 주로 사용한다.
    }

    private static OnlineClass createNewClass() {
        return new OnlineClass(10, "New class", false);
    }

}
