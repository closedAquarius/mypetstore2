<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>

<html>

<head>
    <link rel="StyleSheet" href="css/mypetstore.css" type="text/css"
          media="screen" />
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <title>MyPetStore</title>
</head>

<body>

<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="mainForm"><img src="images/logo-topbar.gif"></a>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">
            <a href="cartForm"><img align="middle" name="img_cart" src="images/cart.gif" /></a>
            <img align="middle" src="images/separator.gif" />
            <c:if test="${sessionScope.loginAccount ==null}">
                <a href="signOnForm">Sign In</a>
                <img align="middle" src="images/separator.gif" />
            </c:if>
            <c:if test="${sessionScope.loginAccount !=null}">
                <a href="signOff">Sign Out</a>
                <img align="middle" src="images/separator.gif" />
                <a href="editForm">My Account</a>
                <img align="middle" src="images/separator.gif" />
            </c:if>
            <a href="help.html">?</a></div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form action="searchForm" method="post">
                <input type="text" name="keyword" size="14" id="keyword" autocomplete="false">
                <input type="submit" value="Search">
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
        <a href="categoryForm?categoryId=FISH"><img src="images/sm_fish.gif" /></a>
        <img src="images/separator.gif" />
        <a href="categoryForm?categoryId=DOGS"><img src="images/sm_dogs.gif" /></a>
        <img src="images/separator.gif" />
        <a href="categoryForm?categoryId=REPTILES"><img src="images/sm_reptiles.gif" /></a>
        <img src="images/separator.gif" />
        <a href="categoryForm?categoryId=CATS"><img src="images/sm_cats.gif" /></a>
        <img src="images/separator.gif" />
        <a href="categoryForm?categoryId=BIRDS"><img src="images/sm_birds.gif" /></a>
        <img src="images/separator.gif" />
    </div>

</div>

<div id="Content">
