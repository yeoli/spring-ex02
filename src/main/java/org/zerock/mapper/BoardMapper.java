package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;

public interface BoardMapper {

	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO Board);
	
	public BoardVO read(Long bno);
	
	//delete한 행의 개수를 리턴
	public int delete(Long bno);
	
	//update한 행의 개수를 리턴
	public int update(BoardVO board);
}
