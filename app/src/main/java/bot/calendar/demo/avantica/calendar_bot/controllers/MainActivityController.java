package bot.calendar.demo.avantica.calendar_bot.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.Object;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import ai.api.model.AIContext;
import ai.api.model.ResponseMessage;
import bot.calendar.demo.avantica.calendar_bot.commons.Callback;

import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import bot.calendar.demo.avantica.calendar_bot.dialogFlowAPI.DialogFlowTextHelper;
import bot.calendar.demo.avantica.calendar_bot.dialogFlowAPI.DialogFlowVoiceHelper;


public class MainActivityController {

    private static final String PREF_UNIQUE_ID = "ANDROID_ID";

    public void voiceCommand(final Callback<ManagerPresenter> presenterICallback, Context context){
        DialogFlowVoiceHelper dialogFlowVoiceHelper = new DialogFlowVoiceHelper(context, getDeviceKey(context));
        dialogFlowVoiceHelper.listen(new Callback<AIResponse>() {
            @Override
            public void success(AIResponse response) {
                try {
                    String message = "Could you repeat that?";
                    String speech;

                    speech = response.getResult().getFulfillment().getSpeech();
                    if (response.getResult().getFulfillment().getDisplayText() != null){
                        message = response.getResult().getFulfillment().getDisplayText();
                    }else if (response.getResult().getFulfillment().getMessages().size() > 0){
                        message = response.getResult().getFulfillment().getSpeech();
                    }


                    // PARSE USER RESPONSE
                    ManagerPresenter presenter = new ManagerPresenter();
                    presenter.setMessage(message);
                    presenter.setSpeech(speech);
                    presenterICallback.success(presenter);

                } catch (Exception e) {
                    ManagerPresenter presenter = new ManagerPresenter();
                    presenter.setMessage(e.getMessage());
                    presenterICallback.success(presenter);

                }
            }
            @Override
            public void failure(Exception ex) {
                presenterICallback.failure(ex);
            }
        });
    }

    public void textCommand(final Callback<ManagerPresenter> presenterICallback, Context context, String query){
        AIRequest aiRequest = new AIRequest();
        DialogFlowTextHelper request = new DialogFlowTextHelper();
        AIResponse response;

        try {
            Log.w("LOOK HERE","START");
            String message = "Could you repeat that?";
            String speech;

            aiRequest.setQuery(query);

            AIContext aiContext = new AIContext();
            aiContext.setName("session");
            aiContext.setLifespan(10);
            Map parameters = new HashMap();

            parameters.put("id", getDeviceKey(context));
            aiContext.setParameters(parameters);
            aiRequest.addContext(aiContext);

            request.setContext(context);
            response = request.execute(aiRequest).get();


            speech = response.getResult().getFulfillment().getSpeech();
            if (response.getResult().getFulfillment().getDisplayText() != null){
                Log.w("NOTHING HERE",message);
                message = response.getResult().getFulfillment().getDisplayText();
            }else if (response.getResult().getFulfillment().getMessages().size() > 0){
                message = response.getResult().getFulfillment().getSpeech();
            }

            Log.w("LOOK HERE",message);
            // PARSE USER RESPONSE
            ManagerPresenter presenter = new ManagerPresenter();
            presenter.setMessage(message);
            presenter.setSpeech(speech);
            presenterICallback.success(presenter);

        }catch (Exception e) {
            ManagerPresenter presenter = new ManagerPresenter();
            presenter.setMessage(e.getMessage());
            presenterICallback.success(presenter);
        }
    }



    private String getDeviceKey(Context context){
        SharedPreferences sharedPrefs = context.getSharedPreferences(PREF_UNIQUE_ID, Context.MODE_PRIVATE);
        String androidKey = sharedPrefs.getString(PREF_UNIQUE_ID, null);
        if (androidKey == null){
            androidKey = UUID.randomUUID().toString();
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(PREF_UNIQUE_ID, androidKey);
            editor.commit();
        }
        return androidKey;
    }
}