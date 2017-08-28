package App;

import java.util.Timer;
import java.util.TimerTask;
import net.SocketServer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class App {
	// ����һ����̬��־����
	private static final Logger logger = LogManager.getLogger("shooter");
	
	public static void main(String[] args){
		logger.trace("Start server");
		
		// ���ݱ����ʱ��
		Timer dbSaveTimer = new Timer();
		dbSaveTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				player.Player.updateAll();
			}
		}, 5*1000, 5*1000);
		
		// ս�����¼�ʱ��
		Timer roomUpdateTimer = new Timer();
		roomUpdateTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				room.RoomMgr.update();
			}
		}, 0, 1000);
		
		// ��������
		SocketServer server = SocketServer.getInstance();
		server.start();
		
		logger.trace("Exite server");
	}
}
