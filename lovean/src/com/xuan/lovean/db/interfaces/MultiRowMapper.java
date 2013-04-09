/* 
 * @(#)MultiRowMapper.java    Created on 2004-12-3
 * Copyright (c) 2004 ZDSoft Networks, Inc. All rights reserved.
 * $Id: MultiRowMapper.java 8036 2010-08-02 04:33:40Z huangwj $
 */
package com.xuan.lovean.db.interfaces;

import java.sql.SQLException;

import android.database.Cursor;

/**
 * 用来处理多行记录集的情况的接口
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2013-4-9 下午6:47:29 $
 */
public interface MultiRowMapper<T> {
    /**
     * 把结果集的一行记录映射成一个实体对象，方法里不需要执行 <code>rs.next()</code>。
     * 
     * @param rs
     *            结果集
     * @param rowNum
     *            第几条记录，从1开始
     * @return 实体对象
     * @throws SQLException
     *             在数据库发生错误时抛出此异常
     */
    T mapRow(Cursor rs, int rowNum) throws SQLException;

}
