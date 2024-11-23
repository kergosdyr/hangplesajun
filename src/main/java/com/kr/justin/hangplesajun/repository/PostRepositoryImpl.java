package com.kr.justin.hangplesajun.repository;

import com.kr.justin.hangplesajun.controller.PostSearchQuery;
import com.kr.justin.hangplesajun.domain.Post;
import com.kr.justin.hangplesajun.domain.QPost;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
class PostRepositoryImpl implements QueryPostRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> getAllBy(PostSearchQuery query) {

        QPost post = QPost.post;
        BooleanBuilder whereClause = new BooleanBuilder();

        if (StringUtils.hasText(query.title())) {
            whereClause.and(post.title.containsIgnoreCase(query.title()));
        }

        if (StringUtils.hasText(query.content())) {
            whereClause.and(post.content.containsIgnoreCase(query.content()));
        }

        if (query.createAt() != null) {
            whereClause.and(post.createdAt.after(query.createAt()));
        }

        var orderSpecifier = getSortOrder(query).orElse(null);

        return queryFactory
                .selectFrom(post)
                .where(whereClause)
                .orderBy(orderSpecifier)
                .fetch();
    }

    private Optional<OrderSpecifier<?>> getSortOrder(PostSearchQuery query) {
        QPost post = QPost.post;

        if (query.searchOrder() == null) {
            return Optional.empty();
        }

        return switch (query.searchOrder()) {
            case ASC -> Optional.of(post.createdAt.asc());
            default -> Optional.of(post.createdAt.desc());
        };
    }
}
