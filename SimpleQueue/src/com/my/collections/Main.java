package com.my.collections;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		SafeQueue<Integer> queue = new SafeQueue<Integer>();
		new QueueThread(queue, 0, "one").start();
		new QueueThread(queue, 0, "Two").start();
		new QueueThread(queue, 1, "Three").start();
		new QueueThread(queue, 1, "Four").start();
		new QueueThread(queue, -1, "Five").start();
		new QueueThread(queue, -1, "Six").start();
	}

	static class QueueThread extends Thread {

		private static volatile int curr = 1;
		private SafeQueue<Integer> queue;
		private int p;

		public QueueThread(SafeQueue<Integer> queue, int p, String threadName) {
			this.queue = queue;
			this.p = p;
			setName(threadName);
		}

		@Override
		public void run() {

			if (p == -1) {
				Iterator<Integer> iterator = queue.getIterator();
				while (iterator.hasNext()) {
					System.out.println(iterator.next());
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return;
			}
			for (int i = 0; i < 5; i++) {
				if (p == 0) {
					queue.push(curr++);
					continue;
				}
				queue.pop();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
			}
		}

	}
}
