package ru.korpanov.extensions

import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import ru.korpanov.extensions.annotation.CallThePolice

class MyExtension : BeforeEachCallback {
    override fun beforeEach(context: ExtensionContext?) {
        val isPresent = context!!.testMethod.get().isAnnotationPresent(CallThePolice::class.java)
        if (isPresent) {
            print("Галя вызывай метов, у нас чп")
        }
    }
}