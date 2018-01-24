package pers.hanchao.hespringmvc.freemarker;

/**
 * <p>新闻</p>
 * @author hanchao 2018/1/21 13:31
 **/
public class News {
    /** 新闻编号 */
    private Integer id;
    /** 新闻标题 */
    private String title;
    /** 新闻内容 */
    private String content;

    public News() {}

    public News(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
