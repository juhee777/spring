package com.yedam.app.board.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardService;

import jakarta.annotation.Resource;

import com.yedam.app.board.service.BoardVO;



@Controller
//@AllArgsConstructor
public class BoardController {
	
	private BoardService boardService;
	
	@Autowired
	BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("boardList")
	public String boardList(Model model) {
		List<BoardVO> list = boardService.boardList();
		model.addAttribute("boards", list); //페이지에서 사용할 데이터
		return "board/boardList";
		// prefix + return + suffix
		//classpath:/templates/ + board/boardList + .html
	}
	
	// 단건조회 : URI - boardInfo / PARAMETER - BoardVO(QueryString)
	//          RETURN - board/boardInfo
	@GetMapping("boardInfo") // 커맨드객체 : QueryString
							 // ?bno=100
	public String boardInfo(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("board",findVO);
		return "board/boardInfo";
	}
		
	// 등록 - 페이지 : URI - boardInsert / RETURN - board/boardInsert
	@GetMapping("boardInsert")
	public String boardInsertForm() {
		return "board/boardInsert";
	}
		
	// 등록 - 처리 : URI - boardInsert / PARAMETER - BoardVO(QueryString)
	//             RETURN - 단건조회 다시 호출
	@PostMapping("boardInsert") //일반적으로 <form/> 활용 => QueryString
	public String boardInsertProcess(BoardVO boardVO) {
		int bno = boardService.insertBoard(boardVO);
	    return "redirect:boardInfo?bno="+bno;
	
		
	}
		
	// 수정 - 페이지 : URI - boardUpdate / PARAMETER - BoardVO(QueryString)
	//               RETURN - board/boardUpdate
	// => 단건조회
	@GetMapping("boardUpdate")
	public String boardUpdateForm(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("board",findVO);
		return "board/boardUpdate";
	}
	// 수정 - 처리 : URI - boardUpdate / PARAMETER - BoardVO(JSON)
	//             RETURN - 수정결과 데이터(Map)
	// => 등록(내부에서 수행하는 쿼리문 - UPDATE문)
	@PostMapping("boardUpdate")
	@ResponseBody // => AJAX
	public Map<String, Object> boardUpdateProcess(@RequestBody BoardVO boardVO) { //@RequestBody에서 파싱 
		return boardService.updateBoard(boardVO);
	}
		
	// 삭제 - 처리 : URI - boardDelete / PARAMETER - Integer
	//             RETURN - 전체조회 다시 호출
	@GetMapping("boardDelete") // QueryString : ?no=10
	public String boardDelete(@RequestParam Integer no) { //@RequestParam가 생략가능 하나 명시하는 경우 파람 값이 반드시 있어야 함
		boardService.deleteBoard(no);
		return "redirect:boardList";
	}
}
