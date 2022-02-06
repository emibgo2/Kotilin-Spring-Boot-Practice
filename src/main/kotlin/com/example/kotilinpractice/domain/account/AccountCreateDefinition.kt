package com.example.kotilinpractice.domain.account

import link.pple.assets.domain.Blood
import java.time.LocalDate

data class AccountCreateDefinition(
    val key: Account.ProviderKey,
    val email: String,
    val displayName: String,
    val profileImageUrl: String?
)

data class AccountApplyDefinition(
    val uuid: String,
    val displayName: String,
    val birthDay: LocalDate,
    val gender: Account.Gender,
    val phoneNumber: String,
    val blood: Blood
)
