package cn.edu.nju.FindIt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ContextConfiguration;

import cn.edu.nju.FindIt.repository.TagRepository;

@SpringBootTest
class FindItApplicationTests {

	private TagRepository tagRepository;

	@Test
	void contextLoads() {
	}

	@Autowired
	public FindItApplicationTests(TagRepository tagRepository){
		this.tagRepository = tagRepository;
	}

	// @Test
	// public void testFindAllItems(){
	// 	System.out.println(tagRepository.findAllItems());
	// }
}
