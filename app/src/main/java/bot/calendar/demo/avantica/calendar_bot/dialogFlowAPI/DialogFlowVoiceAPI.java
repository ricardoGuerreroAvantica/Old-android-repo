package bot.calendar.demo.avantica.calendar_bot.dialogFlowAPI;


import ai.api.AIListener;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import bot.calendar.demo.avantica.calendar_bot.commons.Callback;


public class DialogFlowVoiceAPI implements AIListener {

    Callback<AIResponse> response;

    public DialogFlowVoiceAPI(Callback<AIResponse> response){
        this.response = response;
    }


    @Override
    public void onResult(AIResponse result) {
        this.response.success(result);
    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onError(AIError error) {
        throw new IllegalArgumentException(error.getMessage());
    }
}
