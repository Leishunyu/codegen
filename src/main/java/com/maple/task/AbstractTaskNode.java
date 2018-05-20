package com.maple.task;

import com.maple.freemarker.CodeService;
import com.maple.freemarker.Helper;
import com.maple.wapper.TableWapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTaskNode implements TaskNode{
    
    @Resource
    protected CodeService codeService;
    
    private Helper helper = new Helper();
    
    protected Map<String, Object> buildContext(TableWapper table, Map<String, Object> config) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table", table);
        map.put("config", config);
        //生成SerialVersionUID
        map.put("serialVersionUID",helper.getSerialVersionUID());
        return map;
    }
}
