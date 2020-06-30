package com.spring.zulkarnaen.oauth2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
/**
 * 
 * @author zulkarnaen
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.spring.zulkarnaen.oauth2")
public class HelloWorldConfiguration {

	/*
	 * Access Token : Dikirim dengan setiap permintaan, biasanya berlaku untuk waktu
	 * hidup yang sangat singkat [misalnya satu jam]
	 */
	/*
	 * Refresh Token : Terutama digunakan untuk mendapatkan token akses baru, tidak
	 * dikirim dengan setiap permintaan, biasanya hidup lebih lama dari token akses.
	 */

}