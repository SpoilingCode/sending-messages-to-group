package core.verification;

import javax.swing.*;

public class VerificationFields {

    public boolean isEmptyTokenField(JTextField myTokenfField){
        return  isEmptyField(myTokenfField);
    }

    public boolean isEmptyIdGroup(JTextField idGroupField){
        return  isEmptyField(idGroupField);
    }

    public boolean isEmptyMessage(JTextField messageField){
        return  isEmptyField(messageField);
    }

    public boolean isEmptyGroupName(JTextField groupNameField){
        return  isEmptyField(groupNameField);
    }

    public boolean isEmptyField(JTextField nameField){
        String valueField = nameField.getText();
        int valueFieldLength = valueField.trim().length();
        return  valueFieldLength == 0;
    }

    public String getValueFieldAfterVerification(String message, String windowTitle){
        return JOptionPane.showInputDialog( null, message,
                            windowTitle, JOptionPane.INFORMATION_MESSAGE );
    }

}
