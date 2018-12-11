package org.quickmap.apiservice;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.quickMap.base.BaseController;
import org.quickMap.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//跨域支持
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins = "*")
@RestController()
@RequestMapping("/file")
public class OperatorController extends BaseController {

    @Autowired
    FileService fileService;


    /**
     * 下载
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/{group}/{path}")
    public ResponseEntity<byte[]> download(@PathVariable("group") String group, @PathVariable("path") String path) {
        return fileService.download(group, path);
    }

    /**
     * 删除文件
     *
     * @param delPath
     * @return
     */
    @RequestMapping(value = "/file/del/{delPath}")
    String delete(@PathVariable("delPath") String delPath) {
        return fileService.delete(delPath);
    }

    /**
     * 上传
     *
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile[] files) {
        if (files.length == 0) {
            return failedRender();
        }
        JSONArray resp = new JSONArray();
        for (MultipartFile file : files) {
            String s = fileService.upload(file);
            resp.add(JSONObject.parse(s));
        }
        return successRenderData(resp);
    }

    /**
     * 上传base64
     *
     * @return
     */
    @PostMapping("/upload64")
    public String upload64(@RequestParam("b64") String b64, @RequestParam("fileName") String fileName) {
        return successRender(fileService.uploadB64(b64, fileName));
    }

    /**
     * 根据文件名称查询记录
     *
     * @return
     */
    @RequestMapping("/search/{fileName}")
    public String search(@PathVariable("fileName") String fileName) {
        return fileService.search(fileName);
    }

    /**
     * 根据前缀获取完成建议
     *
     * @return
     */
    @RequestMapping("/prefix/{prefix}")
    public String prefix(@PathVariable("prefix") String prefix) {
        return fileService.prefix(prefix);
    }


    /**
     * 获取过去1小时的上传记录
     *
     * @return
     */
    @PostMapping("/list")
    public String list() {
        return "";
    }

}
