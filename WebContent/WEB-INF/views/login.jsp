<b:base title="${initParam.tema}">
	<div class="container">
		<form class="form-signin" action="${pageContext.request.contextPath}/Login" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputUsername" class="sr-only">Username</label>
        <input type="text" id="inputUsername" name="inputUsername" class="form-control" placeholder="Username" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
        <br>
        <c:if test="${not empty requestScope.errors }">
        	<c:forEach var="error" items="${requestScope.errors }">
        		<div class="alert alert-danger" role="alert">${error }</div>
        	</c:forEach>
        </c:if>
        <c:if test="${not empty requestScope.warnings }">
        	<c:forEach var="warning" items="${requestScope.warnings }">
        		<div class="alert alert-info" role="alert">${warning }</div>
        	</c:forEach>
        </c:if>
      </form>
	</div>
</b:base>