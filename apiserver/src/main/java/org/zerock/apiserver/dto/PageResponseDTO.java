package org.zerock.apiserver.dto;

// 페이징이 필요한 모든 게시판 다 써먹을 수 있다.

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data // Getter Setter
public class PageResponseDTO<E> {  // E = 엘리먼트  응답 프론트쪽에 보낸다.

    private List<E> dtoList;  // dto의 목록

    private List<Integer> pageNumList;
    // React나, JQuery든 페이징 처리를 하는 경우가 많은데
    // 페이징처리를 미리 해서 보내주기 위해
    // 숫자를 담는 리스트를 만든다?

    private PageRequestDTO pageRequestDTO;
    // 검색조건, 현재 페이지 이런건 PageRequestDTO에 있다.
    // 그래서 이정보도 있으면 좋지않을까? 해서 해당 내용을 추가했다고 한다.

    private boolean prev,next; // 이전, 다음

    private int totalCount, prevPage, nextPage, totalPage, current;
    // 총데이터가 몇개인지,  시작번호, 다음번호? ,  전체페이지,  현재페이지
    // 토탈값이 있어야 페이징처리가 가능하다.


    @Builder(builderMethodName = "withAll")
    // 얘는 커스텀 생성자 페이징처리하는 로직
    public PageResponseDTO(List<E> dtolist, PageRequestDTO pageRequestDTO, long total){

        this.dtoList = dtolist;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = (int) total; // 다운캐스팅 long을 => int로 사용하기 위함

        // 끝페이지부터 계산 end
        // 현제 페이지번호가 필요하다. pageRequestDTO가 가지고있기때문에 얘를 가지고 page를 가져온다.
        int end = (int)(Math.ceil(pageRequestDTO.getPage() / 10.0)) * 10; // 10.0 으로 나눈다. (double)
        // end 값은 나중에 다시 계산 해야한다.
        // 시작값은 고정이다.어차피 무조건 -9를 해야된다고 한다.
        int start = end - 9;
        // 진짜 마지막페이지 몇개씩 나눌 것인지
        int last = (int)(Math.ceil(totalCount / (double)pageRequestDTO.getSize()));
        // 소수점으로 나눠야하니 double 적용 => 페이지가 몇개의 사이즈로 되어있는지. 그래서 getSize 호출
        // 마지막으로 나눈 다음에 소수점으로 나눴으니 78이라면 7.8로 나오기때문에 Math.ceil로 올려준다.


        end = end > last ? last : end;
        // 엔드가 라스트보다 클때 즉, last 계산은 8 인데 엔드가 계산한건 10페이지라면
        // ★ 마지막 페이지(8) 을 넘지 않도록 하는 로직 ★
        // 즉, end = 10; last = 8; 일때, end가 더 크니 last인 8을 end값으로 변경
        // 또, end가 10이고, last가 4 일때, end가 더 크니 last인 4를 end값으로 변경

        this.prev = start > 1; // 결과는 boolean으로 나온다.
        // 현재 보여주는 페이지 묶음 앞에 이전 묶음이 있는가?
        // start 가 1 ~ 10 또는, 11 ~ 20 일때
        // 1보다 start가 더 크다면 true !
        // start가 1보다 작다면 이전페이지가 false

        this.next = totalCount > end * pageRequestDTO.getSize(); // 결과는 boolean으로 나온다.
        // 위 prev의 반대라고 생각하면 된다.
        // end = 10; size = 10; 일때, 10 * 10 = 100 이므로 100이 넘지않도록 하는 로직.
        // totalCount = 101 > 100 일때, true
        // totalCount = 100 > 100 일때, false 다음페이지 없음


        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
        // rangeClosed() 시작에서 끝까지. 즉, start부터 end까지 다 만든다.
        // start = 1; end = 10 일때, [ 1,2,3,4,5,6,7,8,9,10]
        // start = 11; end = 20 일때, [ 11,12,13,14,15,16,17,18,19,20]

        this.prevPage = prev ? start -1 : 0;
        // 이전 페이지가 있으면 start에 -1을 해서 그 전페이지로 이동할 수있게
        // 이전 페이지가 없으면 0을 줘서 아무것도 하지 않는다.

        this.nextPage = next ? end + 1 : 0;
        // 다음 페이지가 있으면 end에 +1을 해서 그 다음 페이지로 이동할 수있게
        // 다음 페이지가 없으면 0을 줘서 아무것도 하지 않는다.

    }

}


// size = 10  보통은 10개씩 나오니까
// page 1 ~ 10 다음(NextPage)으로가거나 이전(PrevPage)으로 가거나
// page 11 ~ 20 다음(Next)으로가거나 이전(Prev)으로 가거나
// 흔히들 시작값을 먼저 구한다.
// 12 => 12를 / 10.0으로 나눈다. 그럼 얘는 1.2가 된다. 얘를 올림해버리면 2가 된다.
// 페이지가 10단위가 되니까 x 10을 해버린다. 그럼 20이되고 -9를 하면 11이 된다.
