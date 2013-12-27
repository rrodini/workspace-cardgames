
<%@ page import="com.cardtech.web.Player" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Player Selection</title>
<g:javascript library="jquery" />
<r:script >
function countPlayers() {
   var value = true;
   var count = $("option:selected").length;   
   if (count == 0) {
     alert('You must select at least one player.');
     value = false;
   }
   return value;
 }

</r:script>
	</head>
	<body>
    <a href="${createLink(uri:'/')}">Home</a>
    <h1>Select players for game</h1>
	<g:form controller="pokerGame" action="showGame">
		<fieldset class="buttons">
			<g:select name="playerIds" from="${Player.list()}" optionKey="id" optionValue="${playerIds?.firstName}" multiple="true" /><br/>
			<g:submitButton name="showGame" value="Play Game"  onclick="return countPlayers()" />
		</fieldset>
	</g:form>
	</body>
</html>
