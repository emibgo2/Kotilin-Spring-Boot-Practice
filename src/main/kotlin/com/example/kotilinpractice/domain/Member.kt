package com.example.kotilinpractice.domain

import com.example.kotilinpractice.domain.lateInit
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member {

    @Id
    @GeneratedValue
    var id: Long? = lateInit()

    constructor(name: String?) {
        this.name = name
    }

    var name:String? = lateInit()
}