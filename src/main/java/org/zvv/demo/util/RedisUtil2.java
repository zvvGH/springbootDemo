//package org.zvv.demo.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//public class RedisUtil2 {
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//        // =============================common============================
//        /**
//         * 指定缓存失效时间
//         * @param key 键
//         * @param time 时间(秒)
//         * @return
//         */
//    public boolean expire(String key, long time) {
//        try {
//            if (time > 0) {
//                redisTemplate.expire(key, time, TimeUnit.SECONDS);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//        /**
//         * 根据key 获取过期时间
//         * @param key 键 不能为null
//         * @return 时间(秒) 返回0代表为永久有效
//         */
//    public long getExpire(String key) {
//        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
//    }
//        /**
//         * 判断key是否存在
//         * @param key 键
//         * @return true 存在 false不存在
//         */
//    public boolean hasKey(String key) {
//        try {
//            return redisTemplate.hasKey(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//        /**
//         * 删除缓存
//         * @param key 可以传一个值 或多个
//         */
//    @SuppressWarnings("unchecked")
//    public void del(String... key) {
//        if (key != null && key.length > 0) {
//            if (key.length == 1) {
//                redisTemplate.delete(key[0]);
//            } else {
//                redisTemplate.delete(CollectionUtils.arrayToList(key));
//            }
//        }
//    }
//        // ============================String=============================
//        /**
//         * 普通缓存获取
//         * @param key 键
//         * @return 值
//         */
//    public Object get(String key) {
//        return key == null ? null : redisTemplate.opsForValue().get(key);
//    }
//        /**
//         * 普通缓存放入
//         * @param key 键
//         * @param value 值
//         * @return true成功 false失败
//         */
//    public boolean set(String key, Object value) {
//        try {
//            redisTemplate.opsForValue().set(key, value);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//        /**
//         * 普通缓存放入并设置时间
//         * @param key 键
//         * @param value 值
//         * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
//         * @return true成功 false 失败
//         */
//    public boolean set(String key, Object value, long time) {
//        try {
//            if (time > 0) {
//                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
//            } else {
//                set(key, value);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//        /**
//         * 递增
//         * @param key 键
//         * @param delta 要增加几(大于0)
//         * @return
//         */
//    public long incr(String key, long delta) {
//        if (delta < 0) {
//            throw new RuntimeException("递增因子必须大于0");
//        }
//        return redisTemplate.opsForValue().increment(key, delta);
//    }
//        /**
//         * 递减
//         * @param key 键
//         * @param delta 要减少几(小于0)
//         * @return
//         */
//    public long decr(String key, long delta) {
//        if (delta < 0) {
//            throw new RuntimeException("递减因子必须大于0");
//        }
//        return redisTemplate.opsForValue().increment(key, -delta);
//    }
////        155
////        // ================================Map=================================
////        156
////        /**
////         157
////         * HashGet
////         158
////         * @param key 键 不能为null
////        159
////         * @param item 项 不能为null
////        160
////         * @return 值
////        161
////         */
////        162
////    public Object hget(String key, String item) {
////        163
////        return redisTemplate.opsForHash().get(key, item);
////        164
////    }
////165
////        166
////        /**
////         167
////         * 获取hashKey对应的所有键值
////         168
////         * @param key 键
////        169
////         * @return 对应的多个键值
////        170
////         */
////        171
////    public Map<Object, Object> hmget(String key) {
////        172
////        return redisTemplate.opsForHash().entries(key);
////        173
////    }
////174
////        175
////        /**
////         176
////         * HashSet
////         177
////         * @param key 键
////        178
////         * @param map 对应多个键值
////        179
////         * @return true 成功 false 失败
////        180
////         */
////        181
////    public boolean hmset(String key, Map<String, Object> map) {
////        182
////        try {
////            183
////            redisTemplate.opsForHash().putAll(key, map);
////            184
////            return true;
////            185
////        } catch (Exception e) {
////            186
////            e.printStackTrace();
////            187
////            return false;
////            188
////        }
////        189
////    }
////190
////        191
////        /**
////         192
////         * HashSet 并设置时间
////         193
////         * @param key 键
////        194
////         * @param map 对应多个键值
////        195
////         * @param time 时间(秒)
////        196
////         * @return true成功 false失败
////        197
////         */
////        198
////    public boolean hmset(String key, Map<String, Object> map, long time) {
////        199
////        try {
////            200
////            redisTemplate.opsForHash().putAll(key, map);
////            201
////            if (time > 0) {
////                202
////                expire(key, time);
////                203
////            }
////            204
////            return true;
////            205
////        } catch (Exception e) {
////            206
////            e.printStackTrace();
////            207
////            return false;
////            208
////        }
////        209
////    }
////210
////        211
////        /**
////         212
////         * 向一张hash表中放入数据,如果不存在将创建
////         213
////         * @param key 键
////        214
////         * @param item 项
////        215
////         * @param value 值
////        216
////         * @return true 成功 false失败
////        217
////         */
////        218
////    public boolean hset(String key, String item, Object value) {
////        219
////        try {
////            220
////            redisTemplate.opsForHash().put(key, item, value);
////            221
////            return true;
////            222
////        } catch (Exception e) {
////            223
////            e.printStackTrace();
////            224
////            return false;
////            225
////        }
////        226
////    }
////227
////        228
////        /**
////         229
////         * 向一张hash表中放入数据,如果不存在将创建
////         230
////         * @param key 键
////        231
////         * @param item 项
////        232
////         * @param value 值
////        233
////         * @param time 时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
////        234
////         * @return true 成功 false失败
////        235
////         */
////        236
////    public boolean hset(String key, String item, Object value, long time) {
////        237
////        try {
////            238
////            redisTemplate.opsForHash().put(key, item, value);
////            239
////            if (time > 0) {
////                240
////                expire(key, time);
////                241
////            }
////            242
////            return true;
////            243
////        } catch (Exception e) {
////            244
////            e.printStackTrace();
////            245
////            return false;
////            246
////        }
////        247
////    }
////248
////        249
////        /**
////         250
////         * 删除hash表中的值
////         251
////         * @param key 键 不能为null
////        252
////         * @param item 项 可以使多个 不能为null
////        253
////         */
////        254
////    public void hdel(String key, Object... item) {
////        255
////        redisTemplate.opsForHash().delete(key, item);
////        256
////    }
////257
////        258
////        /**
////         259
////         * 判断hash表中是否有该项的值
////         260
////         * @param key 键 不能为null
////        261
////         * @param item 项 不能为null
////        262
////         * @return true 存在 false不存在
////        263
////         */
////        264
////    public boolean hHasKey(String key, String item) {
////        265
////        return redisTemplate.opsForHash().hasKey(key, item);
////        266
////    }
////267
////        268
////        /**
////         269
////         * hash递增 如果不存在,就会创建一个 并把新增后的值返回
////         270
////         * @param key 键
////        271
////         * @param item 项
////        272
////         * @param by 要增加几(大于0)
////        273
////         * @return
////        274
////         */
////        275
////    public double hincr(String key, String item, double by) {
////        276
////        return redisTemplate.opsForHash().increment(key, item, by);
////        277
////    }
////278
////        279
////        /**
////         280
////         * hash递减
////         281
////         * @param key 键
////        282
////         * @param item 项
////        283
////         * @param by 要减少记(小于0)
////        284
////         * @return
////        285
////         */
////        286
////    public double hdecr(String key, String item, double by) {
////        287
////        return redisTemplate.opsForHash().increment(key, item, -by);
////        288
////    }
////289
////        290
////        // ============================set=============================
////        291
////        /**
////         292
////         * 根据key获取Set中的所有值
////         293
////         * @param key 键
////        294
////         * @return
////        295
////         */
////        296
////    public Set<Object> sGet(String key) {
////        297
////        try {
////            298
////            return redisTemplate.opsForSet().members(key);
////            299
////        } catch (Exception e) {
////            300
////            e.printStackTrace();
////            301
////            return null;
////            302
////        }
////        303
////    }
////304
////        305
////        /**
////         306
////         * 根据value从一个set中查询,是否存在
////         307
////         * @param key 键
////        308
////         * @param value 值
////        309
////         * @return true 存在 false不存在
////        310
////         */
////        311
////    public boolean sHasKey(String key, Object value) {
////        312
////        try {
////            313
////            return redisTemplate.opsForSet().isMember(key, value);
////            314
////        } catch (Exception e) {
////            315
////            e.printStackTrace();
////            316
////            return false;
////            317
////        }
////        318
////    }
//        /**
//         * 将数据放入set缓存
//         * @param key 键
//         * @param values 值 可以是多个
//         * @return 成功个数
//         */
//    public long sSet(String key, Object... values) {
//        try {
//            return redisTemplate.opsForSet().add(key, values);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//        /**
//         * 将set数据放入缓存
//         * @param key 键
//         * @param time 时间(秒)
//         * @param values 值 可以是多个
//         * @return 成功个数
//         */
//    public long sSetAndTime(String key, long time, Object... values) {
//        try {
//            Long count = redisTemplate.opsForSet().add(key, values);
//            if (time > 0)
//            expire(key, time);
//            return count;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//        /**
//         * 获取set缓存的长度
//         * @param key 键
//         * @return
//         */
//    public long sGetSetSize(String key) {
//        try {
//            return redisTemplate.opsForSet().size(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//        /**
//         * 移除值为value的
//         * @param key 键
//         * @param values 值 可以是多个
//         * @return 移除的个数
//         */
//    public long setRemove(String key, Object... values) {
//        try {
//            Long count = redisTemplate.opsForSet().remove(key, values);
//            return count;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//        // ===============================list=================================
//        /**
//         * 获取list缓存的内容
//         * @param key 键
//         * @param start 开始
//         * @param end 结束 0 到 -1代表所有值
//         * @return
//         */
//    public List<Object> lGet(String key, long start, long end) {
//        try {
//            return redisTemplate.opsForList().range(key, start, end);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//        /**
//         * 获取list缓存的长度
//         * @param key 键
//         * @return
//         */
//    public long lGetListSize(String key) {
//        try {
//            return redisTemplate.opsForList().size(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//        /**
//         * 通过索引 获取list中的值
//         * @param key 键
//         * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
//         * @return
//         */
//    public Object lGetIndex(String key, long index) {
//        try {
//            return redisTemplate.opsForList().index(key, index);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//        /**
//         431
//         * 将list放入缓存
//         432
//         * @param key 键
//        433
//         * @param value 值
//         * @param time 时间(秒)
//         * @return
//         */
//    public boolean lSet(String key, Object value) {
//        try {
//            redisTemplate.opsForList().rightPush(key, value);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//        /**
//         * 将list放入缓存
//         * @param key 键
//         * @param value 值
//         * @param time 时间(秒)
//         * @return
//         */
//    public boolean lSet(String key, Object value, long time) {
//        try {
//            redisTemplate.opsForList().rightPush(key, value);
//            if (time > 0)
//            expire(key, time);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//        /**
//         467
//         * 将list放入缓存
//         * @param key 键
//         * @param value 值
//         * @param time 时间(秒)
//         * @return
//         */
//    public boolean lSet(String key, List<Object> value) {
//        try {
//            redisTemplate.opsForList().rightPushAll(key, value);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//        /**
//         * 将list放入缓存
//         *
//         * @param key 键
//         * @param value 值
//         * @param time 时间(秒)
//         * @return
//         */
//    public boolean lSet(String key, List<Object> value, long time) {
//        try {
//            redisTemplate.opsForList().rightPushAll(key, value);
//            if (time > 0)
//            expire(key, time);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//        /**
//         * 根据索引修改list中的某条数据
//         * @param key 键
//         * @param index 索引
//         * @param value 值
//         * @return
//         */
//    public boolean lUpdateIndex(String key, long index, Object value) {
//        try {
//            redisTemplate.opsForList().set(key, index, value);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//        /**
//         * 移除N个值为value
//         * @param key 键
//         * @param count 移除多少个
//         * @param value 值
//         * @return 移除的个数
//         */
//    public long lRemove(String key, long count, Object value) {
//        try {
//            Long remove = redisTemplate.opsForList().remove(key, count, value);
//            return remove;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//}