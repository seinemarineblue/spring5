package com.team404.util;

import java.util.ArrayList;

import com.team404.command.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //게터,세터
@AllArgsConstructor //모든값 초기화 생성자
@NoArgsConstructor //기본 생성자
public class ReplyPageVO {

	private ArrayList<ReplyVO> list;//1번 변수
	private int replyCount; //2번 변수
	
}