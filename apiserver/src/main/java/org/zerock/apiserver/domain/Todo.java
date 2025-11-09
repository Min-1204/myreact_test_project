package org.zerock.apiserver.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@ToString
@Getter
@Builder // 빌더를 쓰려면 AllargsConstructor가 필요하다
// @Builder → @AllArgsConstructor를 내부적으로 사용
@AllArgsConstructor
@NoArgsConstructor  // JPA/Hibernate가 이걸 필요로 한다.

@Table(name = "tbl_todo") // 테이블이름 내가 지정할때
public class Todo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // trategy = 키 생성전략 아이덴티티가 가장 무난
    private Long tno;

    @Column(length = 500, nullable = false)
    private String title;

    private String content;

    private String writer;

    private boolean complete;

    private LocalDate dueDate;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeComplete(boolean complete) {
        this.complete = complete;
    }

    public void changeDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void changeWriter(String writer) {
        this.writer = writer;
    }
}
