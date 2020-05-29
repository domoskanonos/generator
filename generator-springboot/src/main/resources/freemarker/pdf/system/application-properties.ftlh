<html>
<div style="font-size:50pt;">Spring Boot Environmental Properties</div>

<table>
    <tr>
        <td>key</td>
        <td>value</td>
    </tr>

    <#list model.properties as propName, propValue>
        <tr>
            <td>${propName}</td>
            <td>${propValue}</td>
        </tr>
    </#list>

</table>

</html>