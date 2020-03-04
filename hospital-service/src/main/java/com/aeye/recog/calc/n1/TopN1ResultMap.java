/**
 * Project Name:mbr-calc-node
 * File Name:TopN1ResultMap.java
 * Package Name:com.aeye.recog.calc.loader.util
 * Date:2015年12月10日下午5:42:11
 * Copyright (c) 2015, shengpeng@a-eye.cn All Rights Reserved.
 *
*/

package com.aeye.recog.calc.n1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;


/**
 * ClassName:TopN1ResultMap <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年12月10日 下午5:42:11 <br/>
 * @author   shengpeng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TopN1ResultMap<T> extends TreeMap<TopN1ResultMap.N1ResultKey, T>{
	private Map<Long,N1ResultKey> keys = new ConcurrentHashMap<Long, N1ResultKey>();
	
	private int n;
	public TopN1ResultMap(int n) {
		super(new Comparator<N1ResultKey>() {
			@Override
			public int compare(N1ResultKey o1, N1ResultKey o2) {
				//同一个人，只放一个，此处默认替换，需要外层对同一个人的数据进行保存
				if(o1.getPersonId().equals(o2.getPersonId()))
				{
					return 0;
				}
				
				//不是同一个人，若分数相等，放在其后
				float t = o1.getScore()-o2.getScore();
				if(t > 0)
				{
					return -1; //放队尾
				}else if( t <= 0)
				{
					return 1;//放对头
				}
				return 0;
			}
		});
		this.n = n;
		
	}
	
	public List<T> toOrderedList(){
		List<T> results = new ArrayList<T>();
		Map.Entry<N1ResultKey,T> entry = pollFirstEntry();
		while(null != entry)
		{
			if(null != entry.getValue())
			{
				results.add(entry.getValue());
			}
			entry = pollFirstEntry();
		}
		return results;
	}
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么 )by shengpeng.
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public synchronized T put(N1ResultKey key, T value) {
		T old = null;
		//检查是否有本人的key
		N1ResultKey oldKey = keys.get(key.getPersonId());
		if(null == oldKey)
		{
			//没有本人信息，直接放
			old = super.put(key, value);
			keys.put(key.getPersonId(), key);
		}else //已经存在，比较一下是不是分数比之前高 才能替换掉
		{
			if(oldKey.getScore() < key.getScore())
			{
				T ov = super.remove(oldKey);
				keys.remove(oldKey.getPersonId());
				
				old = super.put(key, value);
//				values.remove(key);
				keys.put(key.getPersonId(), key);
			}
		}
		
		//满了 移除掉队尾的
		if(size() > n)
		{
			N1ResultKey keyToRemove = pollLastEntry().getKey();
			keys.remove(keyToRemove.getPersonId());
		}
		return old;
	}


	public static class N1ResultKey{
		private Long personId;
		private Float score;
		private boolean isAbandend;
		public boolean isAbandend() {
			return isAbandend;
		}
		public void setAbandend(boolean isAbandend) {
			this.isAbandend = isAbandend;
		}
		public N1ResultKey(Long personId, Float score) {
			super();
			this.personId = personId;
			this.score = score;
		}
		public Long getPersonId() {
			return personId;
		}
		public void setPersonId(Long personId) {
			this.personId = personId;
		}
		public Float getScore() {
			return score;
		}
		public void setScore(Float score) {
			this.score = score;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((personId == null) ? 0 : personId.hashCode());
			result = prime * result + ((score == null) ? 0 : score.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			N1ResultKey other = (N1ResultKey) obj;
			if (personId == null) {
				if (other.personId != null)
					return false;
			} else if (!personId.equals(other.personId))
				return false;
			if (score == null) {
				if (other.score != null)
					return false;
			} else if (!score.equals(other.score))
				return false;
			return true;
		}
		
		
		@Override
		public String toString() {
			return "N1ResultKey [personId=" + personId + ", score=" + score
					+ "]";
		}
	}
}

