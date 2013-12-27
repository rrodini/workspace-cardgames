<%@ page import="com.cardtech.web.Player" %>
<%@ page import="com.cardtech.game.PokerHand" %>
<%@ page import="com.cardtech.game.Player" %>
<%@ page import="com.cardtech.game.PokerRank" %>

<! DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
<style type="text/css">
img.cardSmall  { width:18px; height:24px }
img.cardMedium { width:36px; height:48px }
img.cardLarge  { width:72px; height:96px }
.content {
 padding: 5px 5px;
}
.heading {
 margin: 1px;
 /* color: #abbf78; */
 padding: 3px 3px;
 cursor: pointer;
 position: relative;
 background-color: #abbf78;
}
</style>
<g:javascript library="jquery" />
<r:script >
$(document).ready(function() {
 $(".content").hide();
 $(".heading").click(function() {
   var sOld = $(this).text();
   var sNew = ' ';
   if (sOld.lastIndexOf('+') > 0) {
     sNew = '-';
   } else if (sOld.lastIndexOf('-') > 0) {
     sNew = '+'
   }
   sNew = sOld.substr(0, sOld.length - 1) + sNew;
   //alert('Old: '+ sOld + '\nNew:' + sNew );
   $(this).text(sNew);
   $(this).siblings().not(this).toggle();
 });
});
</r:script>
	 <title>Game Results</title>
	</head>
	<body>
    <a href="${createLink(uri:'/')}">Home</a><br/>
    <h1>Game Results </h1>
   <table>
    <g:each var="player" in="${players}" status="i">
     <tr>
      <td> <!-- players name -->
       <p class="heading">${player} +</p><br/>
       <div class="content">
       ${hands?.get(i).getRank().toString()}
       </div>
      </td>
      <td> <!-- players cards -->
       <g:each var="j" in="${[0,1,2,3,4]}" >
        <td>
         <img class="cardLarge" src="/CardGameWeb/images/card/${hands?.get(i).getCard(j).toString()}.png" />
        </td>
       </g:each>
      </td>
     </tr>
    </g:each>
   </table>
   <g:if test="${winners.size == 1} " >
   Winner: ${winners[0]} with 
   </g:if>
   <g:else>
   Winners:
    <g:each var="winner" in="${winners}" status="w" >${winner}
     <g:if test="${ w+1 < winners.size}">,</g:if>
    </g:each>
    tie
   </g:else>
    ${winningRank?.toString() }<br/>
   <!-- 
   <a href="${createLink(action:'showPlayers')}">Play again?</a><br/>
   -->
   <g:form controller="pokerGame">
   <g:actionSubmit action="showPlayers" value="Play again?"/>
   </g:form>
</body>

</html>
