package club.javafan.blog.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @desc 博客vo
 */
@Data
public class BlogListVO implements Serializable {

    private Long blogId;

    private String blogTitle;

    private String blogSubUrl;

    private String blogCoverImage;

    private Integer blogCategoryId;

    private String blogCategoryIcon;

    private String blogCategoryName;

    private Date createTime;

    private String abstractContent;
}
