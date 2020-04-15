/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggla.recaptcha.RecaptchaVerifying;
import dunggla.registrations.RegistrationDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class LoginAction {

    private String email;
    private String password;
    private final String ADMIN = "admin";
    private final String MEMBER = "member";
    private final String INVALID = "invalid";

    public LoginAction() {
    }

    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();

        String url = INVALID;
        String gRecaptchaResponse = ServletActionContext.getRequest().getParameter("g-recaptcha-response");
        if (gRecaptchaResponse == null) {

        } else if (gRecaptchaResponse.equals("")) {
            request.setAttribute("ERROR", "You must confirm that you are not a robot");
        } else {
            boolean verify = RecaptchaVerifying.verify(gRecaptchaResponse);

            if (verify) {
                String status = "Active";
                RegistrationDAO dao = new RegistrationDAO();
                String role = dao.checkLogin(email, password, status);

                if (role.equals("admin")) {
                    url = ADMIN;
                } else if (role.equals("member")) {
                    url = MEMBER;
                } else {
                    request.setAttribute("ERROR", "Invalid email or password");
                }

                if (role.equals("admin") || role.equals("member")) {
                    String name = dao.getNameUser(email);
                    HttpSession session = request.getSession();
                    session.setAttribute("NAME", name);
                    session.setAttribute("EMAIL", email);
                }
            } else {
                request.setAttribute("ERROR", "Verify Captcha failed");
            }
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

}
