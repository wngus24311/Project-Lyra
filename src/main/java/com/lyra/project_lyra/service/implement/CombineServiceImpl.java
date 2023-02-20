package com.lyra.project_lyra.service.implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.lyra.project_lyra.dto.CombineDTO;
import com.lyra.project_lyra.entity.combine.CombineKeep;
import com.lyra.project_lyra.repository.combine.CombineKeepRepository;
import com.lyra.project_lyra.service.interfaces.CombineService;

import io.github._1tchy.shaded.org.h2.tools.Console;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CombineServiceImpl implements CombineService {

	private final CombineKeepRepository combineKeepRepository;
	
	
	@Override
	public void bookKeepSave(String username, Long bookNum) {
		CombineKeep combineKeep = new CombineKeep();
		CombineDTO combineDTO = new CombineDTO();
		
		combineDTO.setUsername(username);
		combineDTO.setBookNum(bookNum);
		
		combineKeep = combineDtoToEntity(combineDTO);
		
		combineKeepRepository.save(combineKeep);
	}

	@Override
	public CombineDTO combineList(String username) {
		return null;
	}

	@Override
	public boolean bookKeepDelete(String username, Long bookNum) {
		boolean bResult = false;
		String strBookNum = Long.toString(bookNum);
		String strListBookNum;

		List<CombineKeep> list = new ArrayList<>();
		
		list = combineKeepRepository.findAll();
		
		for (int i = 0; i < list.size(); i++) {
			
			strListBookNum = Long.toString(list.get(i).getBookInfo().getBookNum());
			
			if (list.get(i).getMemberInfo().getUsername().equals(username) 
					&& strBookNum.equals(strListBookNum)) {

				combineKeepRepository.deleteById(list.get(i).getKeepNum());
				
				bResult = true;
			}
		}

		return bResult;
	}

}
