/*
 *  A simple tag handler class to perform a INSERT into a database
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author atreeisnotatree
 */
public class NewTagHandler extends SimpleTagSupport {
    /**
     * @param request servlet request
     */
    private String user;
    private String password;
    private String url;
    private String driver;
    private String id = "0";
    
    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setId(String id) {
        this.id = id;
    }
    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     * @throws javax.servlet.jsp.JspException
     */
    @Override
    public void doTag() throws JspException {
        
        
//                String id = request.getParameter(admin_book_types_id);
//                String type = request.getParameter("admin_book_types_type");
                // declare specific variables
                ResultSet resultSet = null;
                Statement statement = null;
                Connection connection = null;
                PreparedStatement pstmnt = null;
        try
                {
                    //check driver and create connection
                    Class driverClass = Class.forName(driver);
                    connection = DriverManager.getConnection(url, user, password); 
                    // test if role field is empty & realize the insert
                    if(!("".equals(id))){
                        String DML = "INSERT INTO EBOOKS.BOOK_TYPES VALUES (?)";
                        pstmnt = connection.prepareStatement(DML);
                        pstmnt.setString(1, id);
                        pstmnt.execute();
                    }
                } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewTagHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewTagHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          
    }  
}
