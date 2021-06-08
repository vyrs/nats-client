package br.com.zup.nats

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.com.zup.nats")
		.start()
}

