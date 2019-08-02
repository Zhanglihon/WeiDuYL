package adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.open_show.R;


import java.util.List;

import androidx.annotation.Nullable;
import zhang.bw.com.common.core.WDApplication;

/*
 * Data 2019/7/11
 *
 */
public class Image2Adapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public Image2Adapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imageView = helper.getView(R.id.image);
        Glide.with(WDApplication.getContext()).load(item).into(imageView);
    }
}
