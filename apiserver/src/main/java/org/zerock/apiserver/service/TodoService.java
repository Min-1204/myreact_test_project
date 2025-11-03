package org.zerock.apiserver.service;

import jakarta.transaction.Transactional;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.TodoDTO;

@Transactional
public interface TodoService {

    // 조회
    TodoDTO get(Long tno); // DTO 반환

    // 등록
    Long register(TodoDTO dto); // 번호

    // 수정
    void modify(TodoDTO dto);

    // 삭제
    void remove(Long tno);


    // 원래 인터페이스는 기능을 못가지는 실체가 없는데
    // default라는 기능이 추가되었다. 기능이나 메서드를 선언해줄 수 있게되었다.
    // 추상클래스가 애매해 졌지만, 지금은 이런 기능은 좋다.
    default TodoDTO entityToDTO(Todo todo) { // Entity를 DTO로 변환해줄 메서드다.
        // 기존엔 ModelMapper를 사용했다.

        return TodoDTO.builder()
                .tno(todo.getTno())
                .title(todo.getTitle())
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
                .content(todoDTO.getContent())
                .complete(todoDTO.isComplete())
                .dueDate(todoDTO.getDueDate())
                .build();

    }

}
