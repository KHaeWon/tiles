package com.multi.erp.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// BoardDAO의 메소드를 호출
// → 컨트롤러에서 받은 데이터를 가공해서 DAO로 넘기거나 DAO에서 받은 데이터를 가공해서 컨트롤러로 넘기는 작업을 수행
// → 비지니스 로직 구현하기
// → 서비스 단위로 트랜잭션 처리
@Service
public class BoardServiceImpl implements BoardService {
	
	BoardDAO dao;
	public BoardServiceImpl() {
		
	}
	
	@Autowired
	public BoardServiceImpl(BoardDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public int insert(BoardDTO board) {
		
		return dao.insert(board);
	}

	@Override
	public List<BoardDTO> boardList() {
		// TODO Auto-generated method stub
		return dao.boardList();
	}

	@Override
	public BoardDTO getBoardInfo(String board_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(BoardDTO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String board_no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardDTO> search(String data) {
		// TODO Auto-generated method stub
		return dao.search(data);
	}

	@Override
	public List<BoardDTO> search(String tag, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardDTO> findByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

}
