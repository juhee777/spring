package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardVO;

@SpringBootTest
class Boot02ApplicationTests {
	@Autowired
	BoardMapper boardMapper;
	
	//@Test
	void contextLoads() { //전체조회
		List<BoardVO> list = boardMapper.selectBoardAll();
		assertTrue(!list.isEmpty());
	}
	
	@Test
	void boardInfo() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(100);
		
		BoardVO findVO = boardMapper.selectBoardInfo(boardVO);
		assertEquals("Any", findVO.getWriter());
	}
	
	//@Test
	void boardInsert() {
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("test");
		boardVO.setContents("test");
		boardVO.setWriter("test");
		boardVO.setRegdate(new Date());
		boardVO.setImage(null);
		
		int result = boardMapper.insertBoardInfo(boardVO);
		System.out.println(boardVO.getBno());
		assertEquals(1, result);
		
	}
	
	//@Test
	public void boardUpdate() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(101);
		
		BoardVO findVO = boardMapper.selectBoardInfo(boardVO);
		findVO.setWriter("ttt");
		
		int result = boardMapper.updateBoardInfo(boardVO);
		assertEquals(1, result);
	}

}
