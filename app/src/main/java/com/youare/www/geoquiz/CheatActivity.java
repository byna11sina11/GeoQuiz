package com.youare.www.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by st on 2017-11-20.
 */
public class CheatActivity extends Activity {
    private Button mSureButton;
    private TextView mResultView;
    private boolean isRight;
    private boolean isCheat;
    public static final String RESULT_EXA="CheatActivity.return";
    public static final String CHEAT_EXA="CheatActivity.cheat";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        if(savedInstanceState!=null){
            isCheat=savedInstanceState.getBoolean(CHEAT_EXA);
        }else {
            isCheat=false;
        }

        isRight=getIntent().getBooleanExtra(MainActivity.ANSWER_OK,false);

        setAnswerShownResult(isCheat);


        mResultView= (TextView) findViewById(R.id.tv_info);
        mSureButton= (Button) findViewById(R.id.btn_sure);
        mSureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRight){
                    mResultView.setText(R.string.strue);
                }else{
                    mResultView.setText(R.string.sfalse);
                }
                isCheat=true;
                setAnswerShownResult(isCheat);
               // onSaveInstanceState(savedInstanceState);
            }
        });

    }

    private void setAnswerShownResult(boolean isShow){
        Intent i=new Intent();
        i.putExtra(RESULT_EXA,isShow);
        setResult(RESULT_OK,i);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(CHEAT_EXA,isCheat);
    }
}
