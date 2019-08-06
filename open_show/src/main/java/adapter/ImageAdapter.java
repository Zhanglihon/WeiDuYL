package adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.open_show.R;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.List;

import androidx.annotation.Nullable;

/*
 * Data 2019/7/11
 *
 */
public class ImageAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public ImageAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.simpleDraweeView);
        simpleDraweeView.setImageURI(item);
    }
}
