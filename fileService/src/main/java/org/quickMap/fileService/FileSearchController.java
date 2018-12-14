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

    @RequestMapping("/{fileName}")
    public String search(@PathVariable("fileName") String fileName) throws Exception{
        return jsonRender(fileService.searchByFileName(fileName,null));
    }



    @RequestMapping("/initAuto")
    public String initAuto(@RequestParam("rebuild") boolean rebuild) throws Exception{
        fileService.initFileNameSearchText(rebuild);
        return successRender();
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
