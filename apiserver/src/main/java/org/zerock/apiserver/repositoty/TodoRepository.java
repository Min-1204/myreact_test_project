package org.zerock.apiserver.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.repositoty.search.TodoSearch;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {
}

// 이렇게만들면 Boot(부트쪽에서) 알아서 만들어준다.
