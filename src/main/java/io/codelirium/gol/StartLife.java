package io.codelirium.gol;

import io.codelirium.gol.core.Life;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static java.lang.Runtime.getRuntime;
import static java.lang.System.out;
import static java.lang.Thread.sleep;


@SpringBootApplication
public class StartLife implements CommandLineRunner {

	private static final String CLEAR_SCREEN = "\033[H\033[2J";


	@Override
	public void run(String... args) throws InterruptedException {

		Life earth = new Life(60, 60, 0.1D);

		earth.drawWorld();

		setUpApocalypse(earth);

		while(true) {

			sleep(300);


			earth.nextWorldOrder();

			out.print(CLEAR_SCREEN);

			earth.drawWorld();
		}
	}

	private void setUpApocalypse(Life life) {

		getRuntime().addShutdownHook(new Thread() {

			public void run() {

				life.apocalypse();

			}
		});
	}

	public static void main(String[] args) {

		new SpringApplicationBuilder(StartLife.class)
											.logStartupInfo(false)
											.run(args);

	}
}
