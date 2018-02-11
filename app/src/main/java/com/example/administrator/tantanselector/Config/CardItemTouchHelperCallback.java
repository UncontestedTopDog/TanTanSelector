package com.example.administrator.tantanselector.Config;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;


import com.example.administrator.tantanselector.AddDataCommand;
import com.example.administrator.tantanselector.RxBus;

import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */

public class CardItemTouchHelperCallback<T> extends ItemTouchHelper.Callback {

    public static final String TAG = "CardItemTouchHelperCallback";
    private final RecyclerView.Adapter adapter;
    private List<T> dataList;
    private OnSwipeListener<T> mListener;

    public CardItemTouchHelperCallback(@NonNull RecyclerView.Adapter adapter, @NonNull List<T> dataList) {
        this.adapter = checkIsNull(adapter);
        this.dataList = checkIsNull(dataList);
    }

    private <T> T checkIsNull(T t) {
        if (t != null)
            return t;
        else throw new NullPointerException();
    }

    public void setOnSwipedListener(OnSwipeListener<T> mListener) {
        this.mListener = mListener;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList ;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = 0;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof CardLayoutManager)
            swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        viewHolder.itemView.setOnTouchListener(null);
        int layoutPosition = viewHolder.getLayoutPosition();
        T remove = dataList.remove(layoutPosition);
        if (dataList.size()<5){
            AddDataCommand addDataCommand = new AddDataCommand("");
            RxBus.getDefault().post(addDataCommand);
        }
        adapter.notifyDataSetChanged();
        if (mListener != null)
            switch (direction) {
                case ItemTouchHelper.UP:
                    mListener.onSwiped(viewHolder, null, CardConfig.SWIPED_UP);
                    break;
                case ItemTouchHelper.DOWN:
                    mListener.onSwiped(viewHolder, null, CardConfig.SWIPED_DOWN);
                    break;
                case ItemTouchHelper.LEFT:
                    mListener.onSwiped(viewHolder, null, CardConfig.SWIPED_LEFT);
                    break;
                case ItemTouchHelper.RIGHT:
                    mListener.onSwiped(viewHolder, null, CardConfig.SWIPED_RIGHT);
                    break;
                default:
                    break;
            }
        if (adapter.getItemCount() == 0) {
            if (mListener != null)
                mListener.onSwipedClear();
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View itemView = viewHolder.itemView;
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            float ratioX = dX / getThreshold(recyclerView, viewHolder) > 1 ? 1 : (dX / getThreshold(recyclerView, viewHolder) < -1 ? -1 : dX / getThreshold(recyclerView, viewHolder));
            float ratioY = dY / getThreshold(recyclerView, viewHolder);
            float ratio = Math.abs(dX) > Math.abs(dY) ? (ratioX > 1 ? 1 : (ratioX < -1 ? -1 : ratioX)) : (ratioY > 1 ? 1 : (ratioY < -1 ? -1 : ratioY));
            itemView.setRotation(ratioX * CardConfig.DEFAULT_ROTATE_DEGREE);
            int childCount = recyclerView.getChildCount();
            if (childCount > CardConfig.DEFAULT_SHOW_ITEM) {
                for (int position = 1; position < childCount - 1; position++) {
                    int index = childCount - position - 1;
                    View view = recyclerView.getChildAt(position);
                    view.setScaleX(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(ratio) * CardConfig.DEFAULT_SCALE);
                    view.setScaleY(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(ratio) * CardConfig.DEFAULT_SCALE);
                    view.setTranslationY((index - Math.abs(ratio)) * itemView.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);
                }
            } else {
                // 当数据源个数小于或等于最大显示数时
                for (int position = 0; position < childCount - 1; position++) {
                    int index = childCount - position - 1;
                    View view = recyclerView.getChildAt(position);
                    view.setScaleX(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(ratio) * CardConfig.DEFAULT_SCALE);
                    view.setScaleY(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(ratio) * CardConfig.DEFAULT_SCALE);
                    view.setTranslationY((index - Math.abs(ratio)) * itemView.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);
                }
            }
            // 回调监听器
            if (mListener != null) {
                if (Math.abs(ratioX) < 0.5 && Math.abs(ratioY) > 1)
                    mListener.onSwiping(viewHolder, ratioY, ratioY < 0 ? CardConfig.SWIPING_UP : CardConfig.SWIPING_DOWN);
                else if (ratioX != 0)
                    mListener.onSwiping(viewHolder, ratioX, ratioX < 0 ? CardConfig.SWIPING_RIGHT : CardConfig.SWIPING_LEFT);
                else
                    mListener.onSwiping(viewHolder, ratioX, CardConfig.SWIPING_NONE);
            }
        }
    }

    private float getThreshold(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return recyclerView.getWidth() * getSwipeThreshold(viewHolder);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setRotation(0f);
    }

}
