<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Community Squeeze OS: Unexpected Error</title>
</head>

<body>
<table>
  <tr>
    <td>
      <a href="index.jsp"><img src="cs-logo-146x50.png" alt="Community Squeeze Logo" width="146" height="50" /></a>
    </td>
    <td>
      <h2>An unexpected error has occured!</h2>
    </td>
  </tr>
</table>

<!--
<p>
    Please report this error to your system administrator
    or appropriate technical support personnel.
    Thank you for your cooperation.
</p>
-->

<hr/>

<h3>Error Message</h3>

<s:actionerror />

<p>
    <s:property value="%{exception.message}"/>
</p>

<hr/>

<h3>Technical Details</h3>

<p>
    <s:property value="%{exceptionStack}"/>
</p>

<jsp:include page="Footer.jsp"/>

</body>
</html>
