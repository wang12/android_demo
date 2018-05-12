package com.example.xiaoqiang.myapplication.play.kingsoft;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.xiaoqiang.myapplication.R;
import com.ksyun.media.player.IMediaPlayer;
import com.ksyun.media.player.KSYMediaPlayer;

import java.io.IOException;

/**
 * @Author: [xiaoqiang]
 * @Description: [KingSoftActivity]
 * @CreateDate: [2018/5/8]
 * @UpdateDate: [2018/5/8]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class KingSoftActivity extends Activity {

    // 播放器的对象
    private KSYMediaPlayer ksyMediaPlayer;
    // SurfaceView需在Layout中定义，此处不在赘述
//    private SurfaceView mVideoSurfaceView;
//    private SurfaceHolder mSurfaceHolder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kingsoft);
        ksyMediaPlayer = new KSYMediaPlayer.Builder(this.getApplicationContext()).build();

        ksyMediaPlayer.setOnCompletionListener(mOnCompletionListener);
        ksyMediaPlayer.setOnPreparedListener(mOnPreparedListener);
//        ksyMediaPlayer.setOnInfoListener(mOnInfoListener);
//        ksyMediaPlayer.setOnVideoSizeChangedListener(mOnVideoSizeChangeListener);
//        ksyMediaPlayer.setOnErrorListener(mOnErrorListener);
//        ksyMediaPlayer.setOnSeekCompleteListener(mOnSeekCompletedListener);

        try {
            ksyMediaPlayer.setDataSource("https://staticresource.console.ksyun.com/kmc/fireworkmv/home/NewHome.mp4");
            ksyMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private IMediaPlayer.OnCompletionListener mOnCompletionListener = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer mp) {
            // 播放完成，用户可选择释放播放器
            if(ksyMediaPlayer != null) {
                ksyMediaPlayer.stop();
                ksyMediaPlayer.release();
            }
        }
    };

    private IMediaPlayer.OnPreparedListener mOnPreparedListener = new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(IMediaPlayer mp) {
            if(ksyMediaPlayer != null) {
                // 设置视频伸缩模式，此模式为裁剪模式
                Log.w("wqq","开始播放");
                ksyMediaPlayer.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                // 开始播放视频
                ksyMediaPlayer.start();
            }
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if(ksyMediaPlayer != null)
            ksyMediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(ksyMediaPlayer != null)
            ksyMediaPlayer.release();
        ksyMediaPlayer = null;
    }
}
