package pers.hanchao.hespringmvc.interceptors.filter;

import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by 韩超 on 2018/1/25.
 */
public class RequestBodyWrapper extends HttpServletRequestWrapper {
    //定义字段保存request的body信息
    private byte[] requestBody;

    private final static Logger LOGGER = Logger.getLogger(RequestBodyWrapper.class);

    /**
     * <p>Title: 读取request.getInputStream信息，保存到requestBody数组中</p>
     * @author 韩超@bksx 2018/1/25 10:40
     */
    public RequestBodyWrapper(HttpServletRequest request) {
        super(request);
        try {
            //将request的body信息写入到requestBody中
            requestBody = StreamUtils.getBytes(request.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("[URI=" + request.getRequestURI() + ",method=" + request.getMethod() + "],无法获取body数据！");
        }
    }

    /**
     * <p>Title:  重写getInputStream，返回requestBody数组中的信息</p>
     * @author 韩超@bksx 2018/1/25 10:42
     */
    @Override
    public ServletInputStream getInputStream(){
        if (null == requestBody){
            requestBody = new byte[0];
        }
        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    /**
     * <p>重写getReader方法，调用getInputStream，将byte[]数组转换成BufferedReader</p>
     * @author hanchao 2018/1/26 14:54
     **/
    @Override
    public BufferedReader getReader(){
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
