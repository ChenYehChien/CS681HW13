package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	private AccessCounter() {};
	private static AccessCounter instance = null;
	private static ReentrantLock staticLock = new ReentrantLock();
	private Map<java.nio.file.Path, Integer> map = new HashMap<java.nio.file.Path, Integer>();
	private ReentrantLock lock = new ReentrantLock();
	
	
	public static AccessCounter getInstance() {
		staticLock.lock();
		try {
			if (instance == null)
				instance = new AccessCounter();
			return instance;
		}
		finally {
			staticLock.unlock();
		}
	}
	
	//increment
	public void increment(Path path) {
		lock.lock();
		try {
			if (map.get(path) != null)
				map.put(path, map.get(path) + 1);
			else
				map.put(path, 1);
		}
		finally {
			lock.unlock();
		}
	}
	
	//getCount
	public int getCount(Path path) {
		lock.lock();
		try {
			if (map.get(path) != null)
				return map.get(path);
			else
				return 0;
		}
		finally {
			lock.unlock();
		}
	}
	
	
	
	
	
}