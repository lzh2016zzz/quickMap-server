package org.quickMap.fileService;

import org.quickMap.base.BaseController;
import org.quickMap.fileService.service.IFilePrefixSuggestionService;
import org.quickMap.fileService.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/search")
public class FileSearchController extends BaseController {

    @Autowired
    protected IFilePrefixSuggestionService suggestionService;


    @Autowired
    protected IFileService fileService;

    /**
     * 搜索文件
     * @param fileName 文件全名
     * @param before 时间戳 在..之前
     * @param after  时间戳 在..之后
     * @param suffix 后缀名
     * @return
     * @throws Exception
     */
    @RequestMapping("/exec")
    public String search(@RequestParam(value = "fileName",required = false) String fileName,@RequestParam(value = "before",required = false)Long before,@RequestParam(value = "after",required = false)Long after,@RequestParam(value = "suffix",required = false)String suffix)throws Exception{
        return jsonRender(fileService.search(fileName,before,after,suffix,null));
    }

    /**
     * 根据前缀获取完成建议
     *
     * @param prefix
     * @return
     */
    @RequestMapping(value = "/prefix/{prefix}")
    public String prefix(@PathVariable("prefix") String prefix) {
        return jsonRender(suggestionService.getSuggestions(prefix));
    }
}
