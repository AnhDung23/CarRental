/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import com.opensymphony.xwork2.ActionSupport;
import dunggla.registrations.RegistrationDAO;
import java.sql.Timestamp;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class CreateAccountAction extends ActionSupport{

    private String email;
    private String password;
    private String fullname;
    private String phone;
    private String address;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public CreateAccountAction() {
    }

    public String execute() throws Exception {
        String role = "member";
        String status = "New";
        
        // Get verify code by random to 9999
        Random rd = new Random();
        int codeVerify = rd.nextInt(9999);
        String code = Integer.toString(codeVerify);

        long millis = System.currentTimeMillis();
        Timestamp createDate = new Timestamp(millis);

        RegistrationDAO dao = new RegistrationDAO();
        String url = FAIL;
        boolean check = dao.createAccount(getEmail(), getPassword(), getFullname(), getPhone(), getAddress(), createDate, role, status, code);
        if (check) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("CODE_VERIFY", code);
            request.setAttribute("EMAIL", email);
            url = SUCCESS;
        }

        return url;

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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
