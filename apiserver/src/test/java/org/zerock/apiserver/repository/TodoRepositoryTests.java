package org.zerock.apiserver.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.repositoty.TodoRepository;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired // 이거안해주면 인식을 못한다고한다. 주입을 못받는다?
    private TodoRepository todoRepository;

    @Test
    public void test1() {

        Assertions.assertNotNull(todoRepository); // 배포할때는 이게 있는게 맞다라고한다? 이걸 이용해서 체크하는 과정?

        log.info(todoRepository.getClass().getName()); // 클래스의 이름을 찍어봐

    }

    @Test
    public void testInsert() {

        Todo todo = Todo.builder() // final로 써야 더 안전한 파일이된다?
                .title("Title")   // 강의 영상에서 pk ? fk? 에대해서 이야기를했는데 pk는 지정이안된다? 하는게없다? 음?
                .content("Content...")
                .dueDate(LocalDate.of(2023,12,30))
                .build();

        Todo result = todoRepository.save(todo);
        // save를 하면 리턴타입이 entity로 나온다.
        // 그게 저장한 결과가 나온다.
        // 여기에 pk값이 있다면? 얘는 select가 한번 달린다? JPA목적자체가 관계형데이터베이스를 관리하는게 목적
        // 항상 뭔가를 변경하거나 수정했을때는 데이터베이스가 동기화하려고 노력한다?

        log.info(result);

        // 오토인크리먼트? 자동으로 번호가 따진다? 생성된다?
        // 인설트문도 자동으로 실행된다?

    }


    @Test
    public void testRead() {

        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();
//         문제있으면 예외 던지고 아니면 todo 끄집어 내줘

        log.info(todo);

    }

    @Test
    public void testUpdate () {
        // 일단 먼저 로딩하고, 엔티티객체를 변경 /setter를 쓰게된다.

        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        todo.changeTitle("Update Tile");
        todo.changeContent("updated content");
        todo.changeComplete(true);

        todoRepository.save(todo);

    }

    @Test
    public void testPaging() {

        //페이지 번호는 0부터
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(result.getTotalElements()); // getTotalElements 전체데이터 갯수 확인

        log.info(result.getContent()); // 현재페이지의 실제 데이터가 무엇인지
    }


//    @Test
//    public void testSearch1 () {
//
//        todoRepository.search1();
//
//    }

}
