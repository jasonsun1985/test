package com.tec.bucket4j;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;

/** 
	令牌桶是一种限速算法，与之相对的是漏桶。
	
	令牌限速
	当进行任务的操作时，消耗一定的令牌，后台以一定的速率生产令牌。
	在没有令牌的情况下，就阻塞任务，或者拒绝服务。
	令牌的生产速率，代表了大部分情况下的平均流速。
	
	桶限峰值
	桶的作用就是存储令牌，消耗的令牌都是从桶中获取。
	桶的作用是用来限制流速的峰值，当桶中有额外令牌的时候，实际的流速就会高于限定的令牌生产速率。
	假设令牌生产速率为v，桶大小为b，处理时间为t，则实际流量速度为V=v+b/t。
	
	额外消耗
	为了保证功能的完整，后台必须保证令牌生产，而且是持续服务，不能中断。
	同时，为了桶功能的正确作用，当桶满了以后，后续生产的令牌会溢出，不会存储到桶内部。
 */
public class TestBucket4j {
	/**
	   Bandwidth：带宽，也就是每秒能够通过的流量，自动维护令牌生产。
	   Bucket：桶，不论状态，或是令牌的消费，bucket是我们操作的入口。
	   tryConsume：尝试消费n个令牌，返回布尔值，表示能够消费或者不能够消费，给我们判断依据。
	 */
//	public static void main(String[] args) {
//        Bandwidth limit = Bandwidth.simple(1, Duration.ofSeconds(10));
//        Bucket bucket = Bucket4j.builder().addLimit(limit).build();
//        while(true){
//            if(bucket.tryConsume(1)){
//                System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()));
//            }else{
//                try{
//                    System.out.println("waiting...");
//                    Thread.sleep(200);
//                }catch (Exception e){
//                }
//            }
//        }
//    }
	
	/**
	 	asScheduler会进行阻塞，直到获取令牌才进行后续语句的执行。
	 */
	public static void main(String[] args) throws InterruptedException {
        Bandwidth limit = Bandwidth.simple(1, Duration.ofMillis(1000));
        Bucket bucket = Bucket4j.builder().addLimit(limit).build();
        while(true){
            bucket.asScheduler().consume(1);
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
    }
}
