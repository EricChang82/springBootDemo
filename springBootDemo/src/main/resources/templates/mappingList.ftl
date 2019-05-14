    <table class="tableList" >
    <tr>
            <th>类名</th>
            <th>方法名</th>
            <th>URL</th>
            <th>类型</th>
    <tr>
        <c:forEach items="${list}" var="mvc" varStatus="status">
        <tr id="${status.index}">
            <td>${mvc.className}</td>
            <td>${mvc.method}</td>
            <td>
           
            </td>
            <td>${mvc.type}</td>
        </tr>
        </c:forEach>
    </table>