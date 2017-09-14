package App;

import java.util.Timer;
import java.util.TimerTask;
import net.socket.SocketServer;
import org.apache.logging.log4j.Logger;

import net.http.HttpServer;

import org.apache.logging.log4j.LogManager;

public class app {
	// ����һ����̬��־����
	private static final Logger logger = LogManager.getLogger("shooter");
	
	public static void main(String[] args){
		logger.info("--------------");
		logger.info("|Start server|");
		logger.info("--------------");
		
		// ���ݱ����ʱ��
		Timer dbSaveTimer = new Timer();
		dbSaveTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				manager.player.Player.updateAll();
			}
		}, 5*1000, 5*1000);
		
		// ս�����¼�ʱ��
		Timer roomUpdateTimer = new Timer();
		roomUpdateTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				manager.room.RoomMgr.update();
			}
		}, 0, 1000);
		
		// ��������
		SocketServer server = SocketServer.getInstance();
		server.start();
		
		HttpServer httpServer = HttpServer.getInstance();
		httpServer.start();
	}
	
	public static Logger logger() {
		return logger;
	}
}
