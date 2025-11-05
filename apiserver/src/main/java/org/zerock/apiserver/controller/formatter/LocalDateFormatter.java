package org.zerock.apiserver.controller.formatter;


import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// Spring에서 제공해주는 Formatter를 이용해서 처리하면된다.
// 모든 날짜처리
// ★ Formatter를 만들어주면 이 Formatter를 사용할 수 있게끔 등록을 해줘야 한다고 한다.
// 이때 필요한게 Configuration이란걸 잡아줘야한다.
// 특히 웹쪽 잡을때는 웹MVC컨피규러를 사용한다.
public class LocalDateFormatter implements Formatter<LocalDate> {

    // text를 가져다가 Localdate로 바꿔줄꺼야
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {

        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // 패턴추가로 yyyy MM은대문자 dd
    }

//    LocalDate로갔다가 어떻게 문자열로 바꿔줄꺼야
    @Override
    public String print(LocalDate object, Locale locale) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object); // 문자열로 나오게된다.
    }
}
