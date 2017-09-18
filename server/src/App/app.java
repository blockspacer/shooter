package App;

import java.util.Timer;
import java.util.TimerTask;
import net.socket.SocketServer;
import quartz.JobMgr;

import org.apache.logging.log4j.Logger;

import manager.ranking.RankingMgr;
import net.http.HttpServer;

import org.apache.logging.log4j.LogManager;

public class app {
	// ����һ����̬��־����
	private static final Logger logger = LogManager.getLogger("shooter");
	
	public static void main(String[] args){
		logger.info("--------------");
		logger.info("|Start server|");
		logger.info("--------------");
		
		// ������ֹ����
		KillHandler killHandler = new KillHandler();
		killHandler.registerSignal("TERM");
		
		// ������ʱ����
		JobMgr.getInstance().startJobs();
		
		// ���а�
		RankingMgr.getInstance();
		
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
