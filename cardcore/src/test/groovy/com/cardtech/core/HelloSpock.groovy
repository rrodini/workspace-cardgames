package com.cardtech.core

class HelloSpock extends spock.lang.Specification {

	def "length of Spock's friends' names"() {
	expect:
		name.size() == length
	where:
		name		| length
		"Spock"   | 5
		"Kirk"    | 4
		"Scotty"	 | 6
	}

}