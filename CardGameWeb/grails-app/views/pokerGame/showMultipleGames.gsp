<%@ page import="com.cardtech.web.Player" %>
<%@ page import="com.cardtech.web.PokerRank" %>

<! DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
<style type="text/css">
</style>
<g:javascript library="jquery" />
	 <title>Results for ${numOfGames} Games</title>
	</head>
	<body>
	<div style="padding: 20px;">
    <a href="${createLink(uri:'/')}">Home</a><br/>
    <h1>Results for ${numOfGames} Games</h1>
   <table>
    <tr>
     <th>Player</th>
     <th># Wins</th>
     <th>Rank of highest winning hand</th>
    </tr>
    <g:each var="playerObj" in="${playerObjs}" status="i">
     <tr>
      <td> <!-- player name -->
       ${playerObj.firstName}
      </td>
      <td> <!-- # of wins -->
       ${playerWins[playerObj.id]?:""}
      </td>
      <td> <!--highest winning rank -->
       ${playerHighestWinningRank[playerObj.id]?
		   PokerRank.read(playerHighestWinningRank[playerObj.id]).toString()
		   : ""}
      </td>
     </tr>
    </g:each>
   </table>
   <g:form controller="pokerGame">
   <g:actionSubmit action="showPlayers" value="Play again?" class="buttons"/>
   </g:form>
   </div>
</body>

</html>
