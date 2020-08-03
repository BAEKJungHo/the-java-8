package me.weave.java8to11.functionalInterfaceImpl;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {

    public static void main(String[] args) {

        /**
         * Stream 은 데이터를 담고 있는 저장소를 의미하는 것이 아니라, 일련의 처리 과정을 의미
         * Stream 에는 크게 중개 오퍼레이션(중간 연산)과, 종료 오퍼레이션(최종 연산)이 있는데
         *
         * 둘 의 가장 큰 차이는 중개 오퍼레이션은 Stream 을 리턴하며, Lazy 하고,
         * 종료 오퍼레이션은 Stream 이 아닌 다른 타입을 리턴한다. 즉, 스트림 파이프라인에서 결과를 도출한다.
         *
         * 컬렉션의 주제는 데이터고, 스트림의 주제는 계산이다.
         */

        // 중개 오퍼레이션 : map ... Lazy
        List<String> names = new ArrayList<>();
        names.add("weave");

        // 이 부분을 실행해도 sysout 은 실행되지 않는다.
        // 중개 오퍼레이션은 종료 오퍼레이션을 실행해야 실행된다. : Lazy (즉, 필요할 때만 = 최종 연산을 수행할 때 값을 계산한다.)
        // 중개 오퍼레이션으로 연결된 연산을 중간 연산이라고한다.
        // 종료 오퍼레이션은 최종 연산이라고 한다.
        names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        });

        /**
         * 스트림 파이프라인 (Stream pipeLine)
         * 중개 오퍼레이션과 종료 오퍼레이션으로 이루어지는데, 마지막은 무조건 종료 오퍼레이션으로 종료 되어야 하며,
         * 종료 오퍼레이션은 스트림 파이프라인에 하나만 존재한다.
         */


        /**
         * 이미 결정난 값에 대한 불필요한 연산을 하지 않음으로 실행 속도를 높이는 기능을 쇼트 서킷( Short Circuit )이라고 한다.
         */

        /**
         * 중간 연산의 가장 큰 특징은 게으르다(Lazy) 다는 것이다.
         * 스트림의 게으른 특성 덕분에 몇 가지 최적화 효과를 얻을 수 있었다.
         * limit 과 쇼트 서킷(short circuit) 기법 덕분에
         * 여러개의 요리중에서 3개만 선택되며 filter 와 map 은 서로 다른 연산이지만 한 과정으로 병합되었다.(이 기법을 루프 퓨전(loop fusion) 이라고 한다.)
         */

    }

}
