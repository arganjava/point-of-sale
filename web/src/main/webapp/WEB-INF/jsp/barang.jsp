<%--
  Created by IntelliJ IDEA.
  User: Argan
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Barang</title>
</head>
<body>
<table border="1" >
    <thead>
    <tr>
    <th>Code Barang</th>
    <th>Nama Barang</th>
    <th>Harga Beli</th>
    <th>Harga Jual</th>
    <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${barangs}" var="barang">
        <tr>
            <td>${barang.codeBarang}</td>
            <td>${barang.namaBarang}</td>
            <td>${barang.hargaBeliBarang}</td>
            <td>${barang.hargeJualBarang}</td>
            <td>${barang.quantity}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
