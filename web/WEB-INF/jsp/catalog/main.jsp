<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/23
  Time: 下午10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="Welcome">
    <div id="WelcomeContent">
        <c:if test="${sessionScope.loginAccount != null }">
                Welcome ${sessionScope.loginAccount.firstName}!
        </c:if>
    </div>
</div>

<div id="Main">
    <div id="Sidebar">
        <div id="SidebarContent">
            <a href="categoryForm?categoryId=FISH"><img src="images/fish_icon.gif" /></a><br />
            Saltwater, Freshwater <br />
            <a href="categoryForm?categoryId=DOGS"><img src="images/dogs_icon.gif" /></a><br/>
            Various Breeds <br />
            <a href="categoryForm?categoryId=CATS"><img src="images/cats_icon.gif" /></a><br/>
            Various Breeds, Exotic Varieties <br />
            <a href="categoryForm?categoryId=REPTILES"><img src="images/reptiles_icon.gif" /></a><br/>
            Lizards, Turtles, Snakes <br />
            <a href="categoryForm?categoryId=DOGS"><img src="images/birds_icon.gif" /></a><br/>
            Exotic Varieties <br />
            <p><a href="categoryForm?categoryId=${sessionScope.allCategories}">${sessionScope.allCategories}</a> is gaining more attention</p>
        </div>
    </div>

    <div id="MainImage">
        <div id="MainImageContent">
            <map name="estoremap">
                <div class="info" id="info" style="display: none;"></div>
                <area id="BirdsBig" alt="Birds" coords="72,2,280,250"
                      href="categoryForm?categoryId=BIRDS" shape="RECT" />
                <area id="Fish" alt="Fish" coords="2,180,72,250"
                      href="categoryForm?categoryId=FISH" shape="RECT" />
                <area id="Dogs" alt="Dogs" coords="60,250,130,320"
                      href="categoryForm?categoryId=DOGS" shape="RECT" />
                <area id="Reptiles" alt="Reptiles" coords="140,270,210,340"
                      href="categoryForm?categoryId=REPTILES" shape="RECT" />
                <area id="Cats" alt="Cats" coords="225,240,295,310"
                      href="categoryForm?categoryId=CATS" shape="RECT" />
                <area id="BirdsSmall" alt="Birds" coords="280,180,350,250"
                      href="categoryForm?categoryId=BIRDS" shape="RECT" />
            </map>
            <img height="355" src="images/splash.gif" align="middle"
                 usemap="#estoremap" width="350" /></div>
    </div>

    <div id="Separator">&nbsp;</div>

</div>
<script src="js/getDetails.js"></script>
<%@ include file="../common/bottom.jsp"%>
