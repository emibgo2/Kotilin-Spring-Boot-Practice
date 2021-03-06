package com.example.kotilinpractice.domain

import com.example.kotilinpractice.configuration.jpa.Auditor
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*


@MappedSuperclass
open class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = lateInit()

    @Column(columnDefinition = "BINARY(16)")
    var uuid: UUID = lateInit()

    @Column(updatable = false)
    var createdAt: LocalDateTime = lateInit()

    var modifiedAt: LocalDateTime = lateInit()

    @PrePersist
    fun prePersist() {
        val now = LocalDateTime.now()
        createdAt=now
        modifiedAt = now
        uuid = UUID.randomUUID()
    }

    @PreUpdate
    fun preUpdate() {
        modifiedAt = LocalDateTime.now()
    }

    companion object {
        val COMPARATOR_PK_ASC: Comparator<BaseEntity> = compareBy(BaseEntity::requireId)
    }

}

val BaseEntity.requireId: Long get() = id.notNull { "Entity(${javaClass.simpleName}) id is null" }

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class BaseAuditingEntity : BaseEntity() {

    @AttributeOverride(name = "accountId", column = Column(name = "createdAccountId", updatable = false))
    @CreatedBy
    var createdBy: Auditor = lateInit()

    @AttributeOverride(name = "accountId", column = Column(name = "modifiedAccountId", updatable = false))
    @LastModifiedBy
    var modifiedBy: Auditor = lateInit()


}
