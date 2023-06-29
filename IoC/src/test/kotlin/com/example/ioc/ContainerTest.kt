package com.example.ioc

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertNotEquals

class ContainerTest {

    private lateinit var container: Container

    @Before
    fun setUp() {
        container = Container()
    }

    @Test
    fun `resolve returns registered transient instance`() {
        container.register(Foo::class, { Foo() }, isTransient = true)
        val foo1 = container.resolve<Foo>()
        val foo2 = container.resolve<Foo>()

        assertNotEquals(foo1, foo2)
    }

    @Test
    fun `resolve returns registered singleton instance`() {
        container.register(Foo::class, { Foo() })
        val foo1 = container.resolve<Foo>()
        val foo2 = container.resolve<Foo>()

        assertEquals(foo1, foo2)
    }

    @Test
    fun `resolve throws exception for unregistered class`() {
        assertFailsWith<IllegalStateException> {
            container.resolve<Foo>()
        }
    }

    @Test
    fun `can register an interface and its transient implementation class`() {
        val container = Container()
        container.registerInterface(MyInterface::class, MyImplementation::class) { MyImplementation() }

        val impl = container.resolve<MyInterface>()
        val impl2 = container.resolve<MyInterface>()
        assertIs<MyInterface>(impl)
        assertNotEquals(impl, impl2)
    }

    @Test
    fun `can register an interface and its singleton implementation class`() {
        val container = Container()
        container.registerSingletonInterface(MyInterface::class, MyImplementation::class) { MyImplementation() }

        val impl = container.resolve<MyInterface>()
        val impl2 = container.resolve<MyInterface>()
        assertIs<MyInterface>(impl)
        assertEquals(impl, impl2)
    }

    class Foo() {
        // Class implementation
    }

    interface MyInterface {
        fun doSomething()
    }

    class MyImplementation : MyInterface {
        override fun doSomething() {
            println("Doing something from extended interface !")
        }
    }
}