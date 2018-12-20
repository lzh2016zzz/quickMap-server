package org.quickMap.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基础class
 */
public abstract class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    public String getIP() {
        return this.request.getRemoteAddr();
    }

    public String successRender() {
        return successRender(null);
    }

    public String successRender(String msg) {
        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("msg", msg);
        return json.toJSONString();
    }

    public String jsonRender(Object data) {
        return JSON.toJSONString(data);
    }


    public String successRenderData(Object data) {
        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("data", data);
        return json.toJSONString();
    }

    public String failedRender() {
        return failedRender(null);
    }

    public String failedRender(String msg) {
        JSONObject json = new JSONObject();
        json.put("success", false);
        json.put("msg", msg);
        return json.toJSONString();
    }

}
