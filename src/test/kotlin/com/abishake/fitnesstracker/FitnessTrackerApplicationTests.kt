package com.abishake.fitnesstracker

import com.abishake.fitnesstracker.controllers.MainController
import com.abishake.fitnesstracker.repositories.EntryRepository
import com.abishake.fitnesstracker.service.MainService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Import(TestContainerConfiguration::class)
class FitnessTrackerApplicationTests {

	@Test
	fun contextLoads() {}

}
