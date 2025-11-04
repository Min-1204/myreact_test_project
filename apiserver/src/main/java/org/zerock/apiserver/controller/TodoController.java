package org.zerock.apiserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.TodoDTO;
import org.zerock.apiserver.service.TodoService;

// SSR이랑 다른점은 예전에는 같이 쓰는데 지금처럼 API만 만들경우에는 화면이 없기 때문에
// RestController만 만들어준다.

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/todo")  // 어떤경로로 호출했을때 이 컨트롤러가 동작할 것이냐 원래는 복수형을 권장하는데 한국어는 복수형이 애매하다.
public class TodoController {

    // get방식 먼저 한다. 설계를 잡는게 편하다고한다.
    // 브라우저만으로도 충분히 확인이 가능하다.

    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable("tno") Long tno) {

        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list (PageRequestDTO pageRequestDTO) {

        log.info("list ............." , pageRequestDTO);

        return todoService.getList(pageRequestDTO);
        
//        @RestController 어노테이션 공부 시작

    }


}
