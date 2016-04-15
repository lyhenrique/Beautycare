package com.beautycare.discountpage;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.beautycare.R;
import com.beautycare.discountpage.widget.ImageLoadingDialog;
import com.bm.library.PhotoView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class SeeBigImg extends AppCompatActivity {
    PhotoView img;
    Bundle bundle;
    DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_big_img);
        //显示系统Actionbar的返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory()
                .cacheOnDisc()
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
        img = (PhotoView)findViewById(R.id.img);
        img.enable();
        bundle = getIntent().getExtras();
        ImageLoader.getInstance().displayImage(bundle.getString("URL"),img,options);


        final ImageLoadingDialog dialog = new ImageLoadingDialog(this);
        dialog.show();
        // 两秒后关闭后dialog
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1000 * 2);
    }

    //菜单返回上一页
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
                return true;
        }
        return false;
    }
}