package com.ybl.sign.http.admin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭流工具类
 *  
 * @author  zhangpeijun
 * @version  [v1.0.1, 2016年12月9日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class IOUtil {
    
    private final static Logger LOG = LoggerFactory.getLogger(IOUtil.class);
    
    /**
     * 关闭一个或多个流对象
     * @param closeables
     * @throws IOException [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static void close(Closeable... closeables) {
        if (null != closeables) {
            for (Closeable closeable : closeables) {
                if (null != closeable) {
                    try {
                        // 可用apache工具类关闭：IOUtils.closeQuietly(closeable);
                        closeable.close();
                    } catch (IOException e) {
                        LOG.error("IOUtil close is exception :", e);
                    }
                }
                
            }
        }
    }
    
}
