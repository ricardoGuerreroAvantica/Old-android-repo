package bot.calendar.demo.avantica.calendar_bot.groupChat;
import bot.calendar.demo.avantica.calendar_bot.R;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> messageList;

    public static final int SENDER = 0;
    public static final int RECEIVER = 1;

    public MessageAdapter(List messages) {
        messageList = messages;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView messageText;
        public TextView messageMeta;

        public ViewHolder(FrameLayout v) {
            super(v);
            messageText = v.findViewById(R.id.messageText);
            messageMeta = v.findViewById(R.id.messageMeta);
        }
    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_left, parent, false);
            ViewHolder vh = new ViewHolder((FrameLayout) v);
            return vh;
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_right, parent, false);
            ViewHolder vh = new ViewHolder((FrameLayout) v);
            return vh;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.messageText.setText(messageList.get(position).getMessage());
        holder.messageMeta.setText(messageList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);

        if (message.getSenderName().equals("Calendar bot")) {
            return RECEIVER;
        } else {
            return SENDER;
        }

    }

}