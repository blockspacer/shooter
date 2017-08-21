package room;

import player.Player;

enum GameState{
	GS_PREPARE,
	GS_PLAYER_READY,
	GS_PLAYER0_TURN,
	GS_PLAYER1_TURN,
	GS_END
}

public class Room {
	public GameState m_gameState = GameState.GS_PREPARE;
	public float  	 m_gameTime= 0.f;
	public Player 	 m_player0 = null;
	public Player 	 m_player1 = null;
	
	public void process(float delta){
		m_gameTime += delta;
		if(m_gameState == GameState.GS_PLAYER_READY && m_gameTime>=1.f) {
			protocol.battle_begin msg = new protocol.battle_begin();
			m_player0.sendMsg( msg.data());
			m_player1.sendMsg( msg.data());
			
			m_gameState = GameState.GS_PLAYER0_TURN;
		}
		else if(m_gameState==GameState.GS_PLAYER0_TURN) {
			
		}
		else if(m_gameState==GameState.GS_PLAYER1_TURN) {
			
		}
		else if(m_gameState==GameState.GS_END) {
			
		}
		
		System.out.println("room process");
	}
	
	void addPlayer( Integer p0, Integer p1) {
		m_player0 = Player.get(p0);
		m_player1 = Player.get(p1);
		
		protocol.battle_player_enter msg0 = new protocol.battle_player_enter();
		msg0.pos = 0;
		msg0.name = "player_0";
		m_player0.sendMsg(msg0.data());
		m_player1.sendMsg(msg0.data());
		
		protocol.battle_player_enter msg1 = new protocol.battle_player_enter();
		msg1.pos = 1;
		msg1.name = "player_1";
		m_player0.sendMsg(msg1.data());
		m_player1.sendMsg(msg1.data());
	}
}