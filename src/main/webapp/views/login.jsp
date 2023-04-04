<%--
  Created by IntelliJ IDEA.
  User: tiennh
  Date: 4/4/23
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<div class="alert-danger alert">${ errorMessage }</div>
<form method="POST" action="/SP23B2_SOF3011_IT17313_war_exploded/login">
    <div class="mt-3">
        <label>Mã</label>
        <input type="text" name="ma" class="form-control" />
    </div>
    <div class="mt-3">
        <label>Mật khẩu</label>
        <input type="password" name="matKhau" class="form-control" />
    </div>
    <div class="mt-3">
        <button class="btn btn-primary">Đăng nhập</button>
    </div>
</form>
