package com.tec.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.collect.any.People;
import com.collect.weakreference.Car;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;


public class TestGuava {
	public static void main(String[] args) {
		List<String> keys = Lists.newArrayList("RUIJIE", "H3C", "HUAWEI");
		Collection<String> result = Collections2.filter(keys, new Predicate<String>() {
			@Override
			public boolean apply(String key) {
				return key.contains("RUIJIE");
			}
		});
		for (String str : result) {
			System.out.println(str);// "RUIJIE"
		}

		List<String> numbersAsStrings = Lists.newArrayList("1", "2", "3");
		Collection<Double> doubles = Collections2.transform(numbersAsStrings, new Function<String, Double>() {
			@Override
			public Double apply(String src) {
				return Double.parseDouble(src) * 100;
			}
		});

		for (Double d : doubles) {
			System.out.println("转换后的结果 ： " + d);
		}

		List<Car> listCar = Lists.newArrayList(new Car(300000, "red"), new Car(426362, "White"),
				new Car(500000, "Black"));
		Collection<Double> listNewCarPrice = Collections2.transform(listCar, new Function<Car, Double>() {
			@Override
			public Double apply(Car car) {
				return car.getPrice();
			}
		});

		for (Double d1 : listNewCarPrice) {
			System.out.println("汽车价格： " + d1);
		}

		System.out.println(Strings.isNullOrEmpty(""));

		// use java
		List<String> list = new ArrayList<String>();
		String a = "1-2-3-4-5-6";
		String[] strs = a.split("-");
		for (int i = 0; i < strs.length; i++) {
			list.add(strs[i]);
		}
		System.out.println(list);

		// use guava
		String str = "1-2-3-4-5-6";
		List<String> list2 = Splitter.on("-").splitToList(str);
		System.out.println(list2);
		// list为 [1, 2, 3, 4, 5, 6]

		String str3 = "ip=192.168.1.166&port=8899&chan=0&event=32";
		Map<String, String> map3 = new HashMap<>();
		map3 = Splitter.on("&").withKeyValueSeparator("=").split(str3);
		System.out.println("map3 : " + map3);
		System.out.println("map3 port : " + map3.get("port"));

		// 把map集合转换为特定规则的字符串
		Map<String, Integer> map = Maps.newHashMap();
		map.put("JASON", 88);
		map.put("A", 99);
		String result1 = Joiner.on(",").withKeyValueSeparator("=").join(map);
		System.out.println(result1);

		// 将集合转换为特定规则的字符串
		List<String> list3 = new ArrayList<String>();
		list3.add("aa");
		list3.add("bb");
		list3.add("cc");
		String str1 = "";
		for (int i = 0; i < list3.size(); i++) {
			str1 = str1 + "-" + list3.get(i);
		}
		System.out.println(str1);
		// str 为-aa-bb-cc

		System.out.println(Joiner.on("-").join(list3));

		Map<String, List<Integer>> map4 = new HashMap<String, List<Integer>>();
		List<Integer> list4 = new ArrayList<Integer>();
		list4.add(32);
		list4.add(92);
		map4.put("aa", list4);
		System.out.println(map4.get("aa"));// [32, 92]

		Multimap<String, Integer> map5 = ArrayListMultimap.create();
		map5.put("jason", 88);
		map5.put("jason", 100);
		System.out.println(map5.get("jason"));// [88, 100]

		String str5 = "1-2-3-4-  5-  6   ";
		List<String> list5 = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str5);
		System.out.println(list5);// [1, 2, 3, 4, 5, 6]

		// 将String转换为map
		String str6 = "ruijie=100,huawei=90";
		Map<String, String> map6 = new HashMap<String, String>();
		map6 = Splitter.on(",").withKeyValueSeparator("=").split(str6);
		System.out.println(map6);// {ruijie=100, huawei=90}

		HashSet setA = new HashSet();
		setA.add(1);
		setA.add(2);
		setA.add(3);
		setA.add(4);
		setA.add(5);

		HashSet setB = new HashSet();
		setB.add(5);
		setB.add(6);
		setB.add(7);
		setB.add(8);

		SetView<Integer> setView = Sets.union(setA, setB);// 并集
		System.out.println("union: " + setView);// union: [1, 2, 3, 4, 5, 6, 7,
												// 8]
		SetView difference = Sets.difference(setA, setB);
		System.out.println("difference: " + difference);// difference: [1, 2, 3,
														// 4]
		SetView intersection = Sets.intersection(setA, setB);
		System.out.println("intersection: " + intersection);// 交集 intersection:
															// [5]

		Map<String, String> mapA = new HashMap<String, String>();
		Map<String, String> mapB = new HashMap<String, String>();
		mapA.put("ap", "RUIJIE");
		mapA.put("ac", "RUIJIE");
		mapB.put("ap", "H3C");
		mapB.put("ac", "H3C");
		MapDifference differenceMap = Maps.difference(mapA, mapB);
		differenceMap.areEqual();
		Map entriesDiffering = differenceMap.entriesDiffering();
		System.out.println(entriesDiffering.get("ap"));
		Map entriesOnlyOnLeft = differenceMap.entriesOnlyOnLeft();
		System.out.println(entriesOnlyOnLeft.get("ap"));
		Map entriesOnlyOnRight = differenceMap.entriesOnlyOnRight();
		System.out.println(entriesOnlyOnRight.get("ap"));
		Map entriesInCommon = differenceMap.entriesInCommon();
		System.out.println(entriesInCommon.get("ap"));

		List<String> big = new ArrayList<String>();
		big.add("1");
		big.add("2");
		big.add("3");
		big.add("4");
		List<String> small = new ArrayList<String>();
		small.add("3");
		small.add("4");
		small.add("5");
		small.add("6");
		SetView differenceSet = Sets.difference(Sets.newHashSet(big), Sets.newHashSet(small));
		System.out.println("differenceSet : " + Lists.newArrayList(differenceSet));
		// 以big为准 small多了哪些
		Set<String> differenceSet2 = Sets.difference(Sets.newHashSet(small), Sets.newHashSet(big));
		System.out.println(Lists.newArrayList(differenceSet2));
		List<People> list11 = Lists.newArrayList(new People("AA", 10), new People("BB", 20));
		List<People> list22 = Lists.newArrayList(new People("BB", 20), new People("CC", 20));
		Set<People> differenceSet12 = Sets.difference(Sets.newHashSet(list22), Sets.newHashSet(list11));
		System.out.println(Lists.newArrayList(differenceSet12));

		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("172.17.160.182_8081_", "AA");
//		map1.put("172.17.160.183_8082_", "BB");
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("172.17.160.182_8081_", "AA");
		map2.put("172.17.160.182_8081_0", "CC");
		map2.put("172.17.160.182_8081_1", "DD");
		MapDifference<String, String> diffMap = Maps.difference(map1, map2);
		// differenceMap.areEqual();
		// Map entriesDiffering2 = diffMap.entriesDiffering();
		// Map entriesDiffering2 = diffMap.entriesInCommon();
//		 Map entriesDiffering2 = diffMap.entriesOnlyOnLeft();
		Map entriesDiffering2 = diffMap.entriesOnlyOnRight();
		System.out.println("diffMap : " + diffMap);
		System.out.println("entriesDiffering2 : " + entriesDiffering2);

		System.out.println(list11.retainAll(list22));
		System.out.println(list11);
		List<String> exist = Lists.newArrayList("192.168.1.30_8000_null", "192.168.1.10_8899_null",
				"192.168.1.18_8000_null");
		List<String> newList = Lists.newArrayList("192.168.1.30_8000_null", "192.168.1.10_8899_null",
				"192.168.1.18_8000_null");
		Set<String> differenceSet123 = Sets.difference(Sets.newHashSet(newList), Sets.newHashSet(exist));
		System.out.println(Lists.newArrayList(differenceSet123));

		Map<String, Integer> salary = Maps.newHashMap();
		salary.put("John", 1000);
		salary.put("Jane", 1500);
		String result4 = Joiner.on(" , ").withKeyValueSeparator(" = ").join(salary);
		System.out.println(result4);

	}

}
