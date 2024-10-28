<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/26
  Time: 下午11:33
  To change this template use File | Settings | File Templates.
--%>
<h3>Account Information</h3>

<table>
  <tr>
    <td>First name:</td>
    <td><input type="text" name="firstName" value="${sessionScope.loginAccount.firstName}"></td>
  </tr>
  <tr>
    <td>Last name:</td>
    <td><input type="text" name="lastName" value="${sessionScope.loginAccount.lastName}"></td>
  </tr>
  <tr>
    <td>Email:</td>
    <td><input type="text" size="40" name="email" value="${sessionScope.loginAccount.email}"></td>
  </tr>
  <tr>
    <td>Phone:</td>
    <td><input type="text" name="phone" value="${sessionScope.loginAccount.phone}"></td>
  </tr>
  <tr>
    <td>Address 1:</td>
    <td><input type="text" size="40" name="address1" value="${sessionScope.loginAccount.address1}"></td>
  </tr>
  <tr>
    <td>Address 2:</td>
    <td><input type="text" size="40" name="address2" value="${sessionScope.loginAccount.address2}"></td>
  </tr>
  <tr>
    <td>City:</td>
    <td><input type="text" name="city" value="${sessionScope.loginAccount.city}"></td>
  </tr>
  <tr>
    <td>State:</td>
    <td><input type="text" size="4" name="state" value="${sessionScope.loginAccount.state}"></td>
  </tr>
  <tr>
    <td>Zip:</td>
    <td><input type="text" size="10" name="zip" value="${sessionScope.loginAccount.zip}"></td>
  </tr>
  <tr>
    <td>Country:</td>
    <td><input type="text" size="15" name="country" value="${sessionScope.loginAccount.country}"></td>
  </tr>
</table>

<h3>Profile Information</h3>

<table>
  <tr>
    <td>Language Preference:</td>
    <td>
        <select name="languagePreference">
          <c:forEach var="lang" items="${sessionScope.languages}">
            <option value="${lang}">${lang}</option>
          </c:forEach>
        </select>
    </td>
  </tr>
  <tr>
    <td>Favourite Category:</td>
    <td>
      <select name="favouriteCategoryId">
        <c:forEach var="cate" items="${sessionScope.categories}">
          <option value="${cate}">${cate}</option>
        </c:forEach>
      </select>
    </td>
  </tr>
  <tr>
    <td>Enable MyList</td>
    <td><input type="checkbox" name="listOption" value="1" checked></td>
  </tr>
  <tr>
    <td>Enable MyBanner</td>
    <td><input type="checkbox" name="bannerOption" value="1" checked></td>
  </tr>

</table>
