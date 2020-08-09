package me.weave.java8to11;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTest {

    public static void main(String[] args) {
        // 자바 8 이전 Date 의 문제점 1. 작명이 정확하지 않다.
        Date date = new Date();

        //  EPOCK : January 1, 1970, 00:00:00 GMT
        // 즉, getTime() 은 EPOCK time 을 리턴한다.
        // result : 1592779348765 (기계용 시간이다.)
        // 기계용 시간은 Instant 로 나타낼 수 있다.
        long time = date.getTime(); // 날짜에서 시간을 꺼낸다. (작명이 정확하지 않다.)

        // 오라클 DB 테이블 컬럼명도 regDate 가 아닌 regDateTime 이 더 맞는거 같다.

        // 자바 8 이전 Date 의 문제점 2. mutable 하다.
        // 따라서, 멀티스레드 환경에서 안전하게 사용하기 어렵다.
        try {
            Thread.sleep(1000 * 3);
            Date after3Seconds = new Date();
            System.out.println(after3Seconds);
            after3Seconds.setTime(time); // 값이 바뀐다.
            System.out.println(after3Seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 자바 8 이전 Date 의 문제점 2. GregorianCalender 문제  (1월 값은 0이다. = Calender.JANUARY 는  0이다.)
        // 또한 type safety 하지 않다. 생성자 매개변수로 int 를 받는데, 음수 값도 들어갈 수 있기 때문이다.
        // type safety 하게 바꾸려면 각 매개변수에 year 라는 enum 타입, month 라는 enum 타입을 받게 바꿔야한다.
        Calendar weaveBirthday = new GregorianCalendar(1994, Calendar.MAY, 2);

        // 문제점 : 위에서 Date 타입에서 getTime 하면 long 이 나오고, Calender 에서 getTime() 을 하면 Date 가 나온다.
        System.out.println(weaveBirthday.getTime());

        // 자바 8 LocalDate 의 Immutable
        // Month Enum 클래스의 상수값은 영어랑 숫자랑 정확히 일치한다.
        LocalDate dateOfBirth = LocalDate.of(2012, Month.MAY, 14);
        LocalDate firstBirthday = dateOfBirth.plusYears(1);
    }

}
