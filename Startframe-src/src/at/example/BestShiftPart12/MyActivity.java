package at.example.BestShiftPart12;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import at.example.BestShiftPart12.R;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MyActivity extends Activity {

    ImageView imgIcon;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    //all contents
        Button button1 = (Button) findViewById(R.id.button);
        final ImageView imgViewBS = (ImageView) findViewById(R.id.imageView11);
        final ImageView imgIcon = (ImageView) findViewById(R.id.imageView2);
        //Rotating icon :)
        final Animation iconRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imgIcon.startAnimation(iconRotate);

        //BestShift fadein
        final Animation BsFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        imgViewBS.startAnimation(BsFadeIn);

        final Animation StartPulse = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        button1.startAnimation(StartPulse);





        }
}

