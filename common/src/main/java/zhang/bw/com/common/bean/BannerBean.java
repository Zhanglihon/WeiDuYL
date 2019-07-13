package zhang.bw.com.common.bean;


import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

public class BannerBean  extends SimpleBannerInfo {
    public String imageUrl;
    public String jumpUrl;
    public String rank;
    public String title;

    @Override
    public Object getXBannerUrl() {
        return null;
    }
}
