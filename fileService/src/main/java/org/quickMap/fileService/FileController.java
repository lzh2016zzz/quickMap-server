package org.quickMap.fileService;

import com.github.tobato.fastdfs.exception.FdfsServerException;
import org.quickMap.base.BaseController;
import org.quickMap.fileService.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping("/file")
public class FileController extends BaseController {

    @Autowired
    protected IFileService fileService;

    /**
     * 上传文件
     *
     * @param file 文件
     * @param thumbImage 是否生成缩略图
     * @return
     * @throws Exception
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,@RequestParam(value = "thumbImage",required = false,defaultValue= "false")boolean thumbImage) throws Exception {
        return jsonRender(fileService.uploadFile(file.getInputStream(), file.getOriginalFilename(), file.getSize(),thumbImage));
    }

    /**
     * 上传base64
     *
     * @param b64 文件base64编码
     * @param thumbImage 是否生成缩略图
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadB64")
    public String uploadB64(@RequestParam("b64") String b64, @RequestParam("fileName") String fileName,@RequestParam(required = false,defaultValue= "true")boolean thumbImage) throws Exception {
        return jsonRender(fileService.uploadB64File(b64, fileName,thumbImage));
    }

    /**
     * 删除文件
     * @param delPath 根据参数删除文件
     * @return
     */
    @RequestMapping(value = "/del/{delPath}")
    public String delete(@PathVariable("delPath") String delPath) throws Exception {
        try {
            fileService.deleteFile(delPath);
        } catch (FdfsServerException f) {
            if (f.getErrorCode() == 2) {
                return successRender("文件已被删除");
            } else throw f;
        }
        return successRender("删除成功");
    }
}
