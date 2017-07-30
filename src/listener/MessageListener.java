package listener;

import core.verification.VerificationFields;
import core.workwithvkapi.MessageProvider;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageListener implements ActionListener {

    private final int EXIT_CODE = 0;
    private String myToken;
    private String message;
    private String idGroup;
    private String groupName;
    private JTextField myTokenfField;
    private JTextField idGroupField;
    private JTextField messageField;
    private JTextField groupNameField;
    private JButton sendBtn;
    private JButton exitBtn;
    private VerificationFields verification;
    private MessageProvider messageProvider;

    public MessageListener() {
        this.verification = new VerificationFields();
        this.messageProvider = new MessageProvider();
    }

    public void setGroupNameField(JTextField groupNameField) {
        this.groupNameField = groupNameField;
    }

    public void setSendBtn(JButton sendBtn) {
        this.sendBtn = sendBtn;
    }

    public void setExitBtn(JButton exitBtn) {
        this.exitBtn = exitBtn;
    }

    public void setMyTokenfField(JTextField myTokenfField) {
        this.myTokenfField = myTokenfField;
    }

    public void setIdGroupField(JTextField idGroupField) {
        this.idGroupField = idGroupField;
    }

    public void setMessageField(JTextField messageField) {
        this.messageField = messageField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( !isPressedExitButton(e) ) {
                checkFields();
            if (isPressedSendButton(e)) {
                getFieldsValuesAndReportToMessagePovider();
            }
        } else {
            int answer = JOptionPane.showConfirmDialog(null, "Вы действительно хотите выйти?",
                                                       "Выход из программы", JOptionPane.YES_NO_OPTION);
            if (isExitFromProgramm(answer) ) {
                System.exit(EXIT_CODE);
            }
        }
    }

    public boolean isExitFromProgramm(int answer){
        return answer == JOptionPane.YES_OPTION;
    }

    public boolean isPressedExitButton(ActionEvent e){
        return e.getSource() == exitBtn;
    }

    public boolean isPressedSendButton(ActionEvent e){
        return e.getSource() == sendBtn;
    }

    public void getFieldsValuesAndReportToMessagePovider(){
        myToken = myTokenfField.getText();
        idGroup = idGroupField.getText();
        message = messageField.getText();
        groupName = groupNameField.getText();

        messageProvider.setMyToken(myToken);
        messageProvider.setIdGroup(idGroup);
        messageProvider.setGroupName(groupName);
        messageProvider.sendMessage(message);
    }

    public void checkFields(){
        if(verification.isEmptyTokenField(myTokenfField)){
            myToken = verification.getValueFieldAfterVerification("Вы не ввели токен, введите снова:",
                                                                    "Токен не заполнен!");
            setValueInField(myToken, myTokenfField);
        }

        if(verification.isEmptyIdGroup(idGroupField) && verification.isEmptyGroupName(groupNameField) ){
            idGroup =  verification.getValueFieldAfterVerification("Вы не ввели id группы, введите снова:",
                                                                    "Id группы не заполнено!");
            setValueInField(idGroup,idGroupField);

            if(verification.isEmptyIdGroup(idGroupField)){
                groupName =  verification.getValueFieldAfterVerification("Вы не ввели имя группы, введите снова:",
                                                                           "Имя группы не заполнено!");
                setValueInField(groupName, groupNameField);
            }
        }

        if(verification.isEmptyMessage(messageField)) {
            message = verification.getValueFieldAfterVerification("Вы не ввели сообщение, введите снова:",
                                                                   "Сообщение не запаолнено!");
            setValueInField(message, messageField);
        }
    }

    public void setValueInField(String fieldValue, JTextField fieldName){
        fieldName.setText(fieldValue);
    }
}
