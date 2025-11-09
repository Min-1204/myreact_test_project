package org.zerock.apiserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.TodoDTO;
import org.zerock.apiserver.repositoty.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
// Spring Container에 등록 - "이 클래스는 Spring이 관리할게"
// "나는 서비스 계층이야, Spring아 나를 관리해줘!"

@Log4j2
@RequiredArgsConstructor  // lombok 어노테이션으로 자동으로 생성자를 만들어준다. 그 이후 의존성 주입은 Spring이 한다.
public class TodoServiceImpl implements TodoService {
    // TodoService는 의존성주입이 필요한애다? 인터페이스인데 왜 새로운게 나오는거지? 생성자를 써야했던거 아닌가요?
    // TodoRepository가 없으면 제대로 동작할 수 가 없어서

    private final TodoRepository todoRepository;
    // @RequiredArgsConstructor 어노테이션으로 자동생성 되고 의존성주입도 Spring이 해줬기때문에 사용 가능

    @Override
    // 조회
    public TodoDTO get(Long tno) {

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        return entityToDTO(todo);
    }

    // 등록
    @Override
    public Long register(TodoDTO dto) {

        Todo todo = dtoToEntity(dto);
        Todo result = todoRepository.save(todo);
        return result.getTno();
    }

    @Override
    public void modify(TodoDTO dto) {
        Optional<Todo> result = todoRepository.findById(dto.getTno());
        // DB에 dto를 가지고 와서

        Todo todo = result.orElseThrow(); // 그 데이터베이스를 꺼내와서

        todo.changeTitle(dto.getTitle()); // 그 데이터베이스에 있는 데이터를 바꿔줘서
        todo.changeWriter(dto.getWriter());
        todo.changeContent(dto.getContent());
        todo.changeComplete(dto.isComplete());
        todo.changeDueDate(dto.getDueDate());

        todoRepository.save(todo);  // 그 바뀐 데이터를 Save 한다.

    }

    @Override
    public void remove(Long tno) {

        todoRepository.deleteById(tno);

    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {

        // JPA 관련된 처리를 해야한다.
        Page<Todo> result = todoRepository.search1(pageRequestDTO);

        // TodoList => TodoDTOList가 되야한다.
        // result.get()  하면 todo들의 목록이 나온다 => 얘를 가져다가 변환을 해줘야한다
        List<TodoDTO> dtoList = result.get().map(todo -> entityToDTO(todo)).collect(Collectors.toList());

        PageResponseDTO<TodoDTO> responseDTO =
                PageResponseDTO.<TodoDTO>withAll()
                        .dtolist(dtoList)
                        .pageRequestDTO(pageRequestDTO)
                        .total(result.getTotalElements())
                        .build();

        return responseDTO;
    }
}


// 실행은 서비스가 데이터베이스에서 꺼내오는건 레포지토리가
