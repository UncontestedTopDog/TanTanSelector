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

    private ImageView avatar ;
    private TextView name ;
    private TextView ageAndGemder ;
    private TextView constellation ;
    private TextView occupation ;

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
        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        ageAndGemder = findViewById(R.id.age_and_gender);
        constellation = findViewById(R.id.constellation);
        occupation = findViewById(R.id.occupation);


    }

    public void bindData(Data data){
        Glide.with(getContext())
                .load(data.getAvatar())
                .placeholder(R.drawable.img_avatar_01)
                .into(avatar);
        name.setText(data.getName());
        ageAndGemder.setText(data.getGender().equals(getResources().getString(R.string.female))?getResources().getString(R.string.female)+data.getage():getResources().getString(R.string.male)+data.getage());
        if (data.getConstellation() == getResources().getString(R.string.aquarius))
            constellation.setBackgroundResource(R.drawable.constellation_aquarius_label);
        else if (data.getConstellation() == getResources().getString(R.string.aries))
            constellation.setBackgroundResource(R.drawable.constellation_aries_label);
        else if (data.getConstellation() == getResources().getString(R.string.cancer))
            constellation.setBackgroundResource(R.drawable.constellation_cancer_label);
        else if (data.getConstellation() == getResources().getString(R.string.capricorn))
            constellation.setBackgroundResource(R.drawable.constellation_capricorn_label);
        else if (data.getConstellation() == getResources().getString(R.string.gemini))
            constellation.setBackgroundResource(R.drawable.constellation_gemini_label);
        else if (data.getConstellation() == getResources().getString(R.string.leo))
            constellation.setBackgroundResource(R.drawable.constellation_leo_label);
        else if (data.getConstellation() == getResources().getString(R.string.libra))
            constellation.setBackgroundResource(R.drawable.constellation_libra_label);
        else if (data.getConstellation() == getResources().getString(R.string.pisces))
            constellation.setBackgroundResource(R.drawable.constellation_pisces_label);
        else if (data.getConstellation() == getResources().getString(R.string.sagittarius))
            constellation.setBackgroundResource(R.drawable.constellation_sagittarius_label);
        else if (data.getConstellation() == getResources().getString(R.string.scorpio))
            constellation.setBackgroundResource(R.drawable.constellation_scorpio_label);
        else if (data.getConstellation() == getResources().getString(R.string.taurus))
            constellation.setBackgroundResource(R.drawable.constellation_taurus_label);
        else if (data.getConstellation() == getResources().getString(R.string.virgo))
            constellation.setBackgroundResource(R.drawable.constellation_virgo_label);
        constellation.setText(data.getConstellation());
        occupation.setText(data.getOccupation());
    }
}
