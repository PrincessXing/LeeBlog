package com.lychee.constans;

public class SystemConstants {

    // 字面值常量 实际项目中不允许出现字面值常量 都要定义成常量来使用 有利于提高代码的可维护性

    /**
     * 文章状态是草稿
     */
    public static final Integer ARTICLE_STATUS_DRAFT = 1;
    /**
     * 文章状态是发布
     */
    public static final Integer ARTICLE_STATUS_PUBLISH = 0;
    /**
     * 分类状态是删除
     */
    public static final String CATEGORY_STATUS_NORMAL = "0";
    /**
     * 友链审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";
    /**
     * 根评论默认值
     */
    public static final String ROOT_ID_DEFAULT_VALUE = "-1";

    /**
     * 评论类型为：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为：友链评论
     */
    public static final String LINK_COMMENT = "1";
    /**
     * 角色状态为：正常
     */
    public static final String STATUS_NORMAL = "0";
    /**
     * 菜单类型为：菜单
     */
    public static final String MENU = "M";
    /**
     * 菜单类型为：按钮
     */
    public static final String BUTTON = "F";

}
