package oo.core.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DomainRepository<ENTITY extends DomainEntity<PK>, PK extends DomainIdentity> extends JpaRepository<ENTITY, PK>, JpaSpecificationExecutor<ENTITY> {
//	public interface BaseRepository<ENTITY extends BaseDomainEntity<PK>, PK extends BaseDomainPK> extends JpaRepository<ENTITY, PK>, JpaSpecificationExecutor<ENTITY>, QueryDslPredicateExecutor<ENTITY> {
//    /**
//     * Special customization of {@link org.springframework.data.jpa.repository.CrudRepository#findOne(java.io.Serializable)} to return a JDK 8 {@link java.util.Optional}.
//     */
//    default Optional<ENTITY> readOne(PK id) {
//        return Optional.<ENTITY> ofNullable(findOne(id));
//    }
//    
//    /**
//     * Special customization of {@link org.springframework.data.jpa.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Sort)} to return a JDK 8 {@link java.util.stream.Stream}.
//     */
//    default Stream<ENTITY> readAll(Sort sort) {
//        return findAll(sort).stream();
//    }
//    
//    /**
//     * Special customization of {@link org.springframework.data.jpa.repository.CrudRepository#findAll(java.lang.Iterable)} to return a JDK 8 {@link java.util.stream.Stream}.
//     */
//    default Stream<ENTITY> readAll(Iterable<PK> ids) {
//        return findAll(ids).stream();
//    }
//    
//    /**
//     * Special customization of {@link org.springframework.data.jpa.repository.CrudRepository#save(java.lang.Iterable)} to return a JDK 8 {@link java.util.stream.Stream}.
//     */
//    default <S extends ENTITY> Stream<S> stored(Iterable<S> entities) {
//        return save(entities).stream();
//    }
//    
//    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    
//    /**
//     * Special customization of {@link org.springframework.data.jpa.repository.JpaSpecificationExecutor#findOne(org.springframework.data.jpa.domain.Specification)} to return a JDK 8 {@link java.util.Optional}.
//     */
//    default Optional<ENTITY> readOne(Specification<ENTITY> spec) {
//        return Optional.<ENTITY> ofNullable(findOne(spec));
//    }
//    
//    /**
//     * Special customization of {@link org.springframework.data.jpa.repository.JpaSpecificationExecutor#findAll(org.springframework.data.jpa.domain.Specification)} to return a JDK 8 {@link java.util.stream.Stream}.
//     */
//    default Stream<ENTITY> readAll(Specification<ENTITY> spec) {
//        return findAll(spec).stream();
//    }
//    
//    /**
//     * Special customization of {@link org.springframework.data.jpa.repository.JpaSpecificationExecutor#findAll(org.springframework.data.jpa.domain.Specification, org.springframework.data.domain.Sort)} to return a JDK 8 {@link java.util.stream.Stream}.
//     */
//    default Stream<ENTITY> readAll(Specification<ENTITY> spec, Sort sort) {
//        return findAll(spec, sort).stream();
//    }
//    
//    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    
//    /**
//     * Special customization of {@link org.springframework.data.querydsl.QueryDslPredicateExecutor#findOne( com.mysema.query.types.Predicate)} to return a JDK 8 {@link java.util.Optional}.
//     */
//    default Optional<ENTITY> readOne(Predicate predicate) {
//        return Optional.<ENTITY> ofNullable(findOne(predicate));
//    }
//    
//    /**
//     * Special customization of {@link org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll( com.mysema.query.types.Predicate)} to return a JDK 8 {@link java.util.stream.Stream}.
//     */
//    default Stream<ENTITY> readAll(Predicate predicate) {
//        return StreamSupport.stream(findAll(predicate).spliterator(), false);
//    }
//    
//    /**
//     * Special customization of {@link org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll( com.mysema.query.types.Predicate, org.springframework.data.domain.Sort)} to return a JDK 8 {@link java.util.stream.Stream}.
//     */
//    default Stream<ENTITY> readAll(Predicate predicate, Sort sort) {
//        return StreamSupport.stream(findAll(predicate, sort).spliterator(), false);
//    }
//    
//    /**
//     * Special customization of {@link org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll( com.mysema.query.types.Predicate, com.mysema.query.types.OrderSpecifier)} to return a JDK 8 {@link java.util.stream.Stream}.
//     */
//    default Stream<ENTITY> readAll(Predicate predicate, OrderSpecifier<?>... orders) {
//        return StreamSupport.stream(findAll(predicate, orders).spliterator(), false);
//    }
//    
//    /**
//     * Special customization of {@link org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll( com.mysema.query.types.OrderSpecifier)} to return a JDK 8 {@link java.util.stream.Stream}.
//     */
//    default Stream<ENTITY> readAll(OrderSpecifier<?>... orders) {
//        return StreamSupport.stream(findAll(orders).spliterator(), false);
//    }
    
    //TODO [고도화] JPA 비동기 실행에 관한 CompletableFuture 공부해서 적용해보자!
//    @Async
//    default CompletableFuture<List<T>> readAllBy() {
//        return findAll();
//    }
}
