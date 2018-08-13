package tgsoftware.com.br.agendawebservice2.firebase;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Map;

import tgsoftware.com.br.agendawebservice2.dao.AlunoDAO;
import tgsoftware.com.br.agendawebservice2.dto.AlunoSync;
import tgsoftware.com.br.agendawebservice2.event.AtualizaListaAlunoEvent;

public class AgendaMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MessagingService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().size() > 0) {
            Map<String, String> data = remoteMessage.getData();
            Log.i(TAG, data.toString());
            converteParaAluno(data);
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    private void converteParaAluno(Map<String, String> mensagem) {
        String chaveDeAcesso = "alunoSync";
        if (mensagem.containsKey(chaveDeAcesso)) {
            String json = mensagem.get(chaveDeAcesso);
            ObjectMapper mapper = new ObjectMapper();
            try {
                AlunoSync alunoSync = mapper.readValue(json, AlunoSync.class);
                AlunoDAO alunoDAO = new AlunoDAO(this);
                alunoDAO.sincroniza(alunoSync.getAlunos());
                alunoDAO.close();

                EventBus eventBus = EventBus.getDefault();
                eventBus.post(new AtualizaListaAlunoEvent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}