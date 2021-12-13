<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.dto.UserDTO, es.uco.pw.data.dao.userDAO,es.uco.pw.data.dao.userDAO,java.util.ArrayList" %>
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<%

    //These strings will be used in order to indicate the next page which will be rendered and the message which the user receives on that page
    String  nextPage="/mvc/view/loginView.jsp";
    String  nextPageMessage="";

    //First case: If user is logged in we simply return him to the index
    
    if(customerBean != null){
        nextPage="/index.jsp";
    }%>
    <jsp:forward page="<%=nextPage%>">
    	<jsp:param value="<%=nextPageMessage%>" name="message"/>
	</jsp:forward>
    
    <%

    //Second case: If user is not logged in
    //Here we will have two subcases: if the user exists or if it doesn't.
        String userEmail=request.getParameter("email");
        String userPassword=request.getParameter("password");
        //Subcase 1: user exists but it's not logged in
        if(userEmail!=null){ //if user inputed a username
        	
            //we store the username and call the DAO for that specific username
            String urlDB = getServletContext().getInitParameter("DBurl");
            String userDB = getServletContext().getInitParameter("DBuser");
            String passDB = getServletContext().getInitParameter("DBpass");
            String file = getServletContext().getInitParameter("properties");
            //open the file  sql.properties and load as Input Stream
            java.io.InputStream myIO = application.getResourceAsStream(file);
            // create properties object empty
            java.util.Properties prop = new java.util.Properties();
            // load file content
            prop.load(myIO);
            
            //PAss content to DAO for future query get
            userDAO uDao = new userDAO(urlDB,userDB,passDB,prop);
           
            // Get the user if exists or return null value
        	UserDTO user = uDao.searchUserByEmail(userEmail);
            

            //we check if the username actually exists (dao must have returned something !=null)
            if(user!=null){
            	// Check the credentials
            	if (user.getEmail().equalsIgnoreCase(userEmail) && user.getPassword().equals(userPassword)){
        			
            		%>
            		    <jsp:setProperty property="id" value="<%=user.getId()%>" name="customerBean" ></jsp:setProperty>   	
                   		<jsp:setProperty property="emailUser" value="<%=userEmail%>" name="customerBean" ></jsp:setProperty>   	
            		    <jsp:setProperty property="administrator" value="<%=user.isAdministrator()%>" name="customerBean" ></jsp:setProperty>
            		    <jsp:setProperty property="name" value="<%=user.getName()%>" name="customerBean" ></jsp:setProperty>
            		    <jsp:setProperty property="surnames" value="<%=user.getSurnames()%>" name="customerBean" ></jsp:setProperty>
            		    <jsp:setProperty property="nick" value="<%=user.getNick()%>" name="customerBean" ></jsp:setProperty>   	
            			<jsp:setProperty property="password" value="<%=user.getPassword()%>" name="customerBean" ></jsp:setProperty>   	
            		
            		<%
            		if(user.isAdministrator()){
            			uDao.addLogDate(user.getId());
                       	nextPage="/dashboard.jsp";

                       	String returnValue = "";
                    	ArrayList<UserDTO> users = uDao.getAll();
                    	String temp ="";
                    	for (UserDTO u: users){
                    		String lastLogging = uDao.getLastLog(u.getId());
                    		if (lastLogging.equals("")){
                    			temp =  String.format("%s,El usuario no ha accedido\n",u.getEmail());
                    		}
                    		else{
                        		temp = String.format("%s,%s\n",u.getEmail(),lastLogging);
                    		}
                    		returnValue += temp;
                    	}
                       	%>
                       	<jsp:forward page="<%=nextPage%>">
    						<jsp:param value="<%=returnValue%>" name="message"/>
						</jsp:forward>
                       	<%
                       }
                   	// If the user isn't admin redirect to user dashboard
                       else{
                    	    uDao.addLogDate(user.getId());

                        	java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
                        	java.util.Date current = new java.util.Date();
                        	nextPageMessage = df.format(user.getCreationDate());
                          	nextPage="/dashboard.jsp";
                       }
            		 //if it gets here, success, user exists. We simply redirect him with specified parameters and done
            	 %>
            	
                   
               	<%
               	// If the user is administrator redirect into dashboard admin
                   
            	}
            	else{
            		nextPage="/mvc/view/loginView.jsp";
                	nextPageMessage="User doesn't exist.";
                }
          	               
           }

            //If the user doesn't exist, we must redirect him to the register page
            else{
                nextPage="/mvc/view/loginView.jsp";
                nextPageMessage="User doesn't exist.";
            }         
        }
        //No info given through parameters (userName==null). We simply return him to the login page (for now)
        else{
            nextPage="/mvc/view/loginView.jsp";
            nextPageMessage="Los datos introducidos no son correctos";
        }
    

    //Now that we have all this, we must forward the page and send the message to the next page
%>
<jsp:forward page="<%=nextPage%>">
    <jsp:param value="<%=nextPageMessage%>" name="message"/>
</jsp:forward>

