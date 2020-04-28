package com.cyq.animdemo.transition;

import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cyq.animdemo.R;

import static com.cyq.animdemo.transition.utils.Constant.DURATION;

public class OneActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBack;
    private Button mNext;
    private View rootView;
    private Animation exitAnimation;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        rootView = findViewById(R.id.root_view);

        //setupWindowAnimations();
        mBack = findViewById(R.id.btn_back);
        mNext = findViewById(R.id.btn_next);
        mBack.setOnClickListener(this);
        mNext.setOnClickListener(this);
        exitAnimation = AnimationUtils.loadAnimation(this, R.anim.air_fry_transition_close_enter);
    }

    private void setupWindowAnimations() {
        Slide enter = new Slide();
        enter.setDuration(DURATION);
        enter.setSlideEdge(Gravity.RIGHT);
        enter.setInterpolator(new DecelerateInterpolator());

        Slide exit = new Slide();
        exit.setDuration(DURATION);
        exit.setSlideEdge(Gravity.LEFT);
        exit.setInterpolator(new DecelerateInterpolator());

        Slide reenter = new Slide();
        reenter.setSlideEdge(Gravity.LEFT);
        reenter.setDuration(DURATION);
        reenter.setInterpolator(new DecelerateInterpolator());

        Slide returnT = new Slide();
        returnT.setDuration(DURATION);
        returnT.setSlideEdge(Gravity.RIGHT);
        returnT.setInterpolator(new DecelerateInterpolator());

        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(DURATION);
        changeBounds.setResizeClip(true);

        getWindow().setReenterTransition(reenter);
        getWindow().setEnterTransition(enter);
        getWindow().setReturnTransition(returnT);
        getWindow().setExitTransition(exit);
        getWindow().setAllowEnterTransitionOverlap(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                break;
            case R.id.btn_next:
                Intent i = new Intent(this, TowActivity.class);
                startActivityForResult(i,101);
                startRootAnimation();
                break;
            default:

        }
    }

    private void startRootAnimation() {
        animation = AnimationUtils.loadAnimation(this, R.anim.air_fry_transition_open_exit);
        rootView.startAnimation(animation);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("test","收回返回.....");
        rootView.startAnimation(exitAnimation);
    }
}
