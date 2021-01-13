package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//스프링이 관리하는 빈으로 만들기 위해서 xml에 명시되어야 함<context:param으로
//@Component
//서비스 역할을 하는 빈이고 이 안에 컴포넌트가 들어가 있음!
@Service
@AllArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService{
  private BoardMapper mapper;
//  public BoardServiceImpl(BoardMapper mapper) {
//	  this.mapper = mapper;
//  }
  //mapper.read(33)
  @Override
	public void register(BoardVO board) {
		mapper.insertSelectKey(board);
	}
  
  @Override
	public List<BoardVO> getList() {
		return mapper.getList();
	}
  
  @Override
	public BoardVO get(Long bno) {
	    return mapper.read(bno);
	}
  
  @Override
	public boolean remove(Long bno) {
		return mapper.delete(bno) == 1;
	}
  
  @Override
	public boolean modify(BoardVO board) {
	  return mapper.update(board) == 1;
	}
}
