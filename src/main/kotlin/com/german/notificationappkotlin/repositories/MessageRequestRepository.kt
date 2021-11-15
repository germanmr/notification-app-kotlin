package com.german.notificationappkotlin.repositories

import com.german.notificationappkotlin.domain.MessageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.LockModeType

@Repository
interface MessageRequestRepository : JpaRepository<MessageRequest, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    override fun findById(id: Long): Optional<MessageRequest>

    //    @Query(
//        value = "select * FROM message_request mr " +
//                " where mr.favorite_media='MAIL' and mr.message_state in ('PENDING','ERROR') AND MR.id IN (378,382) " +
//                " order by mr.id for update skip locked limit :batch_size", nativeQuery = true
//    )
    @Query(
        value = "select * FROM message_request mr " +
                " where mr.message_state in ('PENDING','ERROR') " +
                " order by mr.id for update skip locked limit :batch_size", nativeQuery = true
    )
//    @QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = "5000") })
    fun getBatchForUpdateById(@Param("batch_size") batch_size: Long): List<MessageRequest>

    @Query(
        value = "select * FROM message_request mr " +
                " where mr.favorite_media='MAIL' and mr.message_state in ('PENDING','ERROR') AND MR.id IN (378) " +
                " order by mr.id for update skip locked limit 1", nativeQuery = true
    )
//    @QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = "5000") })
    fun getNextMessageForUpdate(): MessageRequest

}