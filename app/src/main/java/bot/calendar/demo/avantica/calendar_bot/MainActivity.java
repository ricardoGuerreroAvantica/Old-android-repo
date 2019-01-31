package bot.calendar.demo.avantica.calendar_bot;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import bot.calendar.demo.avantica.calendar_bot.controllers.MainActivityController;
import bot.calendar.demo.avantica.calendar_bot.controllers.ManagerPresenter;

import bot.calendar.demo.avantica.calendar_bot.commons.Callback;
import bot.calendar.demo.avantica.calendar_bot.groupChat.Message;
import bot.calendar.demo.avantica.calendar_bot.groupChat.MessageAdapter;

        public class MainActivity extends AppCompatActivity {

            private static final int REQUEST = 200;

            private MainActivityController mainActivityController;

            private EditText mEdittext_chatbox;
            private RecyclerView mRecyclerView;
            private RecyclerView.Adapter mAdapter;
            private RecyclerView.LayoutManager mLayoutManager;
            private List messageList = new ArrayList();
            private AppCompatImageButton mSendButton;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.fragment_messages);

                mEdittext_chatbox = findViewById(R.id.edittext_chatbox);

                mRecyclerView = findViewById(R.id.reyclerview_message_list);
                mRecyclerView.setHasFixedSize(true);

                mLayoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(mLayoutManager);

                mAdapter = new MessageAdapter(messageList);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());

                mSendButton = findViewById(R.id.send_message);

                messageList.add(new Message(1,"Hi, how i can help you?", "Calendar bot"));

                mEdittext_chatbox.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (mEdittext_chatbox.getText().toString().isEmpty()){
                            mSendButton.setImageResource(R.drawable.ic_voice);
                        }else{
                            mSendButton.setImageResource(R.drawable.ic_send);
                        }
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (mEdittext_chatbox.getText().toString().isEmpty()){
                            mSendButton.setImageResource(R.drawable.ic_voice);
                        }else{
                            mSendButton.setImageResource(R.drawable.ic_send);
                        }
                    }
                });

                mainActivityController = new MainActivityController();
                TTS.init(getApplicationContext());
                validateRecordingPermission();
            }

            private void validateRecordingPermission() {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST);
                }
            }


    public void voiceSend(){
        mainActivityController.voiceCommand(managerCallback, this);
    }


    public void textSend(String query){
        messageList.add(new Message(messageList.size() + 1,query, "You"));
        mEdittext_chatbox.setText("", TextView.BufferType.EDITABLE);
        mainActivityController.textCommand(managerCallback, this, query);

        mAdapter.notifyDataSetChanged();
        mRecyclerView.smoothScrollToPosition(messageList.size()-1);
    }

    public void sendData(View v){
        String query = mEdittext_chatbox.getText().toString();
        if (query.isEmpty()){
            voiceSend();
        }else{
            textSend(query);
        }
    }



    Callback<ManagerPresenter> managerCallback = new Callback<ManagerPresenter>() {
        @Override
        public void success(ManagerPresenter response) {
            messageList.add(new Message(messageList.size() + 1,response.getSpeech(), "Calendar bot"));
            TTS.speak(response.getMessage());
            mAdapter.notifyDataSetChanged();
            mRecyclerView.scrollToPosition(messageList.size()-1);

        }

        @Override
        public void failure(Exception ex) {

        }
    };




}


