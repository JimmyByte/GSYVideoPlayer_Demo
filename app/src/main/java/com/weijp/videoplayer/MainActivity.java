package com.weijp.videoplayer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.listener.GSYVideoShotListener;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class MainActivity extends Activity {

    private OrientationUtils orientationUtils;
    private StandardGSYVideoPlayer videoPlayer;
    private ImageView cutimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoPlayer = (StandardGSYVideoPlayer)findViewById(R.id.detail_player);
        cutimg = (ImageView)findViewById(R.id.cut_img_iv);
        String source1 = "https://vd1.bdstatic.com/mda-hcmswkpbvevrdr7u/sc/mda-hcmswkpbvevrdr7u.mp4";
        videoPlayer.setUp(source1, true, "测试视频");

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_launcher);
        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        videoPlayer.startPlayLogic();

        videoPlayer.setVideoAllCallBack(new VideoAllCallBack() {
            @Override
            public void onStartPrepared(String url, Object... objects) {
                System.out.println("---1");
            }

            @Override
            public void onPrepared(String url, Object... objects) {
                System.out.println("---2");
            }

            @Override
            public void onClickStartIcon(String url, Object... objects) {
                System.out.println("---3");
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                System.out.println("---4");
            }

            @Override
            public void onClickStop(String url, Object... objects) {
                videoPlayer.taskShotPic(new GSYVideoShotListener() {
                    @Override
                    public void getBitmap(Bitmap bitmap) {
                        Matrix matrix = new Matrix();
                        matrix.setScale(0.7f, 0.7f);
                       Bitmap stop_videoimp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);//截图
                        cutimg.setImageBitmap(stop_videoimp);
                    }
                });

                System.out.println("---5");
            }

            @Override
            public void onClickStopFullscreen(String url, Object... objects) {
                System.out.println("---6");
            }

            @Override
            public void onClickResume(String url, Object... objects) {
                System.out.println("---7");
            }

            @Override
            public void onClickResumeFullscreen(String url, Object... objects) {
                System.out.println("---8");
            }

            @Override
            public void onClickSeekbar(String url, Object... objects) {
                System.out.println("---9");
            }

            @Override
            public void onClickSeekbarFullscreen(String url, Object... objects) {
                System.out.println("---10");
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                System.out.println("---11");
            }

            @Override
            public void onEnterFullscreen(String url, Object... objects) {
                System.out.println("---12");
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                System.out.println("---13");
            }

            @Override
            public void onQuitSmallWidget(String url, Object... objects) {
                System.out.println("---14");
            }

            @Override
            public void onEnterSmallWidget(String url, Object... objects) {
                System.out.println("---15");
            }

            @Override
            public void onTouchScreenSeekVolume(String url, Object... objects) {
                System.out.println("---16");
            }

            @Override
            public void onTouchScreenSeekPosition(String url, Object... objects) {
                System.out.println("---17");
            }

            @Override
            public void onTouchScreenSeekLight(String url, Object... objects) {
                System.out.println("---18");
            }

            @Override
            public void onPlayError(String url, Object... objects) {
                System.out.println("---19");
            }

            @Override
            public void onClickStartThumb(String url, Object... objects) {
                System.out.println("---20");
            }

            @Override
            public void onClickBlank(String url, Object... objects) {
                System.out.println("---21");
            }

            @Override
            public void onClickBlankFullscreen(String url, Object... objects) {
                System.out.println("---22");
            }
        });
    }
}
