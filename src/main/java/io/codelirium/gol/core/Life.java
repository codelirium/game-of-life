package io.codelirium.gol.core;

import io.codelirium.gol.model.Agent;

import static java.util.Objects.nonNull;


@Bitch
public class Life {

	private Agent[][] world;
	private long generation;


	public Life(int width, int height, double sparsityFactor) {

		this.world = new Agent[width][height];
		this.generation = 0L;

		initializeWorld(sparsityFactor);
	}


	protected Life(Agent[][] world) {

		this.world = world;
		this.generation = 0L;

	}


	protected void initializeWorld(double initFactor) {

		for (int x = 0; x < world.length; x++) {

			for (int y = 0; y < world[x].length; y++) {

				world[x][y] = new Agent(x, y, (Math.random() < initFactor));

			}
		}
	}


	public void drawWorld() {

		for (Agent[] row : world) {

			for (Agent agent : row) {

				System.out.print(agent.isAlive() ? "O " : ". ");

			}

			System.out.println();
		}

		System.out.println("Generation: #" + getGeneration());
		System.out.println("Population: #" + getPopulation());
	}


	public void nextWorldOrder() {

		for (Agent[] row : world) {

			for (Agent agent : row) {

				decideAgentLifeStatus(world, agent);

			}
		}

		generation++;
	}


	protected Agent[][] getWorld() {

		return world;

	}


	protected long getGeneration() {

		return generation;

	}


	protected long getPopulation() {

		long population = 0L;

		for (int x = 0; x < world.length; x++) {

			for (int y = 0; y < world[x].length; y++) {

				Agent agent = world[x][y];

				if (nonNull(agent) && agent.isAlive()) {

					population++;

				}
			}
		}


		return population;
	}

	protected static void decideAgentLifeStatus(Agent[][] world, Agent agent){

		int     x       = agent.getX();
		int     y       = agent.getY();
		boolean isAlive = agent.isAlive();

		int liveCount = 0;

		for (int r = -1; r <= 1; r++) {

			int currentRow = x + r;

			currentRow = (currentRow < 0)? world.length - 1: currentRow;
			currentRow = (currentRow >= world.length)? 0 : currentRow;

			for (int c = -1; c <= 1; c++) {

				int currentCol = y + c;

				currentCol = (currentCol < 0)? world[0].length - 1: currentCol;
				currentCol = (currentCol >= world[0].length)? 0 : currentCol;

				Agent neighbor = world[currentRow][currentCol];

				if (neighbor.isAlive()) {

					liveCount++;

				}
			}
		}


		if (isAlive) {

			liveCount--;

		}


		if (liveCount == 2 && isAlive) {

			world[x][y].setAlive(true);

			return;

		} else if (liveCount == 3) {

			world[x][y].setAlive(true);

			return;
		}


		world[x][y].setAlive(false);
	}
}
