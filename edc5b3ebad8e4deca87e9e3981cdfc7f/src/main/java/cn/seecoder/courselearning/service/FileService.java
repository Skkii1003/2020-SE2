package cn.seecoder.courselearning.service;

import cn.seecoder.courselearning.vo.FileInfoVO;
import cn.seecoder.courselearning.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    ResultVO<FileInfoVO> uploadFile(MultipartFile file);
    void downloadFile(String originName, String newName, HttpServletResponse response);
}
