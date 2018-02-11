package com.example.administrator.tantanselector.Selector;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.widget.Toast;

import com.example.administrator.tantanselector.Config.CardConfig;
import com.example.administrator.tantanselector.Config.CardItemTouchHelperCallback;
import com.example.administrator.tantanselector.Config.CardLayoutManager;
import com.example.administrator.tantanselector.Config.OnSwipeListener;
import com.example.administrator.tantanselector.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */

public class TanTanSelector extends RecyclerView {

    private static final String TAG = "CounterfeitMOMOSelector";
    List<Data> datas = new ArrayList<>();
    TanTanSelectorAdapter counterfeitMOMOSelectorAdapter;
    CardItemTouchHelperCallback cardItemTouchHelperCallback ;

    public TanTanSelector(Context context) {
        super(context);
    }

    public TanTanSelector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TanTanSelector(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void initView(final Context context) {
        cardItemTouchHelperCallback = new CardItemTouchHelperCallback(getAdapter(), datas);
        cardItemTouchHelperCallback.setOnSwipedListener(new OnSwipeListener<Integer>() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                TanTanSelectorAdapter.CounterfeitMOMOSelectorViewHolder myViewHolder = (TanTanSelectorAdapter.CounterfeitMOMOSelectorViewHolder) viewHolder;
//                viewHolder.itemView.setAlpha(1-Math.abs(ratio)*0.2f);

                switch (direction) {
                    case CardConfig.SWIPING_UP:
                        myViewHolder.verylike.setAlpha(Math.abs(ratio) - 1);
                        myViewHolder.like.setAlpha(0f);
                        myViewHolder.unlike.setAlpha(0f);
                        break;
                    case CardConfig.SWIPING_DOWN:
                        myViewHolder.verylike.setAlpha(0f);
                        myViewHolder.like.setAlpha(0f);
                        myViewHolder.unlike.setAlpha(0f);
                        break;
                    case CardConfig.SWIPING_LEFT:
                        myViewHolder.like.setAlpha(Math.abs(ratio));
                        myViewHolder.verylike.setAlpha(0f);
                        myViewHolder.unlike.setAlpha(0f);
                        break;
                    case CardConfig.SWIPING_RIGHT:
                        myViewHolder.unlike.setAlpha(Math.abs(ratio));
                        myViewHolder.verylike.setAlpha(0f);
                        myViewHolder.like.setAlpha(0f);
                        break;
                    case CardConfig.SWIPING_NONE:
                        myViewHolder.verylike.setAlpha(0f);
                        myViewHolder.like.setAlpha(0f);
                        myViewHolder.unlike.setAlpha(0f);
                        break;
                    default:
                        myViewHolder.verylike.setAlpha(0f);
                        myViewHolder.like.setAlpha(0f);
                        myViewHolder.unlike.setAlpha(0f);
                        break;

                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Integer integer, int direction) {
                TanTanSelectorAdapter.CounterfeitMOMOSelectorViewHolder myViewHolder = (TanTanSelectorAdapter.CounterfeitMOMOSelectorViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                myViewHolder.verylike.setAlpha(0f);
                myViewHolder.like.setAlpha(0f);
                myViewHolder.unlike.setAlpha(0f);
                switch (direction) {
                    case CardConfig.SWIPED_UP:
                        Toast.makeText(context, "swiped UP", Toast.LENGTH_SHORT).show();
                        break;
                    case CardConfig.SWIPED_DOWN:
                        Toast.makeText(context, "swiped DOWN", Toast.LENGTH_SHORT).show();
                        break;
                    case CardConfig.SWIPED_LEFT:
                        Toast.makeText(context, "swiped LEFT", Toast.LENGTH_SHORT).show();
                        break;
                    case CardConfig.SWIPED_RIGHT:
                        Toast.makeText(context, "swiped RIGHT", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onSwipedClear() {
                Toast.makeText(context, "data clear", Toast.LENGTH_SHORT).show();
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(cardItemTouchHelperCallback);
        CardLayoutManager cardLayoutManager = new CardLayoutManager(itemTouchHelper, this);
        setLayoutManager(cardLayoutManager);
        itemTouchHelper.attachToRecyclerView(this);

    }

    public void bindData(List<Data> datas) {
        this.datas = datas;
        if (counterfeitMOMOSelectorAdapter == null) {
            counterfeitMOMOSelectorAdapter = new TanTanSelectorAdapter(datas);
            setAdapter(counterfeitMOMOSelectorAdapter);
        } else counterfeitMOMOSelectorAdapter.notifyDataSetChanged();
        initView(getContext());
    }

    public CardItemTouchHelperCallback getCardItemTouchHelperCallback() {
        return cardItemTouchHelperCallback;
    }

    public void setCardItemTouchHelperCallback(CardItemTouchHelperCallback cardItemTouchHelperCallback) {
        this.cardItemTouchHelperCallback = cardItemTouchHelperCallback;
    }
}
