<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>

<html>

<head>
    <!--<link rel="StyleSheet" href="css/mypetstore.css" type="text/css"
          media="screen">-->
    <link rel="StyleSheet" href="css/header.css" type="text/css" media="screen" />
    <link rel="StyleSheet" href="css/common.css" type="text/css" media="screen" />
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <title>MyPetStore</title>
</head>

<body>

<div id="Header">
    <div id="Menu">
        <div id="Logo">
            <div id="LogoContent">
                <a href="mainForm"><img src="images/logo-topbar.gif"></a>
            </div>
        </div>

        <ul id="MenuContent">
            <c:if test="${sessionScope.loginAccount ==null}">
                <li><a href="signOnForm">Sign In</a></li>
            </c:if>
            <c:if test="${sessionScope.loginAccount !=null}">
                <li><a href="signOff">Sign Out</a></li>
                <li><a href="editForm">My Account</a></li>
            </c:if>
                <li id="last"><a href="help.html">Need Help</a></li>
        </ul>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form action="searchForm" method="post">
                <input type="text" name="keyword" size="14" id="keyword"
                       placeholder="Enter Search Text" autocomplete="false">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" value="Search" id="submit">
            </form>
            <div id="productAutoComplete">
                <ul id="productAutoList">
                    <%--<li class="productAutoItem">19239912319031</li>
                    <li class="productAutoItem">19239912319032</li>
                    <li class="productAutoItem">19239912319033</li>
                    <li class="productAutoItem">19239912319034</li>
                    <li class="productAutoItem">19239912319035</li>--%>
                </ul>
            </div>
        </div>
    </div>

    <div id="QuickLinks">
        <p>
            <a href="cartForm"><img align="middle" name="img_cart" src="images/cart.gif" /></a>
            &nbsp;&nbsp;<span>|</span>&nbsp;&nbsp;
            <a href="categoryForm?categoryId=FISH">Fish</a>
            &nbsp;&nbsp;<span>|</span>&nbsp;&nbsp;
            <a href="categoryForm?categoryId=DOGS">Dogs</a>
            &nbsp;&nbsp;<span>|</span>&nbsp;&nbsp;
            <a href="categoryForm?categoryId=REPTILES">Reptiles</a>
            &nbsp;&nbsp;<span>|</span>&nbsp;&nbsp;
            <a href="categoryForm?categoryId=CATS">Cats</a>
            &nbsp;&nbsp;<span>|</span>&nbsp;&nbsp;
            <a href="categoryForm?categoryId=BIRDS">Birds</a>
        </p>
    </div>
</div>

<div id="Content">
