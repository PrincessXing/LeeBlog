package com.lychee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lychee.constans.SystemConstants;
import com.lychee.domain.ResponseResult;
import com.lychee.domain.entity.Comment;
import com.lychee.domain.vo.CommentVo;
import com.lychee.domain.vo.PageVo;
import com.lychee.enums.AppHttpCodeEnum;
import com.lychee.exception.SystemException;
import com.lychee.mapper.CommentMapper;
import com.lychee.service.CommentService;
import com.lychee.service.UserService;
import com.lychee.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private UserService userService;
    // 评论列表查询
    @Override
    public ResponseResult<?> commentList(String commentType,Long articleId, Integer pageNum, Integer pageSize) {
        // 查询文章的根评论(值为-1的评论)
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getType, commentType);
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getRootId, SystemConstants.ROOT_ID_DEFAULT_VALUE);
        // 分页查询
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<CommentVo> commentVos = toCommentVoList(page.getRecords());
        // 查询根评论下的子评论 并将子评论赋值给对应的根评论
        for (CommentVo commentVo : commentVos) {
            List<CommentVo> children = getChildren(commentVo.getId());
            commentVo.setChildren(children);
        }
        return ResponseResult.okResult(new PageVo(commentVos,page.getTotal()));
    }
    // 将Comment转换为CommentVo方法 用于将Comment中的createBy和toCommentUserId转换为对应的昵称 并将CommentVo的toCommentUserName赋值
    private List<CommentVo> toCommentVoList(List<Comment> comments) {
        List<CommentVo> commentVos = BeanCopyUtils.copyMultiple(comments, CommentVo.class);
        for (CommentVo commentVo : commentVos) {
            // 通过createBy查询用户的昵称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUserName(nickName);
            // 通过toCommentUserId查询用户的昵称并赋值 toCommentUserId的值为-1时才进行查询
            if (commentVo.getToCommentUserId() != -1) {
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;
    }

    // 添加评论
    @Override
    public ResponseResult<?> addComment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        // 评论的创建者 创建时间由MyMetaObjectHandler自动填充
        save(comment);
        return ResponseResult.okResult();
    }


    /**
     * 根据根评论id查询对应子评论的集合
     * @param id 根评论id
     * @return 子评论集合
     */
    private List<CommentVo> getChildren(Long id){
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);
        return toCommentVoList(comments);
    }

}
