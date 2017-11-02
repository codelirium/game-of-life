package io.codelirium.gol;

import io.codelirium.gol.core.Life;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class StartLife implements CommandLineRunner {

	private static final String CLEAR_SCREEN = "\033[H\033[2J";


	@Override
	public void run(String... args) throws InterruptedException {

		Life earth = new Life(60, 60, 0.08);

		earth.drawWorld();


		while(true) {

			Thread.sleep(200);


			earth.nextWorldOrder();

			System.out.print(CLEAR_SCREEN);

			earth.drawWorld();
		}
	}



	public static void main(String[] args) {

		new SpringApplicationBuilder(StartLife.class)
											.logStartupInfo(false)
											.run(args);

	}
}
