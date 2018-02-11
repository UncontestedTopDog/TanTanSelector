package com.example.administrator.tantanselector.Selector;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.tantanselector.Data;
import com.example.administrator.tantanselector.R;

/**
 * Created by Administrator on 2018/2/8.
 */

public class TanTanSelectorItem extends RelativeLayout {

    private ImageView imageView ;
    private TextView textView ;

    public TanTanSelectorItem(Context context) {
        super(context);
        init();
    }

    public TanTanSelectorItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TanTanSelectorItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.tantan_selector_item,this);
        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.text);
    }

    public void bindData(Data data){
        Glide.with(getContext())
                .load(data.getUrl())
                .placeholder(R.drawable.img_avatar_01)
                .into(imageView);
        textView.setText(data.getName());
    }
}
