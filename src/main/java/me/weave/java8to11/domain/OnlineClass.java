package me.weave.java8to11.domain;

import java.util.Optional;

public class OnlineClass {

    private Integer id;
    private String title;
    private boolean closed;

    // 인스턴스 필드 타입에 Optional 을 쓰게 되면 이것은 도메인 설계 문제이다.
    // public Optional<Progress> progress;
    // 차라리 Delegation 을 사용 하던가, 상위 클래스 하위 클래스로 쪼개는게 좋다.
    public Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * 자바 8 이전에서는 null 자체를 리턴하는것이 문제가 된다.
     * 따라서 해결방법은 에러를 던지는 방법이 있다.
     * @return
     */
    public Progress getProgress() {
        return progress;
    }

    /**
     * 해결방법 1
     * 문제 1 : 하지만 여기서 예외를 던질 때 UncheckedException 을 던지면 클라이언트 쪽에서 고통이 덜하지만
     * CheckedException 이면 예외처리 코드를 작성해야하기 때문에 고통받는다.
     *
     * 문제 2 : 예외를 아래와 같은 방식으로 던질 때의 문제는 에러가 발생하면 자바는 StackTrace 를 찍는다.
     * 이 에러가 발생하기 전까지의 어떠한 call stack 을 거쳐서 에러가 발생하게 됬는지에 대한 정보를 담게된다.
     * 이 자체로 리소스를 쓰게 되는 것이다.
     * 따라서 꼭 필요할 때 에러를 써야지, 어떠한 로직을 처리하기 위해서 에러를 사용하는 것은 좋은 습관은 아니다.
     *
     * CheckedException 과 UncheckedException
     * UncheckedException 의 대표적인 예는 RuntimeException 이다.
     * UncheckedException 은 예외처리 코드를 작성하지 않아도 되는데 그 이유는 예상치 못한 상황에서 발생하는 예외가 아니라 거의 대부분
     * 개발자가 부주의해서 발생하는 예외이기 때문이다.
     * CheckedException 은 대표적인 예로 IOException 등이 있다.
     * @return
     */
    public Progress getProgress1(){
        if(this.progress == null) {
            throw new IllegalStateException();
        }
        return progress;
    }

    /**
     * 자바 8 부터는 비어 있을 수 경우에 Optional 로 감싸서 처리할 수 있다.
     */
    public Optional<Progress> getProgress2() {
        return Optional.of(progress);
    }

    /**
     * Optional 은 맵의 키 타입, 매개변수 타입, 인스턴스 필드 타입 등으로 쓸 수는 있기는 한데
     * 권장 사항은 리턴 타입에만 쓰는 것이다.
     *
     * 맵의 키 타입에 Optional 을 쓰면 안되는 이유가 map 의 기본적인 철학을 깨트린다.
     * map 의 가장 중요한 특징은 key 는 null 이면 안된다.
     */
    public void setProgress1(Optional<Progress> progress) {
        progress.ifPresent(p -> this.progress = p); // ifPresent 값이 있으면 이라는 조건이다. 아래에 있는 코드를 이 코드로 대체한다 하더라도 위험하다.
        // 왜 위험하냐면 클라이언트쪽 코드에서 spring_boot.setProgress(null); 이런식으로 null 값을 넣어버리면 이 코드에서 NPE 가 발생한다.
        // 따라서 Optional 을 쓰는 의미가 없어지고 더 번거로워진다.
        // 즉, 아래 처럼 코드가 더 추가 된다.

        if(progress != null) {
            progress.ifPresent(p -> this.progress = p);
        }


        // this.progress = progress.get(); // 이 코드 한 줄만 쓸 수 없다. 체크를 해야한다.
    }

    /**
     * Primitive 타입을 위한 Optional 도 존재한다.
     *
     * Optional.of(10); 이런식으로 Primitive 타입을 넣을 수는 있는데, 안에서 박싱 언박싱이 일어나서 성능이 좋지않다.
     * 따라서
     * OptionalInt.of(10) 이런식으로 해야 좋다.
     */

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

}
