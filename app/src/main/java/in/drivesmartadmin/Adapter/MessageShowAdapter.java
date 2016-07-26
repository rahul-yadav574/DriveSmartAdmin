package in.drivesmartadmin.Adapter;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import in.drivesmartadmin.Db.DbHelper;
import in.drivesmartadmin.R;

/**
 * Created by Brekkishhh on 25-07-2016.
 */
public class MessageShowAdapter extends BaseAdapter {

    private List<DbHelper.Message>  messageList;

    public MessageShowAdapter(List<DbHelper.Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_message_list,parent,false);
            holder = new ViewHolder();

            holder.timeStampTextView = (TextView) convertView.findViewById(R.id.timeStampTextView);
            holder.phoneTextView = (TextView) convertView.findViewById(R.id.phoneTextView);
            holder.messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);

            convertView.setTag(holder);
        }

        else{
            holder = (ViewHolder)  convertView.getTag();
        }

        holder.phoneTextView.setText(messageList.get(position).getPhone());
        holder.messageTextView.setText(messageList.get(position).getMessage());
        holder.timeStampTextView.setText(convertTimeStampToDate(messageList.get(position).getTime()));

        return convertView;
    }


    class ViewHolder{

        public TextView phoneTextView;
        public TextView messageTextView;
        public TextView timeStampTextView;
    }

    String convertTimeStampToDate(String timeStamp){

        Calendar calendar  = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(Long.parseLong(timeStamp));
        String date = DateFormat.format("dd-MM-yyyy", calendar).toString();
        return date;

    }

}
