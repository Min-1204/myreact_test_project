package org.zerock.apiserver.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.TodoDTO;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

    @Autowired // 의존성 주입(Dependency Injection, DI)을 자동으로 해주는 어노테이션입니다.
    TodoService todoService;


    @Test
    public void testGet () {
        Long tno = 1L;

        log.info(todoService.get(tno));
    }

    @Test
    // 등록
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("Title...")
                .content("Content...")
                .dueDate(LocalDate.of(2023,12,31))
                .build();

        log.info(todoService.register(todoDTO));
    }

//    @Test


}
