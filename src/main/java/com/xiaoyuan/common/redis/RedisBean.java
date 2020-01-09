package com.xiaoyuan.common.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiaoyuan.common.log.LogUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;


/**
 * 
 * 封装redis 缓存服务器服务接口 <功能详细描述>
 * 
 * @author 卿剑
 * @version [版本号, 2015年1月8日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RedisBean {

	private static final LogUtil logger = LogUtil.getLogUtil(RedisBean.class);

	@Autowired
	private JedisPool jedisPool;

	/**
	 * 获取一个jedis 客户端
	 * 
	 * @return
	 */
	protected Jedis getJedis() throws JedisException {
		Jedis jedis = jedisPool.getResource();
		return jedis; // 模板方法
	}

	/**
	 * Handle jedisException, write log and return whether the connection is
	 * broken.
	 */
	protected boolean handleJedisException(JedisException jedisException) {
		if (jedisException instanceof JedisConnectionException) {
			logger.error("Redis connection lost.", jedisException);
		} else if (jedisException instanceof JedisDataException) {
			if ((jedisException.getMessage() != null)
					&& (jedisException.getMessage().indexOf("READONLY") != -1)) {
				logger.error("Redis connection are read-only slave.",
						jedisException);
			} else {
				// dataException, isBroken=false
				return false;
			}
		} else {
			logger.error("Jedis exception happen.", jedisException);
		}
		return true;
	}

	/**
	 * Return jedis connection to the pool, call different return methods
	 * depends on the conectionBroken status.
	 */
	protected void closeResource(Jedis jedis, boolean conectionBroken) {
		try {
			if (conectionBroken) {
				jedisPool.returnBrokenResource(jedis);
			} else {
				jedisPool.returnResource(jedis);
			}
		} catch (Exception e) {
			logger.error(
					"return back jedis failed, will fore close the jedis.", e);

			if ((jedis != null) && jedis.isConnected()) {
				try {
					try {
						jedis.quit();
					} catch (Exception e2) {
					}
					jedis.disconnect();
				} catch (Exception e2) {

				}
			}
		}
	}

	/**
	 * 增加 <功能详细描述>
	 * 
	 * @param key
	 * @param expiresTime
	 * @param value
	 * @return
	 * @throws Exception
	 *             [参数说明]
	 * 
	 * @return boolean [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public boolean add(final String key, final int expiresTime,
			final String value) throws Exception {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			if (jedis.exists(key)) {
				return Boolean.FALSE;
			}
			jedis.set(key, value);
			if (expiresTime > 0) {
				jedis.expire(key, expiresTime);
			}
			return Boolean.TRUE;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}

	}

	public boolean delete(final String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.del(key) > 0;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}

	}

	public Long deleteAndReturnCounts(final String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.del(key);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}

	}

	public String get(final String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.get(key);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	public boolean set(final String key, final int expiresTime,
			final String value) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.set(key, value);
			if (expiresTime > 0) {
				jedis.expire(key, expiresTime);
			}
			return Boolean.TRUE;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	public boolean hsetTime(final String key, final String field,
			int expiresTime, final String value) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.hset(key, field, value);
			if (expiresTime > 0) {
				jedis.expire(key, expiresTime);
			}
			return Boolean.TRUE;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	public long incr(final String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.incr(key);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}

	}

	public long incrBy(final String key, final long step) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.incrBy(key, step);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	public long decr(final String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.decr(key);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	public long decrBy(final String key, final long decrement) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.decrBy(key, decrement);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	// redis key 操作
	public boolean exists(final String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.exists(key);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	// redis Hash 操作
	public long hlen(final String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.hlen(key);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public long hsetnx(final String key, final String field, final String value) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			long result = jedis.hsetnx(key, field, value);

			return result;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public long hset(final String key, final String field, final String value) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			long result = jedis.hset(key, field, value);

			return result;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public String hset(final String key, final Map<String, String> hash) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			String result = jedis.hmset(key, hash);
			return result;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public String hget(final String key, final String field) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			String result = jedis.hget(key, field);

			return result;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public Map<String, String> hgetAll(final String key, final String field) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			Map<String, String> result = jedis.hgetAll(key);

			return result;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public boolean hexists(final String key, final String field) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			boolean result = jedis.hexists(key, field);

			return result;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public Map<String, String> hgetAll(final String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			Map<String, String> result = jedis.hgetAll(key);
			return result;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public Long hdel(final String key, final String[] fields) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			Long result = jedis.hdel(key, fields);
			return result;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	// Redis List 操作
	public long lpush(final String key, final String... values) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			long result = jedis.lpush(key, values);

			return result;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public String rpop(final String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			String result = jedis.rpop(key);
			return result;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		

	}

	// Redis Set操作
	public long sadd(final String key, final String... members) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.sadd(key, members);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public long sremove(final String key, final String... members) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.srem(key, members);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public long scard(final String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.scard(key);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public boolean sismember(final String key, final String member) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.sismember(key, member);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public Set<String> smembers(final String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.smembers(key);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public String spop(final String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.spop(key);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public String srandmember(final String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.srandmember(key);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	// Redis pub/sub
	public void publish(final String channel, final String message) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.publish(channel, message);// 发送消息
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public boolean setnx(final String key, final String value,
			final int expiresTime) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			long result = jedis.setnx(key, value);
			if (result == 1 && expiresTime > 0) {
				jedis.expire(key, expiresTime);
			}
			return result == 1;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public String setex(final String key, final String value,
			final int expiresTime) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			String result = jedis.setex(key, expiresTime, value);
			return result;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		

	}

	public Map<String, String> hgetAll(final String key,
			final boolean afterDelete) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();

			Map<String, String> map = jedis.hgetAll(key);

			if (afterDelete) {
				jedis.del(key);
			}

			return map;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	public void psubscribe(final JedisPubSub listener, final String[] patterns) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			// 使用模式匹配的方式设置频道
			jedis.psubscribe(listener, patterns);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		
	}

	/**
	 * 清空redis 所有数据
	 * 
	 * @return
	 */
	public String flushDB() {
		return getJedis().flushDB();
	}

	/**
	 * 查看redis里有多少数据
	 */
	public long dbSize() {
		return getJedis().dbSize();
	}

	/**
	 * 检查是否连接成功
	 * 
	 * @return
	 */
	public String ping() {
		return getJedis().ping();
	}

	public Long hdel(final String key, String field) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			Long result = jedis.hdel(key, field);
			return result;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		
		

		
	}

	/**
	 * 检查是有key值的缓存数据
	 * 
	 * @param key
	 * @return
	 */
	public boolean isExist(String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			boolean result = jedis.exists(key);
			return result;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	/**
	 * 返回key对应hash 的所有value。
	 * 
	 * @param key
	 * @return
	 */
	public List<String> hvals(String key) {
		boolean broken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			List<String> result = jedis.hvals(key);
			return result;
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	/**
	 * 关闭redis连接
	 */
	public void closeRedis(Jedis jedis) {

		jedisPool.returnResource(jedis);
	}
}