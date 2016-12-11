<%--
  Created by IntelliJ IDEA.
  User: Le Pham Minh Duc
  Date: 12/11/2016
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
      <p>-SPARQL-</p>
      <br/>
      <form method="post" action="MainServlet">
          <textarea name="sparql-query" rows="25" cols="100">
            prefix : &lt;http://users.jyu.fi/~miduleph/TIES452/Individuals.owl#&gt;
            prefix onto: &lt;http://users.jyu.fi/~miduleph/TIES452/Final_Assignment.owl#&gt;

            SELECT DISTINCT ?Children ?Country
            WHERE {
                ?Children onto:hasParent ?Parent .
                ?Parent onto:hasNationality ?Country .
            }

          </textarea>
          <br/>
          <input type="submit" value="EXECUTE" size="30">
      </form>
      <br/>
      <%--<button type="button">ExecuteQuery</button>--%>
  </body>
</html>
