package com.cg.ars.ui;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class LogoAnimation implements Runnable
{
	private int frameId;
	
	private char[][] frameBuffer;
	
	private volatile ScheduledFuture<?> self;
	
	private volatile ScheduledExecutorService service;
	
	private final Semaphore semaphore;
	
	public LogoAnimation()
	{
		frameId = 0;
		
		frameBuffer = new char[][] {
									{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', '*', '*', '*', '*', '*', ' ', '*', '*', '*', '*', '*', ' ', '*', '*', '*', '*', '*', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', '*', '*', '*', '*', '*', ' ', '*', '*', '*', '*', '*', ' ', '*', '*', '*', '*', '*', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', '*', '*', '*', '*', '*', ' ', '*', '*', '*', '*', '*', ' ', '*', '*', '*', '*', '*', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', '*', '*', '*', '*', '*', ' ', '*', '*', '*', '*', '*', ' ', '*', '*', '*', '*', '*', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', '*', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', '*', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', ' ', '*', ' ', '*', '*', '*', '*', '*', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', '*', '*', ' ', '*', '*', ' ', '*', '*', ' ', ' ', '*', ' ', '*', '*', '*', '*', '*', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', '*'},
									{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', '*', '*'},
									{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', '*'},
									{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' ', '*'}
								   };
		
		semaphore = new Semaphore(0);
	}
	
	public void startAnimation()
	{
		service = Executors.newSingleThreadScheduledExecutor();
		
		self = service.scheduleAtFixedRate(this, 0, 500, TimeUnit.MICROSECONDS);
		
		// acquire semaphore
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		frameId++;
		
		// if animation is complete, cancel scheduler
		if (frameId > frameBuffer.length) {
			// hold the screen for 2 second
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
						
			// cancel scheduler, without interrupting
			self.cancel(true);
			
			// shutdown service
			service.shutdown();
			
			// try to clear console
			ARSClient.clearScreen();
			
			// release semaphore
			semaphore.release();
			
			return;
		}
		
		// try to clear console
		ARSClient.clearScreen();
				
		// draw current frame
		drawFrame();
	}
	
	private void drawFrame()
	{
		for (int i = 0; i < frameId; i++) {
			for (int j = 0; j < frameId; j++) {
				System.out.print(frameBuffer[i][j] + "   ");
			}
			System.out.println();
		}
	}
}
