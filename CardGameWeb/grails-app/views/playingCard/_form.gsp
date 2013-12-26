<%@ page import="com.cardtech.web.PlayingCard" %>



<div class="fieldcontain ${hasErrors(bean: playingCardInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="playingCard.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" pattern="${playingCardInstance.constraints.name.matches}" required="" value="${playingCardInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playingCardInstance, field: 'imageFile', 'error')} required">
	<label for="imageFile">
		<g:message code="playingCard.imageFile.label" default="Image File" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="imageFile" pattern="${playingCardInstance.constraints.imageFile.matches}" required="" value="${playingCardInstance?.imageFile}"/>
</div>

