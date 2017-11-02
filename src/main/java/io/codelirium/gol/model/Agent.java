package io.codelirium.gol.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Agent {

	private int x;
	private int y;
	private boolean isAlive;

}
