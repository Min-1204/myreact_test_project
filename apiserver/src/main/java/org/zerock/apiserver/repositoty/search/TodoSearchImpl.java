package org.zerock.apiserver.repositoty.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.apiserver.domain.QTodo;
import org.zerock.apiserver.domain.Todo;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }
    // 생성자가 있어야한다? 생성자를 만들어줘야한다? interface니까?


    @Override
    public Page<Todo> search1() {

        log.info("search1..............");

        QTodo todo = QTodo.todo; // 쿼리를 날리기 위한 객체

        JPQLQuery<Todo> query = from(todo); // todo에 관련된 쿼리를 만들어낸다.

        // from을 이용해서 todo에서부터 뽑아낸다 라는 뜻
        // JPQL은 10년전 방식이다.

        query.where(todo.title.contains("1"));   // Predicate = 예상한다 뭔가 이게 true인 애를 만들어낸다?
        // 컨테인즈에서 특정한 문자가 있다면?

        Pageable pageable = PageRequest.of(1,10, Sort.by("tno").descending());

        this.getQuerydsl().applyPagination(pageable, query);

        query.fetch(); // 쿼리를 실행할 수 있다.

        query.fetchCount(); // 얘들은 Long타입으로 나온다.

        // 쿼리Dsl 셋팅 어떻게 하는건지 알려주는 단계. 제대로 동작하는지 확인하는 것.

        return null;
    }
}
