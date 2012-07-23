package net.slipp.support.wiki;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlippWikiUtilsTest {
	private Logger logger = LoggerFactory.getLogger(SlippWikiUtilsTest.class);

	@Test
	public void createImageList() throws Exception {
		String contents = TestFileReader.read(this, "images.txt");
		List<String> images = SlippWikiUtils.createImageListFrom(contents);
		assertThat(images.size(), is(2));
		logger.debug(images.toString());
	}
	
	@Test
	public void replaceImages() throws Exception {
		String contents = TestFileReader.read(this, "images.txt");
		String targetUrl = "http://localhost:8080";
		String actual = SlippWikiUtils.replaceImages(contents, targetUrl);
		logger.debug("result : {}", actual);
	}
	
	@Test
	public void convertTabToSpace() throws Exception {
		String contents = TestFileReader.read(this, "tab.txt");
		String actual = SlippWikiUtils.convertTabToSpace(contents);
		logger.debug("converted contents : {}", actual);
	}
}