package core.workwithvkapi;

import javax.swing.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MessageProvider {
    private URL url;
    private String queryToVkontakteWithId;
    private String queryToVkontakteWithGroupName;
    private String myToken;
    private String idGroup;
    private String groupName;

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setMyToken(String myToken) {
        this.myToken = myToken;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public void sendMessage(String message){
       if(groupName.trim().length() == 0) {
           queryToVkontakteWithId = "https://api.vk.com/method/messages.send?" +
                   "user_ids=-" + idGroup +
                   "&message=" + message +
                   "&access_token=" + myToken;
           
           executeQueryOnVkotakte(queryToVkontakteWithId);
       }

       if(idGroup.trim().length() == 0) {
           queryToVkontakteWithGroupName = "https://api.vk.com/method/messages.send?" +
                   "domain=" + groupName +
                   "&message=" + message +
                   "&access_token=" + myToken;

           executeQueryOnVkotakte(queryToVkontakteWithGroupName);
       }
    }

    private void executeQueryOnVkotakte(String query){
        HttpURLConnection connectionForVkontakte = null;
        try{
            url = new URL(query);
            connectionForVkontakte = (HttpURLConnection) url.openConnection();
            connectionForVkontakte.setRequestMethod("GET");
            connectionForVkontakte.setUseCaches(false);
            int connectTimeout = 20000;
            int readTimeout = 20000;
            connectionForVkontakte.setConnectTimeout(connectTimeout);
            connectionForVkontakte.setReadTimeout(readTimeout);
            connectionForVkontakte.connect();

            int responseCode = connectionForVkontakte.getResponseCode();
            notifyOfConnectionState(responseCode);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if(isConnect(connectionForVkontakte)){
                connectionForVkontakte.disconnect();
            }
        }
    }

    private void notifyOfConnectionState(int responseCode){
        if(isSuccessfulConnection(responseCode)){
            JOptionPane.showMessageDialog(null,"Сообщение успешно " +
                                             "отправлено в группу " + idGroup);
        } else {
            JOptionPane.showMessageDialog(null,"Запрос не выполнился. " +
                                             "Код  ответа сервера: "+ responseCode);
        }
    }

    private boolean isSuccessfulConnection(int responseCode){
        return HttpURLConnection.HTTP_OK == responseCode;
    }

    private boolean isConnect(HttpURLConnection connectionFroVkontakte){
        return connectionFroVkontakte != null;
    }
}