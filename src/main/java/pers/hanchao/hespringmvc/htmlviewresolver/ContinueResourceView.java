package pers.hanchao.hespringmvc.htmlviewresolver;

import org.springframework.web.servlet.view.InternalResourceView;

import java.io.File;
import java.util.Locale;

/**
 * <p>定义一个Html视图，重写了checkResource方法，实现解析器的连续解析</p>
 * @author hanchao 2018/1/18 21:57
 **/
public class ContinueResourceView extends InternalResourceView{
    /**
     * <p>AbstractUrlBasedView中的checkResource永远都返回true，表示视图存在，不会再进入其他的视图解析器。
     * ContinueResourceView重写了checkResource方法，若当前视图无法解析，则返回false，使之能够进入下一个视图。</p>
     * @author hanchao 2018/1/18 21:58
     **/
    @Override
    public boolean checkResource(Locale locale) throws Exception {
        File file = new File(this.getServletContext().getRealPath("/") + getUrl());
        return file.exists();
    }
}
