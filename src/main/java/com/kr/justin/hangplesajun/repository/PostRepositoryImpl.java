package com.kr.justin.hangplesajun.repository;

import static com.kr.justin.hangplesajun.domain.QPost.post;

import com.kr.justin.hangplesajun.controller.PostSearchQuery;
import com.kr.justin.hangplesajun.controller.SearchOrder;
import com.kr.justin.hangplesajun.domain.Post;
import com.kr.justin.hangplesajun.domain.QPost;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
class PostRepositoryImpl implements QueryPostRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> getAllBy(PostSearchQuery query) {

        var orderSpecifier = getSortOrder(query).orElse(null);

        return queryFactory
                .selectFrom(post)
                .where(postSearchEq(query))
                .orderBy(orderSpecifier)
                .fetch();
    }

    private static BooleanBuilder postSearchEq(PostSearchQuery query) {
        return new BooleanBuilder()
                .and(userIdEq(query.userId()))
                .and(titleEq(query.title()))
                .and(contentEq(query.content()))
                .and(createdAtAfter(query.createAt()));
    }

    private static BooleanExpression createdAtAfter(LocalDateTime createdAt) {
        return createdAt != null ? post.createdAt.after(createdAt) : null;
    }

    private static BooleanExpression contentEq(String content) {
        return StringUtils.hasText(content) ? post.content.containsIgnoreCase(content) : null;
    }

    private static BooleanExpression titleEq(String title) {
        return StringUtils.hasText(title) ? post.title.containsIgnoreCase(title) : null;
    }

    private static BooleanExpression userIdEq(long userId) {
        return userId != 0L ? post.userId.eq(userId) : null;
    }

    private Optional<OrderSpecifier<?>> getSortOrder(PostSearchQuery query) {
        QPost post = QPost.post;

        if (query.searchOrder() == null) {
            return Optional.empty();
        }

        if (query.searchOrder() == SearchOrder.ASC) {
            return Optional.of(post.createdAt.asc());
        }
        return Optional.of(post.createdAt.desc());
    }
}
