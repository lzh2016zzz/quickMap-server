package org.quickMap.fileService.task;


import org.quickMap.fileService.service.IFileService;
import org.quickMap.fileService.service.impl.FilePrefixSuggestionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private Logger logger = LoggerFactory.getLogger(FilePrefixSuggestionServiceImpl.class);

    @Autowired
    IFileService fileService;

    /**
     * 更新自动补全字段
     */
    @Scheduled(cron = "0 0 4,9,17,23 * * ? ")
    public void refreshFileNameSearchIndex() {
        try {
            fileService.initFileNameSearchText(true);
        } catch (Exception e) {
            logger.error("初始化自动补全文本异常",e);
        }
    }

}
