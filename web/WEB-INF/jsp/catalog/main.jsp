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
        <span><a id="WelcomeText"></a>
            <c:if test="${sessionScope.loginAccount != null }">
                ${sessionScope.loginAccount.firstName}
            </c:if>
        </span>
    </div>
</div>

<div id="Main">
    <div id="Sidebar">
        <ul id="SidebarContent">
            <li>
                <a href="categoryForm?categoryId=FISH">
                    <p>Fish  <span>Saltwater, Freshwater</span></p>
                </a>
            </li>
            <li>
                <a href="categoryForm?categoryId=DOGS">
                    <p>Dogs  <span>Various Breeds</span></p>
                </a>
            </li>
            <li>
                <a href="categoryForm?categoryId=CATS">
                    <p>Cats  <span>Various Breeds, Exotic Varieties</span></p>
                </a>
            </li>
            <li>
                <a href="categoryForm?categoryId=REPTILES">
                    <p>Reptiles  <span>Lizards, Turtles, Snakes</span></p>
                </a>
            </li>
            <li>
                <a href="categoryForm?categoryId=BIRDS">
                    <p>Birds  <span>Exotic Varieties</span></p>
                </a>
            </li>
            <li id="suggestion">
                <a href="categoryForm?categoryId=${sessionScope.allCategories}">
                    <p style="text-align: center">
                        Today's Suggestion<br>
                        <span>
                            ${sessionScope.allCategories} is gaining more attention
                        </span>
                    </p>
                </a>
            </li>
        </ul>
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
