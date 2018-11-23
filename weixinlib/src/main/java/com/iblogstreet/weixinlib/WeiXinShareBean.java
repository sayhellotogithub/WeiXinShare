package com.iblogstreet.weixinlib;

import android.content.Context;
import android.graphics.Bitmap;

import com.iblogstreet.weixinlib.constant.Util;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXEmojiObject;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.File;

/**
 * @author Armyone
 * @date 2018/11/23 11:02
 * @desc
 */

public class WeiXinShareBean {
    public IWXAPI getApi() {
        return mApi;
    }

    private IWXAPI mApi;

    public int getmTargetScene() {
        return mTargetScene;
    }

    public void setmTargetScene(int mTargetScene) {
        this.mTargetScene = mTargetScene;
    }

    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;

    public WeiXinShareBean WeiXinShareBean(Context context, String appId) {
        this.mApi = WXAPIFactory.createWXAPI(context, appId);
        return this;
    }

    /**
     * 发送文本
     *
     * @param text
     */
    public void sendText(String text) {
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = text;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = msg;
        req.scene = this.mTargetScene;
        mApi.sendReq(req);
    }

    public void sendImage(Bitmap bmp, Bitmap thumbBmp) {
        WXImageObject imgObj = new WXImageObject(bmp);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;
        req.scene = this.mTargetScene;
        mApi.sendReq(req);
    }

    public void sendMusic(String musicUrl, String title, String desc, Bitmap thumbBmp) {
        WXMusicObject music = new WXMusicObject();
        //music.musicUrl = "http://www.baidu.com";
        music.musicUrl = musicUrl;
        //  "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
        //music.musicUrl="http://120.196.211.49/XlFNM14sois/AKVPrOJ9CBnIN556OrWEuGhZvlDF02p5zIXwrZqLUTti4o6MOJ4g7C6FPXmtlh6vPtgbKQ==/31353278.mp3";

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = music;
        msg.title = title;//"Music Title Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
        msg.description = desc;// "Music Album Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("music");
        req.message = msg;
        req.scene = this.mTargetScene;
        mApi.sendReq(req);
    }

    /**
     * 发送低带宽音乐
     *
     * @param musicUrl
     * @param title
     * @param desc
     * @param thumbBmp
     */
    public void sendmusicLowBandUrl(String musicUrl, String title, String desc, Bitmap thumbBmp) {
        WXMusicObject music = new WXMusicObject();
        music.musicLowBandUrl = musicUrl;
        //  "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
        //music.musicUrl="http://120.196.211.49/XlFNM14sois/AKVPrOJ9CBnIN556OrWEuGhZvlDF02p5zIXwrZqLUTti4o6MOJ4g7C6FPXmtlh6vPtgbKQ==/31353278.mp3";

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = music;
        msg.title = title;//"Music Title Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
        msg.description = desc;// "Music Album Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("music");
        req.message = msg;
        req.scene = this.mTargetScene;
        mApi.sendReq(req);
    }

    public void sendVideo(String videoUrl, String title, String desc, Bitmap thumbBmp) {
        WXVideoObject video = new WXVideoObject();
        video.videoUrl = videoUrl;// "http://www.qq.com";

        WXMediaMessage msg = new WXMediaMessage(video);
        msg.title = title;//"Video Title Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
        msg.description = desc;//"Video Description Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long";

        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("video");
        req.message = msg;
        req.scene = this.mTargetScene;
        mApi.sendReq(req);
    }

    /**
     * 发送低带宽视频
     *
     * @param videoLowBandUrl
     * @param title
     * @param desc
     * @param thumbBmp
     */
    public void sendVideoLowBandUrl(String videoLowBandUrl, String title, String desc, Bitmap thumbBmp) {
        WXVideoObject video = new WXVideoObject();
        video.videoLowBandUrl = videoLowBandUrl;// "http://www.qq.com";

        WXMediaMessage msg = new WXMediaMessage(video);
        msg.title = title;//"Video Title Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
        msg.description = desc;//"Video Description Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long";

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("video");
        req.message = msg;
        req.scene = this.mTargetScene;
        mApi.sendReq(req);
    }

    /**
     * 发送网页
     *
     * @param webpageUrl
     * @param title
     * @param desc
     * @param thumbBmp
     */
    public void sendWebpage(String webpageUrl, String title, String desc, Bitmap thumbBmp) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = webpageUrl;//"http://www.qq.com";
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;// "WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
        msg.description = desc;//"WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = this.mTargetScene;
        mApi.sendReq(req);
    }

    /**
     * 发送小程序
     *
     * @param webpageUrl
     * @param userName
     * @param path
     * @param title
     * @param desc
     * @param thumbBmp
     */
    public void sendMiniProgram(String webpageUrl, String userName, String path, String title, String desc, Bitmap thumbBmp) {
        WXMiniProgramObject miniProgram = new WXMiniProgramObject();
        miniProgram.webpageUrl = webpageUrl;// "http://www.qq.com";
        miniProgram.userName = userName;// "gh_d43f693ca31f";
        miniProgram.path = path;//"pages/play/index?cid=fvue88y1fsnk4w2&ptag=vicyao&seek=3219";
        WXMediaMessage msg = new WXMediaMessage(miniProgram);
        msg.title = title;//"分享小程序Title";
        msg.description = desc;//"分享小程序描述信息";
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = this.mTargetScene;
        mApi.sendReq(req);
    }

    /**
     * 分享本地文件
     *
     * @param localPath
     * @param path
     * @param extInfo
     * @param title
     * @param desc
     */
    public void sendAppLocal(String localPath, String path, String extInfo, String title, String desc) {
        final WXAppExtendObject appdata = new WXAppExtendObject();
        appdata.filePath = localPath;
        appdata.extInfo = extInfo;//"this is ext info";

        final WXMediaMessage msg = new WXMediaMessage();
        msg.setThumbImage(Util.extractThumbNail(path, 150, 150, true));
        msg.title = title;// "this is title";
        msg.description = desc;// "this is description";
        msg.mediaObject = appdata;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("appdata");
        req.message = msg;
        req.scene = this.mTargetScene;
        mApi.sendReq(req);
    }

    /**
     * 发送二进制数据
     *
     * @param localPath
     * @param extInfo
     * @param title
     * @param desc
     */
    public void sendAppByte(String localPath, String extInfo, String title, String desc) {
        final WXAppExtendObject appdata = new WXAppExtendObject();
        // final String path = localPath;//SDCARD_ROOT + "/test.png";
        appdata.fileData = Util.readFromFile(localPath, 0, -1);
        appdata.extInfo = extInfo;//"this is ext info";

        final WXMediaMessage msg = new WXMediaMessage();
        msg.setThumbImage(Util.extractThumbNail(localPath, 150, 150, true));
        msg.title = title;// "this is title";
        msg.description = desc;// "this is description sjgksgj sklgjl sjgsgskl gslgj sklgj sjglsjgs kl gjksss ssssssss sjskgs kgjsj jskgjs kjgk sgjsk Very Long Very Long Very Long Very Longgj skjgks kgsk lgskg jslgj";
        msg.mediaObject = appdata;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("appdata");
        req.message = msg;
        req.scene = this.mTargetScene;
        mApi.sendReq(req);
    }

    /**
     * 发送无附件的app
     *
     * @param extInfo
     * @param title
     * @param desc
     */
    public void sendAppNoPic(String extInfo, String title, String desc) {
        final WXAppExtendObject appdata = new WXAppExtendObject();
        appdata.extInfo = extInfo;//"this is ext info";
        final WXMediaMessage msg = new WXMediaMessage();
        msg.title = title;//"this is title";
        msg.description = desc;//"this is description";
        msg.mediaObject = appdata;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("appdata");
        req.message = msg;
        req.scene = this.mTargetScene;
        mApi.sendReq(req);
    }

    /**
     * 发送表情
     *
     * @param emojFilePath
     * @param emojFileThumbPath
     * @param title
     * @param desc
     */
    public void sendEmoj(String emojFilePath, String emojFileThumbPath, String title, String desc) {
        WXEmojiObject emoji = new WXEmojiObject();
        emoji.emojiPath = emojFilePath;

        WXMediaMessage msg = new WXMediaMessage(emoji);
        msg.title = title;// "Emoji Title";
        msg.description = desc;// "Emoji Description";
        msg.thumbData = Util.readFromFile(emojFileThumbPath, 0, (int) new File(emojFileThumbPath).length());


        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("emoji");
        req.message = msg;
        req.scene = this.mTargetScene;
        mApi.sendReq(req);
    }

    /**
     * 发送表情
     *
     * @param emojFilePath
     * @param emojFileThumbPath
     * @param title
     * @param desc
     */
    public void sendEmojByte(String emojFilePath, String emojFileThumbPath, String title, String desc) {
        WXEmojiObject emoji = new WXEmojiObject();
        emoji.emojiData = Util.readFromFile(emojFilePath, 0, (int) new File(emojFilePath).length());

        WXMediaMessage msg = new WXMediaMessage(emoji);
        msg.title = title;// "Emoji Title";
        msg.description = desc;// "Emoji Description";
        msg.thumbData = Util.readFromFile(emojFileThumbPath, 0, (int) new File(emojFileThumbPath).length());


        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("emoji");
        req.message = msg;
        req.scene = this.mTargetScene;
        mApi.sendReq(req);
    }

    /**
     * 打开微信
     */
    public void openWX() {
        mApi.openWXApp();
    }

    private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;

    /**
     * 是否支持朋友圈
     */
    public void isSupportTimeLine() {
        int wxSdkVersion = this.mApi.getWXAppSupportAPI();
        if (wxSdkVersion >= TIMELINE_SUPPORTED_VERSION) {
            // Toast.makeText(WXEntryActivity.this, "wxSdkVersion = " + Integer.toHexString(wxSdkVersion) + "\ntimeline supported", Toast.LENGTH_LONG).show();
        } else {
            //Toast.makeText(WXEntryActivity.this, "wxSdkVersion = " + Integer.toHexString(wxSdkVersion) + "\ntimeline not supported", Toast.LENGTH_LONG).show();
        }
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


}
