package org.zerock.apiserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.TodoDTO;
import org.zerock.apiserver.service.TodoService;

import java.util.Map;


//@CrossOrigin 컨트롤러에다가 직접 CORS설정을 해도 된다. 설정 전체를 잡아줄거라면 따로 CustomServletConfig에 하면된다.


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

    }



    // JSON 데이터로 받으려면 반드시 필요한게 RequestBody 이다.
    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TodoDTO dto) {

        log.info("todoDTO: " , dto);

        Long tno = todoService.register(dto);

        return Map.of("tno", tno);

    }

    @PutMapping("/{tno}")
    public Map<String, String> modify (@PathVariable("tno") Long tno,
                                     @RequestBody TodoDTO todoDTO) {

        todoDTO.setTno(tno);

        todoService.modify(todoDTO);

        return Map.of("RESULT", "SUCCESS");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> remove (@PathVariable Long tno) {

        todoService.remove(tno);

        return Map.of("RESULT", "SUCCESS");

    }


}
