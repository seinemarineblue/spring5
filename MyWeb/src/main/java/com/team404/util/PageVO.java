package com.team404.util;

import lombok.Data;

@Data //게터, 세터 자동생성
public class PageVO {

	private int endPage; //화면에 그려질 끝번호
	private int startPage; //화면에 그려질 처음번호
	private boolean prev; //화면에 그려질 다음버튼
	private boolean next; //화면에 그려질 이전 버튼
	
	private int pageNum;//현재 조회중인 페이지
	private int amount; //한페이지에서 몇개의 데이터를 보여줄건가
	private int total; //전체게시글수
	
	private Criteria cri; //페이징 기준클래스
	
	public PageVO(Criteria cri, int total) {
		this.cri = cri;
		this.pageNum = cri.getPageNum();
		this.amount = cri.getAmount();
		this.total = total;
		
		//전달되는 기준에 따라서 pageVO계산처리
		//끝페이지 계산
		//현재 조회하는 페이지 12번 -> 화면에 보여질 끝페이지 20
		//현재 조회하는 페이지가 25번 -> 화면에 보여질 끝페이지 30
		//공식: 끝페이지 = (int)Math.ceil(조회하는 페이지 / 10.0 ) * 10
		this.endPage = (int)Math.ceil( this.pageNum / 10.0 ) * 10;
		
		//시작 페이지 계산
		this.startPage = this.endPage - 10 + 1;
		
		//실제 페이지 마지막 페이지 계산
		//만약에 게시물이 63개 밖에 없으면? -> 페이지번호는 7까지 표기되어야 함
		//만약에 게시물이 106개 라면 -> 페이지번호 11까지 표기되어야 함
		//공식 = (int)Math.ceil( 전체게시글 수 / 한페이지에 보여지는 데이터수)
		int realEnd = (int)Math.ceil( total /  (double)this.amount   );
		
		//ex: 152개의 게시물
		//1~10번 페이지 클릭시 -> endPage공식=10, realEnd=16
		//보여져야 하는 페이지 번호 = 10
		//11~16번 페이지 클릭시 -> endPage공식=20, realEnd=16
		//보여져야 하는 페이지 번호 = 16
		if(this.endPage > realEnd) {
			this.endPage = realEnd; //진짜 끝번호를 따라감
		}
		//이전 버튼 활성화 여부
		//startPage = 1, 11, 21 ..... 101
		this.prev = this.startPage > 1;
		
		
		//다음 버튼 활성화 여부
		this.next = realEnd > this.endPage;
		
		//게터세터
	}
}
