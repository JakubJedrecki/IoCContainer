package com.example.ioc

import kotlin.reflect.KClass

class Container {
    private val dependencies = mutableMapOf<KClass<*>, () -> Any>()
    private val singletons = mutableMapOf<KClass<*>, Any>()
    private val interfaceMappings = mutableMapOf<KClass<*>, KClass<*>>()

    fun <T : Any> register(clazz: KClass<T>, creator: () -> T, isTransient: Boolean = false) {
        if (isTransient) {
            dependencies[clazz] = creator
        } else {
            singletons[clazz] = creator()
        }
    }

    fun <T : Any, R : T> registerInterface(interfaceClass: KClass<T>, implementationClass: KClass<R>, implementationCreator: () -> R) {
        interfaceMappings[interfaceClass] = implementationClass
        register(implementationClass, { implementationCreator() }, isTransient = true)
    }

    fun <T : Any, R : T> registerSingletonInterface(interfaceClass: KClass<T>, implementationClass: KClass<R>, implementationCreator: () -> R) {
        interfaceMappings[interfaceClass] = implementationClass
        register(implementationClass, { implementationCreator() })
    }

    inline fun <reified T: Any> resolve(): T {
        return resolve(T::class) as T
    }

    fun resolve(clazz: KClass<*>): Any {
        val resolvedClass = interfaceMappings[clazz] ?: clazz

        return if (singletons.containsKey(resolvedClass)) {
            singletons[resolvedClass]
        } else {
            dependencies[resolvedClass]?.invoke() ?: throw IllegalStateException("Class $clazz is not registered in the container")
        } as Any
    }
}