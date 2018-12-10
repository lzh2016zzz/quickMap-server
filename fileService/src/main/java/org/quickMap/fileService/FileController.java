package org.quickMap.fileService;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import org.quickMap.Utils.FileOperatorUtil;
import org.quickMap.base.BaseController;
import org.quickMap.fileService.model.UploadB64;
import org.quickMap.fileService.model.UploadResponse;
import org.quickMap.fileService.service.IFileSearchService;
import org.quickMap.fileService.service.IFileService;
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

    @Autowired
    IFileSearchService redisSearchService;

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        StorePath storePath = fileService.uploadFile(file.getInputStream(), file.getOriginalFilename(), file.getSize());
        storePath.setPath(FileOperatorUtil.encodePath(storePath.getPath()));
        UploadResponse uploadResponse = new UploadResponse();
        uploadResponse.setFilename(file.getOriginalFilename());
        uploadResponse.setSize(file.getSize());
        uploadResponse.setPath(storePath.getFullPath());
        /*uploadResponse.setfPath(storePath.getPath());
        uploadResponse.setGroup(storePath.getGroup());*/
        return jsonRender(uploadResponse);
    }


    @PostMapping("/uploadB64")
    public String uploadB64(@RequestParam("b64") String b64, @RequestParam("fileName") String fileName) throws Exception {
        UploadB64 uploadB64 = fileService.uploadB64File(b64, fileName);
        UploadResponse uploadResponse = new UploadResponse();
        uploadResponse.setFilename(fileName);
        uploadResponse.setSize(uploadB64.getLength());
        uploadResponse.setPath(uploadB64.getStorePath().getFullPath());
        /*uploadResponse.setfPath(uploadB64.getStorePath().getPath());
        uploadResponse.setGroup(uploadB64.getStorePath().getGroup());*/
        return jsonRender(uploadResponse);
    }

    @RequestMapping(value = "/{group}/{path}")
    public ResponseEntity<byte[]> download(@PathVariable("group") String group, @PathVariable("path") String path) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        StorePath storePath = new StorePath();
        storePath.setGroup(group);
        //解码
        storePath.setPath(FileOperatorUtil.decodePath(path));
        String fileName;
        if ((fileName = fileService.getOriginalFileName(storePath)) == null) {
            return null;
        }
        byte[] b = fileService.downloadFile(storePath);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, /*attachment;*/"filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
        headers.add(HttpHeaders.CONTENT_TYPE, FileOperatorUtil.getContentType(fileName.substring(fileName.indexOf("."))));
        return new ResponseEntity<>(b, headers, HttpStatus.OK);
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
