package view;

import listener.MessageListener;

import javax.swing.*;
import java.awt.*;

public class MessengerView extends JFrame{

    private final String APPLICATION_NAME = "Отправка записей в группу ВКонтакте";
    private final int BEGIN_POSITION = 200;
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 480;
    private JTextField myTokenfField;
    private JTextField idGroupField;
    private JTextField messageField;
    private JTextField groupNameField;
    private JLabel myTokenLabel;
    private JLabel idGroupLabel;
    private JLabel messageLabel;
    private JLabel groupNameLabel;
    private JButton sendBtn;
    private JButton exitBtn;
    private  MessageListener messageListener;

    public static void main(String[] args) {
        MessengerView messengerView = new MessengerView();
        messengerView.drawWindowElements();
    }

    public MessengerView()  {
        setSettingsForMainWindow();
        messageListener = new MessageListener();
    }

    public void drawWindowElements(){
        myTokenfField = new JTextField();
        myTokenfField.setToolTipText("Введите ваш токен");
        myTokenfField.addActionListener(messageListener);

        idGroupField = new JTextField();
        idGroupField.setToolTipText("Введите id группы(например 46258034) в " +
                   "которые хотите написать(либо имя группы в следующем поле)");
        idGroupField.addActionListener(messageListener);

        messageField = new JTextField();
        messageField.setToolTipText("Введите сообщение");
        messageField.addActionListener(messageListener);

        groupNameField = new JTextField();
        groupNameField.setToolTipText("Введите имя группы(например wormix_plus)");
        groupNameField.addActionListener(messageListener);

        myTokenLabel = new JLabel("Ваш токен:");
        idGroupLabel = new JLabel("ID группы(например 46258034):");
        groupNameLabel = new JLabel("Имя группы(например wormix_plus):");
        messageLabel = new JLabel("Cообщение:");

        sendBtn = new JButton("Отправить");
        sendBtn.setToolTipText("Нажмите, чтобы отправить сообщение в группу");
        sendBtn.addActionListener(messageListener);

        exitBtn = new JButton("Выйти");
        exitBtn.setToolTipText("Нажмите, чтобы выйти из программы.");
        exitBtn.addActionListener(messageListener);

        JPanel mainPanel = new JPanel();
        int rows = 12;
        int columns = 12;
        mainPanel.setLayout(new GridLayout(rows,columns));
        mainPanel.add(myTokenLabel);
        mainPanel.add(myTokenfField);
        mainPanel.add(idGroupLabel);
        mainPanel.add(idGroupField);
        mainPanel.add(groupNameLabel);
        mainPanel.add(groupNameField);
        mainPanel.add(messageLabel);

        JScrollPane scrollBarForMessage = new JScrollPane(messageField);
        mainPanel.add(scrollBarForMessage);
        mainPanel.add(sendBtn);
        mainPanel.add(exitBtn);
        add(BorderLayout.CENTER,mainPanel);
        setVisible(true);

        messageListener.setMyTokenfField(myTokenfField);
        messageListener.setIdGroupField(idGroupField);
        messageListener.setGroupNameField(groupNameField);

        messageListener.setMessageField(messageField);
        messageListener.setSendBtn(sendBtn);
        messageListener.setExitBtn(exitBtn);
    }

    private void setSettingsForMainWindow(){
        setTitle(APPLICATION_NAME);
        setBounds(BEGIN_POSITION, BEGIN_POSITION,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
