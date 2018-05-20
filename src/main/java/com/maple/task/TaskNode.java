package com.maple.task;

import com.maple.freemarker.CodeFile;
import com.maple.wapper.TableWapper;

import java.util.List;
import java.util.Map;

public interface TaskNode {
    
    List<CodeFile> execute(TableWapper table, Map<String, Object> config) throws Exception;
}
