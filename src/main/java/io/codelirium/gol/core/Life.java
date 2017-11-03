package io.codelirium.gol.core;

import io.codelirium.gol.model.Agent;

import java.util.Arrays;

import static java.lang.System.out;
import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;


@Bitch
public class Life {

	private Agent[][] world;
	private long generation;


	public Life(int width, int height, double densityFactor) {

		this.world = new Agent[width][height];
		this.generation = 0L;

		initializeWorld(densityFactor);
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

				out.print(agent.isAlive() ? "O " : ". ");

			}

			out.println();
		}

		out.println("Generation: #" + getGeneration());
		out.println("Population: #" + getPopulation());
	}


	public void nextWorldOrder() {

		asList(world)
			.parallelStream()
				.flatMap(Arrays::stream)
					.forEach(agent -> decideAgentLifeStatus(world, agent));

		generation++;
	}


	protected Agent[][] getWorld() {

		return world;

	}


	protected long getGeneration() {

		return generation;

	}


	protected long getPopulation() {

		return asList(world)
					.parallelStream()
						.flatMap(Arrays::stream)
						.filter(agent -> nonNull(agent) && agent.isAlive())
						.count();

	}

	protected static void decideAgentLifeStatus(Agent[][] world, Agent agent){

		int     myX     = agent.getX();
		int     myY     = agent.getY();
		boolean isAlive = agent.isAlive();

		int liveCount = 0;

		for (int xOffset = -1; xOffset <= 1; xOffset++) {

			int neighborX = myX + xOffset;

			neighborX = (neighborX < 0)? world.length - 1: neighborX;
			neighborX = (neighborX >= world.length)? 0 : neighborX;

			for (int yOffset = -1; yOffset <= 1; yOffset++) {

				int neighborY = myY + yOffset;

				neighborY = (neighborY < 0)? world[0].length - 1: neighborY;
				neighborY = (neighborY >= world[0].length)? 0 : neighborY;

				Agent neighbor = world[neighborX][neighborY];

				if (neighbor.isAlive()) {

					liveCount++;

				}
			}
		}


		if (isAlive) {

			liveCount--;

		}


		if (liveCount == 2 && isAlive) {

			world[myX][myY].setAlive(true);

			return;

		} else if (liveCount == 3) {

			world[myX][myY].setAlive(true);

			return;
		}


		world[myX][myY].setAlive(false);
	}

	public void apocalypse() {

		world = null;

		out.println("kaboom!");
	}
}
