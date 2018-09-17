package com.hourz.common.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageFilter;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * <p>图片生成引擎</p>
 * @author hourz
 * @since 2016-06-20
 */
public class ImageEngine extends ListImageCaptchaEngine {
	// 最小的单词长度
	private static final int minWordLength = 4;
	// 最大的单词长度
	private static final int maxWordLength=5;
	// 字体大小
	private static final int fontSize = 20;
	// 图片宽度
	private static final int imageWidth = 100;
	// 图片高度
	private static final int imageHeight = 32;

	
	@Override
	protected void buildInitialFactories() {
		// 单词生成器
		WordGenerator dictionaryWords = new ComposeDictionaryWordGenerator(new FileDictionary("toddlist"));
		
		// 单词转图片组件
		TextPaster randomPaster = new DecoratedRandomTextPaster(
				minWordLength, 
				maxWordLength, 
				new RandomListColorGenerator(
						new Color[]{  new Color(23, 170, 27), 
											new Color(220, 34, 11),
											new Color(23, 67, 172)}), 
						new TextDecorator[]{});
		
		// 背景生成器
		BackgroundGenerator background = new UniColorBackgroundGenerator(imageWidth, imageHeight, Color.white);
		
		// 字体生成器
		FontGenerator font = new RandomFontGenerator(fontSize, fontSize, new Font[]{
				new Font("Arial", Font.BOLD, fontSize),
				new Font("Verdana", Font.BOLD, fontSize)
		});
		
		// 图片变形
		ImageDeformation postDef = new ImageDeformationByFilters(new ImageFilter[]{});
		ImageDeformation backDef = new ImageDeformationByFilters(new ImageFilter[]{});
		ImageDeformation textDef = new ImageDeformationByFilters(new ImageFilter[]{});
		
		// 单词转换为图片
		WordToImage word2image = new DeformedComposedWordToImage(font, background, randomPaster, backDef, textDef, postDef);
		
		addFactory(new GimpyFactory(dictionaryWords, word2image));
	}
}
