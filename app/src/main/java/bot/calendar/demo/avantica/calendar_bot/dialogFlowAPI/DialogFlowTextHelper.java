package bot.calendar.demo.avantica.calendar_bot.dialogFlowAPI;

import android.content.Context;
import android.os.AsyncTask;

import ai.api.android.AIConfiguration;
import ai.api.android.AIDataService;
import ai.api.model.AIContext;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;


public class DialogFlowTextHelper extends AsyncTask<AIRequest, Void, AIResponse> {

    private Context context;

    public void setContext(Context context){
        this.context = context;
    }


    @Override
    protected AIResponse doInBackground(AIRequest... requests) {
        AIResponse response;
        try {
            AIConfiguration config = new AIConfiguration(Constants.DIALOG_CLIENT_ID,
                    AIConfiguration.SupportedLanguages.English,
                    AIConfiguration.RecognitionEngine.System);
            AIRequest request = requests[0];
            AIDataService aiDataService = new AIDataService(context, config);
            response = aiDataService.request(request);
            return response;

        }catch(Exception e){
            e.printStackTrace();
            response = null;
        }
        return response;
    }

    @Override
    protected void onPostExecute(AIResponse result){
        super.onPostExecute(result);
    }




}
