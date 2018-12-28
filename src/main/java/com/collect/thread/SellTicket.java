package com.collect.thread;

public class SellTicket {
	public static void main(String[] args) {
		Ticket ticket = new Ticket(100);
//		Runnable r = new TicketSeller(5,ticket);
//		Thread t = new Thread(r);
//		t.start();
		for (int i = 0; i < 10; i++) {
			new Thread(new TicketSeller(i, ticket)).start();
		}
	}
}
class Ticket{
	int ticketNum = 100;
	boolean flag = false;
	public Ticket(){
	}
	public Ticket(int ticketNum) {
		this.ticketNum = ticketNum;
	}
	public synchronized void sell(TicketSeller ticketSeller){
		if(ticketNum>0){
			System.out.println("售票点"+ticketSeller.sellerNum+"售出第"+(100-ticketNum+1)+"张票");
			ticketNum--;	
		}else{
			flag = true;
			System.out.println("售票结束");
		}
	}
}
class TicketSeller implements Runnable{
	Ticket ticket;
	int sellerNum;
	public TicketSeller(int sellerNum,Ticket ticket){
		this.ticket = ticket;
		this.sellerNum = sellerNum;
	}
	public void run() {
		while(!ticket.flag){
			ticket.sell(this);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
