
<%@ page import="com.cardtech.web.Player" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Player Selection</title>
<script src="../js/jquery-2.1.3.min.js" ></script>
<script>
function countPlayers() {
   var value = true;
   var count = $("#playerIds :selected").length;   
   if (count == 0) {
     alert('You must select at least one player.');
     value = false;
   }
   return value;
 }

</script>
	</head>
	<body>
	<div style="padding: 20px;">
    <a href="${createLink(uri:'/')}">Home</a>
	<g:form controller="pokerGame" action="showGame">
      <p>Select players for game:</p>
	  <g:select id="playerIds" name="playerIds" from="${Player.list()}" optionKey="id" optionValue="${playerIds?.firstName}" multiple="true" /><br/>
      <p>Select number of games to play:</p>
	  <g:select name="numOfGames" from="${1..100}" value="${1}" /></br>			
      <g:submitButton name="showGame" value="Play Game"  onclick="return countPlayers()" class="buttons" />
	</g:form>
	</div>
	</body>
</html>
