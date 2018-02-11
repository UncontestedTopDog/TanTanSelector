package com.example.administrator.tantanselector;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.tantanselector.Selector.TanTanSelector;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends RxAppCompatActivity {

    TanTanSelector counterfeit_momo_selector ;
    List<Data> datas = new ArrayList<>();
    Button reset ;
    private Subscription subscription ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        counterfeit_momo_selector = findViewById(R.id.counterfeit_momo_selector);
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
                counterfeit_momo_selector.bindData(datas);
            }
        });
        counterfeit_momo_selector.bindData(datas);

        subscription = RxBus.getDefault().register(AddDataCommand.class, this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<AddDataCommand>() {
                    @Override
                    public void call(AddDataCommand cmd) {
                        initData();
                        counterfeit_momo_selector.getCardItemTouchHelperCallback().setDataList(datas);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    private void initData() {
        Data data0 = new Data("http://pic.qiantucdn.com/58pic/26/05/97/56258PICU5y_1024.jpg","0");
        Data data1 = new Data("http://pic.qiantucdn.com/58pic/26/07/29/29B58PICh82_1024.jpg","1");
        Data data2 = new Data("http://pic.qiantucdn.com/58pic/26/07/29/44m58PICe9D_1024.jpg","2");
        Data data3 = new Data("http://pic.qiantucdn.com/58pic/26/07/37/46A58PICRkV_1024.jpg","3");
        Data data4 = new Data("http://pic.qiantucdn.com/58pic/26/09/91/42n58PICVXi_1024.jpg","4");
        Data data5 = new Data("http://pic.qiantucdn.com/58pic/26/19/44/60Z58PICyjq_1024.jpg","5");
        Data data6 = new Data("http://pic.qiantucdn.com/58pic/26/20/42/57X58PICYtG_1024.jpg","6");
        Data data7 = new Data("http://pic.qiantucdn.com/58pic/26/20/42/18y58PICp4g_1024.jpg","7");
        Data data8 = new Data("http://pic.qiantucdn.com/58pic/26/20/42/70R58PICzQ5_1024.jpg","8");
        Data data9 = new Data("http://pic.qiantucdn.com/58pic/26/20/42/86p58PICQwH_1024.jpg","9");
        datas.add(data0);
        datas.add(data1);
        datas.add(data2);
        datas.add(data3);
        datas.add(data4);
        datas.add(data5);
        datas.add(data6);
        datas.add(data7);
        datas.add(data8);
        datas.add(data9);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
