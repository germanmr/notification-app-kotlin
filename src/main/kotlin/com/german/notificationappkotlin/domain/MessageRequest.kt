package com.german.notificationappkotlin.domain

import java.util.*
import javax.persistence.*


@Entity
//@Table(name = "message_request")
//object class MessageRequest(
data class MessageRequest(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
    val title: String,
    val uuid: UUID,
    @Embedded
    val client: Client,
    @Embedded
    val publication: Publication,
    var error: String? = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "message_state")
    var messageState: MessageStates
) {

    // TODO use an entity to with Embeddable StateWithMessage?
    fun setRequestSuccess() {
        messageState = MessageStates.SUCCESS
        error = null
    }

    fun setRequestError(errorMessage: String) {
        messageState = MessageStates.ERROR
        error = errorMessage
    }

    fun setRequestBeginProcessing() {
        messageState = MessageStates.PROCESSING
        error = null
    }

//    fun setRequestAcknowledgement(messageRequestDTO: MessageRequestDTO) {
//        if (MessageStates.SUCCESS.equals(MessageStates.valueOf(messageRequestDTO.getMessageState().toString()))) {
//            setSuccess()
//        } else {
//            setError(messageRequestDTO.getError())
//        }
//    }
}