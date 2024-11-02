<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <div id="ListJournal">
        <h2>My Journals</h2>

        <table>
            <tr>
                <th>Operation</th>
                <th>Date</th>
            </tr>

            <c:forEach var="journal" items="${requestScope.journals}">
                <tr>
                    <td>
                        <font color="${journal.color}">${journal.description}</font>
                    </td>
                    <td>
                        <font color="${journal.color}">${journal.date}</font>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@ include file="../common/bottom.jsp"%>
