<%@ page import="java.net.InetAddress" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Deal Material Repository API Service</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:400,400i,700,700i" rel="stylesheet">
    <style>
        @import url('https://fonts.googleapis.com/css?family=Patua+One');
        @import url('https://fonts.googleapis.com/css?family=Open+Sans:400,400i,600i');
        @import url('https://fonts.googleapis.com/css?family=Noto+Serif:400,400i,700,700i');

        * {
            margin: 0;
        }

        body {
            font-family: "Noto Serif", serif;
            background-color: #252525;
        }

        .header {
            background-color: #000000;
            padding: 1em;
            margin: 0;
            box-shadow: 0px 10px 5px 0px rgba(0, 0, 0, 0.75);
        }

        #title {
            color: #a4a4a4;
            font-size: 1.5em;
            margin-top: 1em;
            margin-bottom: 1em;
            font-family: "Patua One";
        }

        #desc {
            font-size: 0.9em;
            color: #989898;
            padding-top: 1em;
            padding-bottom: 1em;
            font-family: 'Roboto Condensed', sans-serif;
        }

        .content {
            background-color: #fffeff;
            font-size: 0.9em;
            padding: 4em 2em;
        }

        #entries-title {
            margin-bottom: 1.4em;
            font-family: 'Roboto Condensed', sans-serif;
            color: black;
            font-weight: bold;
            text-shadow: 0 1px 0 white;
        }

        .entry-title {
            /*background-color: #f9f9f9;*/
            padding: 4px;
            border-radius: 4px;
            font-family: 'Roboto Condensed', sans-serif;
            font-weight: bold;
            text-shadow: 0 0 8px #c8c7d5;
        }

        #entry-list {
            margin-left: 1em;
        }

        a {
            text-decoration: none;
            color: #1a1a1f;
        }

        .entry-value {
            font-family: 'Roboto Condensed', sans-serif;
            /*font-style: italic;*/
        }

        li {
            margin: 1em;
        }

        .footer {
            padding: 1em;
            font-size: 0.8em;
            color: #6a6a6a;
        }

        #server-info {
            float: left;
            font-family: 'Roboto Condensed', sans-serif;
        }

        #sig {
            /*text-align: right;*/
            float: right;
        }

        .entry-desc {
            padding: 0.3em;
            font-family: 'Roboto Condensed', sans-serif;
            font-size: 0.9em;
            font-style: italic;
            color: #6a6a6a;
            text-shadow: 0 1px 0 white;
        }
    </style>
</head>
<body>
<div class="header ">
    <span id="title"></span>
    <p id="desc">

    </p>
</div>
<div class="content">
    <p id="entries-title">Where to Go:</p>
    <ul id="entry-list">
        <li>
            <a href="/docs/">
                <span class="entry-title">API Docs</span>
                <span class="sep"> - </span>
                <span class="entry-value">/docs/</span>
                <p class="entry-desc">API descriptions and try-out.</p>
            </a>
        </li>
        <li>
            <a href="/v2/api-docs">
                <span class="entry-title">OpenAPI Specification</span>
                <span class="sep"> - </span>
                <span class="entry-value">/v2/api-docs</span>
                <p class="entry-desc">Json-based Swagger 2.0 specification file.</p>
            </a>
        </li>
    </ul>
</div>
<div class="footer">
    <p id="server-info">
        <% InetAddress inet = InetAddress.getLocalHost();%>
        Connected to <span class="ip-info">
        <%=inet.getHostName()%> (<%=inet.getHostAddress()%>)</span>
    </p>
    <p id="sig">

    </p>
</div>
</body>
</html>