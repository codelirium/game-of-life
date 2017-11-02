package io.codelirium.gol.core;

import io.codelirium.gol.model.Agent;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class LifeTest {

	private Life earth;


	@Test
	public void testThatForAnInitialLifeGenerationThePopulationIsZero() {

		earth = getAnEmptySquareLife();

		assertTrue(earth.getGeneration() == 0L);
		assertTrue(earth.getPopulation() == 0L);
	}

	@Test
	public void testThatTheWorldIsInitialisedSuccessfully() {

		earth = getAnEmptySquareLife();
		earth.initializeWorld(12.345);

		Agent[][] world = earth.getWorld();

		for (Agent[] row : world) {
			for (Agent agent : row) {
				assertTrue(agent.isAlive());
			}
		}

		assertTrue(earth.getGeneration() == 0);
		assertTrue(earth.getPopulation() == 25);
	}

	@Test
	public void testThatAnAgentWithFewerThanTwoLiveNeighboursDies() {



	}

	@Test
	public void testThatAnAgentWith2Or3LiveNeighboursLivesOnToTheNextGeneration() {



	}

	@Test
	public void testThatAnAgentWithMoreThan3LiveNeighboursDiesOfOvercrowding() {



	}

	@Test
	public void testThatADeadAgentWithExactly3LiveNeighboursComesToLife() {



	}


	private Life getAnEmptySquareLife() {
		return new Life(new Agent[5][5]);
	}
}
