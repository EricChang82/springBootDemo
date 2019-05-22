package com.alibaba.excel.event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jipengfei
 * @date 2017/07/21
 */
public class OneRowAnalysisFinishEvent {

    public OneRowAnalysisFinishEvent(List<String> content) {
        List<String> list = new ArrayList<String>();
        list.addAll(content);
        this.data = list;
    }

    public OneRowAnalysisFinishEvent(Object[] content, int length) {
        if (content != null) {
            List<Object> ls = new ArrayList<Object>(length);
            for (int i = 0; i <= length; i++) {
                ls.add(content[i]);
            }
            data = ls;
        }
    }

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
