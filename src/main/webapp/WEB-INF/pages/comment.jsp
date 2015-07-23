<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Spring MVC Application</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="span8 offset2">
                <h1>New comment</h1>
                <form:form method="post" action="comment" modelAttribute="commentForm" class="form-horizontal">
                    <% // This is a way to retrieve the bindingResult :
                        //<c:set var="result" value="requestScope['org.springframework.validation.BindingResult.comment']"/>
                        // Another way is to use <spring:bind path="form.*"><c:out value="${status.errors.allErrors}"/></spring:bind>
                    %>

                    <spring:bind path="commentForm.text">
                        <form:hidden path="id" value="${commentForm.id}"/>
                    </spring:bind>

                    <spring:bind path="commentForm.text">

                        <div class="control-group ${status.error ? 'has-error' : ''}">
                            <form:label cssClass="control-label" path="text">Comment:</form:label>
                            <div class="controls">
                                <form:errors path="text"/>
                                <form:textarea path="text" value="${commentForm.text}" cssClass="form-control" rows="10"/>
                            </div>
                        </div>
                    </spring:bind>
                    <div class="control-group">
                        <div class="controls">
                            <input type="submit" value="Add comment" class="btn"/>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>
