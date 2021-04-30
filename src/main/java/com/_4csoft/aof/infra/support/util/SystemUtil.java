/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.util;

import java.io.File;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.util
 * @File : SystemUtil.java
 * @Title : System Util
 * @date : 2013. 5. 3.
 * @author : 김종규
 * @descrption : 시스템 관련 Util
 */
public class SystemUtil {

	private static OperatingSystemMXBean osMXBean = sun.management.ManagementFactory.getOperatingSystemMXBean();
	private static RuntimeMXBean runtimeMXBean = sun.management.ManagementFactory.getRuntimeMXBean();
	private static ThreadMXBean treadMXBean = sun.management.ManagementFactory.getThreadMXBean();
	private static long lastUpTime = 0L;
	private static long lastProcessCpuTime = 0L;

	/**
	 * OS 이름
	 * 
	 * @return
	 */
	public static String osName() {
		// System.getProperty("os.name");
		return osMXBean.getName();
	}

	/**
	 * OS 버전
	 * 
	 * @return
	 */
	public static String osVersion() {
		// System.getProperty("os.version");
		return osMXBean.getVersion();
	}

	/**
	 * the number of processors available to the virtual machine
	 * 
	 * @return
	 */
	public static int getAvailableProcessors() {
		// Runtime.availableProcessors()
		return osMXBean.getAvailableProcessors();
	}

	/**
	 * system memory 정보
	 * 
	 * @return
	 */
	public static HashMap<String, Object> getMemoryInfo() {
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put("committedVirtualMemorySize", invokeOsMXBean("getCommittedVirtualMemorySize"));
		info.put("freePhysicalMemorySize", invokeOsMXBean("getFreePhysicalMemorySize"));
		info.put("totalPhysicalMemorySize", invokeOsMXBean("getTotalPhysicalMemorySize"));
		info.put("freeSwapSpaceSize", invokeOsMXBean("getFreeSwapSpaceSize"));
		info.put("totalSwapSpaceSize", invokeOsMXBean("getTotalSwapSpaceSize"));
		return info;
	}

	/**
	 * JVM이 사용하는 memory 정보
	 * 
	 * @return
	 */
	public static HashMap<String, Object> getMemoryInfoJVM() {
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put("maxMemory", Runtime.getRuntime().maxMemory()); // 최대 메모리
		info.put("totalMemory", Runtime.getRuntime().totalMemory()); // 할당된 메모리.
		info.put("freeMemory", Runtime.getRuntime().freeMemory()); // 가용 메모리
		return info;
	}

	/**
	 * 디스크 정보
	 * 
	 * @return
	 */
	public static ArrayList<HashMap<String, Object>> getDiskInfo() {
		ArrayList<HashMap<String, Object>> disks = new ArrayList<HashMap<String, Object>>();
		File[] roots = File.listRoots();
		if (roots != null) {
			for (File root : roots) {
				HashMap<String, Object> info = new HashMap<String, Object>();
				info.put("root", root.getAbsolutePath());
				// info.put("totalSpace", root.getTotalSpace());
				// info.put("freeSpace", root.getFreeSpace());
				disks.add(info);
			}
		}
		return disks;
	}

	/**
	 * thread 수
	 * 
	 * @return
	 */
	public static int countThread() {
		return treadMXBean.getThreadCount();
	}

	/**
	 * cpu 사용율
	 * 
	 * @return
	 */
	public static float usageCpu() {
		int cpus = getAvailableProcessors();
		long upTime = runtimeMXBean.getUptime();
		long processCpuTime = ((Long)invokeOsMXBean("getProcessCpuTime")).longValue();
		long terms = upTime - lastUpTime;

		float usage = 0f;
		if (terms > 0L) {
			long cpuTime = (processCpuTime - lastProcessCpuTime);
			usage = cpuTime / (terms * 10000F * cpus);
		}

		lastUpTime = upTime;
		lastProcessCpuTime = processCpuTime;

		return Math.round(Math.min(100f, usage));
	}

	/**
	 * OperatingSystemMXBean 의 get 함수를 실행
	 * 
	 * @param getMethodName
	 * @return
	 */
	public static Object invokeOsMXBean(String getMethodName) {
		if (getMethodName.startsWith("get") == false) {
			return null;
		}
		try {
			Method method = osMXBean.getClass().getDeclaredMethod(getMethodName);
			method.setAccessible(true);
			return method.invoke(osMXBean);
		} catch (Exception e) {
			return null;
		}
	}

//	public static void main(String[] args) throws Exception {
//	}

}
