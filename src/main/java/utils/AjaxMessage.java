package utils;

import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Yao on 2016/8/1.
 */
public class AjaxMessage implements Serializable{

    private JSONObject json = new JSONObject();

    public AjaxMessage(boolean success) {
        this.json.put("msg", "");
        this.json.put("success", Boolean.valueOf(success));
    }

    public AjaxMessage(boolean success, String msg) {
        this.json.put("msg", msg);
        this.json.put("success", Boolean.valueOf(success));
    }

    public void put(String key, Object value) {
        this.json.put(key, value);
    }

    public static String result(boolean success) {
        return new AjaxMessage(success).toString();
    }

    public static String result(boolean success, String successMsg, String failedMsg) {
        return new AjaxMessage(success, success ? successMsg : failedMsg).toString();
    }

    public static String success()
    {
        return new AjaxMessage(true).toString();
    }

    public static String success(String msg)
    {
        return new AjaxMessage(true, msg).toString();
    }

    public static String success(String msg, Map<String, Object> map)
    {
        return new AjaxMessage(true, msg).put(map);
    }

    public static String failed()
    {
        return new AjaxMessage(false).toString();
    }

    public static String sessionTimeout()
    {
        AjaxMessage ajaxMessage = new AjaxMessage(false);
        ajaxMessage.put("timeout", Boolean.valueOf(true));
        return ajaxMessage.toString();
    }

    public static String failed(String msg)
    {
        return new AjaxMessage(false, msg).toString();
    }

    public static String failed(String msg, Map<String, Object> map)
    {
        return new AjaxMessage(false, msg).put(map);
    }

    private String put(Map<String, Object> map)
    {
        for (Map.Entry<String, Object> entry : map.entrySet())
        {
            this.json.put(entry.getKey(), entry.getValue());
        }

        return this.toString();
    }

    public String toString()
    {
        return this.json.toString();
    }
}
