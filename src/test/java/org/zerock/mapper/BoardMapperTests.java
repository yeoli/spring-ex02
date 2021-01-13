package org.zerock.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Test
	public void testGetList() {
		List<BoardVO> list = mapper.getList();

		// assertEquals(list.size(), 5);
		assertNotEquals(list.size(), 0);
	}

	
	@Test 
	public void testInsert() {
	  BoardVO board = new BoardVO();
	  board.setTitle("새로 작성하는 제목"); 
	  board.setContent("ddd");
	  board.setWriter("newbie");
	 
	  int before = mapper.getList().size(); 
	  mapper.insert(board); 
	  int after = mapper.getList().size();
	 
	  assertEquals(before + 1, after); 
	}

	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 select key");
		board.setContent("ddd select key");
		board.setWriter("newbie");

		int before = mapper.getList().size();
		mapper.insertSelectKey(board);
		int after = mapper.getList().size();

		assertEquals(before + 1, after);
		assertNotEquals(board.getBno(), 0);
	}

	@Test
	public void testRead() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 select key");
		board.setContent("ddd select key");
		board.setWriter("newbie");

		mapper.insertSelectKey(board);

		BoardVO readBoard = mapper.read(board.getBno());
		assertNotNull(readBoard);
		assertEquals(readBoard.getBno(), board.getBno());
	}
	
	@Test
	public void testDelete() {
		BoardVO board = new BoardVO();
		board.setTitle("lulu");
		board.setContent("dfdf");
		board.setWriter("zzzz");

		mapper.insertSelectKey(board);
		int before = mapper.getList().size();
		
		int cnt = mapper.delete(board.getBno());
		assertEquals(1, cnt);
		int after = mapper.getList().size();
		
		assertEquals(before-1, after);
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setTitle("lulu");
		board.setContent("dfdf");
		board.setWriter("zzzz");

		mapper.insertSelectKey(board);
		
		board.setTitle("변경된 제목");
		board.setContent("변경된 내용");
		int cnt = mapper.update(board);
		
		assertEquals(1, cnt);
		
		BoardVO updateVO = mapper.read(board.getBno());
		assertEquals("변경된 제목", updateVO.getTitle());
		assertEquals("변경된 내용", updateVO.getContent());
	}
}
