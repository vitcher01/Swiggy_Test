package com.vishal.GameExec;

public class GameExec {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		GameLogic initializer = new GameLogic();
		try {
			initializer.GameBegins();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
