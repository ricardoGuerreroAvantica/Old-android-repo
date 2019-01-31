package bot.calendar.demo.avantica.calendar_bot.groupChat;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Message {

    private int id;
    private String message;
    private String senderName;
    private Calendar createdAt;

    public Message(int id, String message, String senderName){
        this.id = id;
        this.message = message;
        this.senderName = senderName;
        this.createdAt = Calendar.getInstance(TimeZone.getDefault());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getDate(){
        return new SimpleDateFormat("HH:mm").format(this.createdAt.getTime());
    }
}
