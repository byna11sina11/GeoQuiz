package com.youare.www.geoquiz;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button mTrueButton;
    private Button mFlaseButton;
    private Button mCheatButton;
    private Button mNextButton;
    private boolean mIsCheat;
    private TextView mQuestionTextView;
    private static  final String KEY_INDEX="index";
    private static  final String KEY_cheat="ss";
    public static  final String ANSWER_OK="my anwser";

    private TrueFalse[] questionBox={
            new TrueFalse(R.string.question1,true),
            new TrueFalse(R.string.question2,false),
            new TrueFalse(R.string.question3,true),
            new TrueFalse(R.string.question4,true)
    };
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mQuestionTextView= (TextView) findViewById(R.id.tv_question);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (index + 1) % questionBox.length;
                updateQuestion(index);
            }
        });


        if(savedInstanceState!=null){
            index=savedInstanceState.getInt(KEY_INDEX,0);
            mIsCheat=savedInstanceState.getBoolean(KEY_cheat,false);
        }
        updateQuestion(index);


        mCheatButton= (Button) findViewById(R.id.btn_cheat);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CheatActivity.class);
                i.putExtra(ANSWER_OK, questionBox[index].isTrueQuestion());
              //  startActivity(i);
                startActivityForResult(i,0);
            }
        });
        mTrueButton= (Button) findViewById(R.id.btn_true);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFlaseButton= (Button) findViewById(R.id.btn_false);
        mFlaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mNextButton= (Button) findViewById(R.id.btn_next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (index + 1) % questionBox.length;
                updateQuestion(index);
            }
        });

    }

    private void checkAnswer(boolean b) {
        boolean answer=questionBox[index].isTrueQuestion();

        int toastId=0;
        if(mIsCheat==true){
            toastId=R.string.judgment_toast;
        }else {
            if(b==answer){
                toastId=R.string.right;
            }else {
                toastId=R.string.wrong;
            }
        }

        Toast.makeText(MainActivity.this,toastId,Toast.LENGTH_SHORT).show();

    }

    private void updateQuestion(int index) {
        mQuestionTextView.setText(questionBox[index].getQuestion());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX,index);
        outState.putBoolean(KEY_cheat,mIsCheat);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data==null){
            return;
        }
        mIsCheat=data.getBooleanExtra(CheatActivity.RESULT_EXA,false);
    }
}
