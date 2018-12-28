package com.hadoop.mr;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordCount {

	private static final Logger LOG = LoggerFactory.getLogger(WordCount.class);

	public static final class M extends Mapper<Long, String, String, Long> {

		@Override
		public void map(Long key, String value, Context context)
				throws RuntimeException {
			String tmp = value.toLowerCase();
			String[] line = tmp.split("[\\s]+");

			for (String word : line) {
				context.write(word, 1L);
			}
		}

	}

	public static final class R extends Reducer<String, Long, String, Long> {

		@Override
		public void reduce(String k, Iterable<Long> list, Context context)
				throws RuntimeException {
			Long count = 0L;
			for (Long item : list) {
				count += item;
			}
			context.write(k, count);
		}

	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
//	public static void main(String[] args) throws IOException, InterruptedException {
//		LOG.info("Hi");
//		String[] data = {
//				"What Is Apache Hadoop?",
//				"The Apache™ Hadoop™ project develops open-source software for reliable, scalable, distributed computing.",
//				"The Apache Hadoop software library is a framework that allows for the distributed processing of large data sets across clusters of computers using a simple programming model. It is designed to scale up from single servers to thousands of machines, each offering local computation and storage. Rather than rely on hardware to deliver high-avaiability, the library itself is designed to detect and handle failures at the application layer, so delivering a highly-availabile service on top of a cluster of computers, each of which may be prone to failures.",
//				"The project includes these subprojects:",
//				"Hadoop Common: The common utilities that support the other Hadoop subprojects.",
//				"Hadoop Distributed File System (HDFS™): A distributed file system that provides high-throughput access to application data.",
//				"Hadoop MapReduce: A software framework for distributed processing of large data sets on compute clusters.",
//				"Other Hadoop-related projects at Apache include:",
//				"Avro™: A data serialization system.",
//				"Cassandra™: A scalable multi-master database with no single points of failure.",
//				"Chukwa™: A data collection system for managing large distributed systems.",
//				"HBase™: A scalable, distributed database that supports structured data storage for large tables.",
//				"Hive™: A data warehouse infrastructure that provides data summarization and ad hoc querying.",
//				"Mahout™: A Scalable machine learning and data mining library.",
//				"Pig™: A high-level data-flow language and execution framework for parallel computation.",
//				"ZooKeeper™: A high-performance coordination service for distributed applications." };
//		Job job = new Job();
//		job.setMap(M.class);
//		job.setReduce(R.class);
//		job.run(data);
//	}
	public static void main(String[] args) {
		LOG.info("Hi");
		String[] data = { "What Are You Doing?", "What Is Your Name?", "What Is Going on?" };
		Job job = new Job();
		job.setMap(M.class);
		job.setReduce(R.class);
		job.run(data);
	}
}
