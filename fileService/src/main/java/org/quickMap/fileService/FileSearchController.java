package org.quickMap.fileService;

import org.quickMap.base.BaseController;
import org.quickMap.fileService.model.FileInfo;
import org.quickmap.storageService.dao.FileInfoMapper;
import org.quickmap.storageService.service.IFilePrefixSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/search")
public class FileSearchController extends BaseController {

    @Autowired
    protected IFilePrefixSuggestionService suggestionService;

    @Autowired
    protected FileInfoMapper fileInfoMapper;

    @RequestMapping("/{fileName}")
    public String search(@PathVariable("fileName") String fileName) {
        List<FileInfo> f = dataService.findByFileName(fileName);
        if(f == null || f.size() == 0){
            suggestionService.deleteSugKey(fileName);
        }
        return jsonRender(f);
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
