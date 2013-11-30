<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel='stylesheet' href="<c:url value="/styles/syntax.css" />" type='text/css' media='screen, projection'/>
<link rel='stylesheet' href="<c:url value="/styles/hjemme.css" />" type='text/css' media='screen, projection'/>
<title><decorator:title /> | hjemme.nu</title>
</head>

<body>

<div id='header'><jsp:include page="/WEB-INF/decorators/header.jsp" /></div>

<table>
  <tr>

    <td valign="top" width="128px">

      <%-- left menu --%>
      <div id="container.menu.left">
        <jsp:include page="/WEB-INF/decorators/menu.left.jsp" />
      </div>
    </td>

    <%-- Show page --%>
    <td width="1024px">
      <div id="container.main"><decorator:body /></div>
    </td>

    <%-- Right menu --%>
    <td valign="top" width='128px'>
      <div id"container.menu.right">
         <jsp:include page="/WEB-INF/decorators/menu.right.jsp" />
       </div>
    </td>

  </tr>
</table>
<br>

<div id='footer'>www.hjemme.nu :: <a href='mailto:toregiljacobsen@hotmail.com'><fmt:message key="footer.contact" /></a></div>

</body>
</html>
