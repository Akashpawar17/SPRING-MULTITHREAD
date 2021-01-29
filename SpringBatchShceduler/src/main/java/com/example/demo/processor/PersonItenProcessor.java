package com.example.demo.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.scheduling.annotation.Async;

import com.example.demo.model.Person;

public class PersonItenProcessor  implements ItemProcessor<Person, Person>{

	@Override
	public Person process(Person person) throws Exception {
		return person;
	}
}
