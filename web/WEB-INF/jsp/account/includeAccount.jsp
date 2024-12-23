<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/26
  Time: 下午11:33
  To change this template use File | Settings | File Templates.
--%>
<h3>Account Information</h3>
<table id="table2">
  <tr>
    <th>First name:</th>
    <td><input type="text" name="firstName" value="${sessionScope.loginAccount.firstName}"></td>
    <th>Last name:</th>
    <td><input type="text" name="lastName" value="${sessionScope.loginAccount.lastName}"></td>
  </tr>
  <tr>
    <th>Email:</th>
    <td><input type="text" size="40" name="email" id="email" value="${sessionScope.loginAccount.email}" ></td>
    <div id="emailFeedback" class="feedback"></div>
    <th>Phone:</th>
    <td><input type="text" name="phone" value="${sessionScope.loginAccount.phone}"></td>
  </tr>
  <tr>
    <th>Address 1:</th>
    <td><input type="text" size="40" name="address1" value="${sessionScope.loginAccount.address1}"></td>
    <th>Address 2:</th>
    <td><input type="text" size="40" name="address2" value="${sessionScope.loginAccount.address2}"></td>
  </tr>
  <tr>
    <th>City:</th>
    <td><input type="text" name="city" value="${sessionScope.loginAccount.city}"></td>
    <th>State:</th>
    <td><input type="text" size="4" name="state" value="${sessionScope.loginAccount.state}"></td>
  </tr>
  <tr>
    <th>Zip:</th>
    <td><input type="text" size="10" name="zip" value="${sessionScope.loginAccount.zip}"></td>
    <th>Country:</th>
    <td><input type="text" size="15" name="country" value="${sessionScope.loginAccount.country}"></td>
  </tr>
</table>

<h3>Profile Information</h3>
<table id="table3">
  <tr>
    <th>Language Preference:</th>
    <td>
        <select name="languagePreference">
          <c:forEach var="lang" items="${sessionScope.languages}">
            <option value="${lang}">${lang}</option>
          </c:forEach>
        </select>
    </td>
    <th>Enable MyList</th>
    <td><input type="checkbox" name="listOption" value="1" checked></td>
    </td>
  </tr>
  <tr>
    <th>Favourite Category:</th>
    <td>
      <select name="favouriteCategoryId">
        <c:forEach var="cate" items="${sessionScope.categories}">
          <option value="${cate}">${cate}</option>
        </c:forEach>
      </select>
    <th>Enable MyBanner</th>
    <td><input type="checkbox" name="bannerOption" value="1" checked></td>
  </tr>

</table>
<script src="js/examination.js"></script>
