package org.zerock.apiserver.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.TodoDTO;
import org.zerock.apiserver.repositoty.TodoRepository;

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
        for (int i = 0; i < 100 ; i++) {
            TodoDTO todoDTO = TodoDTO.builder()
                    .title("Title..."+ i)
                    .writer("writer" + i)
                    .content("Content..." + i)
                    .dueDate(LocalDate.of(2023, 12, (int)(Math.random() * 29) + 1))
                    .build();

            Long tno = todoService.register(todoDTO);
            log.info(tno);
        }
    }

    @Test
    public void testGetList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        log.info(todoService.getList(pageRequestDTO));
    }

}
