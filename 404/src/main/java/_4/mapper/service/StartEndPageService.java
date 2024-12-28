package _4.mapper.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.StartEndPageDTO;

@Service
public class StartEndPageService {
	int limit;
	
	public StartEndPageDTO execute(int page, String searchWord) {
		
		/* 요거 ListService에 갖다 붙이면 됩니다~~
		String searchWord;
		public void execute(Model model, String searchWord, int page) {
			if(searchWord != null) {
				this.searchWord = searchWord.trim();
		}
		StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
		*/
		
		limit = 1; // 한 페이지마다 n명씩 보여주기 위한 페이징 - limit = n
		int startRow = ((page - 1) * limit) + 1;		// 시작 번호 가져오기
		int endRow = startRow + limit - 1;				// startRow와 endRow 그리고 searchWord를 mybatis에게 넘기기 위해 DTO 생성 - StartEndPageDTO
		StartEndPageDTO sepDTO = new StartEndPageDTO();
		sepDTO.setEndRow(endRow);
		sepDTO.setSearchWord(searchWord);
		sepDTO.setStartRow(startRow);
		return sepDTO;
	}
	
	public void execute(int page, int count, Model model, List list, String searchWord) {
		int limitPage = 10; // 보여주는 페이지 수 [1],[2],[3],...,[10]
		int startPage = (int)((double)page / limit + 0.95 - 1) & limitPage + 1;
		int endPage = startPage + limitPage - 1;
		int maxPage = (int)((double) page / limit  + 0.95);
		
		if (maxPage < endPage) endPage = maxPage;
		model.addAttribute("dtos", list);
		
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("page", page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("count", count);
		model.addAttribute("maxPage", maxPage);
	}
}


