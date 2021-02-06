package com.company.tribe.filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import org.slf4j.MDC
import org.springframework.stereotype.Component

@Component
class RequestFilter : Filter {
    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        chain: FilterChain
    ) {
        val req = request as HttpServletRequest
        val requestId = req.getHeader("x-company-request-id")
        val sessionId = req.getHeader("x-company-session-id")

        val mdcMap = mapOf(
            "requestId" to requestId,
            "sessionId" to sessionId
        )

        MDC.setContextMap(mdcMap)

        chain.doFilter(request, response)

        MDC.clear()
    }
}
