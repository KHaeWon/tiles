package com.multi.erp.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.multi.erp.board.BoardFileDTO;

@Service
public class FileUploadLogicService {
	// 실제 파일 업로드를 수행하는 메소드 - 업로드된 파일의 정보를 BoardFileDTO로 변환해서 리턴
	// 여러 개인 경우 BoardFileDTO를 List에 담아서 리턴
	// List<MultipartFile> multipartFiles에는 <input type="file">을 통해 업로드 하는 파일의 정보가
	// 담겨있다
	// 업로드한 파일의 내용,파일사이즈, 파일의 이름 등
	public List<BoardFileDTO> uploadFiles(List<MultipartFile> multipartFiles, String path)
			throws IllegalStateException, IOException {

		List<BoardFileDTO> filedtolist = new ArrayList<BoardFileDTO>();
		int count = 1;

		for (MultipartFile multipartFile : multipartFiles) {
			// 파일 관리는 어떻게 해야할까?
			// 파일명이 중복되지 않게 하기 위해서
			// 키값으로 파일명을 자동 생성 -> 서버에 저장됨
			// ------(UUID : 난수 발생, 중복될 확률이 0에 수렴)
			// 원본파일도 같이 저장!
			// 즉 2개를 같이 저장하는 것이지

			// 업로드를 하는 경우 원본 파일명과 서버에서 식별할 수 있는 실제 서버에 저장되는 파일명 2개를 관리!!
			if (!multipartFile.isEmpty()) {

				// 클라이언트가 업로드한 원본파일명
				String originalFilename = multipartFile.getOriginalFilename();// 원본파일명 뽑고
				// 서버에서 식별할 수 있도록 파일명 변경
				String storeFilename = createStoreFilename(originalFilename);// 저장파일명 뽑고
				// 파일명과 path를 이용해서 실제 File 객체를 만든 후 업로드하기
				// XXX/WEB-INF/upload + / + 파일명
				multipartFile.transferTo(new File(path + File.separator + storeFilename));// 저장하고
				// path가 upload파일까지만 나옴
				System.out.println("원본파일명 : " + originalFilename);
				System.out.println("저장파일명 : " + storeFilename);
				filedtolist.add(new BoardFileDTO(null, originalFilename, storeFilename, count + ""));
				count++;
			}
		}
		return filedtolist;
	}

	// UUID를 이용해서 파일명을 변경해서 리턴하는 메소드
	private String createStoreFilename(String originalFilename) {
		int pos = originalFilename.lastIndexOf("."); // 확장자 파악하기
		String ext = originalFilename.substring(pos + 1); // 시작 index만 지정하면 시작 index부터 끝까지 문자열 추출
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}
}
