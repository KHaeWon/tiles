package com.multi.erp.board;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardDTO {
	String board_no; //auto_increment니까 냅두셈
	String id;
	Date write_date; //오늘 날짜로 처리할 거니까 냅두셈
	String title;
	String content;
	String category;
	
	// 클라이언트가 전송하는 바이너리 파일 데이터를 spring  mvc가 내부에서 MultipartFile 객체로 만들어서 관리함
	// 첨부파일이 여러 개인 경우 List에 넣거나 배열로 관리
	// MultipartFile[] files;
	List<MultipartFile> files;
	
	//board_write에서
	//<input type="file" class="form-control input-lg" name="files"
	//id="files" placeholder="파일선택" multiple="multiple">
	//name="files"로 지정해뒀으니까 이거랑 이름 똑같이 만들어 줘야 돼!
	public BoardDTO() {
		
	}
	public BoardDTO(String board_no, String id, Date write_date, String title, String content, String category) {
		super();
		this.board_no = board_no;
		this.id = id;
		this.write_date = write_date;
		this.title = title;
		this.content = content;
		this.category = category;
	}
	
	public BoardDTO(String board_no, String id, Date write_date, String title, String content, String category,
			List<MultipartFile> files) {
		super();
		this.board_no = board_no;
		this.id = id;
		this.write_date = write_date;
		this.title = title;
		this.content = content;
		this.category = category;
		this.files = files;
	}
	
	
	@Override
	public String toString() {
		return "BoardDTO [board_no=" + board_no + ", id=" + id + ", write_date=" + write_date + ", title=" + title
				+ ", content=" + content + ", category=" + category + ", files=" + files + "]";
	}
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	public String getBoard_no() {
		return board_no;
	}
	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}
	public String getId() {
		System.out.println("getId()");
		return id;
	}
	public void setId(String id) {
		System.out.println("setId()");
		this.id = id;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
