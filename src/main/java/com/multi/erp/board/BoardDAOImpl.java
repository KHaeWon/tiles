package com.multi.erp.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class BoardDAOImpl implements BoardDAO {
	
	//mybatis의 핵심 클래스를 이용해서 sql문 실행
	SqlSession sqlSessionTemplate;
	
	public BoardDAOImpl() {
		
	}
	
	//필드에서 @Autowired하는 것은 수정이 가능하나 생성자에서의 @Autowired는 수정이 불가능하다.
	//실무에서는 필드보단 이렇게 생성자에서 받는 방법을 선호함.
	@Autowired
	public BoardDAOImpl(SqlSession sqlSessionTemplate) {
		super();
		this.sqlSessionTemplate = sqlSessionTemplate;
	}



	@Override
	public int insert(BoardDTO board) {
		// sqlSession의 insert 메소드는 insert SQL 명령문을 실행하기 위해 제공되는 메소드
		// insert(statement, 파라미터 객체)
		// statement가 mapper에 정의한 sql문을 구분하는 id명 - 네임스페이스명.id명
		// 네임스페이스명은 패키지명과 동일하게 쓴다구 했징
		// 파라미터객체 - 사용자가 입력한 값이 저장되어 있는 DTO
		return sqlSessionTemplate.insert("com.multi.erp.board.insert", board);
	}

	@Override
	public List<BoardDTO> boardList() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("com.multi.erp.board.selectall");
		
	}

	@Override
	public BoardDTO read(String board_no) {
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
		return sqlSessionTemplate.selectList("com.multi.erp.board.search",data);
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
