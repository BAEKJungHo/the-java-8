package me.weave.java8to11;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeAPITest {

    public static void main(String[] args) {
        // 기계용 API : 메서드 실행시간 비교할 때 쓰임
        Instant instant = Instant.now();
        System.out.println(instant); // 2020-08-09T11:37:46.348363300Z, 기준시 UTC GMT
        System.out.println(instant.atZone(ZoneId.of("UTC")));

        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId);
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        System.out.println(zonedDateTime); // 2020-08-09T20:40:01.170237700+09:00[Asia/Seoul] 컴퓨터 시간과 동일하게 찍힌다.

        // 휴먼용 API
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now); // LocalDateTime 은 서버의 시스템 기본 정보를 참고해서 시간이 쓰이게된다. 만약 배포된 서버가 미국에 있으면 미국 시간이 찍힌다.
        LocalDateTime.of(1994, Month.MAY, 02, 0, 0);
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);

        // 기계용 기간 비교 : Duration
        Instant now1 = Instant.now();
        Instant plus = now1.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now, plus);
        System.out.println(between.getSeconds());

        // 휴먼용 기간 비교 : Period
        LocalDate today = LocalDate.now();
        LocalDate nextYearBirthday = LocalDate.of(2021, Month.MAY, 02);

        Period period = Period.between(today, nextYearBirthday);
        System.out.println(period.getDays());

        Period until = today.until(nextYearBirthday);
        System.out.println(until.get(ChronoUnit.DAYS));

        // DateTimeFormatter
        // 미리 정의해둔 포매팅 : https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#predefined
        LocalDateTime now2 = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now2.format(dateTimeFormatter));
    }

}
