package com.example.androidlabs;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.AlertDialog;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

        import static android.view.View.TEXT_ALIGNMENT_TEXT_START;

public class ChatRoomActivity extends AppCompatActivity {

    private List<Message> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        BaseAdapter chatAdapter = new BaseAdapter() {

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Message getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = getLayoutInflater();
                View newView;
                TextView tView;

                Message message = getItem(position);
                if (message.getType() == MessageType.RECEIVE) {
                    newView = inflater.inflate(R.layout.recieve_row_layout, parent, false );
                    tView = newView.findViewById(R.id.textGoesReceive);
                } else {
                    newView = inflater.inflate(R.layout.send_row_layout, parent, false );
                    tView = newView.findViewById(R.id.textGoesSend);
                }
                tView.setText(message.getText());

                return newView;
            }
        };
        ListView myList = findViewById(R.id.theListID);
        myList.setAdapter(chatAdapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoomActivity.this);
                builder.setTitle("Do you want to delete this?")
                        .setMessage("The selected row is: " + position +
                                "\nThe database id id: " + id)
                        .setCancelable(false)
                        .setPositiveButton("Yes", (dialog, which) -> {
                            list.remove(position);
                            chatAdapter.notifyDataSetChanged();
                        })
                        .setNegativeButton("No", (dialog, which) -> {});

                //Creating dialog box
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(bt -> {
            Message message = buildMessage(MessageType.SEND);
            list.add(message);
            chatAdapter.notifyDataSetChanged();
        });

        Button receiveButton = findViewById(R.id.receiveButton);
        receiveButton.setOnClickListener(bt -> {
            Message message = buildMessage(MessageType.RECEIVE);
            list.add(message);
            chatAdapter.notifyDataSetChanged();
        });
    }

    private Message buildMessage(MessageType type) {
        Message message = new Message();
        EditText messageInput = findViewById(R.id.messageInput);
        message.setText(messageInput.getText().toString());
        message.setType(type);
        messageInput.getText().clear();
        return message;
    }
}
