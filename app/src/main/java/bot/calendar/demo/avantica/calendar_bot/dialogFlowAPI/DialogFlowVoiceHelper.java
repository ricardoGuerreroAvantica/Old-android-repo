package bot.calendar.demo.avantica.calendar_bot.dialogFlowAPI;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIContext;
import ai.api.model.AIResponse;
import bot.calendar.demo.avantica.calendar_bot.commons.Callback;


public class DialogFlowVoiceHelper{

    private Context context;
    private String androidKey;


    public DialogFlowVoiceHelper(Context context, String androidKey){
        this.androidKey = androidKey;
        this.context = context;
    }

    public void listen(Callback<AIResponse> response){

        final AIConfiguration config = new AIConfiguration(Constants.DIALOG_CLIENT_ID,
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

        config.setNormalizeInputSound(true);

        AIService aiService = AIService.getService(this.context, config);

        DialogFlowVoiceAPI listener = new DialogFlowVoiceAPI(response);

        aiService.setListener(listener);

        AIContext aiContext = new AIContext();
        aiContext.setName("session");
        aiContext.setLifespan(10);
        Map parameters = new HashMap();

        parameters.put("id", androidKey);
        aiContext.setParameters(parameters);
        ArrayList<AIContext> contexts = new ArrayList<>();
        contexts.add(aiContext);

        aiService.startListening(contexts);
    }



}
