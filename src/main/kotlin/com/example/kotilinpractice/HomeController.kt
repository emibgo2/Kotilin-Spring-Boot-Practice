package com.example.kotilinpractice

import com.example.kotilinpractice.domain.Member
import com.example.kotilinpractice.domain.MemberRepository
import com.example.kotilinpractice.domain.lateInit
import lombok.AllArgsConstructor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.annotation.PostConstruct

@Controller
@AllArgsConstructor

class HomeController(val memberRepository: MemberRepository) {

    val region: String = "ap-northeast-2"
    val name: String = "hello"

    var helloMember: List<Member>? = lateInit()


    @GetMapping("/")
    fun home(model:Model): String {

        val findAll = memberRepository.findAll()
        model.addAttribute("members", findAll)

        for (i in findAll) {
            println(i.name)
        }
        return "html"
    }


    @PostConstruct
    fun init() {
        for (x in 1..5) {
            val member = Member("hi" + x)
            memberRepository.save(member)
        }
    }
}