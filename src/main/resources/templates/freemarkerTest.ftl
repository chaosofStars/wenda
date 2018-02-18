<html>
<body>
<pre>
    <#list colors as color>
    the color is ${color} ${color_index}
    </#list>
    ${value1}
    ${value2}
    ${value3!"null"}
    <#list map?keys as key>
        the key is ${key}
        the value is ${map[key]}
    </#list>
    ${user.description}
    ${user.getDescription()}
    <#include "foot.ftl">
</pre>
</body>
</html>