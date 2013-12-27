<%@ page import="com.cardtech.core.Card" %>
<%@ page import="com.cardtech.core.Deck" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Deck</title>
	</head>
	<body>
    <h1>Deck </h1>
    <a href="${createLink(uri:'/')}">Home</a>
    <a href="${createLink(action:'shuffle')}">Shuffle</a> 
    <a href="${createLink(action:'cut')}">Cut</a>
    <table><tr>
    <g:each status="i" var="card" in="${deckObj.deck}" >
      <g:if test="${(i > 0) && (i % 13 == 0)}" >
        </tr>
        <tr>
      </g:if>
	     <td>
	     <img style="display: block" width="36" height="48" src="/CardGameWeb/images/card/${card?.toString()}.png" title="${card?.toString()}" />
	     </td>
	</g:each>
	</tr></table>
	</body>
</html>