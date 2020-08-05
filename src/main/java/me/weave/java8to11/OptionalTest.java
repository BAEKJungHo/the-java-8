package me.weave.java8to11;

import me.weave.java8to11.domain.OnlineClass;
import me.weave.java8to11.domain.Progress;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api", false));

        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
        Duration studyDuration = spring_boot.getProgress().getStudyDuration();
        // System.out.println(studyDuration); // NPE  : progress 가 현재 null 이기 때문이다.

        // optional 사용 전
        // 이런 코드의 문제점은 에러를 만들기 좋은 코드라는 것이다. 왜냐하면 null 을 체크하는 것을 깜빡 할 수 있기 때문이다.
        // 두 번째 문제는 null 을 리턴하는 자체가 문제이다.
        Progress progress = spring_boot.getProgress();
        if(progress != null) {
            System.out.println(progress.getStudyDuration());
        }

        // optional 을 리턴하는 메서드에서 null 을 리턴하면 안된다. 만약 getProgress() 의 리턴타입이 Optional 이면
        // 사용하는 사람은 아마 다음과 같이 null 체크를 안해도 된다는 생각을 하고 코딩할 것이다.
        // 근데 progress1 이 null 이면 에러가 발생한다.
        // 따라서 만약에 정작 반환할게 없다면 Optional.empty(); 를 리턴하면된다.
        Optional<Progress> progress1 = spring_boot.getProgress2();
        progress1.ifPresent(p -> System.out.println(p.getStudyDuration()));

        // 그리고 Container Type : collection, map, stream array, optional 등 은 Optional 로 감싸면 안된다.
        // 왜냐하면 저런 타입들은 비어있을 수 있다는 타입들이기 때문이다.

    }

}

