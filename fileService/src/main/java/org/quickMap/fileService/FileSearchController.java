package org.quickMap.fileService;

import org.quickMap.base.BaseController;
import org.quickMap.fileService.service.IFileSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/search")
public class FileSearchController extends BaseController {

    @Autowired
    IFileSearchService fileSearchService;

    @RequestMapping("/{fileName}")
    public String search(@PathVariable("fileName") String fileName) {
        return jsonRender(fileSearchService.findByOriginalName(fileName));
    }


    /**
     * 根据前缀获取完成建议
     *
     * @param prefix
     * @return
     */
    @RequestMapping(value = "/prefix/{prefix}")
    public String prefix(@PathVariable("prefix") String prefix) {
        return jsonRender(fileSearchService.getSuggestions(prefix));
    }
}
