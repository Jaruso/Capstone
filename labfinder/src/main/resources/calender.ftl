<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>main</title>
    <style type="text/css">
        #filter {
        font-family: Gotham, "Helvetica Neue", Helvetica, Arial, sans-serif;
        font-style: normal;
        }
        .left {

        left: 10px;
        width:inherit;

        border: 3px solid #73AD21;
        padding: 10px;
        }
        .results {

        right: 10px;
        width:inherit;

        border: 3px solid #74AD21;
        padding: 10px;
        }
        #title {

        }
        .top {

        }
        .tftextinput {

        }
        .tfbutton {

        }
    </style>
</head>

<body>
<div id="title" class="top">
    <h1>LabFinder</h1>
</div>
<div id="filter" class="left">
    <h2>Search By</h2>
    <div id="tfheader">
        <form action="/" method="POST">
            <input type="text" name="str" class="tftextinput" name="q" size="21" maxlength="120" >
            <input type="submit" value="search" class="tfbutton">
        </form>
    </div>

    <h3>Software</h3>
    <form action="/" method="POST">
        <select>
            <#list softwareoptions as software>
                <option value="${software}">${software}</option>
            </#list>
        </select>
        <h3>Hardware</h3>
        <form action="/" method="POST">
        <select>
            <#list hardwareoptions as hardware>
                <option value="${hardware}">${hardware}</option>
            </#list>
        </select>
    </form>
    <h3>Extra</h3>
        <form action="/" method="POST">
    <select>
        <#list extraoptions as extra>
            <option value="${extra}">${extra}</option>
        </#list>
    </select>

</div>
<div  id="results" class="result">

    <h1> Results </h1>

    <p>

        ${body}

    </p>




</div>
</body>
</html>
