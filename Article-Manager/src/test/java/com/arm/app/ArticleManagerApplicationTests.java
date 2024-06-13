package com.arm.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.arm.app.entity.Article;
import com.arm.app.repository.ArticleRepository;
import com.arm.app.service.ArticleService;

@SpringBootTest
public class ArticleManagerApplicationTests {

	@Autowired
	private ArticleService articleService;

	@MockBean
	private ArticleRepository articleRepository;

	@Test
	public void testSaveArticle() {
		Article article = new Article();
		article.setTitle("Test Article");
		article.setDescription("Test Description");

		when(articleRepository.save(article)).thenReturn(article);

		Article savedArticle = articleService.saveArticle(article);

		assertEquals("Test Article", savedArticle.getTitle());
	}
}
