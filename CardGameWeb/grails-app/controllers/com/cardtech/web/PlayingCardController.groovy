package com.cardtech.web

import org.springframework.dao.DataIntegrityViolationException

import com.cardtech.web.PlayingCard;

class PlayingCardController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
		log.debug "PlayingCardController: list"
        params.max = Math.min(max ?: 10, 100)
        [playingCardInstanceList: PlayingCard.list(params), playingCardInstanceTotal: PlayingCard.count()]
    }

    def create() {
		log.debug "PlayingCardController: create"
        [playingCardInstance: new PlayingCard(params)]
    }

    def save() {
		log.debug "PlayingCardController: save"
        def playingCardInstance = new PlayingCard(params)
        if (!playingCardInstance.save(flush: true)) {
            render(view: "create", model: [playingCardInstance: playingCardInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'playingCard.label', default: 'PlayingCard'), playingCardInstance.id])
        redirect(action: "show", id: playingCardInstance.id)
    }

    def show(Long id) {
		log.debug "PlayingCardController: show"
        def playingCardInstance = PlayingCard.get(id)
        if (!playingCardInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'playingCard.label', default: 'PlayingCard'), id])
            redirect(action: "list")
            return
        }

        [playingCardInstance: playingCardInstance]
    }

    def edit(Long id) {
		log.debug "PlayingCardController: edit"
        def playingCardInstance = PlayingCard.get(id)
        if (!playingCardInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'playingCard.label', default: 'PlayingCard'), id])
            redirect(action: "list")
            return
        }

        [playingCardInstance: playingCardInstance]
    }

    def update(Long id, Long version) {
		log.debug "PlayingCardController: update"
        def playingCardInstance = PlayingCard.get(id)
        if (!playingCardInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'playingCard.label', default: 'PlayingCard'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (playingCardInstance.version > version) {
                playingCardInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'playingCard.label', default: 'PlayingCard')] as Object[],
                          "Another user has updated this PlayingCard while you were editing")
                render(view: "edit", model: [playingCardInstance: playingCardInstance])
                return
            }
        }

        playingCardInstance.properties = params

        if (!playingCardInstance.save(flush: true)) {
            render(view: "edit", model: [playingCardInstance: playingCardInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'playingCard.label', default: 'PlayingCard'), playingCardInstance.id])
        redirect(action: "show", id: playingCardInstance.id)
    }

    def delete(Long id) {
		log.debug "PlayingCardController: delete"
        def playingCardInstance = PlayingCard.get(id)
        if (!playingCardInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'playingCard.label', default: 'PlayingCard'), id])
            redirect(action: "list")
            return
        }

        try {
            playingCardInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'playingCard.label', default: 'PlayingCard'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'playingCard.label', default: 'PlayingCard'), id])
            redirect(action: "show", id: id)
        }
    }
}
