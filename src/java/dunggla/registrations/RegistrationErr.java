/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.registrations;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RegistrationErr implements Serializable{
    private String emailDuplicateErr;
    private String emailFormatError;
    private String passwordLenghtErr;
    private String fullnameLenghtErr;
    private String phoneFormatErr;
    private String addressLenghtErr;

    public RegistrationErr() {
    }

    /**
     * @return the emailDuplicateErr
     */
    public String getEmailDuplicateErr() {
        return emailDuplicateErr;
    }

    /**
     * @param emailDuplicateErr the emailDuplicateErr to set
     */
    public void setEmailDuplicateErr(String emailDuplicateErr) {
        this.emailDuplicateErr = emailDuplicateErr;
    }

    /**
     * @return the emailFormatError
     */
    public String getEmailFormatError() {
        return emailFormatError;
    }

    /**
     * @param emailFormatError the emailFormatError to set
     */
    public void setEmailFormatError(String emailFormatError) {
        this.emailFormatError = emailFormatError;
    }

    /**
     * @return the passwordLenghtErr
     */
    public String getPasswordLenghtErr() {
        return passwordLenghtErr;
    }

    /**
     * @param passwordLenghtErr the passwordLenghtErr to set
     */
    public void setPasswordLenghtErr(String passwordLenghtErr) {
        this.passwordLenghtErr = passwordLenghtErr;
    }

    /**
     * @return the fullnameLenghtErr
     */
    public String getFullnameLenghtErr() {
        return fullnameLenghtErr;
    }

    /**
     * @param fullnameLenghtErr the fullnameLenghtErr to set
     */
    public void setFullnameLenghtErr(String fullnameLenghtErr) {
        this.fullnameLenghtErr = fullnameLenghtErr;
    }

    /**
     * @return the phoneFormatErr
     */
    public String getPhoneFormatErr() {
        return phoneFormatErr;
    }

    /**
     * @param phoneFormatErr the phoneFormatErr to set
     */
    public void setPhoneFormatErr(String phoneFormatErr) {
        this.phoneFormatErr = phoneFormatErr;
    }

    /**
     * @return the addressLenghtErr
     */
    public String getAddressLenghtErr() {
        return addressLenghtErr;
    }

    /**
     * @param addressLenghtErr the addressLenghtErr to set
     */
    public void setAddressLenghtErr(String addressLenghtErr) {
        this.addressLenghtErr = addressLenghtErr;
    }
    
    // check syntax of email
    public boolean checkSyntaxEmail(String email){
        return email.matches("\\w+@\\w+[.]\\w+([.]\\w+)?");
    }
    
    // check syntax of phone number
    public boolean checkFormatPhone(String phone){
        return phone.matches("\\d{10,11}");
    }
}
