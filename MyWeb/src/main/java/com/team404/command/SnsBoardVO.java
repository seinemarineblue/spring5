package com.team404.command;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SnsBoardVO {

	private int bno;
	private String writer;
	private String uploadPath; //fileLoca를 포함한 업로드경로
	private String fileLoca; //날짜폴더경로
	private String fileName; //변경해서 저장할 이름
	private String fileRealName; //원본 이름
	private String content; //내용
	private Timestamp regdate; //등록일
}