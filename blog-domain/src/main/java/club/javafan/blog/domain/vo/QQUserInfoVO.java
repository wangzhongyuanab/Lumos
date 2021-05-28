package club.javafan.blog.domain.vo;

import lombok.Data;


@Data
public class QQUserInfoVO {
    /**
     *  QQ昵称
     */
    private String nickName;
    /**
     * QQ头像URL
     */
    private String headImage;
    /**
     * QQ号
     */
    private String qNumber;
    /**
     *  QQ邮箱
     */
    private String qEmail;
}
