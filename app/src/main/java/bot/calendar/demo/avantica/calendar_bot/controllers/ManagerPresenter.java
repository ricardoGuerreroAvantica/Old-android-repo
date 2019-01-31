package bot.calendar.demo.avantica.calendar_bot.controllers;

/**

 */

public class ManagerPresenter {

    private String message;
    private String title;
    private boolean loading;
    private String speech;

    ManagerPresenter(){
        this.loading = false;
        this.message = "";
        this.title = "";
        this.speech = "";
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }
}
