package org.zerock.apiserver.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data  // Getter, Setter  다만들어준다.
@Builder
@AllArgsConstructor
@NoArgsConstructor
// Entity는 데이터베이스 테이블과 관련이 있지만, DTO는 관련이없고 업무와 관련이있다.
// DTO는 상황에 따라서 여러개를 만들어서 쓴다.
// 이 DTO는 Service에서 한다.
public class TodoDTO {

    private Long tno;

    private String title;

    private String content;

    private boolean complete;

    private LocalDate dueDate;
}



// 자유로운 아이.
// Entity는 JPA에서 관리.
//
