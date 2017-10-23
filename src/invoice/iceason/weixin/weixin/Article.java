package invoice.iceason.weixin.weixin;
public class Article {

   // 图文消息名称  

    private String Title = "名称";  

    // 图文消息描写叙述  

    private String Description = "图文消息描写叙述";  

    // 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名须要与开发人员填写的基本资料中的Url一致  

    private String PicUrl = "http://wechat.talentsolution.net/CustomerOrder/images/10330.png";  

    // 点击图文消息跳转链接  

    private String Url ="http://www.e-fp.cn//wx_cfm_new_title.html?m=papajohns&d=20170803&p=200.000000&o=7785131&doid=&v=3?qqdrsign=07fe7";  

    public String getTitle() {  

        return Title;  

    }  

    public void setTitle(String title) {  

        Title = title;  

    }  

    public String getDescription() {  

        return null == Description ? "" : Description;  

    }  

    public void setDescription(String description) {  

        Description = description;  

    }  

    public String getPicUrl() {  

        return null == PicUrl ? "" : PicUrl;  

    }  

    public void setPicUrl(String picUrl) {  

        PicUrl = picUrl;  

    }  

    public String getUrl() {  

        return null == Url ? "" : Url;  

    }  

    public void setUrl(String url) {  

        Url = url;  

    }  

}