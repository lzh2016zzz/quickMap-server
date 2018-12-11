package org.quickMap.fileService;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import org.quickMap.Utils.FileOperatorUtil;
import org.quickMap.base.BaseController;
import org.quickMap.fileService.model.FileInfo;
import org.quickMap.fileService.service.IFileService;
import org.quickmap.storageService.dao.FileInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping("/file")
public class FileController extends BaseController {

    @Autowired
    protected IFileService fileService;

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        return jsonRender(fileService.uploadFile(file.getInputStream(), file.getOriginalFilename(), file.getSize()));
    }


    @PostMapping("/uploadB64")
    public String uploadB64(@RequestParam("b64") String b64, @RequestParam("fileName") String fileName) throws Exception {
        return jsonRender(fileService.uploadB64File(b64, fileName));
    }

    @RequestMapping(value = "/del/{group}/{path}")
    public String delete(@PathVariable("group") String group, @PathVariable("path") String path) throws Exception {
        StorePath storePath = new StorePath();
        storePath.setGroup(group);
        storePath.setPath(FileOperatorUtil.decodePath(path));
        try {
            fileService.deleteFile(storePath);
        } catch (FdfsServerException f) {
            if (f.getErrorCode() == 2) {
                return successRender("文件已被删除");
            } else throw f;
        }
        return successRender("删除成功");
    }
}
