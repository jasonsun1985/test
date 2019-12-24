package com.tec.moneycas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @Description: 为了保证扣款不会出现多扣 余额为负数。 1.查询当前账户余额。 2.更新账户余额时候 使用id和 (当前账户余额-消费金额)大于等于0
 * 作为唯一条件去扣款。 3.扣款结果为0说明没成功，为1说明成功更新了一条。
 * 
 * 场景： 账户有130元，每个线程扣1块，3000个线程并发扣款不会出现负数，最后结果为1
 * <p>
 * 创建日期：2019年12月24日
 * </p>
 * @author SUNLEI
 */
public class MysqlMoneyCAS {
	private static DruidDataSource druidDataSource = new DruidDataSource(); // 阿里的连接池
	private static Connection connection;

	private MysqlMoneyCAS() {
		if (connection == null) {
			connection = getConnection(druidDataSource); // 连接池
		}
	}

	private static Connection getConnection(DruidDataSource druidDataSource) {
		druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		// 注意，替换成自己本地的 mysql 数据库地址和用户名、密码
		druidDataSource.setUrl("jdbc:mysql://172.17.160.66:3306/test");
		druidDataSource.setUsername("riil");
		druidDataSource.setPassword("r4rfde32wsaq1");
		// 设置连接池的一些参数
		// 1.数据库连接池初始化的连接个数
		druidDataSource.setInitialSize(50);
		// 2.指定最大的连接数，同一时刻可以同时向数据库申请的连接数
		druidDataSource.setMaxActive(200);
		// 3.指定小连接数：在数据库连接池空闲状态下，连接池中保存的最少的空闲连接数
		druidDataSource.setMinIdle(30);
		Connection con = null;
		try {
			con = druidDataSource.getConnection();
			System.out.println("创建连接池：" + con);
		}
		catch (Exception e) {
			System.out.println("-----------mysql get connection has exception , msg = " + e.getMessage());
		}
		return con;
	}

	public static void main(String[] args) {
		MysqlMoneyCAS moneyCAS = new MysqlMoneyCAS();
		consumeCAS();
		produceCAS();
		
	}

	private static void produceCAS() {
		final int produce = 1;
		for (int i = 0; i < 2000; i++) {
			new Thread(() -> {
				try {
					PreparedStatement psR = connection.prepareStatement("SELECT money FROM money WHERE id = 1");
					ResultSet rs = psR.executeQuery();
					int money = 0;
					while (rs.next()) {
						money = rs.getInt(1);
						System.out.println("账户余额 money : " + money);
					}
					PreparedStatement ps = connection.prepareStatement("update money set money=money + " + produce + " where id = ? ");
					// ps.setInt(1, money);
					ps.setString(1, "1");
//					ps.setInt(2, money);
					int updateRs = ps.executeUpdate();
					System.out.println("充值结果： " + updateRs);
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}

	private static void consumeCAS() {
		final int consume = 3;
		for (int i = 0; i < 3000; i++) {
			new Thread(() -> {
				try {
					PreparedStatement psR = connection.prepareStatement("SELECT money FROM money WHERE id = 1");
					ResultSet rs = psR.executeQuery();
					int money = 0;
					while (rs.next()) {
						money = rs.getInt(1);
						System.out.println("账户余额 money : " + money);
					}
					PreparedStatement ps = connection.prepareStatement("update money set money=money - " + consume + " where id = ? and money = ? and money - " + consume + " >=0");
					// ps.setInt(1, money);
					ps.setString(1, "1");
					ps.setInt(2, money);
					int updateRs = ps.executeUpdate();
					System.out.println("扣减结果： " + updateRs);
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
