<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>CardTech Inc.</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}
			.large-logo { float: left }
			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
	    <div id="cards-60" class="large-logo" >
	      <img alt="credit: George Hart" src="${resource(dir: 'images', file: 'cards-60.jpg')}" style="display: block" width="256" height="256">
	      &nbsp;credit: <a href="http://www.georgehart.com">George Hart</a>
	    </div>
		<div id="page-body" role="main" align="left">
			<h1 style="font-style: italic" >CardTech Inc</h1>
			<p>CardTech is the leading provider of card, deck, and game objects in the industry.  Actually it's a fictitious
			   company invented by Bob Rodini for instructional purposes.</p>

			<div id="controller-list" role="navigation">
			 <h2>For Users:</h2>
			  <ul>
			   <li class="controller" > <g:link controller="player">Players</g:link></li>
			   <li class="controller" > <g:link controller="pokerGame">Play poker</g:link></li>
			  </ul>
			 <h2>For Admins:</h2>
			  <ul>
			   <li class="controller" > <g:link controller="playingCard">Cards</g:link></li>
			   <li class="controller" > <g:link controller="deck">Deck</g:link></li>
			   <li class="controller" > <g:link controller="pokerRank">Poker rank</g:link></li>
			  </ul>
			</div>
		</div>
	</body>
</html>
