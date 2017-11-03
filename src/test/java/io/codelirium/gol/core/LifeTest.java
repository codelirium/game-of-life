package io.codelirium.gol.core;

import io.codelirium.gol.model.Agent;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class LifeTest {

	private Life earth;


	@Test
	public void testThatForAnInitialLifeGenerationThePopulationIsZero() {

		earth = noLife();

		assertNoLife(earth);
		assertGeneration(earth, 0L);
	}

	@Test
	public void testThatTheWorldIsInitialisedSuccessfully() {

		earth = fullOfLife();

		assertSomeLife(earth, 9L);
		assertGeneration(earth, 0L);
	}

	@Test
	public void testThatApocalypseIsEvil() {

		earth = fullOfLife();

		earth.apocalypse();

		assertApocalypse(earth);
	}

	@Test
	public void testThatGenerationsProceed() {

		earth = fullOfLife();

		for (long generation = 0L; generation < 5L; generation++) {

			assertGeneration(earth, generation);

			earth.nextWorldOrder();
		}
	}

	@Test
	public void testThatAnAgentWithFewerThanTwoLiveNeighboursDies() {

		earth = ruleOneLife();

		earth.drawWorld();
		earth.nextWorldOrder();
		earth.drawWorld();

		assertNoLife(earth);
	}

	@Test
	public void testThatAnAgentWith2Or3LiveNeighboursLivesOnToTheNextGeneration() {

		earth = ruleTwoLife();

		earth.drawWorld();
		earth.nextWorldOrder();
		earth.drawWorld();

		assertPopulation(earth, 4L);
	}

	@Test
	public void testThatAnAgentWithMoreThan3LiveNeighboursDiesOfOvercrowding() {

		earth = ruleThreeLife();

		earth.drawWorld();
		earth.nextWorldOrder();
		earth.drawWorld();

		assertPopulation(earth, 4L);
	}

	@Test
	public void testThatADeadAgentWithExactly3LiveNeighboursComesToLife() {

		earth = ruleFourLife();

		earth.drawWorld();
		earth.nextWorldOrder();
		earth.drawWorld();

		assertPopulation(earth, 4L);
	}


	private Life noLife() {

		Life noLife = new Life(new Agent[3][3]);

		noLife.initializeWorld(-1.1D);

		return noLife;
	}

	private void assertNoLife(Life life) {

		assertNotNull(life.getWorld());

		assertPopulation(earth, 0L);
	}

	private void assertApocalypse(Life life) {

		assertNull(life.getWorld());

	}

	private void assertPopulation(Life life, long expectedPopulation) {

		assertTrue(expectedPopulation == life.getPopulation());

	}

	private void assertGeneration(Life life, long expectedGeneration) {

		assertTrue(expectedGeneration == life.getGeneration());

	}

	private Life fullOfLife() {

		Life life = new Life(new Agent[3][3]);

		life.initializeWorld(1.1D);

		return life;
	}

	private void assertSomeLife(Life life, long expectedPopulation) {

		assertNotNull(life.getWorld());

		assertPopulation(life, expectedPopulation);
	}

	private Life ruleOneLife() {

		Life life = noLife();

		// . . .
		// O O .
		// . . .

		life.getWorld()[1][0].setAlive(true);
		life.getWorld()[1][1].setAlive(true);

		return life;
	}

	private Life ruleTwoLife() {

		Life life = noLife();

		// O O .
		// O O .
		// . . .

		life.getWorld()[0][0].setAlive(true);
		life.getWorld()[0][1].setAlive(true);
		life.getWorld()[1][0].setAlive(true);
		life.getWorld()[1][1].setAlive(true);

		return life;
	}

	private Life ruleThreeLife() {

		Life life = noLife();

		// O . .
		// O O .
		// O O .

		life.getWorld()[0][0].setAlive(true);
		life.getWorld()[1][0].setAlive(true);
		life.getWorld()[1][1].setAlive(true);
		life.getWorld()[2][0].setAlive(true);
		life.getWorld()[2][1].setAlive(true);

		return life;
	}

	private Life ruleFourLife() {

		Life life = noLife();

		// O O .
		// O . .
		// . . .

		life.getWorld()[0][0].setAlive(true);
		life.getWorld()[0][1].setAlive(true);
		life.getWorld()[1][0].setAlive(true);

		return life;
	}
}
