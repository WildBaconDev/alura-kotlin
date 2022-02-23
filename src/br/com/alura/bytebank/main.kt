package br.com.alura.bytebank

import java.math.BigDecimal

fun main() {
//    val arr = (1..100)
//    arr.forEach { print("$it ") }
//
//    println( if (1 in arr) "achô" else "faiô" )
//        println(s)


//    ARrayLi
    val salariosArr = arrayOf("1500", "2000", "2500", "3000")
    val salariosBD = salariosArr.map(::toBigDecimal)
    salariosBD.forEach(::prettyPrint)

    println(salariosBD.somatoria())

}

fun toBigDecimal(value: String) = value.toBigDecimal()
fun prettyPrint(value: BigDecimal) = print("${value.longValueExact()} ")

fun List<BigDecimal>.somatoria() = this.reduce { acc, bigDecimal -> acc.add(bigDecimal) }