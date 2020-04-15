/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggla.registrations.RegistrationDAO;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class VerifyEmailAction {

    private String code;
    private String email;
    private String verifyCode;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public VerifyEmailAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        
        if (getCode().equals(getVerifyCode())) {
            String status = "Active";
            RegistrationDAO dao = new RegistrationDAO();
            boolean check = dao.activeAccount(getEmail(), status);

            if (check) {
                url = SUCCESS;
            }
        } else {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("ERROR", "Your input doesn't match with verify code");
        }

        return url;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the verifyCode
     */
    public String getVerifyCode() {
        return verifyCode;
    }

    /**
     * @param verifyCode the verifyCode to set
     */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

}
