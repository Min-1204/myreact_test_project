package org.zerock.apiserver.service;

import jakarta.transaction.Transactional;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.TodoDTO;

@Transactional
public interface TodoService {

    // 조회
    TodoDTO get(Long tno); // DTO 반환
    // Todo정보를 담은 DTO 이다.
    // 매개변수로 Long 의 tno 이란 아이

    // 등록
    Long register(TodoDTO dto); // 번호
    // TodoDTO 등록할 Todo 정보
    // 반환타입이 Long 이다.

    // 수정
    void modify(TodoDTO dto);
    // void 이므로 반환값 없음.

    // 삭제
    void remove(Long tno);
    // void 이므로 반환값 없음.

    // PageRequestDTO를 받아서 return PageResponseDTO를 반환하게하는 메서드
    PageResponseDTO<TodoDTO> getList (PageRequestDTO pageRequestDTO);


    // 원래 인터페이스는 기능을 못가지는 실체가 없는데
    // default라는 기능이 추가되었다. 기능이나 메서드를 선언해줄 수 있게되었다.
    // 추상클래스가 애매해 졌지만, 지금은 이런 기능은 좋다.
    default TodoDTO entityToDTO(Todo todo) { // Entity를 DTO로 변환해줄 메서드다.
        // 기존엔 ModelMapper를 사용했다.

        return TodoDTO.builder()
                .tno(todo.getTno())
                .title(todo.getTitle())
                .writer(todo.getWriter())
                .content(todo.getContent())
                .complete(todo.isComplete())
                .dueDate(todo.getDueDate())
                .build();

    }

    default Todo dtoToEntity(TodoDTO todoDTO) { // Entity를 Todo로 변환해줄 메서드다.
        // 기존엔 ModelMapper를 사용했다.

        return Todo.builder()
                .tno(todoDTO.getTno())
                .title(todoDTO.getTitle())
                .writer(todoDTO.getWriter())
                .content(todoDTO.getContent())
                .complete(todoDTO.isComplete())
                .dueDate(todoDTO.getDueDate())
                .build();

    }

}
