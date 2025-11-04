package org.zerock.apiserver.repositoty.search;

import org.springframework.data.domain.Page;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageRequestDTO;

// 인터페이스 기능 선언부
public interface TodoSearch {

    Page<Todo> search1 (PageRequestDTO pageRequestDTO);
}
