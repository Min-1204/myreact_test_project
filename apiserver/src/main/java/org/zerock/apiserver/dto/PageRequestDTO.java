package org.zerock.apiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// 페이징이 필요한 모든 게시판 다 써먹을 수 있다.

@SuperBuilder
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO { // 프론트에서 요청을 받는다.

    @Builder.Default
    private  int page = 1;

    @Builder.Default
    private int size = 10;
}
