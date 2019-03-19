package cc.ryanc.halo.service;

import cc.ryanc.halo.model.dto.post.PostSimpleOutputDTO;
import cc.ryanc.halo.model.entity.Category;
import cc.ryanc.halo.model.entity.Post;
import cc.ryanc.halo.model.entity.Tag;
import cc.ryanc.halo.model.enums.PostStatus;
import cc.ryanc.halo.model.enums.PostType;
import cc.ryanc.halo.service.base.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Post service.
 *
 * @author johnniang
 * @author RYAN0UP
 */
public interface PostService extends CrudService<Post, Integer> {

    /**
     * Save post with tags and categories
     *
     * @param post       post
     * @param tags       tags
     * @param categories categories
     * @return saved post
     */
    Post save(Post post, List<Tag> tags, List<Category> categories);

    /**
     * Remove post and relationship
     * @param id id
     */
    void remove(Integer id);

    /**
     * Lists latest posts.
     *
     * @param top top number must not be less than 0
     * @return latest posts
     */
    @NonNull
    Page<PostSimpleOutputDTO> pageLatest(int top);

    /**
     * List by status and type
     *
     * @param status   status
     * @param type     type
     * @param pageable pageable
     * @return Page<PostSimpleOutputDTO>
     */
    @NonNull
    Page<PostSimpleOutputDTO> pageByStatus(PostStatus status, PostType type, Pageable pageable);

    /**
     * Count posts by status and type
     *
     * @param status status
     * @param type   type
     * @return posts count
     */
    Long countByStatus(PostStatus status, PostType type);
}