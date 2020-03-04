package com.aeye.recog.calc.n1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 1:n快速识别
 * 
 * @author cyp
 */
public class ScoreCalcuater {

	private static Logger logger = LoggerFactory.getLogger(ScoreCalcuater.class);

	public static ScoreCalcuater instance = new ScoreCalcuater();

	static {
			try {
				logger.info("init libAEyeFaceFunc libr ..");
				logger.info("java.library.path is "
						+ System.getProperty("java.library.path"));

				System.loadLibrary("AEyeFaceFunc");
				logger.info("bit of JVM is "
						+ System.getProperty("sun.arch.data.model"));

				// 初始化算法库
				instance.init();

				logger.info("jni AEyeFaceFunc success!");

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
	}

	public native void init();
//	public void init()
//	{
//		
//	}

	public native void destory();
//	public void destory()
//	{
//		
//	}
	public native boolean simhash(byte[] feature, int[] hashs,int[] middle);
//	public boolean simhash(byte[] feature,int[] hashs,int[] middle)
//	{
//		return false;
//	}

	public native int simdist(byte[] feature1,byte[] feature2,int[] middle1, int[] middle2,float[] dist);
//	public int simdist(byte[] feature1,byte[] feature2,int[] middle1, int[] middle2,float[] dist){
//		return (int)Math.random();
//	}
	
	public native  float compareB(byte[] feature1,byte[] feature2);
//	public  float compareB(byte[] feature1,byte[] feature2){
//		Double d = Math.random();
//		return d.floatValue();
//	}
//	
	public native  float compareF(float[] feature1,float[] feature2);
//	public float compareF(float[] feature1,float[] feature2){
//		return 0.8f;
//	}

}
