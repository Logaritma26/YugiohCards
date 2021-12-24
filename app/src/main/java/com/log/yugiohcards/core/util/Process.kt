package com.log.yugiohcards.core.util


sealed class Process(val message: String? = null) {
    class Success : Process()
    class Fail(message: String? = "Process failed") : Process(message = message)
}
