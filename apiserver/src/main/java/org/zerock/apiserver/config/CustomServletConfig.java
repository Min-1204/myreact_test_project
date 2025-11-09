package org.zerock.apiserver.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zerock.apiserver.controller.formatter.LocalDateFormatter;

// 이거를 해주는 이유
// 이거해주면 메소드 오버라이드 할 수 있는것들이 많아진다고 한다.
// CORS 설정을 또 해줘야한다고 한다.

@Configuration
@Log4j2
public class CustomServletConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        log.info("----------------------------");
        log.info("addFormatters가 동작하고 있습니다.");

        registry.addFormatter(new LocalDateFormatter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) { // API 서버 개발
        registry.addMapping("/**") //어떤경로에다가 cors를 적용할 것인가 => /**모든경로
                .allowedOrigins("http://localhost:5173")  // 어디에서부터 들어오는 연결을 허락해줄 것인가 => * 모든곳에서 허락
                .maxAge(500) // 빨리빨리 연결을 안되면 서버에 문제가있는거라고 생각하기때문에 짧게 주었다고한다
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","PUT","DELETE","HEAD","OPTIONS"); // 어떤방식의 호출을 허용할 것인가
        // 제일중요한 OPTIONS는 프리플라이트 미리한번 찔러볼때 날라오는 방식이라고 한다.
    }
}
