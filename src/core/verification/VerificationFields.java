package core.verification;

import javax.swing.*;

public class VerificationFields {
    public boolean isEmptyField(JTextField nameField){
        String valueField = nameField.getText();
        int valueFieldLength = valueField.trim().length();
        return  valueFieldLength == 0;
    }

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

    public String getValueFieldAfterVerification(String message, String windowTitle){
        return JOptionPane.showInputDialog( null, message,
                            windowTitle, JOptionPane.INFORMATION_MESSAGE );
    }

    public int getLineLengthWithoutSpaces(String line){
        return line.trim().length();
    }

    public boolean isEmptyValue(String value){
        return getLineLengthWithoutSpaces(value) == 0;
    }
}
