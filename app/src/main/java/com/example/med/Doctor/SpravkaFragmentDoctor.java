package com.example.med.Doctor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.med.DBHelper;
import com.example.med.Adapters.MessageAdapter;
import com.example.med.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.Disposable;


public class SpravkaFragmentDoctor extends Fragment {

    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private List<String> messages = new ArrayList<>();

    private Disposable disposable;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spravka_doctor, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        messageAdapter = new MessageAdapter(messages);
        recyclerView.setAdapter(messageAdapter);

        loadMessages();

        Button sendButton = view.findViewById(R.id.buttonSentConversation);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        return view;
    }

    private void loadMessages() {
        SharedPreferences preferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        int patientId = Integer.parseInt(preferences.getString("id", "-1"));

        DBHelper dbHelper = new DBHelper(getActivity());
        messages.clear();
        messages.addAll(dbHelper.getMessages(patientId));
        dbHelper.close();

        messageAdapter.notifyDataSetChanged();
    }

    @SuppressLint("CheckResult")
    private void sendMessage() {
        EditText editTextQuestion = getActivity().findViewById(R.id.editTextQuestion);
        String question = editTextQuestion.getText().toString();

        if (question.isEmpty()) {
            Toast.makeText(getActivity(), "Сообщение не должно быть пустым", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences preferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        int patientId = Integer.parseInt(preferences.getString("id", "-1"));

        DBHelper dbHelper = new DBHelper(getActivity());

        Completable.fromAction(() -> {
                    int chatId = dbHelper.getLastChatId(patientId);
                    if (chatId == -1 || dbHelper.isChatComplete(chatId)) {
                        chatId = new Random().nextInt(100000);
                        dbHelper.insertChat(chatId, patientId);
                    }
                    dbHelper.insertMessage(chatId, question, patientId);
                })
                .subscribe(() -> {
                    Toast.makeText(getActivity(), "Сообщение успешно отправлено", Toast.LENGTH_SHORT).show();
                    editTextQuestion.setText("");
                    dbHelper.close();
                    refreshMessages();
                }, throwable -> {
                    Toast.makeText(getActivity(), "Ошибка при отправке сообщения", Toast.LENGTH_SHORT).show();
                    dbHelper.close();
                    refreshMessages();
                });

    }

    private void refreshMessages() {
        loadMessages();
        messageAdapter.notifyDataSetChanged();
    }


}

