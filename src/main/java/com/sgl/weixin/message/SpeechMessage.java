package com.sgl.weixin.message;

/**
 * 语音消息
 */
public class SpeechMessage extends BaseMessage{

    //语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
    public String MediaId;

    //语音格式，如amr，speex等
    public String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
